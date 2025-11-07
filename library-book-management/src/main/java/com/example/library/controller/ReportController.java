package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.entity.Issue;
import com.example.library.service.BookService;
import com.example.library.service.IssueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final BookService bookService;
    private final IssueService issueService;

    public ReportController(BookService bookService, IssueService issueService) {
        this.bookService = bookService;
        this.issueService = issueService;
    }

    @GetMapping("/inventory")
    public Map<String, Object> inventory(){
        List<Book> all = bookService.all();
        long available = all.stream().filter(Book::isAvailable).count();
        long issued = all.size() - available;
        Map<String,Object> m = new HashMap<>();
        m.put("totalBooks", all.size());
        m.put("available", available);
        m.put("issued", issued);
        return m;
    }

    @GetMapping("/overdue")
    public List<Issue> overdue(){ return issueService.overdueToday(); }
}
