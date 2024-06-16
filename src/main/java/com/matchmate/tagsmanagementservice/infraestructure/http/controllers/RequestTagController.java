package com.matchmate.tagsmanagementservice.infraestructure.http.controllers;

import com.matchmate.tagsmanagementservice.application.services.RequestTagService;
import com.matchmate.tagsmanagementservice.application.web.dto.RequestTagDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/request-tags")
public class RequestTagController {

    private final RequestTagService requestTagService;

    @GetMapping
    public ResponseEntity<Page<RequestTagDto>> findRequestTags(
            @PageableDefault(page = 0, size = 1, sort = "requests", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.status(HttpStatus.OK).body(requestTagService.findAll(pageable));
    }

}
