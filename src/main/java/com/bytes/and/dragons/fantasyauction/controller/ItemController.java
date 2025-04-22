package com.bytes.and.dragons.fantasyauction.controller;

import com.bytes.and.dragons.fantasyauction.annotation.CurrentUserId;
import com.bytes.and.dragons.fantasyauction.model.request.CreateItemRequest;
import com.bytes.and.dragons.fantasyauction.model.response.ItemResponse;
import com.bytes.and.dragons.fantasyauction.service.ItemService;
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
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<ItemResponse> getItems(@CurrentUserId Long userId,
                                                 @PageableDefault(size = 20, direction = Sort.Direction.DESC)
                                                 Pageable pageable) {
        var response = itemService.getItems(pageable, userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> createItem(@CurrentUserId Long userId,
                                           @Valid @RequestBody CreateItemRequest request) {
        itemService.createItem(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}