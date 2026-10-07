package lab1;

public class Image extends Element {
    private final String url;

    public Image(String url) {
        this.url = url;
    }

    @Override
    public void print() {
        System.out.println("Image with name:" + url);
    }
}
