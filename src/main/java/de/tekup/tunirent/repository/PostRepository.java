package de.tekup.tunirent.repository;

import de.tekup.tunirent.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface PostRepository<T extends Post> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {
    List<T> findAllByCreatorId(Long creatorId);
}