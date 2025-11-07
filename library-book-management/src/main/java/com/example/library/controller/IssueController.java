package com.example.library.controller;

import com.example.library.entity.AppUser;
import com.example.library.entity.Book;
import com.example.library.entity.Issue;
import com.example.library.service.BookService;
import com.example.library.service.IssueService;
import com.example.library.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    private final IssueService issueService;
    private final BookService bookService;
    private final UserService userService;

    public IssueController(IssueService issueService, BookService bookService, UserService userService) {
        this.issueService = issueService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping("/issue/{bookId}/to/{studentUsername}")
    @ResponseStatus(HttpStatus.CREATED)
    public Issue issue(@PathVariable Long bookId, @PathVariable String studentUsername){
        Book book = bookService.get(bookId);
        AppUser student = userService.findByUsername(studentUsername);
        return issueService.issueBook(book, student);
    }

    @PostMapping("/return/{issueId}")
    public Issue returnBook(@PathVariable Long issueId){
        Issue iss = issueService.get(issueId);
        return issueService.returnBook(iss);
    }

    @GetMapping("/my")
    public List<Issue> myIssues(Authentication auth){
        AppUser u = userService.findByUsername(auth.getName());
        return issueService.byStudent(u);
    }

    @GetMapping("/overdue")
    public List<Issue> overdue(){
        return issueService.overdueToday();
    }

    @GetMapping("/{issueId}/fine")
    public Map<String, Long> fine(@PathVariable Long issueId){
        Issue iss = issueService.get(issueId);
        Map<String, Long> map = new HashMap<>();
        map.put("fine", issueService.calculateFine(iss));
        return map;
    }
}
