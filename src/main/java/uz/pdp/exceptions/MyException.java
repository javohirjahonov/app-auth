package uz.pdp.exceptions;

import lombok.Getter;

@Getter
public class MyException extends RuntimeException{

    private final String message;

    public MyException(String msg){
        message = msg;
    }
}
