package at.fhv.teamc.jazzers.domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Collection {
    @Id
    @GeneratedValue
    private Long collectionIdInternal;

    private String ownerName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    protected Collection() {

    }

    public Collection(String ownerName, List<Product> products) {
        this.ownerName = ownerName;
        this.products = products;
    }

    void addToCollection(Product product) {
        products.add(product);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collection that = (Collection) o;
        return ownerName.equals(that.ownerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerName);
    }
}
