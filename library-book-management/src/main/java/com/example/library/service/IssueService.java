package com.example.library.service;

import com.example.library.entity.AppUser;
import com.example.library.entity.Book;
import com.example.library.entity.Issue;
import com.example.library.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional
public class IssueService {
    private final IssueRepository repo;
    private final BookService bookService;

    @Value("${app.loan-days:14}")
    private int loanDays;

    @Value("${app.fine-per-day:2}")
    private int finePerDay;

    public IssueService(IssueRepository repo, BookService bookService) {
        this.repo = repo;
        this.bookService = bookService;
    }

    public Issue issueBook(Book book, AppUser student){
        if (!book.isAvailable()) throw new IllegalStateException("Book is not available");
        Issue iss = new Issue();
        iss.setBook(book);
        iss.setStudent(student);
        iss.setIssueDate(LocalDate.now());
        iss.setDueDate(LocalDate.now().plusDays(loanDays));
        book.setAvailable(false);
        return repo.save(iss);
    }

    public Issue returnBook(Issue issue){
        issue.setReturnDate(LocalDate.now());
        issue.getBook().setAvailable(true);
        return repo.save(issue);
    }

    public long calculateFine(Issue issue){
        LocalDate ref = issue.getReturnDate() != null ? issue.getReturnDate() : LocalDate.now();
        if (ref.isAfter(issue.getDueDate())){
            long days = ChronoUnit.DAYS.between(issue.getDueDate(), ref);
            return days * finePerDay;
        }
        return 0L;
    }

    public List<Issue> overdueToday(){
        return repo.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now());
    }

    public Issue get(Long id){ return repo.findById(id).orElseThrow(); }

    public List<Issue> byStudent(AppUser u){ return repo.findByStudent(u); }

    public boolean isBookIssued(Long bookId){ return repo.existsByBookIdAndReturnDateIsNull(bookId); }
}
