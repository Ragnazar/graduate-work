package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.model.entity.*;
import ru.skypro.homework.model.repository.AdsRepository;
import ru.skypro.homework.model.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final AdsRepository adsRepository;
    private final AccessService accessService;
    @Value("${Image.dir.path}")
    private String imageDir;

    public ImageServiceImpl(ImageRepository imageRepository, AdsRepository adsRepository,
                            AccessService accessService) {
        this.imageRepository = imageRepository;
        this.adsRepository = adsRepository;
        this.accessService = accessService;
    }

    @Override
    public byte[] updateAdsImage(Integer id, MultipartFile image, Authentication authentication) throws IOException {
        accessService.checkAdsAccess(id, authentication);
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);

        Image imageToSave = new Image();
        String path = saveImage(UUID.randomUUID().toString(), image);
        imageToSave.setPath(path);
        imageToSave.setAds(ads);
        imageRepository.save(imageToSave);
        return Files.readAllBytes(Paths.get(imageToSave.getPath()));
    }

    @Override
    public byte[] getImage(int id) throws IOException {
        Ads ads = adsRepository.findById(id).orElseThrow(AdsNotFoundException::new);

        //по кеакой то причине фронт не может работать с массивом картинок
        return Files.readAllBytes(Paths.get(Objects.requireNonNull(ads.getImages().stream().
                reduce((first, second) -> second).map(Image::getPath).orElse(null))));


    }

    public String saveImage(String name, MultipartFile file) {
        log.debug("Вызван метод сохраняющий картинку из переданного файла.");


        String extension = Optional.ofNullable(file.getOriginalFilename()).
                map(s -> s.substring(file.getOriginalFilename().lastIndexOf("."))).
                orElse(" ");

        Path filePath = Path.of(imageDir, name + extension);
        System.out.println(filePath);
        System.out.println(Paths.get(name + extension).toAbsolutePath());
        try {
            Files.createDirectories(filePath.getParent());
            Files.deleteIfExists(filePath);
            Files.write(filePath, file.getBytes());
        } catch (IOException ex) {
            log.error("Возникла проблема при создании или записи файла или директории.");
            throw new RuntimeException("Возникла проблема при создании или записи файла или директории.");
        }
        return filePath.toString();
    }
}

