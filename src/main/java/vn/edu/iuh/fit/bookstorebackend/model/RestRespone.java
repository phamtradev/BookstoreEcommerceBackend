package vn.edu.iuh.fit.bookstorebackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestRespone<T> {
    private int statusCode;
    private String error;

    private Object message; //co the string hoac arrayList
    private T data;
}
