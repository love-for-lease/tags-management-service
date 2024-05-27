package com.matchmate.tagsmanagementservice.domain.ports;

import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;

public interface ReceiveRequestTagPort {
    RequestTag save(RequestTag requestTag);
}
