import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Section extends Element {
    private final String title;
    private final List<Element> children = new ArrayList<>();

    public Section(String title) {
        this.title = Objects.requireNonNull(title);
    }

    @Override
    public void add(Element element) {
        Objects.requireNonNull(element, "The element cannot be null.");
        if (element.getParent() != null) {
            throw new IllegalStateException("The element already belongs to a section.");
        }
        // Reject self-containment and adding an ancestor below its descendant.
        for (Section ancestor = this; ancestor != null; ancestor = ancestor.getParent()) {
            if (ancestor == element) {
                throw new IllegalArgumentException("Adding this element would create a cycle.");
            }
        }
        children.add(element);
        element.setParent(this);
    }

    @Override
    public void remove(Element element) {
        if (children.remove(element)) {
            element.setParent(null);
        }
    }

    @Override
    public Element get(int index) {
        return children.get(index);
    }

    @Override
    public void print() {
        if (!title.isEmpty()) {
            System.out.println(title);
        }
        for (Element child : children) {
            child.print();
        }
    }
}
