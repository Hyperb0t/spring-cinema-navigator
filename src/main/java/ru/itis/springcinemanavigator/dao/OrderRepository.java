package ru.itis.springcinemanavigator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.itis.springcinemanavigator.models.Order;
import ru.itis.springcinemanavigator.models.Seance;
import ru.itis.springcinemanavigator.models.User;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findBySeanceId(Long seanceId);
}
