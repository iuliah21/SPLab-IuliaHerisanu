package ro.uvt.observer;

import ro.uvt.services.BookDto;

public interface Subject {
    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers(BookDto book);
}