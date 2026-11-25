package ro.uvt.commands;

import ro.uvt.services.BookDto;
import ro.uvt.services.BooksService;

public record GetBookCommand(BooksService service, long id)
        implements Command<BookDto> {

    @Override
    public BookDto execute() {
        return service.getBook(id);
    }
}