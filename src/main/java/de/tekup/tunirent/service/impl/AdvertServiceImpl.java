package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.exception.ResourceNotFoundException;
import de.tekup.tunirent.model.Advert;
import de.tekup.tunirent.service.AdvertService;
import de.tekup.tunirent.repository.AdvertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertServiceImpl implements AdvertService {
    private final AdvertRepository advertRepository;
    public AdvertServiceImpl(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    @Override
    public Advert saveAdvert(Advert advert) {
        return advertRepository.save(advert);
    }

    @Override
    public List<Advert> getAllAdverts() {
        return advertRepository.findAll();
    }

    @Override
    public List<Advert> getAllAdvertsByCreatorId(Long creatorId) {
        return advertRepository.findByCreatorId(creatorId);
    }

    @Override
    public Advert getAdvertById(Long id) {
        return advertRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Advert", "Id", id));
    }

    @Override
    public Advert updateAdvert(Advert advert, Long id) {
        Advert existingAdvert = advertRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Advert", "Id", id));
        existingAdvert.setCreator(advert.getCreator());
        existingAdvert.setDescription(advert.getDescription());
        existingAdvert.setPrice(advert.getPrice());
        existingAdvert.setImages(advert.getImages());
        existingAdvert.setLocation(advert.getLocation());
        existingAdvert.setNumberOfPlaces(advert.getNumberOfPlaces());
        existingAdvert.setType(advert.getType());
        advertRepository.save(existingAdvert);
        return existingAdvert;
    }

    @Override
    public void deleteAdvertById(Long id) {
        getAdvertById(id);
        advertRepository.deleteById(id);
    }
}
