package com.kg.connections;

import com.mongodb.client.MongoDatabase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


/**
 * Created by kevingracie on 20/02/2016.
 *
 * Test creation of connection to MongoDB. Because connection factory returns singleton
 * I'm using reflection to ensure field is null at the start of the test to ensure
 * values set in a previous run do not affect test execution.
 */
public class MongoConnectionFactoryTest {

    @Test
    public void validObjectTest() throws Exception {
        Field dbField = MongoConnectionFactory.class.getDeclaredField("database");
        dbField.setAccessible(true);
        dbField.set(null, null);

        MongoDatabase database = MongoConnectionFactory.getDatabase(getTestProps("/valid-app.properties"));
        assertNotNull(database);
    }

    @Test
    public void nullDbTest() throws Exception {
        Field dbField = MongoConnectionFactory.class.getDeclaredField("database");
        dbField.setAccessible(true);
        dbField.set(null, null);

        MongoDatabase database = MongoConnectionFactory.getDatabase(getTestProps("/invalid-app.properties"));
        assertNull(database);
    }

    private Properties getTestProps(String propertiesName) {
        Properties props = new Properties();
        try {
            props.load(MongoConnectionFactoryTest.class.getResourceAsStream(propertiesName));
        } catch(IOException e) {

        }
        return props;
    }
}
