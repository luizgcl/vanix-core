package com.vanix.core.infra.database;

public interface Database {

    void connect() throws Exception;

    boolean isConnected();

    void close();

}
