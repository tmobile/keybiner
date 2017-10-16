# keybiner
Entitlements compression and validation library. Clients / services can leverage the Keyabiner library for Authorization information compression, decompression and authorization check as well.

Reference service can configured as JVM property or environment property with key name **authcode.service.URL**. Reference service response should match with following json format.
````
curl http://localhost:9876/references
{
    "WAIVE_PROCESSING_FEE": "1",
    "PUSH_FEES_TO_BILL": "2"
}

````

The following message format should be returned by PDP (Policy Decision Point) or Entitlement Service. 
````
{
   "subject":{
      "id":"test",
      "ABFs":[
         "WAIVE_PROCESSING_FEE",
         "PUSH_FEES_TO_BILL"
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
                  "ACTIVITY_ACCOUNT_MANAGER"
               ]
            }
         ]
      }
   ]
}
````
Above JSON message is the key for feeding into KeyBiner EntitlementPack for Encoding and compressing.

````
    entitlementsJson = EntitlementPack.encodeEntilementJson(s);
    compressed = Compression.compress(entitlementsJson);
````
And it can be used to Uncompress/Decompress and Decode for complete entitlement again. We can use Keyabiner library to get an encoded and compressed String of the above JSON message. 

````
    entitlementsJson = Compression.decompressToString(compressed);
    Entitlements entitlements = EntitlementPack.decodeEntitlement(entitlementsJson);
````
When a client / service / authorization enforcer receives a token, it uses the following method to perform an authorization check.

````
    Authorizer.isAuthorized("987654320", null, entitlements, new String[]{"PUSH_FEES_TO_BILL"})
````

The Keyabiner library can be used/extended for any purpose not just for authorization. Have Fun!!
