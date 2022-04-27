/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.mappers;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Speaker;
import com.example.korvo.models.requests.ConferenceCreateRequest;
import com.example.korvo.models.requests.SpeakerDetails;
import com.example.korvo.models.responses.ConferenceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConferenceMapper {

    Conference modelToEntity(ConferenceCreateRequest model);

    Speaker speakerModelToEntity(SpeakerDetails model);

    ConferenceResponse entityToModel(Conference model);

    SpeakerDetails speakerEntityToModel(Speaker model);

    List<ConferenceResponse> entityListToModel(List<Conference> model);

}
