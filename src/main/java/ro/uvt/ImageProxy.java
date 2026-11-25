package ro.uvt;

import jakarta.persistence.*;

import java.awt.Dimension;

@Entity
public class ImageProxy extends Element implements Picture {
    private String url;

    @Transient
    private Image realImage;

    public ImageProxy() {
    }

    public ImageProxy(String url) {
        this.url = url;
    }

    private Image loadImage() {
        if (realImage == null) {
            realImage = new Image(url);
        }

        return realImage;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String url() {
        return url;
    }

    @Override
    public Dimension dim() {
        return loadImage().dim();
    }

    @Override
    public PictureContent content() {
        return loadImage().content();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void render() {
        loadImage().render();
    }
}