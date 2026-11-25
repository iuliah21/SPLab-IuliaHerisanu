package ro.uvt.observer;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ro.uvt.services.BookDto;

import java.io.IOException;

public class SseObserver implements Observer {
    private final SseEmitter emitter;
    private final Subject subject;

    public SseObserver(SseEmitter emitter, Subject subject) {
        this.emitter = emitter;
        this.subject = subject;
    }

    @Override
    public void update(BookDto book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (IOException | IllegalStateException e) {
            subject.detach(this);
            emitter.completeWithError(e);
        }
    }
}