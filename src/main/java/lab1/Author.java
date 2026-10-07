package lab1;

import java.util.Objects;

public class Author {
    private final String name;
    private final String surname;

    public Author(String fullName) {
        String value = Objects.requireNonNull(fullName).trim();
        int separator = value.lastIndexOf(' ');
        name = separator < 0 ? value : value.substring(0, separator);
        surname = separator < 0 ? "" : value.substring(separator + 1);
    }

    public Author(String name, String surname) {
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
    }

    public void print() {
        System.out.println("Author: " + name + (surname.isEmpty() ? "" : " " + surname));
    }
}
