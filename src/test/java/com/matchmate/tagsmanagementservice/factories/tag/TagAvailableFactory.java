package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import org.instancio.Instancio;
import org.instancio.Model;

import java.util.List;

import static org.instancio.Select.field;

public class TagAvailableFactory {
    private static final Model<TagAvailable> TAG_AVAILABLE_MODEL =
            Instancio.of(TagAvailable.class)
                    .generate(field(TagAvailable::getStatus), gen -> gen.enumOf(TagStatus.class))
                    .toModel();

    public static TagAvailable withValidName(String name) {
        return Instancio.of(TAG_AVAILABLE_MODEL)
                .set(field(TagAvailable::getName), name)
                .create();
    }

    public static List<TagAvailable> withSize(int size) {
        return Instancio.ofList(TAG_AVAILABLE_MODEL)
                .size(size)
                .create();
    }
}
