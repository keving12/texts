package com.kg.connections;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by kevingracie on 31/01/2016.
 */
public class MongoConnectionFactory {

    private static MongoDatabase database;
    private static Logger logger = LogManager.getLogger(MongoConnectionFactory.class);

    private MongoConnectionFactory() {

    }

    public static MongoDatabase getDatabase(Properties props) {
        if(database == null) {
            String dbName = props.getProperty("database.name");
            if(dbName != null) {
                MongoClient client = new MongoClient();
                database = client.getDatabase(dbName);
            }
            else {
                logger.error("Unable to determine name of database to connect to");
            }

        }
        return database;
    }
}
