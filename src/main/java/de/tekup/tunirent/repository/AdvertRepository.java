package de.tekup.tunirent.repository;

import de.tekup.tunirent.enums.LodgingType;
import de.tekup.tunirent.model.Advert;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public interface AdvertRepository extends PostRepository<Advert> {
    @NotNull
    Optional<Advert> findById(@NotNull Long id);
    @NotNull
    List<Advert> findAll();

    List<Advert> findByLocation(String location);
    List<Advert> findByType(LodgingType type);
}