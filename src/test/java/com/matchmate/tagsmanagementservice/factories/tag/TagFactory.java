package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.domain.models.tag.Tag;
import org.instancio.Instancio;
import org.instancio.Model;
import static org.instancio.Select.field;

public class TagFactory {

    private static final Model<Tag> TAG_MODEL = Instancio.of(Tag.class)
            .toModel();

    public static Tag withName(String name) {
        return Instancio.of(TAG_MODEL)
                .set(field(Tag::getName), name)
                .create();
    }
}
