package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) { this.service = service; }

    @GetMapping
    public List<Book> all(@RequestParam(value="q", required = false) String q){
        if (q != null && !q.isBlank()) return service.search(q);
        return service.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@Valid @RequestBody Book b){ return service.save(b); }

    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @Valid @RequestBody Book b){
        Book existing = service.get(id);
        existing.setTitle(b.getTitle());
        existing.setAuthor(b.getAuthor());
        existing.setIsbn(b.getIsbn());
        existing.setCategory(b.getCategory());
        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ service.delete(id); }

    @GetMapping("/{id}")
    public Book one(@PathVariable Long id){ return service.get(id); }
}
