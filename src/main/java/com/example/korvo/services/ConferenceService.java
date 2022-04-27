/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.services;


import com.example.korvo.entities.Conference;

import java.util.List;

public interface ConferenceService {
    Conference findConferenceById(Long id);

    Conference saveConference(Conference request);

    List<Conference> getAllConferences();

}
