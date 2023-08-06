package mareczek100.musiccontests.api.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleException(Exception ex) {
        String errorMessage = String.format("Error occurred: [%s]", ex.getMessage());
        log.error(errorMessage, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", errorMessage);
        return modelView;
    }
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleBindException(BindException ex) {
        String errorMessage = String.format("Bad input for field: [%s], wrong value: [%s]",
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getField).orElse(null),
                Optional.ofNullable(ex.getFieldError()).map(FieldError::getRejectedValue).orElse(null));
        log.error(errorMessage, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", errorMessage);
        return modelView;
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = String.format("Bad input: [%s]", ex.getMessage());
        log.error(errorMessage, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", errorMessage);
        return modelView;
    }
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleNoResourceFound(EntityNotFoundException ex) {
        String errorMessage = String.format("Could not find requested resource! [%s]", ex.getMessage());
        log.error(errorMessage, ex);
        ModelAndView modelView = new ModelAndView("error");
        modelView.addObject("errorMessage", errorMessage);
        return modelView;
    }
}
