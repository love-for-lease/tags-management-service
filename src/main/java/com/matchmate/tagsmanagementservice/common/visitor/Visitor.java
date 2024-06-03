package com.matchmate.tagsmanagementservice.common.visitor;

public interface Visitor<T, R>  {
    R visit(final T element);
}
