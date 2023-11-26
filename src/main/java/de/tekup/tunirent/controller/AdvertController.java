package de.tekup.tunirent.controller;

import de.tekup.tunirent.dto.AdvertDTO;
import de.tekup.tunirent.dto.AdvertRequest;
import de.tekup.tunirent.dto.PostDTO;
import de.tekup.tunirent.enums.LodgingType;
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

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> getAll() {
        List<PostDTO> posts = advertService.getAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable Long id) {
        PostDTO post = advertService.getById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

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

    @GetMapping("/search/location/{location}")
    public ResponseEntity<List<AdvertDTO>> searchAdvertsByLocation(@PathVariable String location) {
        List<AdvertDTO> adverts = advertService.searchAdvertByLocation(location);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/search/price/{price}")
    public ResponseEntity<List<AdvertDTO>> searchAdvertsByPrice(@PathVariable double price) {
        List<AdvertDTO> adverts = advertService.sortByPrice(price);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }

    @GetMapping("/search/type/{type}")
    public ResponseEntity<List<AdvertDTO>> searchAdvertsByType(@PathVariable LodgingType type) {
        List<AdvertDTO> adverts = advertService.searchAdvertByType(type);
        return new ResponseEntity<>(adverts, HttpStatus.OK);
    }
}