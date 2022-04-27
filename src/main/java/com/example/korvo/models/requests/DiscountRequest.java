/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DiscountRequest {

    @NotBlank
    @Schema(description = "Discount Code", example = "KORVOPREMIUM")
    private String discountCode;

}
