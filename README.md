# keybiner
Entitlements compression and validation library. Clients / services can leverage the Keyabiner library for Authorization information compression, decompression and authorization check as well.

Reference service can configured as JVM property or environment property with key name **authcode.service.URL**. Reference service response should match with following json format.
````
[
   {
      "REFID":"ABF NAME"
   }
]
````

Reference service If you have reference implementationFor instance, 
````
{
   "subject":{
      "id":"test",
      "ABFs":[
         "BYPASS_VERIFICATION",
         "MANUAL_CARD_AUTHORIZATION"
      ]
   },
   "resources":[
      {
         "id":"987654320",
         "ABFs":[
            "WAIVE_PROCESSING_FEE",
            "PUSH_FEES_TO_BILL"
         ],
         "subResources":[
            {
               "id":"9876543210",
               "ABFs":[
                  "ACTIVITY_ACCOUNT_OWNER",
                  "ACTIVITY_ACCOUNT_MANAGER",
                  "ACTIVITY_SUBSCRIBER_EDITOR",
                  "WAIVE_PROCESSING_FEE",
                  "PUSH_FEES_TO_BILL",
                  "EXEMPT_PROCESSING_FEE"
               ]
            },
            {
               "id":"9876543211",
               "ABFs":[
                  "ACTIVITY_ACCOUNT_OWNER",
                  "ACTIVITY_ACCOUNT_MANAGER",
                  "ACTIVITY_SUBSCRIBER_EDITOR",
                  "WAIVE_PROCESSING_FEE",
                  "PUSH_FEES_TO_BILL",
                  "EXEMPT_PROCESSING_FEE"
               ]
            }
         ]
      },
      {
         "id":"887654320",
         "ABFs":[
            "WAIVE_PROCESSING_FEE",
            "PUSH_FEES_TO_BILL",
            "EXEMPT_PROCESSING_FEE"
         ],
         "subResources":[
            {
               "id":"8876543210",
               "ABFs":[
                  "ADJUSTMENTS_PER_FAN_IN_PERIOD",
                  "ACTIVITY_ACCOUNT_OWNER",
                  "ACTIVITY_ACCOUNT_MANAGER",
                  "ACTIVITY_SUBSCRIBER_EDITOR",
                  "WAIVE_PROCESSING_FEE",
                  "PUSH_FEES_TO_BILL",
                  "EXEMPT_PROCESSING_FEE"
               ]
            },
            {
               "id":"8876543211",
               "ABFs":[
                  "ACTIVITY_ACCOUNT_OWNER",
                  "ACTIVITY_ACCOUNT_MANAGER",
                  "ACTIVITY_SUBSCRIBER_EDITOR",
                  "OVERRIDE_MAX_CASH_REFUND_AMOUNT",
                  "WAIVE_PROCESSING_FEE",
                  "PUSH_FEES_TO_BILL",
                  "EXEMPT_PROCESSING_FEE"
               ]
            }
         ]
      }
   ]
}
````
Entitlements

````
            String entitlementsJson = EntitlementPack.encodeEntilementJson(s);
            String compressed = Compression.compress(entitlementsJson);
````

We can use Keyabiner library to get an encoded and compressed String of the above JSON message. 
````
            entitlementsJson = Compression.decompressToString(compressed);
            Entitlements entitlements = EntitlementPack.decodeEntitlement(entitlementsJson);
````
When a client / service receives a token, it uses the following method to perform an authorization check.
````
Authorizer.isAuthorized("987654320", null, entitlements, new String[]{"PUSH_FEES_TO_BILL"})
````
The Keyabiner library can be used/extended for any purpose not just for authorization. Have Fun!!
