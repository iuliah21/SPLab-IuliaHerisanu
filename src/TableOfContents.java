import java.util.ArrayList;
import java.util.List;

public class TableOfContents extends Element {
    private final List<String> entries = new ArrayList<>();

    public void addEntry(String title, int page) {
        entries.add(title + " ... " + page);
    }

    @Override
    public void print() {
        System.out.println("Table of Contents:");
        for (String entry : entries) {
            System.out.println(entry);
        }
    }
}
