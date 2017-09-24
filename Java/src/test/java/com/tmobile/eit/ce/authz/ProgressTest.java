package com.tmobile.eit.ce.authz;

import com.tmobile.eit.ce.authz.pojo.Entitlements;
import com.tmobile.eit.ce.authz.util.Compression;
import com.tmobile.eit.ce.authz.util.EntitlementPack;
import com.tmobile.eit.ce.authz.util.Permission64;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ksubram on 3/29/17.
 */
public class ProgressTest {

    static final Logger LOG = LoggerFactory.getLogger(ProgressTest.class);

    @Test
    public void testProgress() {
        String[] inputABFs = new String[]{"BYPASS_VERIFICATION", "BYPASS_ENROLLMENT_WINDOW_RESTRICTIONS", "OPEN_CLOSE_STORE", "CREDITS_PER_FAN_IN_PERIOD", "FORCE_MSISDN_ASSIGNMENT",
                "OVERRIDE_PRICE_DOWN_CUST", "OVERRIDE_PRICE_UP_CUST", "CREDITS_PER_USER_IN_PERIOD", "ACCESS_CUST_WEB_PROFILE", "MSISDN_CHANGE", "BYPASS_DEVICE_DIAGNOSTIC_EXCHANGE_RETURN"};


        try {
            String encodedPermission = Permission64.encodePermissions(inputABFs);


            String s = "{\n" +
                    "   \"subject\":{\n" +
                    "      \"id\":\"test\",\n" +
                    "      \"ABFs\":[\n" +
//                    "         \"CREATE_D2A$75000$@P1D@\",\n" +
                    "         \"UPDATE_CUSTOMER_COMM_PREFERENCES\",\n" +
                    "         \"UPDATE_ORDER_SHIPPING_TO_NAME\",\n" +
                    "         \"BYPASS_DEVICE_EXCHANGE_ELIGIBILITY\",\n" +
                    "         \"SKIP_MEMO_CREATION_ON_ACCOUNT_ACCESS\",\n" +
                    "         \"OVERRIDE_DEVICE_DIAGNOSTICS_EVALUATION\",\n" +
                    "         \"OVERRIDE_DEFAULT_CANCELLINE_DATE\",\n" +
                    "         \"BYPASS_SESSION_PASS_AVAILABILITY_RULES\",\n" +
                    "         \"OVERRIDE_GIFTSTASH_RESTORATION_RULES\",\n" +
                    "         \"MANUAL_CARD_AUTHORIZATION\",\n" +
                    "         \"BYPASS_DEVICE_DIAGNOSTIC_EXCHANGE_RETURN\"\n" +
                    "      ]\n" +
                    "   },\n" +
                    "   \"resources\":[\n" +
                    "      {\n" +
                    "         \"id\":\"987654320\",\n" +
                    "         \"ABFs\":[\n" +
                    "            \"WAIVE_PROCESSING_FEE\",\n" +
                    "            \"PUSH_FEES_TO_BILL\"\n" +
                    "         ],\n" +
                    "         \"subResources\":[\n" +
                    "            {\n" +
                    "               \"id\":\"9876543210\",\n" +
                    "               \"ABFs\":[\n" +
                    "                  \"WAIVE_PROCESSING_FEE\",\n" +
                    "                  \"PUSH_FEES_TO_BILL\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_OWNER\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_MANAGER\",\n" +
                    "                  \"ACTIVITY_SUBSCRIBER_EDITOR\",\n" +
                    "         \"BYPASS_DEVICE_EXCHANGE_ELIGIBILITY\",\n" +
                    "         \"SKIP_MEMO_CREATION_ON_ACCOUNT_ACCESS\",\n" +
                    "         \"OVERRIDE_DEVICE_DIAGNOSTICS_EVALUATION\",\n" +
                    "         \"OVERRIDE_DEFAULT_CANCELLINE_DATE\",\n" +
                    "         \"BYPASS_SESSION_PASS_AVAILABILITY_RULES\",\n" +
                    "         \"OVERRIDE_GIFTSTASH_RESTORATION_RULES\",\n" +
                    "                  \"EXEMPT_PROCESSING_FEE\"\n" +
                    "               ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"id\":\"9876543211\",\n" +
                    "               \"ABFs\":[\n" +
                    "                  \"WAIVE_PROCESSING_FEE\",\n" +
                    "                  \"PUSH_FEES_TO_BILL\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_OWNER\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_MANAGER\",\n" +
                    "                  \"ACTIVITY_SUBSCRIBER_EDITOR\",\n" +
                    "         \"BYPASS_DEVICE_EXCHANGE_ELIGIBILITY\",\n" +
                    "         \"SKIP_MEMO_CREATION_ON_ACCOUNT_ACCESS\",\n" +
                    "         \"OVERRIDE_DEVICE_DIAGNOSTICS_EVALUATION\",\n" +
                    "         \"OVERRIDE_DEFAULT_CANCELLINE_DATE\",\n" +
                    "         \"BYPASS_SESSION_PASS_AVAILABILITY_RULES\",\n" +
                    "         \"OVERRIDE_GIFTSTASH_RESTORATION_RULES\",\n" +
                    "                  \"EXEMPT_PROCESSING_FEE\"\n" +
                    "               ]\n" +
                    "            }\n" +
                    "         ]\n" +
                    "      },\n" +
                    "      {\n" +
                    "         \"id\":\"887654320\",\n" +
                    "         \"ABFs\":[\n" +
                    "            \"WAIVE_PROCESSING_FEE\",\n" +
                    "            \"PUSH_FEES_TO_BILL\",\n" +
                    "            \"EXEMPT_PROCESSING_FEE\"\n" +
                    "         ],\n" +
                    "         \"subResources\":[\n" +
                    "            {\n" +
                    "               \"id\":\"8876543210\",\n" +
                    "               \"ABFs\":[\n" +
                    "                  \"WAIVE_PROCESSING_FEE\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_OWNER\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_MANAGER\",\n" +
                    "                  \"ACTIVITY_SUBSCRIBER_EDITOR\",\n" +
                    "                  \"ADJUSTMENTS_PER_FAN_IN_PERIOD\",\n" +
                    "         \"BYPASS_DEVICE_EXCHANGE_ELIGIBILITY\",\n" +
                    "         \"SKIP_MEMO_CREATION_ON_ACCOUNT_ACCESS\",\n" +
                    "         \"OVERRIDE_DEVICE_DIAGNOSTICS_EVALUATION\",\n" +
                    "         \"OVERRIDE_DEFAULT_CANCELLINE_DATE\",\n" +
                    "         \"BYPASS_SESSION_PASS_AVAILABILITY_RULES\",\n" +
                    "         \"OVERRIDE_GIFTSTASH_RESTORATION_RULES\",\n" +
                    "                  \"PUSH_FEES_TO_BILL\",\n" +
                    "                  \"EXEMPT_PROCESSING_FEE\"\n" +
                    "               ]\n" +
                    "            },\n" +
                    "            {\n" +
                    "               \"id\":\"8876543211\",\n" +
                    "               \"ABFs\":[\n" +
                    "                  \"WAIVE_PROCESSING_FEE\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_OWNER\",\n" +
                    "                  \"ACTIVITY_ACCOUNT_MANAGER\",\n" +
                    "                  \"ACTIVITY_SUBSCRIBER_EDITOR\",\n" +
                    "                  \"PUSH_FEES_TO_BILL\",\n" +
                    "                  \"OVERRIDE_MAX_CASH_REFUND_AMOUNT\",\n" +
                    "         \"BYPASS_DEVICE_EXCHANGE_ELIGIBILITY\",\n" +
                    "         \"SKIP_MEMO_CREATION_ON_ACCOUNT_ACCESS\",\n" +
                    "         \"OVERRIDE_DEVICE_DIAGNOSTICS_EVALUATION\",\n" +
                    "         \"OVERRIDE_DEFAULT_CANCELLINE_DATE\",\n" +
                    "         \"BYPASS_SESSION_PASS_AVAILABILITY_RULES\",\n" +
                    "         \"OVERRIDE_GIFTSTASH_RESTORATION_RULES\",\n" +
                    "                  \"EXEMPT_PROCESSING_FEE\"\n" +
                    "               ]\n" +
                    "            }\n" +
                    "         ]\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}";
            //System.out.println("Original: "+ s.length()+" :" + s);

            String entitlementsJson = EntitlementPack.encodeEntilementJson(s);
            LOG.info("Encoded: " + entitlementsJson.length() + " :" + entitlementsJson);
            String compressed = Compression.compress(entitlementsJson);
            LOG.info("Compressed: " + compressed.length() + " :" + compressed);

            entitlementsJson = Compression.decompressToString(compressed);
            Entitlements entitlements = EntitlementPack.decodeEntitlement(entitlementsJson);
            entitlementsJson = EntitlementPack.decodeEntitlementJson(entitlementsJson);
            LOG.info("Decoded: " + entitlementsJson.length() + " :" + entitlementsJson);

            assert (Authorizer.isAuthorized(null, null, entitlements, new String[]{"OVERRIDE_GIFTSTASH_RESTORATION_RULES"})) : "UnAuthorized";

        } catch (Exception ex) {
            ex.printStackTrace();
            assert (false);
        }
    }


}
