package com.matchmate.tagsmanagementservice.adapter.queues.messages;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestTagMessage {
    private String name;
}
