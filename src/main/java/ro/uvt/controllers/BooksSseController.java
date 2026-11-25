package ro.uvt.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import ro.uvt.observer.AllBooksSubject;
import ro.uvt.observer.SseObserver;

@RestController
public class BooksSseController {
    private final AllBooksSubject allBooksSubject;

    public BooksSseController(AllBooksSubject allBooksSubject) {
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping(
            value = "/books-sse",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public SseEmitter getBooksSse() {
        SseEmitter emitter = new SseEmitter(0L);

        SseObserver observer =
                new SseObserver(emitter, allBooksSubject);

        emitter.onCompletion(() ->
                allBooksSubject.detach(observer));

        emitter.onTimeout(() ->
                allBooksSubject.detach(observer));

        emitter.onError(error ->
                allBooksSubject.detach(observer));

        allBooksSubject.attach(observer);

        return emitter;
    }
}