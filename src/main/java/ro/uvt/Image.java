package ro.uvt;

import jakarta.persistence.*;

import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

@Entity
public class Image extends Element implements Picture {
    private String imageName;

    @Transient
    private Dimension dimension = new Dimension(0, 0);

    @Transient
    private PictureContent imageContent;

    public Image() {
    }

    public Image(String imageName) {
        this.imageName = imageName;

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        imageContent = new PictureContent(imageName);
    }

    public String getImageName() {
        return imageName;
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
        if (imageContent == null) {
            imageContent = new PictureContent(imageName);
        }

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