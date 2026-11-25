package ro.uvt.services;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BooksService {
    private final Map<Long, BookDto> books =
            new ConcurrentHashMap<>();

    private final AtomicLong nextId = new AtomicLong(1);

    public List<BookDto> getBooks() {
        return List.copyOf(books.values());
    }

    public BookDto getBook(long id) {
        BookDto book = books.get(id);

        if (book == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found");
        }

        return book;
    }

    public BookDto createBook(BookRequest request) {
        long id = nextId.getAndIncrement();
        BookDto book = new BookDto(id, request.title());

        books.put(id, book);

        return book;
    }

    public BookDto updateBook(long id, BookRequest request) {
        BookDto book = new BookDto(id, request.title());

        if (books.replace(id, book) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found");
        }

        return book;
    }

    public void deleteBook(long id) {
        if (books.remove(id) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found");
        }
    }
}