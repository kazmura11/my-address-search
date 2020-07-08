package com.example.myAddressSearch.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class ErrorResponse {
    private String key;
    private String message;

    public ErrorResponse(String key, String message) {
        this.key = key;
        this.message = message;
    }
    public ResponseEntity<ErrorResponse> createResponse(HttpStatus status) {
        return new ResponseEntity<ErrorResponse>(this, status);
    }
}
