package com.vanix.core.infra.database.mongodb.repositories;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.model.Filters;
import com.vanix.core.VanixCore;
import com.vanix.core.application.dtos.VanixMemberDto;
import com.vanix.core.application.entities.member.VanixMember;
import com.vanix.core.application.repositories.MemberRepository;
import com.vanix.core.application.repositories.mappers.impl.VanixMemberMapper;
import com.vanix.core.infra.database.mongodb.MongoConnection;
import com.vanix.core.infra.database.mongodb.mappers.ObjectMapper;
import com.vanix.core.infra.database.mongodb.mappers.impl.MemberMapper;
import org.bson.Document;

import java.util.UUID;

public class MemberRepositoryImpl implements MemberRepository {

    MongoCollection<Document> collection;
    ObjectMapper<VanixMemberDto> mapper;

    public MemberRepositoryImpl(MongoConnection connection) {
        this.collection = connection.getDb().getCollection("members");
        this.mapper = new MemberMapper();
    }

    @Override
    public VanixMember findById(UUID id) {

        Document document = this.collection.find(Filters.eq("id", id.toString())).first();

        if (document != null) {
            return VanixMember.fromDto(this.mapper.toClass(document));
        }

        return null;
    }

    @Override
    public VanixMember findByUsername(String username) {
        Document document = this.collection.find(Filters.eq("username", username)).first();

        if (document != null) {
            return VanixMember.fromDto(this.mapper.toClass(document));
        }

        return null;
    }

    @Override
    public void create(VanixMemberDto data) {
        try {
            this.collection.insertOne(this.mapper.toDocument(data));
        } catch (Exception exception) {
            VanixCore.getLogger().severe(exception.getMessage());
        }
    }

    @Override
    public void updateById(UUID id, VanixMemberDto data) {
        try {
            this.collection.findOneAndUpdate(Filters.eq("id", id.toString()), this.mapper.toDocument(data));
        } catch (Exception exception) {
            VanixCore.getLogger().severe(exception.getMessage());
        }
    }

    @Override
    public void destroy(UUID id) {
        try {
            this.collection.deleteOne(Filters.eq("id", id.toString()));
        } catch (Exception exception) {
            VanixCore.getLogger().severe(exception.getMessage());
        }
    }
}
