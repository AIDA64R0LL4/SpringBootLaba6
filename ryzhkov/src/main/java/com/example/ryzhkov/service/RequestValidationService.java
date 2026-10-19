package com.example.ryzhkov.service;

import com.example.ryzhkov.exception.UnsupportedCodeException;
import com.example.ryzhkov.exception.ValidationFailedException;
import com.example.ryzhkov.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isingValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult.getFieldErrors().toString());
        }
    }

    @Override
    @SuppressWarnings("unused")
    public void checkUid(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            throw new UnsupportedCodeException("UID равен 123");
        }
    }
}