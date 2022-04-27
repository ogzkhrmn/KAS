/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.utils;

public class PriceUtil {

    public static Integer calculateDiscount(Integer discount, Integer price) {
        return price * (100 - discount) / 100;
    }

}
