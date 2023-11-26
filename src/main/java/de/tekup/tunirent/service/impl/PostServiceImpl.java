package de.tekup.tunirent.service.impl;

import de.tekup.tunirent.dto.PostDTO;
import de.tekup.tunirent.mapper.PostMapper;
import de.tekup.tunirent.model.Post;
import de.tekup.tunirent.repository.PostRepository;
import de.tekup.tunirent.service.PostService;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@NoArgsConstructor
public abstract class PostServiceImpl<T extends Post> implements PostService {
    public abstract PostRepository<T> getRepository();
    @Autowired
    @Setter
    protected PostMapper<T> postMapper;

    @Override
    public List<PostDTO> getAll() {
        List<T> posts = getRepository().findAll();
        return posts.stream().map(this.postMapper::convertToDTO).toList();
    }

    @Override
    public List<PostDTO> getAllByCreatorId(Long creatorId) {
        List<T> posts = getRepository().findAllByCreatorId(creatorId);
        return posts.stream().map(this.postMapper::convertToDTO).toList();
    }

    @Override
    public PostDTO getById(Long id) {
        T post = getRepository().findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return postMapper.convertToDTO(post);
    }

    @Override
    public void deleteById(Long id) {
        getRepository().deleteById(id);
    }
}
