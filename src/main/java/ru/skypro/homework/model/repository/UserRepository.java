package ru.skypro.homework.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.model.entity.User;

public interface UserRepository  extends JpaRepository<User, Long> {
}
