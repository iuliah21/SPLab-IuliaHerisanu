package ro.uvt;

public class Paragraph extends Element {
    private String text;
    private AlignStrategy alignStrategy;
    private Context context = new Context(40);

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setAlignStrategy(AlignStrategy alignStrategy) {
        this.alignStrategy = alignStrategy;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render() {
        if (alignStrategy == null) {
            System.out.println("Paragraph: " + text);
        } else {
            alignStrategy.render(this, context);
        }
    }
}