package com.matchmate.tagsmanagementservice.adapter.queues.messages;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public record RequestTagMessage(
        @NotBlank
        String name
) {
}
