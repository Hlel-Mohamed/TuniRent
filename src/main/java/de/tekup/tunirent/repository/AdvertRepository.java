package de.tekup.tunirent.repository;

import de.tekup.tunirent.enums.LodgingType;
import de.tekup.tunirent.model.Advert;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends PostRepository<Advert> {
    Optional<Advert> update(Advert advert, Long id);
    List<Advert> findByLocation(String location);
    List<Advert> findByPriceLessThanEqualOrderByPrice(double price);
    List<Advert> findByType(LodgingType type);
}