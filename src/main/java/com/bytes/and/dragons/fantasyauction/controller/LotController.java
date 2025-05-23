package com.bytes.and.dragons.fantasyauction.controller;

import com.bytes.and.dragons.fantasyauction.annotation.CurrentUserId;
import com.bytes.and.dragons.fantasyauction.model.request.CreateLotRequest;
import com.bytes.and.dragons.fantasyauction.model.response.LotResponse;
import com.bytes.and.dragons.fantasyauction.service.LotService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LotResponse> getLots(
            @PageableDefault(size = 20, sort = "expiresAt", direction = Sort.Direction.DESC) Pageable pageable) {
        var response = lotService.getLots(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createLot(@CurrentUserId Long userId,
                                          @Valid @RequestBody CreateLotRequest request) {
        lotService.createLot(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}