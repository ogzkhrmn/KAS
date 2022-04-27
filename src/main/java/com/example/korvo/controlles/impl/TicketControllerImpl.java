/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.controlles.impl;

import com.example.korvo.controlles.TicketController;
import com.example.korvo.entities.Coupon;
import com.example.korvo.entities.Ticket;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.mappers.TicketMapper;
import com.example.korvo.models.internals.TicketRequest;
import com.example.korvo.models.requests.DiscountRequest;
import com.example.korvo.models.requests.TicketBuyRequest;
import com.example.korvo.models.responses.DiscountResponse;
import com.example.korvo.models.responses.UserTicketDetails;
import com.example.korvo.services.TicketService;
import com.example.korvo.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
public class TicketControllerImpl implements TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public ResponseEntity<DiscountResponse> applyDiscount(@Valid DiscountRequest request) {
        Coupon discount = ticketService.getDiscount(request.getDiscountCode());
        Ticket ticket = ticketService.getPrice(LocalDate.now());
        return ResponseEntity.ok(new DiscountResponse(request.getDiscountCode(), discount.getDiscount()
                , PriceUtil.calculateDiscount(discount.getDiscount(), ticket.getPrice())));
    }

    @Override
    public ResponseEntity<UserTicketDetails> buyTicket(@Valid TicketBuyRequest request) {
        UserTicket userTicket = ticketService.createTicket(request);
        return ResponseEntity.ok(ticketMapper.ticketToModel(userTicket));
    }

}
