package ro.uvt.commands;

import ro.uvt.services.BooksService;

public record DeleteBookCommand(
        BooksService service, long id)
        implements Command<Void> {

    @Override
    public Void execute() {
        service.deleteBook(id);
        return null;
    }
}