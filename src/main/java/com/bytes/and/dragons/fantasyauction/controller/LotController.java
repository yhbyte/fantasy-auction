package com.bytes.and.dragons.fantasyauction.controller;

import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.service.LotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lots")
@RequiredArgsConstructor
public class LotController {

    private final LotService lotService;

    @GetMapping
    public ResponseEntity<LotResponse> getLots() {
        var response = lotService.getLots();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createLot(@AuthenticationPrincipal Jwt jwt,
                                          @Valid @RequestBody CreateLotRequest request) {
        Long userId = jwt.getClaim("userId");
        lotService.createLot(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}