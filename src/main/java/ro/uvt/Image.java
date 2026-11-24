package ro.uvt;

import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

public class Image extends Element implements Picture {
    private String imageName;
    private Dimension dimension;
    private PictureContent imageContent;

    public Image(String imageName) {
        this.imageName = imageName;

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        dimension = new Dimension(0, 0);
        imageContent = new PictureContent(imageName);
    }

    @Override
    public String url() {
        return imageName;
    }

    @Override
    public Dimension dim() {
        return dimension;
    }

    @Override
    public PictureContent content() {
        return imageContent;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render() {
        System.out.println("Image with name:" + imageName);
    }
}