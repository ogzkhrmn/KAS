/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.controlles.impl;

import com.example.korvo.controlles.ConferenceController;
import com.example.korvo.entities.Conference;
import com.example.korvo.mappers.ConferenceMapper;
import com.example.korvo.models.requests.ConferenceCreateRequest;
import com.example.korvo.models.responses.ConferenceResponse;
import com.example.korvo.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConferenceControllerImpl implements ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ConferenceMapper conferenceMapper;

    @Override
    public ResponseEntity<ConferenceResponse> saveConference(@Valid ConferenceCreateRequest request) {
        Conference conference = conferenceService.saveConference(conferenceMapper.modelToEntity(request));
        return ResponseEntity.ok(conferenceMapper.entityToModel(conference));
    }

    @Override
    public ResponseEntity<List<ConferenceResponse>> getConferences() {
        return ResponseEntity.ok(conferenceMapper.entityListToModel(conferenceService.getAllConferences()));
    }

    @Override
    public ResponseEntity<ConferenceResponse> getConference(Long id) {
        Conference conference = conferenceService.findConferenceById(id);
        return ResponseEntity.ok(conferenceMapper.entityToModel(conference));
    }

}
