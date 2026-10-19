package com.example.ryzhkov.service;

import com.example.ryzhkov.model.Response;
import com.example.ryzhkov.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@Qualifier("ModifySystemTimerResponseService")
public class ModifySystemTimerResponseService implements ModifyResponseService {
    @Override
    public Response modify(Response response) {
        response.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        return response;
    }
}