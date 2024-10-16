package com.aifitness.fitnessapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final HttpStatus notFound = HttpStatus.NOT_FOUND;
    private final HttpStatus conflict = HttpStatus.CONFLICT;

    @ExceptionHandler(value = {EmailAlreadyTakenException.class})
    public ResponseEntity<Object> handleEmailAlreadyTakenException(EmailAlreadyTakenException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, conflict);
    }

    @ExceptionHandler(value = {TokenAlreadyConfirmedException.class})
    public ResponseEntity<Object> handleTokenAlreadyConfirmedException(TokenAlreadyConfirmedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, conflict);
    }

    @ExceptionHandler(value =  {TokenNotFoundException.class})
    public ResponseEntity<Object> handleTokenNotFoundException(TokenNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException e) {
        HttpStatus gone = HttpStatus.GONE;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                gone,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, gone);
    }
    
    @ExceptionHandler(value = {TrainingPlanNotFoundException.class})
    public ResponseEntity<Object> handleTrainingPlanNotFoundException(TrainingPlanNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {TrainingPlanVersionNotFoundException.class})
    public ResponseEntity<Object> handleTrainingPlanVersionNotFoundException(TrainingPlanVersionNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {WorkoutNotFoundException.class})
    public ResponseEntity<Object> handleWorkoutNotFoundException(WorkoutNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {TrainingSessionNotFoundException.class})
    public ResponseEntity<Object> handleTrainingSessionNotFoundException(TrainingSessionNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {ExerciseNotFoundException.class})
    public ResponseEntity<Object> handleExerciseNotFoundException(ExerciseNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {ExerciseSessionNotFoundException.class})
    public ResponseEntity<Object> handleExerciseSessionNotFoundException(ExerciseSessionNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

    @ExceptionHandler(value = {ExerciseSetNotFoundException.class})
    public ResponseEntity<Object> handleExerciseSetNotFoundException(ExerciseSetNotFoundException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                notFound,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exceptionResponse, notFound);
    }

}
