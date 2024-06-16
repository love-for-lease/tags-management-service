package com.matchmate.tagsmanagementservice.common.mappers;

import com.matchmate.tagsmanagementservice.adapter.persistence.documents.AvailableTagDocument;
import com.matchmate.tagsmanagementservice.adapter.persistence.documents.RequestTagDocument;
import com.matchmate.tagsmanagementservice.application.web.dto.AvailableTagDto;
import com.matchmate.tagsmanagementservice.domain.models.TagAvailable;
import com.matchmate.tagsmanagementservice.domain.models.tag.Tag;

import java.util.List;

public class AvailableTagMapper {

    private AvailableTagMapper() {
    }

    public static AvailableTagDocument tagAvailableToDocument(TagAvailable tagAvailable) {
        return new AvailableTagDocument(tagAvailable.getName(), tagAvailable.getStatus());
    }

    public static TagAvailable documentToTagAvailable(AvailableTagDocument tagDocument) {
        return new TagAvailable(tagDocument.getName(), tagDocument.getStatus());
    }

    public static TagAvailable requestToTagAvailable(RequestTagDocument request) {
        return new TagAvailable(request.getName());
    }


    public static AvailableTagDocument fromDomain(Tag tag) {

        return new AvailableTagDocument(
                tag.getId().fromValue(),
                tag.getName(),
                tag.getStatus()
        );
    }

    public static List<AvailableTagDocument> fromDomain(List<Tag> tags) {
        return tags.stream().map(AvailableTagMapper::fromDomain).toList();
    }

    public static Tag fromDocument(AvailableTagDocument availableTagDocument) {
        return new Tag(availableTagDocument.getName(), availableTagDocument.getStatus());
    }

    public static AvailableTagDto toDto(AvailableTagDocument document) {
        AvailableTagDto availableTagDto = new AvailableTagDto();
        availableTagDto.setId(document.getId());
        availableTagDto.setName(document.getName());
        availableTagDto.createAt(document.getCreatedAt().toOffsetDateTime());
        availableTagDto.setTagStatus(AvailableTagDto.TagStatusEnum.valueOf(document.getStatus().toString()));

        return availableTagDto;
    }
}
