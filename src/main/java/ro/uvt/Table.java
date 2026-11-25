package ro.uvt;

import jakarta.persistence.*;

@Entity
@jakarta.persistence.Table(name = "BookTable")
public class Table extends Element {
    private String title;

    public Table() {
    }

    public Table(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render() {
        System.out.println("Table: " + title);
    }
}