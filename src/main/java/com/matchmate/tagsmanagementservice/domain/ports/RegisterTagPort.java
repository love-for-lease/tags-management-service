package com.matchmate.tagsmanagementservice.domain.ports;

import java.util.List;

public interface RegisterTagPort {
    void register(List<String> name);
}
