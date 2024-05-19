package com.leaseforlove.tagsmanagementservice;

import com.leaseforlove.tagsmanagementservice.domain.models.Tag;
import org.junit.jupiter.api.Test;

public class TagRegisterTest {

    @Test
    void teste() {
        Tag tag = new Tag();

        tag.register("Anime");
    }
}
