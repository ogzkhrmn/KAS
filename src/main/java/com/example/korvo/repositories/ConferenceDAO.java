
/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.repositories;

import com.example.korvo.entities.Conference;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceDAO extends CrudRepository<Conference, Long> {

    List<Conference> findAllByStartDateGreaterThan(LocalDate date);

}
