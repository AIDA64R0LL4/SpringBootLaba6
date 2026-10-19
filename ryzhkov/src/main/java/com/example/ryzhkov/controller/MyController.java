package com.example.ryzhkov.controller;

import com.example.ryzhkov.exception.UnsupportedCodeException;
import com.example.ryzhkov.exception.ValidationFailedException;
import com.example.ryzhkov.model.*;
import com.example.ryzhkov.service.AnnualBonusService;
import com.example.ryzhkov.service.ModifyRequestService;
import com.example.ryzhkov.service.ModifyResponseService;
import com.example.ryzhkov.service.ValidationService;
import com.example.ryzhkov.util.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimerResponseService") ModifyResponseService modifyResponseService,
                        ModifyRequestService modifyRequestService,
                        AnnualBonusService annualBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {
        log.info("request: {}", request);

        Response response = createBaseResponse(request);

        try {
            validateRequest(request, bindingResult);
        } catch (ValidationFailedException e) {
            return buildErrorResponse(response, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            return buildErrorResponse(response, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return buildErrorResponse(response, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifyRequestService.modify(request);
        modifyResponseService.modify(response);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Response createBaseResponse(Request request) {
        return Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
    }

    private void validateRequest(Request request, BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException {
        validationService.isingValid(bindingResult);
        validationService.checkUid(request);
    }

    private ResponseEntity<Response> buildErrorResponse(Response response, ErrorCodes errorCode, ErrorMessages errorMessage, HttpStatus status) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);
        return new ResponseEntity<>(response, status);
    }
}