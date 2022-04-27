/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.services.impl;
import java.time.LocalDate;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Speaker;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.enums.TicketType;

import com.example.korvo.entities.Coupon;
import com.example.korvo.entities.Ticket;
import com.example.korvo.mappers.TicketMapper;
import com.example.korvo.models.requests.TicketBuyRequest;
import com.example.korvo.repositories.ConferenceDAO;
import com.example.korvo.repositories.CouponDAO;
import com.example.korvo.repositories.TicketDAO;
import com.example.korvo.repositories.UserTicketDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketDAO ticketDAO;

    @Mock
    private ConferenceDAO conferenceDAO;

    @Mock
    private UserTicketDAO userTicketDAO;

    @Mock
    private CouponDAO couponDAO;

    @InjectMocks
    private TicketServiceImpl ticketService;

    private TicketMapper ticketMapper = Mappers.getMapper(TicketMapper.class);

    @Test
    void getPrice() {
        Ticket ticket = getTicket();
        when(ticketDAO.findByEndDateGreaterThanEqualAndStartDateLessThanEqual(any(), any())).thenReturn(Optional.of(ticket));
        Ticket response = ticketService.getPrice(LocalDate.now());
        assertEquals(ticket.getTicketType(), response.getTicketType());
        assertEquals(ticket.getPrice(), response.getPrice());
        assertEquals(ticket.getId(), response.getId());
    }

    @Test
    void createTicket() {
        TicketBuyRequest ticketBuyRequest = new TicketBuyRequest();
        ticketBuyRequest.setName("Jeff");
        ticketBuyRequest.setConferenceId(1L);
        ticketBuyRequest.setDiscountCode("KORVO");
        Conference conference = getConferenceModel();
        UserTicket userTicket = getUserTicket();
        Ticket ticket = getTicket();
        ReflectionTestUtils.setField(ticketService, "ticketMapper", ticketMapper);
        when(couponDAO.findByNameAndEndDateGreaterThanEqual(any(), any())).thenReturn(Optional.of(getCoupon()));
        when(conferenceDAO.findById(1L)).thenReturn(Optional.of(conference));
        when(userTicketDAO.save(any())).thenReturn(userTicket);
        when(ticketDAO.findByEndDateGreaterThanEqualAndStartDateLessThanEqual(any(), any())).thenReturn(Optional.of(ticket));
        UserTicket response = ticketService.createTicket(ticketBuyRequest);
        assertEquals(ticket.getTicketType(), response.getTicketType());
        assertEquals(ticket.getPrice(), response.getPrice());
        assertEquals(ticket.getId(), response.getId());
    }

    @Test
    void getDiscount() {
        Coupon coupon = new Coupon();
        coupon.setDiscount(10);
        when(couponDAO.findByNameAndEndDateGreaterThanEqual(any(), any())).thenReturn(Optional.of(coupon));
        Coupon discount = ticketService.getDiscount("test");
        assertEquals(10, discount.getDiscount());
    }

    @Test
    void getDiscount_err() {
        when(couponDAO.findByNameAndEndDateGreaterThanEqual(any(), any())).thenReturn(Optional.empty());
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ticketService.getDiscount("test"));
        assertEquals("Coupon not found", exception.getMessage());
    }

    private Ticket getTicket(){
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setStartDate(LocalDate.now());
        ticket.setEndDate(LocalDate.now());
        ticket.setTicketType(TicketType.EARLY_BIRD);
        ticket.setPrice(100);
        return ticket;
    }

    private Conference getConferenceModel() {
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setName("test");
        conference.setDescription("test");
        conference.setStartDate(LocalDate.now());
        return conference;
    }

    private UserTicket getUserTicket() {
        UserTicket userTicket = new UserTicket();
        userTicket.setId(1L);
        userTicket.setTicketType(TicketType.EARLY_BIRD);
        userTicket.setName("Bezos");
        userTicket.setPrice(100);
        return userTicket;
    }

    private Coupon getCoupon() {
        Coupon coupon = new Coupon();
        coupon.setId(1L);
        coupon.setName("KORVOSTUDENT");
        coupon.setDiscount(10);
        coupon.setEndDate(LocalDate.now());
        return coupon;
    }

}