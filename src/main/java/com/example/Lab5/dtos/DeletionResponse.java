package com.example.Lab5.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeletionResponse {
    private boolean success;
    private String message;
}
