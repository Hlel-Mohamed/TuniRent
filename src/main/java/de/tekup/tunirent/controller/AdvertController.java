package de.tekup.tunirent.controller;

import de.tekup.tunirent.dto.AdvertDTO;
import de.tekup.tunirent.dto.AdvertRequest;
import de.tekup.tunirent.enums.LodgingType;
import de.tekup.tunirent.exception.AdvertNotFoundException;
import de.tekup.tunirent.mapper.AdvertMapper;
import de.tekup.tunirent.service.AdvertService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advert")
@Tag(name = "Advert")
@AllArgsConstructor
public class AdvertController {

    private AdvertService advertService;
    private AdvertMapper advertMapper;


    @PostMapping("/create")
    public ResponseEntity<AdvertDTO> createAdvert(@RequestBody AdvertRequest advertRequest) {
        AdvertDTO advert = advertService.saveAdvert(advertMapper.convertToEntity(advertRequest));
        return new ResponseEntity<>(advert, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdvertDTO> updateAdvert(@RequestBody AdvertRequest advertRequest, @PathVariable Long id) {
        AdvertDTO advert = advertService.updateAdvert(advertMapper.convertToEntity(advertRequest), id);
        return new ResponseEntity<>(advert, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdvert(@PathVariable Long id) {
        advertService.deleteById(id);
        return ResponseEntity.ok("Advert deleted successfully!");
    }

    @GetMapping("/creator/{id}")
    public ResponseEntity<List<AdvertDTO>> searchAdvertsByCreatorId(@PathVariable Long id) {
        List<AdvertDTO> adverts = advertService.getAllByCreatorId(id);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<AdvertDTO>> searchAdvertsByLocation(@PathVariable String location) {
        List<AdvertDTO> adverts = advertService.searchAdvertByLocation(location);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<List<AdvertDTO>> sortAdvertsByPrice() {
        List<AdvertDTO> adverts = advertService.sortAdvertByPrice();
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<AdvertDTO>> searchAdvertsByType(@PathVariable LodgingType type) {
        List<AdvertDTO> adverts = advertService.searchAdvertByType(type);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdvertDTO>> getAll() {
        List<AdvertDTO> adverts = advertService.getAll();
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdvertDTO> getById(@PathVariable Long id) {
        AdvertDTO advert = advertService.getById(id);
        return new ResponseEntity<>(advert, HttpStatus.OK);
    }
}