package com.matchmate.tagsmanagementservice.factories.tag;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.domain.enums.TagStatus;
import com.matchmate.tagsmanagementservice.domain.models.Tag;
import org.instancio.Instancio;
import org.instancio.Model;
import static org.instancio.Select.field;

import java.time.OffsetDateTime;

public class AvailableTagDocumentFactory {
    private static final Model<AvailableTagDocument> AVAILABLE_TAG_DOCUMENT_MODEL =
            Instancio.of(AvailableTagDocument.class)
                    .generate(field(AvailableTagDocument::getStatus), gen -> gen.enumOf(TagStatus.class))
                    .set(field(AvailableTagDocument::getCreatedAt), OffsetDateTime.now())
                    .toModel();

    public static AvailableTagDocument validAvailableTagDocumentWithName(String name) {
        return Instancio.of(AVAILABLE_TAG_DOCUMENT_MODEL)
                .set(field(AvailableTagDocument::getName), name)
                .create();
    }
}
