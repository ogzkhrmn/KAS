/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TicketBuyRequest {

    @NotBlank
    @Schema(description = "Name of customer", example = "Jeff", required = true)
    private String name;

    @NotNull
    @Schema(description = "Id of conference for ticket", example = "1", required = true)
    private Long conferenceId;

    @Schema(description = "Discount Code", example = "KORVOPREMIUM")
    private String discountCode;

}
