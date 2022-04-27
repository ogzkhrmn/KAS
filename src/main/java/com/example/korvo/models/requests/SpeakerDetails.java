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
public class SpeakerDetails {


    @NotBlank
    @Schema(description = "SpeakerDetails Name", example = "Jeff", required = true)
    private String name;


    @NotBlank
    @Schema(description = "SpeakerDetails Photo Url", example = "https://ww.google.com", required = true)
    private String photoUrl;


    @NotBlank
    @Schema(description = "SpeakerDetails Topics", example = "Java, Spring", required = true)
    private String topics;

}
