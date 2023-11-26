package de.tekup.tunirent.service;

import de.tekup.tunirent.dto.AdvertDTO;
import de.tekup.tunirent.enums.LodgingType;
import de.tekup.tunirent.model.Advert;

import java.util.List;
public interface AdvertService extends PostService{
    AdvertDTO saveAdvert(Advert advert);
    AdvertDTO updateAdvert(Advert advert, Long id);
    List<AdvertDTO> searchAdvertByLocation(String location);
    List<AdvertDTO> sortByPrice(double price);
    List<AdvertDTO> searchAdvertByType(LodgingType type);
}
