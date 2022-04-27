/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.requests;

import com.example.korvo.enums.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
public class TicketDetails {

    @NotNull
    @Schema(description = "Ticket type", example = "EARLY_BIRD", required = true)
    private TicketType ticketType;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(example = "2022-05-16", description = "Ticket sell start date", required = true)
    private LocalDate startDate;

    @NotNull
    @Future
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Schema(example = "2022-05-17", description = "Ticket sell  end date", required = true)
    private LocalDate endDate;

    @Positive
    @NotNull
    @Schema(example = "100", description = "Ticket price", required = true)
    private Integer amount;


}
