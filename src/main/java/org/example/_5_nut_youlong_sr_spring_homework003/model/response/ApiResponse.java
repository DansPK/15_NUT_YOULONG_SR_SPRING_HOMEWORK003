package org.example._5_nut_youlong_sr_spring_homework003.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;


@Data
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"success", "message", "status", "payload", "timestamp"})
@Builder
public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private HttpStatus status;
    private T payload;
    private Instant timestamp;



}
