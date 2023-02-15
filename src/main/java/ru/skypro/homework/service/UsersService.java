package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.dto.UserDto;

import java.io.IOException;

@Service
public interface UsersService {

    UserDto update(UserDto user, String email) throws IOException;

    UserDto getUser(String email) throws IOException;

    void updateAvatar(MultipartFile image, String name) throws IOException;

    byte[] getImage(String name) throws IOException;
}
