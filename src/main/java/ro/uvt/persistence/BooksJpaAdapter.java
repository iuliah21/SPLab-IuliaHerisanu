package ro.uvt.persistence;

import org.springframework.stereotype.Component;
import ro.uvt.Book;

import java.util.List;
import java.util.Optional;

@Component
public class BooksJpaAdapter
        implements CrudRepository<Book, Long> {

    private final BooksRepository repository;

    public BooksJpaAdapter(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book save(Book entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Book entity) {
        repository.delete(entity);
    }
}