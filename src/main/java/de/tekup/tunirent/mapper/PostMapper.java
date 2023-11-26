package de.tekup.tunirent.mapper;

import de.tekup.tunirent.dto.PostDTO;
import de.tekup.tunirent.dto.PostRequest;
import de.tekup.tunirent.model.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PostMapper<T extends Post> {

    public PostDTO convertToDTO(T entity) {
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(entity, postDTO);
        return postDTO;
    }

    public T convertToEntity(PostRequest postRequest, Class<T> clazz){
        T post = null;
        try {
            post = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(postRequest, post);
        return post;
    }
}