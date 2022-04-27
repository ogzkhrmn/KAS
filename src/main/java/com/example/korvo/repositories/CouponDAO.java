/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.repositories;

import com.example.korvo.entities.Coupon;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CouponDAO extends CrudRepository<Coupon, Long> {

    Optional<Coupon> findByNameAndEndDateGreaterThanEqual(String name, LocalDate endDate);

}
