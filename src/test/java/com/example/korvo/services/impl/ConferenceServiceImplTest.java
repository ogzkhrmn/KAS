/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.services.impl;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Speaker;
import com.example.korvo.entities.Ticket;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.enums.TicketType;
import com.example.korvo.repositories.ConferenceDAO;
import com.example.korvo.repositories.SpeakerDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConferenceServiceImplTest {

    @Mock
    private ConferenceDAO conferenceDAO;

    @Mock
    private SpeakerDAO speakerDAO;

    @InjectMocks
    private ConferenceServiceImpl conferenceService;

    @Test
    void findConferenceById() {
        Conference conference = getConference();
        Speaker speaker = getSpeaker();
        UserTicket ticket = getTicket();
        when(conferenceDAO.findById(1L)).thenReturn(Optional.of(conference));
        Conference response = conferenceService.findConferenceById(1L);
        validate(response, conference, speaker, ticket);
    }

    @Test
    void saveConference() {
        Conference conference = getConference();
        Speaker speaker = getSpeaker();
        UserTicket ticket = getTicket();
        when(conferenceDAO.save(any())).thenReturn(conference);
        Conference response = conferenceService.saveConference(conference);
        validate(response, conference, speaker, ticket);
    }

    @Test
    void getAllConferences() {
        Conference conference = getConference();
        Speaker speaker = getSpeaker();
        UserTicket ticket = getTicket();
        when(conferenceDAO.findAllByStartDateGreaterThan(any())).thenReturn(Collections.singletonList(conference));
        List<Conference> response = conferenceService.getAllConferences();
        validate(response.get(0), conference, speaker, ticket);
    }

    private void validate(Conference response, Conference conference, Speaker speaker, UserTicket ticket) {
        Speaker responseSpeaker = response.getSpeakers().iterator().next();
        UserTicket responseTicket = response.getTickets().iterator().next();
        assertEquals(conference.getName(), response.getName());
        assertEquals(speaker.getName(), responseSpeaker.getName());
        assertEquals(speaker.getPhotoUrl(), responseSpeaker.getPhotoUrl());
        assertEquals(speaker.getTopics(), responseSpeaker.getTopics());
        assertEquals(ticket.getPrice(), responseTicket.getPrice());
        assertEquals(ticket.getName(), responseTicket.getName());
    }

    private Conference getConference() {
        Conference conference = new Conference();
        conference.setId(0L);
        conference.setName("");
        conference.setStartDate(LocalDate.now());
        conference.setAddress("");
        conference.setDescription("");
        conference.setSpeakers(Collections.singleton(getSpeaker()));
        conference.setTickets(Collections.singleton(getTicket()));
        return conference;
    }

    private Speaker getSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setId(0L);
        speaker.setConference(new Conference());
        speaker.setName("Jeff");
        speaker.setPhotoUrl("image.jpg");
        speaker.setTopics("Java");
        return speaker;
    }

    private UserTicket getTicket() {
        UserTicket userTicket = new UserTicket();
        userTicket.setId(0L);
        userTicket.setName("Java");
        userTicket.setPrice(100);
        userTicket.setTicketType(TicketType.EARLY_BIRD);
        return userTicket;
    }

}