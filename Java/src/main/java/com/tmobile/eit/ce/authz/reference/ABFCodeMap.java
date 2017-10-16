package com.tmobile.eit.ce.authz.reference;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableBiMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by ksubram on 3/24/17.
 *
 * Singleton pattern implemented for now. Later ABFs will be injected from service
 */
public class ABFCodeMap extends HashMap<String, String>{

    static final Logger LOG = LoggerFactory.getLogger(ABFCodeMap.class);

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static Map<String, String> defaultABFMapper() {

        HashMap<String, String> defaultABFMapper = new HashMap<>(2);
        defaultABFMapper.put("WAIVE_PROCESSING_FEE","1");
        defaultABFMapper.put("PUSH_FEES_TO_BILL","2");

        return defaultABFMapper;
    }

    public static ImmutableBiMap<String,String> getABFMapper()    {
        String serviceURL = null;
        ImmutableBiMap<String,String> ABFMAPPER = null;
        try {
            serviceURL = System.getenv("authcode.service.URL");
            if (serviceURL == null)
                serviceURL = System.getProperty("authcode.service.URL","http://localhost:9876/references");
            LOG.info("Initializing ABF Reference with remote service URL: "+ serviceURL);
            Client client = Client.create();
            WebResource webResource = client.resource(serviceURL);
            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);
            String output = response.getEntity(String.class);
            if (response.getStatus() == 200 && output != null && !output.isEmpty()) {
                Map<String, String> mapResponse = OBJECT_MAPPER.readValue(output, new TypeReference<Map<String, String>>() {});
                ABFMAPPER = ImmutableBiMap.copyOf(Collections.unmodifiableMap(mapResponse));
                LOG.info("Success on getting ABF Reference map from  "+ serviceURL);
            } else {
                ABFMAPPER = ImmutableBiMap.copyOf(Collections.unmodifiableMap(ABFCodeMap.defaultABFMapper()));
            }

        }catch(Exception ex){
            ABFMAPPER = ImmutableBiMap.copyOf(Collections.unmodifiableMap(ABFCodeMap.defaultABFMapper()));
            LOG.warn("ABF Mapper initialization failed");
        }
        return ABFMAPPER;
    }

}
