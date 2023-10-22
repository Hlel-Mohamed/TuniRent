package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.exception.ResourceNotFoundException;
import de.tekup.tunirent.model.Image;
import de.tekup.tunirent.repository.ImageRepository;
import de.tekup.tunirent.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image getImage(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image", "Id", id));
    }

    @Override
    public void deleteImage(Long id) {
        getImage(id);
        imageRepository.deleteById(id);
    }
}
