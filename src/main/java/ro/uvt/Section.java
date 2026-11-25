package ro.uvt;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Section extends Element {
    protected String title;

    @OneToMany(
            mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderColumn(name = "element_order")
    private List<Element> elements = new ArrayList<>();

    public Section() {
    }

    public Section(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        acceptChildren(visitor);
    }

    protected void acceptChildren(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }

    @Override
    public void render() {
        System.out.println(title);
    }

    @Override
    public void add(Element element) {
        if (element.getParent() != null) {
            throw new IllegalStateException(
                    "Element already has a parent."
            );
        }

        for (Section section = this; section != null;
             section = section.getParent()) {
            if (section == element) {
                throw new IllegalArgumentException(
                        "Circular reference is not allowed."
                );
            }
        }

        elements.add(element);
        element.setParent(this);
    }

    @Override
    public void remove(Element element) {
        if (elements.remove(element)) {
            element.setParent(null);
        }
    }

    @Override
    public Element get(int index) {
        return elements.get(index);
    }
}