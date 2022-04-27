/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.controlles.impl;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Speaker;
import com.example.korvo.entities.UserTicket;
import com.example.korvo.enums.TicketType;
import com.example.korvo.mappers.ConferenceMapper;
import com.example.korvo.models.requests.ConferenceCreateRequest;
import com.example.korvo.models.requests.SpeakerDetails;
import com.example.korvo.models.responses.ConferenceResponse;
import com.example.korvo.services.ConferenceService;
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
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConferenceControllerImplTest {

    private final ConferenceMapper mapper = Mappers.getMapper(ConferenceMapper.class);

    @Mock
    private ConferenceService conferenceService;

    @InjectMocks
    private ConferenceControllerImpl conferenceController;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(conferenceController, "conferenceMapper", mapper);
    }

    @Test
    void saveConference() {
        ConferenceCreateRequest request = new ConferenceCreateRequest();
        request.setSpeakers(Collections.singletonList(new SpeakerDetails()));
        Conference model = getConferenceModel();
        when(conferenceService.saveConference(any())).thenReturn(model);
        ResponseEntity<ConferenceResponse> responseEntity = conferenceController.saveConference(request);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(model.getId(), responseEntity.getBody().getId());
        assertEquals(model.getName(), responseEntity.getBody().getName());
        assertEquals(model.getDescription(), responseEntity.getBody().getDescription());
        assertEquals(model.getStartDate(), responseEntity.getBody().getStartDate());
        assertEquals(model.getSpeakers().iterator().next().getName(), responseEntity.getBody().getSpeakers().get(0).getName());
        assertEquals(model.getTickets().iterator().next().getName(), responseEntity.getBody().getTickets().get(0).getName());
    }

    @Test
    void getConferences() {
        Conference model = getConferenceModel();
        when(conferenceService.getAllConferences()).thenReturn(Collections.singletonList(model));
        ResponseEntity<List<ConferenceResponse>> responseEntity = conferenceController.getConferences();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(model.getId(), responseEntity.getBody().get(0).getId());
        assertEquals(model.getName(), responseEntity.getBody().get(0).getName());
        assertEquals(model.getDescription(), responseEntity.getBody().get(0).getDescription());
        assertEquals(model.getStartDate(), responseEntity.getBody().get(0).getStartDate());
        assertEquals(model.getSpeakers().iterator().next().getName(), responseEntity.getBody().get(0).getSpeakers().get(0).getName());
        assertEquals(model.getTickets().iterator().next().getName(), responseEntity.getBody().get(0).getTickets().get(0).getName());
    }

    @Test
    void getConference() {
        Conference model = getConferenceModel();
        when(conferenceService.findConferenceById(1L)).thenReturn(model);
        ResponseEntity<ConferenceResponse> responseEntity = conferenceController.getConference(1L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(model.getId(), responseEntity.getBody().getId());
        assertEquals(model.getName(), responseEntity.getBody().getName());
        assertEquals(model.getDescription(), responseEntity.getBody().getDescription());
        assertEquals(model.getStartDate(), responseEntity.getBody().getStartDate());
        assertEquals(model.getSpeakers().iterator().next().getName(), responseEntity.getBody().getSpeakers().get(0).getName());
        assertEquals(model.getTickets().iterator().next().getName(), responseEntity.getBody().getTickets().get(0).getName());
    }

    private Conference getConferenceModel() {
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setName("test");
        conference.setDescription("test");
        conference.setStartDate(LocalDate.now());
        conference.getSpeakers().add(getSpeaker());
        conference.getTickets().add(getUserTicket());
        return conference;
    }

    private Speaker getSpeaker() {
        Speaker speaker = new Speaker();
        speaker.setName("test");
        speaker.setPhotoUrl("photoUrl");
        speaker.setTopics("Java");
        return speaker;
    }

    private UserTicket getUserTicket() {
        UserTicket userTicket = new UserTicket();
        userTicket.setId(1L);
        userTicket.setTicketType(TicketType.LAGGARD);
        userTicket.setName("Bezos");
        userTicket.setPrice(100);
        return userTicket;
    }

}