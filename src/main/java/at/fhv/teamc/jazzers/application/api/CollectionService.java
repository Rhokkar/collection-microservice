package at.fhv.teamc.jazzers.application.api;

import at.fhv.jazzers.shared.dto.DigitalProductDTO;

import java.util.List;

public interface CollectionService {
    List<DigitalProductDTO> getCollection(String username);
    void addToCollection(String username, DigitalProductDTO productDTO);
    boolean ownsProduct(String username, String productName);
}
