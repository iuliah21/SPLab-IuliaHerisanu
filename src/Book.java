import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book extends Section {
    private final String title;
    private final List<Author> authors = new ArrayList<>();

    public Book(String title) {
        // The book prints its own header; Section.print prints its children.
        super("");
        this.title = Objects.requireNonNull(title);
    }

    public void addAuthor(Author author) {
        authors.add(Objects.requireNonNull(author));
    }

    public void addContent(Element element) {
        add(element);
    }

    @Override
    public void print() {
        System.out.println("Book: " + title);
        System.out.println();
        printAuthors();
        System.out.println();
        super.print();
    }

    public void printAuthors() {
        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
    }
}
