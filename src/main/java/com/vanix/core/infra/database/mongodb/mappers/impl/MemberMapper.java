package com.vanix.core.infra.database.mongodb.mappers.impl;

import com.vanix.core.VanixCore;
import com.vanix.core.application.dtos.VanixMemberDto;

import com.vanix.core.infra.database.mongodb.mappers.ObjectMapper;
import org.bson.Document;

public class MemberMapper implements ObjectMapper<VanixMemberDto> {

    @Override
    public VanixMemberDto toClass(Document document) {
        return VanixCore.getGson().fromJson(VanixCore.getGson().toJson(document), VanixMemberDto.class);
    }

    @Override
    public Document toDocument(VanixMemberDto member) {
        return Document.parse(VanixCore.getGson().toJson(member));
    }

}
