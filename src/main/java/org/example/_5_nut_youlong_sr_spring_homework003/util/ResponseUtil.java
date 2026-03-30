package org.example._5_nut_youlong_sr_spring_homework003.util;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;

import java.time.Instant;


@Builder
public class ResponseUtil {

    public static <T>ApiResponse<T> success(String message, T payload, HttpStatus status){

        return ApiResponse.<T>builder().success(true).message(message).status(status).payload(payload).timestamp(Instant.now()).build();
    }


    public static <T>ApiResponse<T> successNoPayload(String message, HttpStatus status){

        return ApiResponse.<T>builder().success(true).message(message).status(status).timestamp(Instant.now()).build();
    }


    public static <T>ApiResponse<T> fail(String message,HttpStatus status){
        return ApiResponse.<T>builder().success(false).message(message).status(status).timestamp(Instant.now()).build();
    }



}
