package com.dubrouskaya.jmpservicerest.controller;

import com.dubrouskaya.jmpdto.dto.request.UserRequestDto;
import com.dubrouskaya.jmpdto.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;

@Tag(name = "User")
public interface UserController {
    @Operation(summary = "Create new user", responses = {
            @ApiResponse(responseCode = "200", description = "Created - User was successfully created"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    UserResponseDto createUser(UserRequestDto userRequestDto);

    @Operation(summary = "Update user by id", responses = {
            @ApiResponse(responseCode = "200", description = "Updated - User was successfully updated"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - User was not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    UserResponseDto updateUser(UserRequestDto userRequestDto);

    @Operation(summary = "Delete user by id", responses = {
            @ApiResponse(
                    responseCode = "200",
                    description = "No Content - User was successfully deleted",
                    content = {@Content(schema = @Schema())}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - User was not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    void deleteUser(Long id);

    @Operation(summary = "Retrieve user by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success - User was found"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request - The request is malformed or missing required parameters",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found - User was not found",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    UserResponseDto getUser(Long id);

    @Operation(summary = "Retrieve all users", responses = {
            @ApiResponse(responseCode = "200", description = "Success - Users were found"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error - An error occurred while processing the request",
                    content = {@Content(schema = @Schema())})})
    CollectionModel<UserResponseDto> getAllUsers();
}
