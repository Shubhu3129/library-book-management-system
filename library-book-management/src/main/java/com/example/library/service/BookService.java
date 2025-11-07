package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public Book save(Book b) { return repo.save(b); }
    public void delete(Long id) { repo.deleteById(id); }
    public Book get(Long id) { return repo.findById(id).orElseThrow(); }
    public List<Book> all() { return repo.findAll(); }

    public List<Book> search(String q) { return repo.search(q); }
}
