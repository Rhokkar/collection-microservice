package at.fhv.teamc.jazzers.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Product extends PanacheEntity {
    public String productId;  // public UUID productId;
    public String title;
    public String interpret;
    public String medium;
    public String genre;
    public String price;      // public double price;

    public Product() {

    }
}
