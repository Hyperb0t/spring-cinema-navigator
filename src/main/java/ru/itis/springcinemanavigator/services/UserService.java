package ru.itis.springcinemanavigator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springcinemanavigator.dao.OrderRepository;
import ru.itis.springcinemanavigator.dao.UserRepository;
import ru.itis.springcinemanavigator.models.Order;
import ru.itis.springcinemanavigator.models.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getOrdersWithUser (User user) {
        return orderRepository.findByUser(user);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public void addBonusesToUser(User user, Integer bonuses) {
        user.setBonuses(user.getBonuses() + bonuses);
        userRepository.save(user);
    }
}
