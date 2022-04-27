/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.services.impl;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Speaker;
import com.example.korvo.repositories.ConferenceDAO;
import com.example.korvo.repositories.SpeakerDAO;
import com.example.korvo.services.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    private ConferenceDAO conferenceDAO;

    @Autowired
    private SpeakerDAO speakerDAO;

    @Override
    public Conference findConferenceById(Long id) {
        return conferenceDAO.findById(id).orElseThrow(() -> new RuntimeException("Conference not found"));
    }

    @Override
    @Transactional
    public Conference saveConference(Conference request) {
        Conference conference = conferenceDAO.save(request);
        for (Speaker speaker : request.getSpeakers()) {
            speaker.setConference(conference);
        }
        speakerDAO.saveAll(request.getSpeakers());
        return conference;
    }

    @Override
    public List<Conference> getAllConferences() {
        return conferenceDAO.findAllByStartDateGreaterThan(LocalDate.now());
    }

}
