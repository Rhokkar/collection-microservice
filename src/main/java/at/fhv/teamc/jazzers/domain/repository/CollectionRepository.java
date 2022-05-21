package at.fhv.teamc.jazzers.domain.repository;

import at.fhv.teamc.jazzers.domain.model.Collection;

import java.util.Optional;

public interface CollectionRepository {
    Optional<Collection> getCollection(String username);
}
