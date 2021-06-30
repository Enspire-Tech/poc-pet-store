package com.boomi.connector.petstore;

import com.boomi.connector.api.ConnectionTester;
import com.boomi.connector.openapi.OpenAPIBrowser;
import com.boomi.connector.openapi.OpenAPIConnection;

public class PetStoreBrowser extends OpenAPIBrowser implements ConnectionTester  {

    public PetStoreBrowser(OpenAPIConnection conn) {
        super(conn);
    }

    @Override
    public PetStoreConnection getConnection() {
        return (PetStoreConnection) super.getConnection();
    }

    public void testConnection() {
        super.getConnection().getObjectTypes();
    }

}
