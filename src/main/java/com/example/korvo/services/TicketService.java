/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.services;

import com.example.korvo.entities.Coupon;
import com.example.korvo.entities.Ticket;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.models.internals.TicketRequest;
import com.example.korvo.models.requests.TicketBuyRequest;

import java.time.LocalDate;

public interface TicketService {
    Ticket getPrice(LocalDate date);

    UserTicket createTicket(TicketBuyRequest request);

    Coupon getDiscount(String couponCode);
}
