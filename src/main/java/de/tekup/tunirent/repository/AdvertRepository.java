package de.tekup.tunirent.repository;

import de.tekup.tunirent.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdvertRepository extends JpaRepository<Advert, Long> {
    List<Advert> findByCreatorId(Long creatorId);
}
