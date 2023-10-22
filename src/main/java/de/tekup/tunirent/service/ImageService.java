package de.tekup.tunirent.service;

import de.tekup.tunirent.model.Image;

public interface ImageService {
    Image saveImage(Image image);
    Image getImage(Long id);
    void deleteImage(Long id);
}
