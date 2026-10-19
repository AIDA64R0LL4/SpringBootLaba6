package com.example.ryzhkov.service;

import com.example.ryzhkov.model.Request;
import com.example.ryzhkov.exception.UnsupportedCodeException;
import com.example.ryzhkov.exception.ValidationFailedException;
import org.springframework.validation.BindingResult;

public interface ValidationService {
    void isingValid(BindingResult bindingResult) throws ValidationFailedException;
    void checkUid(Request request) throws UnsupportedCodeException;
}
