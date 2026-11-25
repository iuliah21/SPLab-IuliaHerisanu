package ro.uvt.commands;

import ro.uvt.services.BookDto;
import ro.uvt.services.BooksService;

import java.util.List;

public record GetBooksCommand(BooksService service)
        implements Command<List<BookDto>> {

    @Override
    public List<BookDto> execute() {
        return service.getBooks();
    }
}