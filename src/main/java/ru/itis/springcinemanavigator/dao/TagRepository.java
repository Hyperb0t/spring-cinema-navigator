package ru.itis.springcinemanavigator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springcinemanavigator.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}
