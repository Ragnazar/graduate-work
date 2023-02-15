package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


   @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<byte[]> updateAdsImage(@PathVariable Integer id,@RequestParam MultipartFile image,
                                                 Authentication authentication) throws IOException {

        return ResponseEntity.ok(imageService.updateAdsImage(id, image,authentication));
        //не приходит запрос от сервера(или приходит не этот)
    }
}
