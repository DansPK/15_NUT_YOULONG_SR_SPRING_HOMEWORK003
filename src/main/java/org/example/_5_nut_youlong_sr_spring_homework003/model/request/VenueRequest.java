package org.example._5_nut_youlong_sr_spring_homework003.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class VenueRequest {

    @NotBlank
    @NotNull
    @Schema(defaultValue = "KSHRD")
    @Size(min = 3, max = 150, message = "Name must be between 3 and 150 characters")
    private String venueName;

    @NotBlank
    @NotNull
    @Schema(defaultValue = "Toul Kork")
    @Size(min = 3, max = 150, message = "Location must be between 3 and 150 characters")
    private String location;
}
