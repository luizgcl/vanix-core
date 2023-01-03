package com.vanix.core.infra.database.mongodb;

import com.mongodb.MongoClientURI;
import com.vanix.core.infra.database.Credentials;
import com.vanix.core.infra.database.Database;
import lombok.Getter;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Getter
public class MongoConnection implements Database {

    private static final String PATTERN = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";

    private static final Pattern IP_PATTERN = Pattern
            .compile(PATTERN + "\\." + PATTERN + "\\." + PATTERN + "\\." + PATTERN);

    @Getter
    private com.mongodb.MongoClient client;
    @Getter
    private com.mongodb.client.MongoDatabase db;

    private final String url;

    private String dataBase;
    private int port;

    public MongoConnection(String url) {
        this.url = url;
    }

    public MongoConnection(String hostName, String userName, String passWord, String dataBase, int port) {
        this(IP_PATTERN.matcher(hostName).matches()
                ? "mongodb://" + (userName.isEmpty() ? "" : userName + ":" + passWord + "@") + hostName + "/" + dataBase
                + "?retryWrites=true&w=majority"
                : "mongodb+srv://" + (userName.isEmpty() ? "" : userName + ":" + passWord + "@") + hostName + "/"
                + dataBase + "?retryWrites=true&w=majority");
    }

    public MongoConnection(Credentials credentials) {
        this(credentials.getHostName(), credentials.getUserName(), credentials.getPassWord(), credentials.getDatabase(),
                credentials.getPort());
    }

    public MongoConnection(String hostName, String userName, String passWord, String dataBase) {
        this(hostName, userName, passWord, dataBase, 27017);
    }

    @Override
    public void connect() {
        System.out.println("[MongoDB] Iniciando conexão com o MongoDB...");
        MongoClientURI uri = new MongoClientURI(getUrl());
        this.dataBase = uri.getDatabase();

        client = new com.mongodb.MongoClient(new MongoClientURI(getUrl()));

        Logger.getLogger("uri").setLevel(Level.SEVERE);

        db = client.getDatabase(dataBase);
        System.out.println("[MongoDB] Conexão estabelecida com sucesso.");
    }

    public com.mongodb.client.MongoDatabase getDatabase(String database) {
        return client.getDatabase(database);
    }

    @Override
    public boolean isConnected() {
        return client != null;
    }

    @Override
    public void close() {
        client.close();
    }

}
