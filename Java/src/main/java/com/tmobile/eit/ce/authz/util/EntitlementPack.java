package com.tmobile.eit.ce.authz.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.eit.ce.authz.pojo.Resource;
import com.tmobile.eit.ce.authz.pojo.Entitlements;
import com.tmobile.eit.ce.authz.pojo.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by ksubram on 4/2/17.
 */
public class EntitlementPack {

    static final Logger LOG = LoggerFactory.getLogger(EntitlementPack.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     *
     * @param entitlementJson
     * @return
     */
    public static  Entitlements decodeEntitlement(String entitlementJson) {
        Entitlements entitlements = null;
        LOG.debug("Incoming decodeEntitlement:"+entitlementJson);
        try {
            entitlements = EntitlementPack.MAPPER.readValue(entitlementJson, Entitlements.class);
            Subject subject = entitlements.getSubject();

            if (subject != null && subject.getABFs() != null) {
                subject.setABFs(
                        Permission64.decode(subject.getABFs())
                );
            }
            List<Resource> accounts = entitlements.getResources();
            if (accounts != null) {
                accounts
                        .parallelStream()
                        .forEach(
                                account -> {
                                    account.setABFs(
                                            Permission64.decode(account.getABFs())
                                    );
                                    List<Resource> lines = account.getSubResources();
                                    if (lines != null) {
                                        lines
                                                .parallelStream()
                                                .forEach(
                                                        line -> {
                                                            line.setABFs(
                                                                    Permission64.decode(line.getABFs())
                                                            );
                                                        }
                                                );
                                    }

                                }
                        );
            }

        } catch (IOException ex)   {
            LOG.error(ex.getMessage(), ex);
        }
        return entitlements;
    }

    /**
     *
     * @param entitlementJson
     * @return
     */
    public static Entitlements encodeEntilement(String entitlementJson) {
        Entitlements entitlements = null;
        try {
            entitlements = EntitlementPack.MAPPER.readValue(entitlementJson, Entitlements.class);
            Subject subject = entitlements.getSubject();

            if (subject != null && subject.getABFs() != null) {
                subject.setABFs(
                        Permission64.encode(subject.getABFs())
                );
            }

            List<Resource> accounts = entitlements.getResources();
            if (accounts != null) {
                accounts
                        .parallelStream()
                        .forEach(account ->
                                {
                                    account.setABFs(
                                            Permission64.encode(account.getABFs())
                                    );
                                    List<Resource> lines = account.getSubResources();
                                    if (lines != null) {
                                        lines
                                                .parallelStream()
                                                .forEach(
                                                        line -> {
                                                            line.setABFs(
                                                                    Permission64.encode(line.getABFs())
                                                            );
                                                        }
                                                );
                                    }
                                }
                        );
            }

        } catch (IOException ex)   {
            LOG.error(ex.getMessage(), ex);
        }
        return entitlements;
    }

    /**
     *
     * @param entitlementJson
     * @return
     */
    public static  String decodeEntitlementJson(String entitlementJson) {
        String jsonString = null;
        try {
            Entitlements entitlements = decodeEntitlement(entitlementJson);
            jsonString = EntitlementPack.MAPPER.writeValueAsString(entitlements);

        } catch (Exception ex)  {
            LOG.error(ex.getMessage(), ex);
        }
        return jsonString;
    }

    /**
     *
     * @param entitlementJson
     * @return
     */
    public static String encodeEntilementJson(String entitlementJson) {
        String jsonString = null;
        try {
            Entitlements entitlements = encodeEntilement(entitlementJson);
            jsonString = EntitlementPack.MAPPER.writeValueAsString(entitlements);

        } catch (Exception ex)  {
            LOG.error(ex.getMessage(), ex);
        }
        return jsonString;
    }
}
