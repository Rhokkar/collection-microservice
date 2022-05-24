package at.fhv.teamc.jazzers.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Collection extends PanacheEntity {
    public String ownerName;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Product> products;

    public Collection() {

    }

    public static Collection findByOwnerName(String ownerName) {
        Collection collection = find("lower(ownerName) like lower(?1)", ownerName).firstResult();

        if (collection == null) {
            collection = new Collection();
            collection.ownerName = ownerName;
            collection.products = new ArrayList<>();
            collection.persist();
        }

        return collection;
    }
}