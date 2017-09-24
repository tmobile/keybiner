package com.tmobile.eit.ce.authz.reference;

import com.google.common.collect.ImmutableBiMap;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by ksubram on 3/24/17.
 *
 * Singleton pattern implemented for now. Later ABFs will be injected from service
 */
public class ABFCodeMap extends HashMap<String, String>{

    private static final ABFCodeMap ABF_MAPPER = new ABFCodeMap();

    private ABFCodeMap() {
        this.put("BYPASS_VERIFICATION","1");
        this.put("MANUAL_CARD_AUTHORIZATION","2");
        this.put("VIEW_ORDER_RECEIPT","3");
        this.put("BYPASS_ENROLLMENT_WINDOW_RESTRICTIONS","4");
        this.put("REQUEST_MSISDN_BLOCK","5");
        this.put("OVERRIDE_DEFAULT_RATEPLAN_CHANGE_DATE","6");
        this.put("VIEW_LOS_SOFTGOODS","7");
        this.put("ADD_IMEI_TO_BLACKLIST","8");
        this.put("REMOVE_IMEI_FROM_BLACKLIST","9");
        this.put("ADD_PROMOTION","10 ");
        this.put("MANAGE_STORED_PAYMENT_METHODS","11");
        this.put("BYPASS_PROMOTION_RESTRICTIONS","12");
        this.put("PROCESS_MANUAL_PAYMENT","13");
        this.put("FORCE_MSISDN_ASSIGNMENT","14");
        this.put("ASSIGN_PORTIN_TEMP_MSISDN","15");
        this.put("UPDATE_LOS_FAN_NICKNAMES","16");
        this.put("MANAGE_LOS_PARTY_ROLES","17");
        this.put("ADD_IMEI_TO_BLACKLIST","18");
        this.put("BYPASS_DEVICE_DIAGNOSTIC_EXCHANGE_RETURN","19");
        this.put("UPDATE_FAN_BILLCYCLE","20");
        this.put("OVERRIDE_PRICE_DOWN","21");
        this.put("OVERRIDE_PRICE_UP","22");
        this.put("CHANGE_RATE_PLAN","23");
        this.put("SKIP_COR_TC_CUSTOMER_ACCEPTANCE","24");
        this.put("REQUEST_ADJUSTMENT","25");
        this.put("CREATE_CHARGE","26");
        this.put("CREDIT_AMT_USER_TRANS","27");
        this.put("ADJUSTMENT_AMT_USER_TRANS","28");
        this.put("ACCESS_WEB_PROFILE","29");
        this.put("VIEW_CUSTOMER_IVR_PIN","30");
        this.put("CHANGE_DEALER_CODE","31");
        this.put("SEND_DEVICE_UNLOCK_CODE","32");
        this.put("ADD_IMEI_TO_BLACKLIST","33 ");
        this.put("VIEW_DEVICE_UNLOCK_CODE","34");
        this.put("UPDATE_ORDER_PAYMENT_CC","35");
        this.put("UPDATE_CUSTOMER_COMM_PREFERENCES","36");
        this.put("UPDATE_ORDER_SHIPPING_TO_NAME","37");
        this.put("MANAGE_BLOCKING_ROAMING","38");
        this.put("BYPASS_DEVICE_EXCHANGE_ELIGIBILITY","39");
        this.put("SKIP_MEMO_CREATION_ON_ACCOUNT_ACCESS","40");
        this.put("CREATE_PORT_IN","41");
        this.put("VIEW_BACK_ORDER_DEVICES","42");
        this.put("ACCEPT_DECIMAL_PRECISION","43");
        this.put("MANAGE_UNLOCK","44");
        this.put("OVERRIDE_DEFAULT_CANCELLINE_DATE","45");
        this.put("ADD_A_LINE","46");
        this.put("PERFORM_BAN_TO_BAN_MOVE","47");
        this.put("RESET_CUSTOMER_TENURE","48");
        this.put("CREATE_D2A","49");
        this.put("OVERRIDE_DEVICE_DIAGNOSTICS_EVALUATION","50");
        this.put("OVERRIDE_DEVICE_IMEI_WARRANTY_EXG","51");
        this.put("WAIVE_SSK","52");
        this.put("OVERRIDE_SYSTEM_UNLOCK","53");
        this.put("BYPASS_STORE_HARDGOODS_PURCHASE_LIMIT", "54");
        this.put("REQUEST_CASEMANAGEMENT_TICKET", "55");
        this.put("SEARCH_CUSTOMER","56");
        this.put("PRINT_MANAGEMENT_REPORTS","57");
        this.put("PURCHASE_SESSION_PASSES","58");
        this.put("BYPASS_SESSION_PASS_AVAILABILITY_RULES","59");
        this.put("REMOVE_IMEI_FROM_BLACKLIST","60");
        this.put("REMOVE_IMEI_FROM_BLACKLIST","61");
        this.put("REQUEST_REFUND","62");
        this.put("RESTORE_SUSPENDED_LOS" ,"63");
        this.put("OVERRIDE_GIFTSTASH_RESTORATION_RULES","64");
        this.put("RETRIEVE_PUK_CODE","65");
        this.put("ADVANCED_CUSTOMER_SEARCH","66");
        this.put("REQUEST_RETURNED _PAYMENT", "67");
        this.put("CREATE_CASEMANAGEMENT_TICKET","68");
        this.put("UPDATE_CASEMANAGEMENT_TICKET","69");
        this.put("UPDATE_PRIMARY_LINE_ASSIGNMENT","70");
        this.put("UPDATE_STORE_ID","71");
        this.put("GET_RATE_PLAN_HISTORY","72");
        this.put("ACCESS_GRANDFATHERED_OFFERS","73");
        this.put("VIEW_SUBSCRIBER_ACCOUNT_ACTIVITY","74");
        this.put("VIEW_CUSTOMER_SSN_TAXID","75");
        this.put("VIEW_ORDER_HISTORY","76");
        this.put("VIEW_POST_VOID_TRANSACTIONS","77");
        this.put("WAIVE_PAYMENT_PROCESSING_FEE","78");
        this.put("WAIVE_SHIPPING_FEE","79");
        this.put("CREATE_AUTOPAY","80");
        this.put("VIEW_AUTOPAY","81");
        this.put("UPDATE_AUTOPAY","82");
        this.put("CANCEL_AUTOPAY","83");
        this.put("UPDATE_CASEMANAGEMENT_TICKET","84");
        this.put("UPDATE_CUSTOMER_PIN","85");
        this.put("VIEW_LOS_SOFTGOODS","86");
        this.put("CHANGE_RATE_PLAN","87");
        this.put("DELETE_LOS","88");
        this.put("MODIFY_LOS_FEATURES","89");
        this.put("VIEW_LOS_SOFTGOODS","90");
        this.put("MODIFY_LOS_FEATURES","91");
        this.put("MODIFY_LOS_FEATURES","92");
        this.put("ADD_PROMOTION","93");
        this.put("REMOVE_PROMOTION","94");
        this.put("REQUEST_MISSING_PAYMENT","95");
        this.put("SHOW_GRAND_CENTRAL_ACCESS_LINK","96");
        this.put("REMEDY_ACCESS_TOOL","97");
        this.put("OPEN_CLOSE_STORE","98");
        this.put("IMEI_BLOCKING_TOOL_ACCESS", "99");
        this.put("NEGATIVE_FILE_REQUEST_ACCESS","100");
        this.put("SHOW_FRAUD_ROUTING_FORM","101");
        this.put("CREATE_SCORE_UPGRADE","102");
        this.put("VIEW_SESSION_PASS_HISTORY", "103");
        this.put("OVERRIDE_PRICE_DOWN_IN_PERIOD", "104");
        this.put("OVERRIDE_PRICE_UP_IN_PERIOD","105");
        this.put("OVERRIDE_PRICE_DOWN_CUST","106");
        this.put("OVERRIDE_PRICE_UP_CUST","107");
        this.put("CREDITS_AMT_FAN_IN_PERIOD","108");
        this.put("ADJUSTMENTS_AMT_FAN_IN_PERIOD","109");
        this.put("CREDITS_AMT_USER_IN_PERIOD","110");
        this.put("ADJUSTMENTS_AMT_USER_IN_PERIOD","111");
        this.put("CREDITS_PER_USER_IN_PERIOD","112");
        this.put("ADJUSTMENTS_PER_USER_IN_PERIOD","113");
        this.put("ACCESS_CUST_WEB_PROFILE","114");
        this.put("BYPASS_REMORSE_RETURN_RESTR","115");
        this.put("IMMEDIATE_DOWNGRADE","116");
        this.put("CREDITS_PER_FAN_IN_PERIOD", "117");
        this.put("ADJUSTMENTS_PER_FAN_IN_PERIOD", "118");
        this.put("ACTIVITY_HEADER_VIEW","119");
        this.put("FRAUD_MEMO_VIEW","120");
        this.put("LIFELINE","121");
        this.put("LIFELINE_PR","122");
        this.put("CRM_DISCOUNTS", "123");
        this.put("SUPER_USERS","124");
        this.put("GENCARE_RETAILMANAGER", "125");
        this.put("RESUME_FROM_CANCEL","126");
        this.put("CHANGE_RTPLN_LOST_STOLEN","127");
        this.put("GET_CUSTOMER_INFO", "128");
        this.put("VIEW_ACTIVITY_LOG", "129");
        this.put("ACTIVITY_ACCOUNT_OWNER","130");
        this.put("ACTIVITY_ACCOUNT_MANAGER","131");
        this.put("ACTIVITY_SUBSCRIBER_EDITOR","132");
        this.put("ACTIVITY_SUBSCRIBER_VIEWER","133");
        this.put("REQUEST_REFUND_METHOD", "134");
        this.put("OVERRIDE_INELIGIBLE_RETURN","135");
        this.put("OVERRIDE_MAX_CASH_REFUND_AMOUNT","136");
        this.put("ACCEPT_TENDER_VARIANCE","137");
        this.put("UPDATE_ADDRESS","138");
        this.put("CREATE_FRAUD_MEMO","139");
        this.put("CREATE_MEMO","140");
        this.put("ADD_TAX_EXEMPTION","141");
        this.put("ACCESS_RETAIL","142");
        this.put("ACCESS_CARE","143");
        this.put("VIEW_POS_REPORTS","144");
        this.put("DISPLAY_ACCOUNT_HISTORY_LOG","145");
        this.put("SUBMIT_AUTOREQ_FOR_D2A_EPIN","146");
        this.put("SUBMIT_MANUALREQ_FOR_D2A_EPIN","147");
        this.put("MSISDN_CHANGE","148");
        this.put("WAIVE_PROCESSING_FEE","149");
        this.put("PUSH_FEES_TO_BILL","150");
        this.put("EXEMPT_PROCESSING_FEE","151");
    }

    public static ImmutableBiMap<String,String> getABFMapper()    {
        return ImmutableBiMap.copyOf(Collections.unmodifiableMap(ABF_MAPPER));
    }

}
