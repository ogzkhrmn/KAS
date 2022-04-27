/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.repositories;

import com.example.korvo.entities.UserTicket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserTicketDAO extends CrudRepository<UserTicket, Long> {

    List<UserTicket> findByConference_Id(Long conferenceId);

}
