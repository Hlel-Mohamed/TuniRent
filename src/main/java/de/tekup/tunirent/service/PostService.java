package de.tekup.tunirent.service;

import de.tekup.tunirent.dto.AdvertDTO;
import de.tekup.tunirent.dto.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();
    List<PostDTO> getAllByCreatorId(Long creatorId);
    PostDTO getById(Long id);
    void deleteById(Long id);
}
