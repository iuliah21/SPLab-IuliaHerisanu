import java.util.ArrayList;
import java.util.List;

public class Section extends Element {
    protected String title;
    private List<Element> elements = new ArrayList<>();

    public Section(String title) {
        this.title = title;
    }

    @Override
    public void print() {
        System.out.println(title);

        for (Element element : elements) {
            element.print();
        }
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