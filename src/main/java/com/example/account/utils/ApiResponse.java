package com.example.account.utils;

import lombok.*;

@Data
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
}




