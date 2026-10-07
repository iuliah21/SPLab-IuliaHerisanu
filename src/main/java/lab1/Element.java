package lab1;

public abstract class Element {
    private Section parent;

    public final Section getParent() {
        return parent;
    }

    // Only Section manages the parent when adding or removing a child.
    final void setParent(Section parent) {
        this.parent = parent;
    }

    public abstract void print();

    public void add(Element element) {
        throw new UnsupportedOperationException("This element cannot contain children.");
    }

    public void remove(Element element) {
        throw new UnsupportedOperationException("This element cannot contain children.");
    }

    public Element get(int index) {
        throw new UnsupportedOperationException("This element has no children.");
    }
}
