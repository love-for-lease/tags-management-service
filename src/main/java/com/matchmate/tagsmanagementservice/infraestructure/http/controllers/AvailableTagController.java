package com.matchmate.tagsmanagementservice.infraestructure.http.controllers;

import com.matchmate.tagsmanagementservice.application.services.AvailableTagService;
import com.matchmate.tagsmanagementservice.application.web.dto.AvailableTagDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/available-tags")
public class AvailableTagController {

    private final AvailableTagService availableTagService;

    @GetMapping
    public ResponseEntity<Page<AvailableTagDto>> findAvailableTags(
            @PageableDefault(page = 0, size = 1, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(availableTagService.findAll(pageable));
    }
}
