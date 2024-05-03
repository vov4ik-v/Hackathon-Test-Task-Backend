package com.spm.vasylyshyn.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(value = UserExistException.class)
    public final ResponseEntity<String> handlerCustomException(UserExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }@ExceptionHandler(value = DeviceNotFoundException.class)
    public final ResponseEntity<String> handlerCustomException(DeviceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = OldPasswordIsIncorectException.class)
    public final ResponseEntity<String> handlerCustomException(OldPasswordIsIncorectException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EmailAlreadyExistException.class)
    public final ResponseEntity<String> handlerCustomException(EmailAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = UsernameAlreadyExistException.class)
    public final ResponseEntity<String> handlerCustomException(UsernameAlreadyExistException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = DeviceHasNotYetBeenCreatedException.class)
    public final ResponseEntity<String> handlerCustomException(DeviceHasNotYetBeenCreatedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
