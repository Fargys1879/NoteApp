package com.noteapp.controller.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Response {
    private HttpStatus status;
    private String message;
    private Object data;
}


