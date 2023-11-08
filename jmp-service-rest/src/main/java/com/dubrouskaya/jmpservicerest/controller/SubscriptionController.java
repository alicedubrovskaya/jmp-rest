package com.dubrouskaya.jmpservicerest.controller;

import com.dubrouskaya.jmpdto.dto.request.SubscriptionRequestDto;
import com.dubrouskaya.jmpdto.dto.response.SubscriptionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "Subscription")
public interface SubscriptionController {
    @Operation(summary = "Create new subscription", responses = {
            @ApiResponse(responseCode = "200", description = "Created - Subscription was successfully created"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    SubscriptionResponseDto createSubscription(SubscriptionRequestDto subscriptionRequestDto);

    @Operation(summary = "Update subscription", responses = {
            @ApiResponse(responseCode = "200", description = "Updated - Subscription was successfully updated"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - Subscription was not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto subscriptionRequestDto);

    @Operation(summary = "Delete subscription by id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "No Content - Subscription was successfully deleted",
                    content = {@Content(schema = @Schema())}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - Subscription was not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    void deleteSubscription(Long id);

    @Operation(summary = "Retrieve subscription by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success - Subscription was found"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - Subscription was not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    SubscriptionResponseDto getSubscription(Long id);

    @Operation(summary = "Retrieve all subscriptions", responses = {
            @ApiResponse(responseCode = "200", description = "Success - Subscriptions were found"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    CollectionModel<SubscriptionResponseDto> getAllSubscriptions();
}
