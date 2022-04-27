/*
 * @author : Oguz Kahraman
 * @since : 26.04.2022
 *
 * Copyright - korvo
 **/
package com.example.korvo.controlles;

import com.example.korvo.models.requests.DiscountRequest;
import com.example.korvo.models.requests.TicketBuyRequest;
import com.example.korvo.models.responses.DiscountResponse;
import com.example.korvo.models.responses.UserTicketDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Ticket operations", description = "This endpoint performs ticket buy and discount operations")
public interface TicketController {

    @Operation(summary = "Apply Discount", description = "Apply discount to ticket", tags = {"Ticket operations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Discount added successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DiscountResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Discount Error")})
    @PostMapping(value = "/tickets/discount")
    ResponseEntity<DiscountResponse> applyDiscount(@RequestBody DiscountRequest request);

    @Operation(summary = "Buy Ticket", description = "Buy conference ticket and return details", tags = {"Ticket operations"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket bought successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserTicketDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Ticket Buy Error")})
    @PostMapping(value = "/tickets")
    ResponseEntity<UserTicketDetails> buyTicket(@RequestBody TicketBuyRequest request);

}
