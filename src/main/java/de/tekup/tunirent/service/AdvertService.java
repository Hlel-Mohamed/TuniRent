package de.tekup.tunirent.service;

import de.tekup.tunirent.model.Advert;

import java.util.List;
public interface AdvertService {
    Advert saveAdvert(Advert advert);
    List<Advert> getAllAdverts();
    List<Advert> getAllAdvertsByCreatorId(Long creatorId);
    Advert getAdvertById(Long id);
    Advert updateAdvert(Advert advert, Long id);
    void deleteAdvertById(Long id);
}
