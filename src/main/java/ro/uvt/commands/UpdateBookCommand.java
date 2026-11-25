package ro.uvt.commands;

import ro.uvt.services.BookDto;
import ro.uvt.services.BookRequest;
import ro.uvt.services.BooksService;

public record UpdateBookCommand(
        BooksService service,
        long id,
        BookRequest request)
        implements Command<BookDto> {

    @Override
    public BookDto execute() {
        return service.updateBook(id, request);
    }
}