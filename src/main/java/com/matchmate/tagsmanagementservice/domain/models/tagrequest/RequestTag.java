package com.matchmate.tagsmanagementservice.domain.models.tagrequest;

import com.matchmate.tagsmanagementservice.common.domain.Aggregate;
import com.matchmate.tagsmanagementservice.common.visitor.Visitable;
import com.matchmate.tagsmanagementservice.common.visitor.Visitor;
import lombok.Getter;

import java.util.UUID;

import static com.matchmate.tagsmanagementservice.common.AssertionConcern.assertArgumentNotBlank;
import static com.matchmate.tagsmanagementservice.common.AssertionConcern.assertArgumentNotNull;

@Getter
public class RequestTag extends Aggregate<RequestTagId> implements Visitable<RequestTag> {
    private final String name;
    private Long requests;

    public RequestTag(String name) {
        super(new RequestTagId());
        assertArgumentNotBlank(name, "request tag name must not be blank.");
        this.name = name;
    }

    public RequestTag(String name, Long requests) {
        super(new RequestTagId());
        assertArgumentNotBlank(name, "request tag name must not be blank.");
        assertArgumentNotNull(requests, "requests must not be null.");
        this.name = name;
        this.requests = requests;
    }

    public RequestTag(String id, String name, Long requests) {
        super(new RequestTagId(UUID.fromString(id)));
        assertArgumentNotBlank(name, "request tag name must not be blank.");
        assertArgumentNotNull(requests, "requests must not be null.");
        this.name = name;
        this.requests = requests;
    }

    public void increment() {
        this.requests++;
    }

    public void firstRequest() {
        this.requests = 0L;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
