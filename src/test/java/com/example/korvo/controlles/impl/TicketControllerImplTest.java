/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.controlles.impl;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Coupon;
import com.example.korvo.entities.Ticket;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.enums.TicketType;
import com.example.korvo.mappers.TicketMapper;
import com.example.korvo.models.requests.DiscountRequest;
import com.example.korvo.models.requests.TicketBuyRequest;
import com.example.korvo.models.responses.DiscountResponse;
import com.example.korvo.models.responses.UserTicketDetails;
import com.example.korvo.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketControllerImplTest {

    @Mock
    private TicketService ticketService;

    private final TicketMapper mapper = Mappers.getMapper(TicketMapper.class);

    @InjectMocks
    private TicketControllerImpl ticketController;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(ticketController, "ticketMapper", mapper);

    }

    @Test
    void applyDiscount() {
        DiscountRequest request = new DiscountRequest();
        request.setDiscountCode("KORVOSTUDENT");
        Coupon coupon = getCoupon();
        Ticket ticket = getTicket();
        when(ticketService.getPrice(any())).thenReturn(ticket);
        when(ticketService.getDiscount("KORVOSTUDENT")).thenReturn(coupon);
        ResponseEntity<DiscountResponse> response = ticketController.applyDiscount(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(450, response.getBody().getPrice());
        assertEquals("KORVOSTUDENT", response.getBody().getDiscountCode());
        assertEquals(10, response.getBody().getDiscount());
    }

    @Test
    void buyTicket_withDiscount() {
        TicketBuyRequest request = new TicketBuyRequest();
        request.setName("Jeff");
        request.setConferenceId(1L);
        request.setDiscountCode("KORVOSTUDENT");
        UserTicket ticket = getUserTicket();
        when(ticketService.createTicket(any())).thenReturn(ticket);
        ResponseEntity<UserTicketDetails> response = ticketController.buyTicket(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(450, response.getBody().getPrice());
        assertEquals(request.getName(), response.getBody().getName());
        assertEquals(TicketType.EARLY_BIRD, response.getBody().getTicketType());
    }

    private Coupon getCoupon() {
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setName("KORVOSTUDENT");
        coupon.setDiscount(10);
        coupon.setEndDate(LocalDate.now());
        return coupon;
    }

    private Ticket getTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(0L);
        ticket.setStartDate(LocalDate.now());
        ticket.setEndDate(LocalDate.now());
        ticket.setTicketType(TicketType.EARLY_BIRD);
        ticket.setPrice(500);
        return ticket;
    }

    private UserTicket getUserTicket() {
        UserTicket userTicket = new UserTicket();
        userTicket.setId(1L);
        userTicket.setName("Jeff");
        userTicket.setConference(new Conference());
        userTicket.setDiscountCode("KORVOSTUDENT");
        userTicket.setDiscount(10);
        userTicket.setPrice(450);
        userTicket.setTicketType(TicketType.EARLY_BIRD);
        return userTicket;
    }

}