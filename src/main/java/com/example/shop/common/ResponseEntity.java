package com.example.shop.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseEntity<T> {
    private int status;
    private T data;
    private int count;


    public ResponseEntity(int status, T data, int count) {
        this.status = status;
        this.data = data;
        this.count = count;
    }


}
