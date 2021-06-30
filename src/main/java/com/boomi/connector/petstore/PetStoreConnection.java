package com.boomi.connector.petstore;

import java.util.logging.Logger;
import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.api.ConnectorException;
import com.boomi.connector.api.PropertyMap;
import com.boomi.connector.openapi.OpenAPIConnection;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class PetStoreConnection extends OpenAPIConnection {

    public enum ConnectionProperties {URL, SWAGGERURL, SWAGGERALTURL, AUTHTYPE, USERNAME, PASSWORD, OAUTHOPTIONS, AUTHFORSWAGGERLOAD}

    Logger logger = Logger.getLogger(this.getClass().getName());
    protected final String _baseUrl;
    protected final PropertyMap _connProps;
    protected CloseableHttpClient _httpClient = null;

    private String _url;
    private String _httpMethod;

    protected HttpClientContext _httpClientContext;

    public PetStoreConnection(BrowseContext context) {
        super(context);
        _connProps = context.getConnectionProperties();
        _baseUrl = _connProps.getProperty(ConnectionProperties.URL.name());
        logger.info("Init: " +_connProps.getProperty(ConnectionProperties.AUTHTYPE.name()));

    }

    protected String getBaseUrl()
    {
        if (_baseUrl == null || _baseUrl.trim().length()==0)
            throw new ConnectorException("Service URL is a required field");
        return this._baseUrl.trim();
    }

    protected String getTestConnectionUrl()
    {
        return this.getBaseUrl();
    }

    protected void testConnection() throws Exception
    {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse httpResponse = null;
        try {
            HttpGet httpRequest = new HttpGet(this.getTestConnectionUrl());
            String authHeader = null;
            if (authHeader!=null)
                httpRequest.addHeader ("Authorization", authHeader);
            logger.info("Test Connection: " +getTestConnectionUrl()+ " " + this.getAuthenticationType().name());
            httpResponse = httpClient.execute(httpRequest);
            if (200 != httpResponse.getStatusLine().getStatusCode())
                throw new Exception(String.format("Problem connecting to endpoint: %s %s %s", this.getTestConnectionUrl(), httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase()));
        } finally {
            if (httpResponse!=null)
                httpResponse.close();
            if (httpClient!=null)
                httpClient.close();
        }
    }
}