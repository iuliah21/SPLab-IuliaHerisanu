package ro.uvt;

public class GenerateToC implements Visitor {
    private TableOfContents tableOfContents =
            new TableOfContents();

    @Override
    public void visit(Book book) {
    }

    @Override
    public void visit(Section section) {
        int depth = 0;
        Section parent = section.getParent();

        while (parent != null && !(parent instanceof Book)) {
            depth++;
            parent = parent.getParent();
        }

        tableOfContents.addEntry(
                "    ".repeat(depth) + section.getTitle()
        );
    }

    @Override
    public void visit(TableOfContents tableOfContents) {
    }

    @Override
    public void visit(Paragraph paragraph) {
    }

    @Override
    public void visit(ImageProxy imageProxy) {
    }

    @Override
    public void visit(Image image) {
    }

    @Override
    public void visit(Table table) {
    }

    public TableOfContents getToC() {
        return tableOfContents;
    }
}