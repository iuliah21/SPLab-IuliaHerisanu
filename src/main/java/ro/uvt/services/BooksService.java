package ro.uvt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import ro.uvt.Book;
import ro.uvt.persistence.BooksRepository;

import java.util.List;

@Service
@Transactional
public class BooksService {
    private final BooksRepository repository;

    public BooksService(BooksRepository repository) {
        this.repository = repository;
    }

    public List<BookDto> getBooks() {
        return repository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public BookDto getBook(long id) {
        return toDto(findBook(id));
    }

    public BookDto createBook(BookRequest request) {
        Book book = new Book(request.title());

        return toDto(repository.save(book));
    }

    public BookDto updateBook(long id, BookRequest request) {
        Book book = findBook(id);
        book.setTitle(request.title());

        return toDto(repository.save(book));
    }

    public void deleteBook(long id) {
        repository.delete(findBook(id));
    }

    private Book findBook(long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book not found"));
    }

    private BookDto toDto(Book book) {
        return new BookDto(book.getId(), book.getTitle());
    }
}