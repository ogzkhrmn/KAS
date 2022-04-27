/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.models.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiscountResponse {

    @Schema(description = "Discount Code", example = "KORVOSTUDENT")
    private String discountCode;
    @Schema(description = "Discount", example = "25")
    private Integer discount;
    @Schema(description = "New Price", example = "75")
    private Integer price;

}
