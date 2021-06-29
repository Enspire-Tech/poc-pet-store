package com.boomi.connector.pet_store;

import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.api.Browser;
import com.boomi.connector.openapi.OpenAPIConnector;

public class PetStoreConnector extends OpenAPIConnector  {

    @Override
    public Browser createBrowser(BrowseContext context) {
        return new PetStoreBrowser(createConnection(context));
    }

    protected PetStoreConnection createConnection(BrowseContext context) {
        return new PetStoreConnection(context);
    }

}
