package etteplan.rest.task.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TaskExceptions {

    //HTTP 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidAdd(MethodArgumentNotValidException exception){

        Map<String, String> exceptionMap = new HashMap<>();

        //Map the errors that are annotated in the model MaintenanceTask
        exception.getBindingResult().getFieldErrors().forEach( error ->{

            exceptionMap.put(error.getField(), error.getDefaultMessage());

        });

        return exceptionMap;
    }

}
