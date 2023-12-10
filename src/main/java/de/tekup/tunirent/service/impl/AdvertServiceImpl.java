package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.dto.AdvertDTO;
import de.tekup.tunirent.dto.PostDTO;
import de.tekup.tunirent.enums.LodgingType;
import de.tekup.tunirent.exception.AdvertNotFoundException;
import de.tekup.tunirent.mapper.AdvertMapper;
import de.tekup.tunirent.model.Advert;
import de.tekup.tunirent.repository.AdvertRepository;
import de.tekup.tunirent.service.AdvertService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AdvertServiceImpl extends PostServiceImpl<Advert> implements AdvertService {
    @Autowired
    private AdvertRepository advertRepository;
    @Autowired
    private AdvertMapper advertMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Override
    public AdvertRepository getRepository() {
        return advertRepository;
    }

    @Override
    public AdvertDTO saveAdvert(Advert advert) {
        advert.setCreator(userServiceImpl.getCurrentLoggedInUser());
        advert.setCreatedDate(new Date(System.currentTimeMillis()));
        Advert savedAdvert = getRepository().save(advert);
        return advertMapper.convertToDTO(savedAdvert);
    }

    @Override
    public AdvertDTO updateAdvert(Advert advert, Long id) {
        Advert existingAdvert = getRepository().findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));

        existingAdvert.setLocation(advert.getLocation());
        existingAdvert.setPrice(advert.getPrice());
        existingAdvert.setDescription(advert.getDescription());
        existingAdvert.setType(advert.getType());
        existingAdvert.setNumberOfPlaces(advert.getNumberOfPlaces());
        existingAdvert.setImages(advert.getImages());

        Advert updatedAdvert = getRepository().save(existingAdvert);
        return advertMapper.convertToDTO(updatedAdvert);
    }

    @Override
    public List<AdvertDTO> getAll() {
        List<Advert> adverts = getRepository().findAll();
        return adverts.stream().map(this.advertMapper::convertToDTO).toList();
    }

    @Override
    public List<AdvertDTO> getAllByCreatorId(Long creatorId) {
        List<Advert> adverts = getRepository().findAllByCreatorId(creatorId);
        return adverts.stream().map(this.advertMapper::convertToDTO).toList();
    }

    @Override
    public AdvertDTO getById(Long id) {
        Advert advert = getRepository().findById(id).orElseThrow(() -> new AdvertNotFoundException("Advert not found"));
        return advertMapper.convertToDTO(advert);
    }

    @Override
    public List<AdvertDTO> searchAdvertByLocation(String location) {
        List<Advert> adverts = getRepository().findByLocation(location);
        return adverts.stream().map(this.advertMapper::convertToDTO).toList();
    }

    @Override
    public List<AdvertDTO> sortAdvertByPrice() {
        List<Advert> adverts = getRepository().findAll();
        return adverts.stream().map(this.advertMapper::convertToDTO).sorted(Comparator.comparing(AdvertDTO::getPrice)).toList();
    }

    @Override
    public List<AdvertDTO> searchAdvertByType(LodgingType type) {
        List<Advert> adverts = getRepository().findByType(type);
        return adverts.stream().map(this.advertMapper::convertToDTO).toList();
    }
}