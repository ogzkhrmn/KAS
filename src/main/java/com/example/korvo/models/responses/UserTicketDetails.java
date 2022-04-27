/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.responses;

import com.example.korvo.enums.TicketType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserTicketDetails {

    @Schema(description = "User Name", example = "Jeff")
    private String name;

    @Schema(description = "Ticket Price", example = "100")
    private Integer price;

    @Schema(description = "Ticket Type", example = "EARLY_BIRD")
    private TicketType ticketType;

}
