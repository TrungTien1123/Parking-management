package vn.com.trungtien.management.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OneForAllException extends RuntimeException{
    private String message;
    private String entity;
    private String code;
}
