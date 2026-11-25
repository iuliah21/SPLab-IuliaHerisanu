package ro.uvt.observer;

import ro.uvt.services.BookDto;

public interface Observer {
    void update(BookDto book);
}