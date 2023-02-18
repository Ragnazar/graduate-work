package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.ImageNotFoundException;
import ru.skypro.homework.exception.NullEmailException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.dto.UserDto;
import ru.skypro.homework.model.entity.ProfileUser;
import ru.skypro.homework.model.mapper.UserMapper;
import ru.skypro.homework.model.repository.UserRepository;
import ru.skypro.homework.service.UsersService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class UsersServiceImpl implements UsersService {
    private final UserRepository userRepository;
    private final ImageServiceImpl imageService;

    public UsersServiceImpl(UserRepository userRepository, ImageServiceImpl imageService) {
        this.userRepository = userRepository;
        this.imageService = imageService;
    }

    @Override
    public UserDto update(UserDto user, String email) throws IOException {
        if (isEmpty(email)) {
            throw new NullEmailException();
        }
        user.setEmail(email);
        ProfileUser userProfile = userRepository.findByEmail(email).orElse(new ProfileUser());
        UserMapper.INSTANCE.partialUpdate(user, userProfile);
        return UserMapper
                .INSTANCE
                .userToUserDto(userRepository.save(userProfile));
    }

      @Override
    public UserDto getUser(String email) throws IOException {
        ProfileUser user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserMapper
                .INSTANCE
                .userToUserDto(user);
    }

    @Override
    public void updateAvatar(MultipartFile image, String name) throws IOException {
        ProfileUser user = userRepository.findByEmail(name).orElseThrow(UserNotFoundException::new);

        if (user.getImage() != null) {
            Files.delete(Paths.get(user.getImage()));
        }
        user.setImage(imageService.saveImage(UUID.randomUUID() + name, image));
        userRepository.save(user);
    }

    @Override
    public byte[] getImage( int id) throws IOException {
        ProfileUser user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if (user.getImage() == null) {
            throw new ImageNotFoundException();
        }
        return Files.readAllBytes(Paths.get(user.getImage()));
    }
}
