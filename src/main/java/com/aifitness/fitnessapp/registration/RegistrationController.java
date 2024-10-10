package com.aifitness.fitnessapp.registration;

import com.aifitness.fitnessapp.exceptions.TokenAlreadyConfirmedException;
import com.aifitness.fitnessapp.exceptions.TokenExpiredException;
import com.aifitness.fitnessapp.exceptions.TokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public ResponseEntity<ConfirmationResponse> confirm(@RequestParam("token") String token) {
        ConfirmationResponse response;
        try {
            registrationService.confirmToken(token);
            return ResponseEntity.ok(new ConfirmationResponse("success", "Your account has been confirmed."));
        } catch (TokenNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ConfirmationResponse("token_not_found", "Token not found."));
        } catch (TokenAlreadyConfirmedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ConfirmationResponse("already_confirmed", "Email has already been confirmed."));
        } catch (TokenExpiredException e) {
            return ResponseEntity.status(HttpStatus.GONE)
                    .body(new ConfirmationResponse("expired", "Token has expired."));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ConfirmationResponse("error", "An error occurred during registration confirmation."));
        }
    }
}
