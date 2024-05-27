package com.matchmate.tagsmanagementservice.domain.models.tagrequest;

import com.matchmate.tagsmanagementservice.common.domain.Aggregate;
import com.matchmate.tagsmanagementservice.common.visitor.Visitable;
import com.matchmate.tagsmanagementservice.common.visitor.Visitor;
import lombok.Getter;

@Getter
public class RequestTag extends Aggregate<RequestTagId> implements Visitable<RequestTag> {
    private final String name;
    private Long requests;

    public RequestTag(String name) {
        super(new RequestTagId());

        this.assertArgumentNotBlank(name, "request tag name must not be blank.");
        this.name = name;
    }

    public RequestTag(String name, Long requests) {
        super(new RequestTagId());
        this.assertArgumentNotBlank(name, "request tag name must not be blank.");
        this.assertArgumentNotNull(requests, "requests must not be null.");
        this.name = name;
        this.requests = requests;
    }

    public void increment() {
        this.assertArgumentRange(this.requests,
                1,
                Long.MAX_VALUE,
                "expected at least 1 request");

        this.requests++;
    }

    public void firstRequest() {
        this.requests = 1L;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
