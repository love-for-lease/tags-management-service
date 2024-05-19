package com.leaseforlove.tagsmanagementservice.common.serializer;

import java.lang.reflect.Type;

public class ObjectSerializer extends AbstractSerializer {

    private static ObjectSerializer eventSerializer;

    public static synchronized ObjectSerializer instance() {
        if (ObjectSerializer.eventSerializer == null) {
            ObjectSerializer.eventSerializer = new ObjectSerializer();
        }

        return ObjectSerializer.eventSerializer;
    }

    public ObjectSerializer(boolean isCompact) {
        this(false, isCompact);
    }

    public ObjectSerializer(boolean isPretty, boolean isCompact) {
        super(isPretty, isCompact);
    }

    public <T> T deserialize(String aSerialization, final Class<T> aType) {

        return this.gson().fromJson(aSerialization, aType);
    }

    public <T> T deserialize(String aSerialization, final Type aType) {

        return this.gson().fromJson(aSerialization, aType);
    }

    public String serialize(Object anObject) {

        return this.gson().toJson(anObject);
    }

    private ObjectSerializer() {
        this(false, false);
    }
}