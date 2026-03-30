package org.example._5_nut_youlong_sr_spring_homework003.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {
    @NotBlank
    @Schema(defaultValue = "Khmer New Year")
    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
    private String eventName;


    @NotNull(message = "Event date is required")
    @Schema(defaultValue = "2026-03-20")
    private Date eventDate;


    @NotNull(message = "Venue ID is required")
    @JsonProperty(required = true)
    private Integer venueId;

    @NotEmpty(message = "At least one attendee ID is required")
    @JsonProperty(required = true)
    private List<Integer> attendeeId;
}
