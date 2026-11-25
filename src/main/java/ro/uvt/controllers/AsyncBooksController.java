package ro.uvt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ro.uvt.commands.AsyncCommandExecutor;
import ro.uvt.commands.CreateBookCommand;
import ro.uvt.services.BookRequest;
import ro.uvt.services.BooksService;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/async/books")
public class AsyncBooksController {
    private final BooksService service;
    private final AsyncCommandExecutor executor;

    public AsyncBooksController(BooksService service,
                                AsyncCommandExecutor executor) {
        this.service = service;
        this.executor = executor;
    }

    @PostMapping
    public ResponseEntity<?> createBook(
            @RequestBody BookRequest request) {

        String id = executor.submit(
                new CreateBookCommand(service, request));

        return ResponseEntity.accepted().body(
                Map.of("taskId", id,
                        "status", "ACCEPTED",
                        "statusUrl", "/async/books/tasks/" + id));
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getStatus(@PathVariable String id) {
        CompletableFuture<?> task = executor.getTask(id);

        if (task == null) {
            return ResponseEntity.notFound().build();
        }

        if (!task.isDone()) {
            return ResponseEntity.ok(
                    Map.of("status", "PENDING"));
        }

        if (task.isCompletedExceptionally()) {
            return ResponseEntity.ok(
                    Map.of("status", "FAILED"));
        }

        return ResponseEntity.ok(
                Map.of("status", "COMPLETED",
                        "result", task.join()));
    }
}