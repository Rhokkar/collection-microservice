package at.fhv.teamc.jazzers.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long productIdInternal;

    private UUID productId;

    private String title;

    private String interpret;

    private String medium;

    private String genre;

    private double price;

    protected Product() {

    }

    public Product(UUID productId, String title, String interpret, String medium, String genre, double price) {
        this.productId = productId;
        this.title = title;
        this.interpret = interpret;
        this.medium = medium;
        this.genre = genre;
        this.price = price;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public String getInterpret() {
        return interpret;
    }

    public String getMedium() {
        return medium;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }
}
