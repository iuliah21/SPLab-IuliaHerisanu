package ro.uvt;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TableOfContents extends Element {
    @ElementCollection
    @OrderColumn(name = "entry_order")
    private List<String> entries = new ArrayList<>();

    public TableOfContents() {
    }

    public void addEntry(String entry) {
        entries.add(entry);
    }

    public List<String> getEntries() {
        return entries;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render() {
        System.out.println("Table of Contents:");

        for (String entry : entries) {
            System.out.println(entry);
        }
    }
}