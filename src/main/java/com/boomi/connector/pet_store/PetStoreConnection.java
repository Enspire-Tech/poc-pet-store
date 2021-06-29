package com.boomi.connector.pet_store;

import java.util.logging.Logger;
import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.openapi.OpenAPIConnection;

public class PetStoreConnection extends OpenAPIConnection {

    Logger logger = Logger.getLogger(this.getClass().getName());

    public PetStoreConnection(BrowseContext context) {
        super(context);
    }

    @Override
    public String getSpec()
    {
        return "src/main/resources/boomi_flow/openapi.json";
    }

}
