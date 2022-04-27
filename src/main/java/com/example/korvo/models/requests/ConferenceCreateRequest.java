/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.requests;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class ConferenceCreateRequest {

    @NotBlank
    @Schema(description = "Conference name", example = "Conference 1", required = true)
    private String name;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(example = "2022-08-16", description = "Conference start date", required = true)
    private LocalDate startDate;

    @NotBlank
    @Schema(description = "Conference Address", example = "Istanbul, Turkey", required = true)
    private String address;

    @NotBlank
    @Schema(description = "Conference Details", example = "An example conference", required = true)
    private String description;

    @NotEmpty
    @ArraySchema(minItems = 1, schema = @Schema(description = "Conference speakers", implementation = SpeakerDetails.class, required = true))
    private List<@Valid SpeakerDetails> speakers;

}
