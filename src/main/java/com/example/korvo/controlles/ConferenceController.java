/*
 * @author : Oguz Kahraman
 * @since : 27.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.controlles;

import com.example.korvo.models.requests.ConferenceCreateRequest;
import com.example.korvo.models.responses.ConferenceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Conference operations", description = "This endpoint performs conference save and details operations")
public interface ConferenceController {

    @Operation(summary = "Add Conference", description = "Add new conference", tags = {"Conference operations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added conference",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConferenceResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Conference Adding Error")})
    @PostMapping(value = "/conferences")
    ResponseEntity<ConferenceResponse> saveConference(@RequestBody ConferenceCreateRequest request);

    @Operation(summary = "Get Conferences", description = "Get all conference details", tags = {"Conference operations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returned conferences",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ConferenceResponse.class)))}),
            @ApiResponse(responseCode = "400", description = "Conference Get Error")})
    @GetMapping(value = "/conferences")
    ResponseEntity<List<ConferenceResponse>> getConferences();

    @Operation(summary = "Get Conference Details", description = "Get conference details", tags = {"Conference operations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conference Details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConferenceResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Conference Get Error")})
    @GetMapping(value = "/conferences/{id}")
    ResponseEntity<ConferenceResponse> getConference(@PathVariable("id") Long id);

}
