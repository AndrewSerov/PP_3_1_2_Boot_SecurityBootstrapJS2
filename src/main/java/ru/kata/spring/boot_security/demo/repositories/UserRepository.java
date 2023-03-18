package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

  default void updateUser(int id, User user) {
      User user1 = getById(id);
      user1.setUsername(user.getUsername());
      user1.setLastname(user.getLastname());
      user1.setRoles(user.getRoles());
  }
}
