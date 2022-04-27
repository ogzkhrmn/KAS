
/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.repositories;

import com.example.korvo.entities.Conference;
import com.example.korvo.entities.Speaker;
import org.springframework.data.repository.CrudRepository;

public interface SpeakerDAO extends CrudRepository<Speaker, Long> {

}
