package org.example._5_nut_youlong_sr_spring_homework003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendeeUpdateRequest {

    @Schema(defaultValue = "Youlong Nut", description = "Full name of the attendee")
    @NotBlank(message = "Attendee name cannot be blank")
    @Size(min = 4, max = 50, message = "Attendee name must be between 4 and 50 characters")
    private String attendeeName;

}

