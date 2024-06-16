package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.domain.enums.TagStatusEnum;
import org.instancio.Instancio;
import org.instancio.Model;

import java.time.ZonedDateTime;
import java.util.List;

import static org.instancio.Select.field;

public class AvailableTagDocumentFactory {
    private static final Model<AvailableTagDocument> AVAILABLE_TAG_DOCUMENT_MODEL =
            Instancio.of(AvailableTagDocument.class)
                    .generate(field(AvailableTagDocument::getStatus), gen -> gen.enumOf(TagStatusEnum.class))
                    .set(field(AvailableTagDocument::getCreatedAt), ZonedDateTime.now())
                    .toModel();

    public static AvailableTagDocument validWithName(String name) {
        return Instancio.of(AVAILABLE_TAG_DOCUMENT_MODEL)
                .set(field(AvailableTagDocument::getName), name)
                .create();
    }

    public static List<AvailableTagDocument> withSize(int size) {
        return Instancio.ofList(AVAILABLE_TAG_DOCUMENT_MODEL)
                .size(size)
                .create();
    }
}
