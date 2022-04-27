/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.internals;

import com.example.korvo.enums.TicketType;
import lombok.Data;

@Data
public class TicketRequest {

    private String name;
    private Long conferenceId;
    private String discountCode;
    private Integer discount;
    private Integer price;
    private TicketType ticketType;

}
