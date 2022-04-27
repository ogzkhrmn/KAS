/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.mappers;

import com.example.korvo.entities.UserTicket;
import com.example.korvo.models.internals.TicketRequest;
import com.example.korvo.models.responses.UserTicketDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    UserTicket mapTicketToEntity(TicketRequest request);

    UserTicketDetails ticketToModel(UserTicket model);


}
