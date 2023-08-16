package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.impl.UserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserHandler userHandler;
    @Operation(summary = "Add Owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner  created", content = @Content),
    })

    @PostMapping("/Owner")
    public ResponseEntity<Void> saveOwner(@RequestBody UserRequestDto userRequestDto){
        userHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Add Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee  created", content = @Content),
    })

    @PostMapping("/Employee")
    public ResponseEntity<Void> saveEmployee(@RequestBody UserRequestDto userRequestDto){
        userHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Add Client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client  created", content = @Content),
    })

    @PostMapping("/Client")
    public ResponseEntity<Void> saveClient(@RequestBody UserRequestDto userRequestDto){
        userHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @Operation(summary = "Get a list of users")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {

        return ResponseEntity.ok(userHandler.getAllUsers());
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@Parameter(name = "Id tipo Long") @PathVariable Long id) {
         return ResponseEntity.ok(userHandler.findByID(id));

    }
    @Operation(summary = "Get user by email")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getByEmail(@Parameter(name = "email") @PathVariable String email) {
        return ResponseEntity.ok(userHandler.findOneByEmail(email));
    }

}
