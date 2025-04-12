package uz.pdp.exceptions;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.model.ApiKetmonResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    public HttpEntity<ApiKetmonResponse> handle(MyException ex) {
        ApiKetmonResponse apiKetmonResponse = ApiKetmonResponse.fail(ex.getMessage());
        return ResponseEntity.status(400).body(apiKetmonResponse);
    }


    @ExceptionHandler
    public HttpEntity<ApiKetmonResponse> handle(RuntimeException ex) {
        ApiKetmonResponse apiKetmonResponse = ApiKetmonResponse.fail(ex.getMessage());
        return ResponseEntity.status(400).body(apiKetmonResponse);
    }

    @ExceptionHandler
    public HttpEntity<ApiKetmonResponse> handle(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErrorData> errorDataList = new ArrayList<>();

        fieldErrors.forEach(fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            errorDataList.add(ErrorData.error(field,message));
        });

        ApiKetmonResponse apiKetmonResponse = ApiKetmonResponse.fail(errorDataList);
        return ResponseEntity.status(400).body(apiKetmonResponse);
    }
}
