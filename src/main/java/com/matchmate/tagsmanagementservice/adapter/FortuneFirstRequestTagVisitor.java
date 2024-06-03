package com.matchmate.tagsmanagementservice.adapter;

import com.matchmate.tagsmanagementservice.common.visitor.Visitor;
import com.matchmate.tagsmanagementservice.domain.models.tagrequest.RequestTag;
import org.springframework.stereotype.Component;

@Component
public class FortuneFirstRequestTagVisitor implements Visitor<RequestTag, RequestTag> {
    @Override
    public RequestTag visit(RequestTag requestTag) {
        requestTag.firstRequest();
        return requestTag;
    }
}
