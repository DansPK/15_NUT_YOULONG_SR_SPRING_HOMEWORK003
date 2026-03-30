package org.example._5_nut_youlong_sr_spring_homework003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@Getter
@Setter
public class AttendeeRequest {

    @NotNull
    @Schema(defaultValue = "Youlong Nut")
    @NotBlank(message = "Attendee name cannot be blank")
    @Size(min = 3, max = 100, message = "Attendee name must be between 4 and 50 characters")
    private String attendeeName;


    @NotNull
    @NotBlank
    @Schema(defaultValue = "Exampleuser@gmail.com")
    @Email
    private String attendeeEmail;
}
