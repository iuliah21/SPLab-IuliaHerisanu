package ro.uvt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ro.uvt.commands.*;
import ro.uvt.services.*;
import ro.uvt.observer.AllBooksSubject;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService service;
    private final SyncCommandExecutor executor;
    private final AllBooksSubject allBooksSubject;

    public BooksController(
            BooksService service,
            SyncCommandExecutor executor,
            AllBooksSubject allBooksSubject) {
        this.service = service;
        this.executor = executor;
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping
    public List<BookDto> getBooks() {
        return executor.execute(
                new GetBooksCommand(service)).join();
    }

    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable long id) {
        return executor.execute(
                new GetBookCommand(service, id)).join();
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(
            @RequestBody BookRequest request) {

        BookDto book = executor.execute(
                new CreateBookCommand(service, request)).join();

        allBooksSubject.add(book);

        return ResponseEntity.status(201).body(book);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(
            @PathVariable long id,
            @RequestBody BookRequest request) {
        return executor.execute(
                new UpdateBookCommand(service, id, request)).join();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @PathVariable long id) {
        executor.execute(
                new DeleteBookCommand(service, id)).join();

        return ResponseEntity.noContent().build();
    }
}