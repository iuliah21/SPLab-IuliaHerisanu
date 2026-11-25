package ro.uvt.commands;

import ro.uvt.services.BookDto;
import ro.uvt.services.BookRequest;
import ro.uvt.services.BooksService;

public record CreateBookCommand(
        BooksService service,
        BookRequest request)
        implements Command<BookDto> {

    @Override
    public BookDto execute() {
        return service.createBook(request);
    }
}