package uz.pdp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import uz.pdp.exceptions.ErrorData;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiKetmonResponse<T> {

    private String message;

    private boolean success;

    private List<ErrorData> errors;

    private T data;

    //success
    private ApiKetmonResponse(String message) {
        this.message = message;
        this.success = true;
    }

    private ApiKetmonResponse(T data) {
        this.data = data;
        this.success = true;
    }

    private ApiKetmonResponse() {
        this.success = true;
    }

    private ApiKetmonResponse(List<ErrorData> errors) {
        this.errors = errors;
    }

    public static ApiKetmonResponse success() {
        return new ApiKetmonResponse();
    }

    public static ApiKetmonResponse success(String message) {
        return new ApiKetmonResponse(message);
    }

    public static <E> ApiKetmonResponse<E> successData(E data) {
        return new ApiKetmonResponse<>(data);
    }

    public static ApiKetmonResponse fail(String msg) {
        return new ApiKetmonResponse(List.of(ErrorData.error(msg)));
    }

    public static ApiKetmonResponse fail(List<ErrorData> errors) {
        return new ApiKetmonResponse(errors);
    }

}
