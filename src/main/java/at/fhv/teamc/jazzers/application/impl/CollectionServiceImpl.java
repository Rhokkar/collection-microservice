package at.fhv.teamc.jazzers.application.impl;

import at.fhv.jazzers.shared.dto.DigitalProductDTO;
import at.fhv.teamc.jazzers.ServiceRegistry;
import at.fhv.teamc.jazzers.application.api.CollectionService;
import at.fhv.teamc.jazzers.domain.model.Product;
import at.fhv.teamc.jazzers.domain.repository.CollectionRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CollectionServiceImpl implements CollectionService {
    private final CollectionRepository collectionRepository = ServiceRegistry.collectionRepository();

    @Override
    public List<DigitalProductDTO> getCollection(String username) {
        return collectionRepository
                .getCollection(username)
                .orElseThrow()
                .getProducts()
                .stream()
                .map(product -> new DigitalProductDTO(product.getProductId(), product.getTitle(), product.getInterpret(), product.getMedium(), product.getGenre(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void addToCollection(String username, DigitalProductDTO productDTO) {
        collectionRepository.getCollection(username).orElseThrow().getProducts().add(new Product(productDTO.getProductId(), productDTO.getTitle(), productDTO.getInterpret(), productDTO.getMedium(), productDTO.getGenre(), productDTO.getPrice()));
    }

    @Override
    public boolean ownsProduct(String username, String productName) {
        return collectionRepository.getCollection(username).orElseThrow().getProducts().stream().map(Product::getTitle).collect(Collectors.toList()).contains(productName);
    }
}
