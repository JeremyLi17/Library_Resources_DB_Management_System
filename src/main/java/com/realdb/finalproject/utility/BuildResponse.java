package com.realdb.finalproject.utility;

import com.realdb.finalproject.domain.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author jeremy on 2022/12/11
 */
public class BuildResponse {
    public static ResponseEntity<HttpResponse> build(HttpStatus status, String message) {
        return new ResponseEntity<>(new HttpResponse(status.value(), status,
                status.getReasonPhrase().toUpperCase(), message.toUpperCase()), status);
    }
}
