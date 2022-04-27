/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.services.impl;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Coupon;
import com.example.korvo.entities.Ticket;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.mappers.TicketMapper;
import com.example.korvo.models.internals.TicketRequest;
import com.example.korvo.models.requests.TicketBuyRequest;
import com.example.korvo.repositories.ConferenceDAO;
import com.example.korvo.repositories.CouponDAO;
import com.example.korvo.repositories.TicketDAO;
import com.example.korvo.repositories.UserTicketDAO;
import com.example.korvo.services.TicketService;
import com.example.korvo.utils.PriceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private ConferenceDAO conferenceDAO;

    @Autowired
    private UserTicketDAO userTicketDAO;

    @Autowired
    private CouponDAO couponDAO;

    @Autowired
    private TicketMapper ticketMapper;

    @Override
    public Ticket getPrice(LocalDate date) {
        return ticketDAO.findByEndDateGreaterThanEqualAndStartDateLessThanEqual(date, date)
                .orElseThrow(() -> new RuntimeException("No ticket found for the given date"));
    }

    @Override
    public UserTicket createTicket(TicketBuyRequest request) {
        Conference conference = conferenceDAO.findById(request.getConferenceId())
                .orElseThrow(() -> new RuntimeException("No conference found for the given id"));
        UserTicket userTicket = ticketMapper.mapTicketToEntity(createRequest(getPrice(LocalDate.now()), request));
        userTicket.setConference(conference);
        userTicketDAO.findAll();
        return userTicketDAO.save(userTicket);
    }

    @Override
    public Coupon getDiscount(String couponCode) {
        return couponDAO.findByNameAndEndDateGreaterThanEqual(couponCode, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

    }


    private TicketRequest createRequest(Ticket ticket, TicketBuyRequest buyRequest) {
        TicketRequest request = new TicketRequest();
        if (buyRequest.getDiscountCode() != null) {
            Coupon discount = getDiscount(buyRequest.getDiscountCode());
            Integer price = PriceUtil.calculateDiscount(discount.getDiscount(), ticket.getPrice());
            request.setDiscount(discount.getDiscount());
            request.setDiscountCode(discount.getName());
            request.setPrice(price);
        } else {
            request.setPrice(ticket.getPrice());
        }
        request.setConferenceId(buyRequest.getConferenceId());
        request.setName(buyRequest.getName());
        request.setTicketType(ticket.getTicketType());
        return request;
    }

}
