package ru.skypro.homework.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.*;

import java.io.IOException;

@Slf4j
@RestControllerAdvice(basePackages = "ru.skypro.homework")
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @org.springframework.web.bind.annotation.ExceptionHandler({AdsNotFoundException.class, CommentNotFoundException.class,
            ImageNotFoundException.class, UserNotFoundException.class})
    public String handleException(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public String handleExceptionADE(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler(IOException.class)
    public String handleExceptionIO(Exception e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }
}
