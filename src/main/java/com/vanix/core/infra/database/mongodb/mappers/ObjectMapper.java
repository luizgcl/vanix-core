package com.vanix.core.infra.database.mongodb.mappers;

import org.bson.Document;

public interface ObjectMapper<T> {

    T toClass(Document document);

    Document toDocument(T type);

}
