/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.responses;

import com.example.korvo.models.requests.SpeakerDetails;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ConferenceResponse {

    @Schema(description = "Conference id", example = "1")
    private Long id;

    @Schema(description = "Conference name", example = "Conference 1")
    private String name;

    @Schema(example = "2022-05-16", description = "Conference start date")
    private LocalDate startDate;

    @Schema(description = "Conference Address", example = "Istanbul, Turkey")
    private String address;

    @Schema(description = "Conference Details", example = "An example conference")
    private String description;

    @ArraySchema(schema = @Schema(description = "Conference speakers", implementation = SpeakerDetails.class))
    private List<SpeakerDetails> speakers;

    @ArraySchema(schema = @Schema(description = "Conference tickets", implementation = UserTicketDetails.class))
    private List<UserTicketDetails> tickets;

}
