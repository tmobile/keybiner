package com.tmobile.eit.ce.authz;

import com.tmobile.eit.ce.authz.pojo.Entitlements;
import com.tmobile.eit.ce.authz.util.Compression;
import com.tmobile.eit.ce.authz.util.EntitlementPack;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;

import static org.junit.Assert.assertNotNull;

public class EntitlementPackTest {
    private static final Logger LOG = LoggerFactory.getLogger(ProgressTest.class);

    @Test
    public void testencodeEntilementJson(){
        String jsonString = "{\"subject\":{\"id\":\"test\",\"ABFs\":[\"BYPASS_VERIFICATION\",\"MANUAL_CARD_AUTHORIZATION\"]},\"resources\":[{\"id\":\"987654320\",\"ABFs\":[\"WAIVE_PROCESSING_FEE\",\"PUSH_FEES_TO_BILL\"],\"subResources\":[{\"id\":\"9876543210\",\"ABFs\":[\"ACTIVITY_ACCOUNT_OWNER\",\"ACTIVITY_ACCOUNT_MANAGER\",\"ACTIVITY_SUBSCRIBER_EDITOR\",\"WAIVE_PROCESSING_FEE\",\"PUSH_FEES_TO_BILL\",\"EXEMPT_PROCESSING_FEE\"]},{\"id\":\"9876543211\",\"ABFs\":[\"ACTIVITY_ACCOUNT_OWNER\",\"ACTIVITY_ACCOUNT_MANAGER\",\"ACTIVITY_SUBSCRIBER_EDITOR\",\"WAIVE_PROCESSING_FEE\",\"PUSH_FEES_TO_BILL\",\"EXEMPT_PROCESSING_FEE\"]}]},{\"id\":\"887654320\",\"ABFs\":[\"WAIVE_PROCESSING_FEE\",\"PUSH_FEES_TO_BILL\",\"EXEMPT_PROCESSING_FEE\"],\"subResources\":[{\"id\":\"8876543210\",\"ABFs\":[\"ADJUSTMENTS_PER_FAN_IN_PERIOD\",\"ACTIVITY_ACCOUNT_OWNER\",\"ACTIVITY_ACCOUNT_MANAGER\",\"ACTIVITY_SUBSCRIBER_EDITOR\",\"WAIVE_PROCESSING_FEE\",\"PUSH_FEES_TO_BILL\",\"EXEMPT_PROCESSING_FEE\"]},{\"id\":\"8876543211\",\"ABFs\":[\"ACTIVITY_ACCOUNT_OWNER\",\"ACTIVITY_ACCOUNT_MANAGER\",\"ACTIVITY_SUBSCRIBER_EDITOR\",\"OVERRIDE_MAX_CASH_REFUND_AMOUNT\",\"WAIVE_PROCESSING_FEE\",\"PUSH_FEES_TO_BILL\",\"EXEMPT_PROCESSING_FEE\"]}]}]}";
        String encoded = EntitlementPack.encodeEntilementJson(jsonString);
        LOG.info("Encoded : "+encoded);
        assertNotNull(encoded);

    }
    @Test
    public void testCompression() throws UnsupportedEncodingException {
        String encoded ="{\"subject\":{\"id\":\"test\",\"ABFs\":[\"2\",\"36028797018963968\",null]},\"resources\":[{\"id\":\"987654320\",\"ABFs\":[null,\"17716740096\",null],\"subResources\":[{\"id\":\"9876543210\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"2\"]},{\"id\":\"9876543211\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"2\"]}]},{\"id\":\"887654320\",\"ABFs\":[\"144115188075855872\",\"17716740096\",null],\"subResources\":[{\"id\":\"8876543210\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"18\"]},{\"id\":\"8876543211\",\"ABFs\":[\"144115188075855876\",\"17716838400\",\"2\"]}]}]}";
        String compressedString = Compression.compress(encoded);
        LOG.info("Compressed : "+compressedString);
        assertNotNull(compressedString);
    }
    @Test
    public void testDecompressed() throws UnsupportedEncodingException, DataFormatException {
        String compressed = "eJydzz0OwjAMBeC7eM4Q589ONxg4ACvqQskAqkBqmqnq3UlQpSKqVsBmS9bn9waI6XwLTQ/VANcLVNCH2IOA3f4QoTqByrN2UjF5ksjeae8YxD21bT0K6EJ8pK4J5XYCPJOzRis5K+VaABKhIyOldxMgyvfjOoFvBqAxiBaZJVm2lqlEe5msOat5U5AzfRr4uzErvCyzgXxVjv8shwzLXNvt3Eq7enwCkip7IQ==";
        String decompressedEncoded = Compression.decompressToString(compressed);
        LOG.info("Decompressed : "+decompressedEncoded);
        assertNotNull(decompressedEncoded);
    }
    @Test
    public void testDecodeEntitlement(){
        String decompressedEncoded = "{\"subject\":{\"id\":\"test\",\"ABFs\":[\"2\",\"36028797018963968\",null]},\"resources\":[{\"id\":\"987654320\",\"ABFs\":[null,\"17716740096\",null],\"subResources\":[{\"id\":\"9876543210\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"2\"]},{\"id\":\"9876543211\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"2\"]}]},{\"id\":\"887654320\",\"ABFs\":[\"144115188075855872\",\"17716740096\",null],\"subResources\":[{\"id\":\"8876543210\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"18\"]},{\"id\":\"8876543211\",\"ABFs\":[\"144115188075855876\",\"17716838400\",\"2\"]}]}]}";
        Entitlements entitlements  = EntitlementPack.decodeEntitlement(decompressedEncoded);
        assertNotNull(entitlements);
    }
    @Test
    public void testDecodeEntitlementJson(){
        String decompressedEncoded = "{\"subject\":{\"id\":\"test\",\"ABFs\":[\"2\",\"36028797018963968\",null]},\"resources\":[{\"id\":\"987654320\",\"ABFs\":[null,\"17716740096\",null],\"subResources\":[{\"id\":\"9876543210\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"2\"]},{\"id\":\"9876543211\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"2\"]}]},{\"id\":\"887654320\",\"ABFs\":[\"144115188075855872\",\"17716740096\",null],\"subResources\":[{\"id\":\"8876543210\",\"ABFs\":[\"144115188075855872\",\"17716838400\",\"18\"]},{\"id\":\"8876543211\",\"ABFs\":[\"144115188075855876\",\"17716838400\",\"2\"]}]}]}";
        String entitlementsJson = EntitlementPack.decodeEntitlementJson(decompressedEncoded);
        LOG.info("Decoded : "+entitlementsJson);
        assertNotNull(entitlementsJson);
    }
}
