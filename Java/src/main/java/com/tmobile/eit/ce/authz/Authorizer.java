package com.tmobile.eit.ce.authz;

import com.tmobile.eit.ce.authz.pojo.Resource;
import com.tmobile.eit.ce.authz.pojo.Entitlements;
import com.tmobile.eit.ce.authz.pojo.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ksubram on 4/2/17.
 */
public class Authorizer {

    static final Logger LOG = LoggerFactory.getLogger(Authorizer.class);
    /**
     *
     * @param resourceId
     * @param subResourceId
     * @param entitlements
     * @param ABFs
     * @return
     */
    public static boolean isAuthorized(String resourceId, String subResourceId, Entitlements entitlements, String[] ABFs)  {
        List<String> inputABFs = null;
        try {
            if (entitlements == null) {
                throw new Exception("Invalid Entitlements");
            }
            inputABFs = Arrays.asList(ABFs);
            Subject subject = entitlements.getSubject();
            if (subject != null && subject.getABFs() != null && subject.getABFs().containsAll(inputABFs))
                return true;

            List<Resource> resources = entitlements.getResources();

            if (resourceId!= null && resources != null)   {
                for (Iterator<Resource> iterator = resources.iterator(); iterator.hasNext(); ) {
                    Resource resource = iterator.next();
                    if (! resourceId.equals(resource.getId())) continue;
                    if (resource.getABFs()!= null && resource.getABFs().containsAll(inputABFs))  {
                        return true;
                    }
                    List<Resource> subResources = resource.getSubResources();
                    if (subResourceId != null && subResources != null) {
                        for (Iterator<Resource> j = subResources.iterator(); j.hasNext(); ) {
                            Resource subResource = j.next();
                            if (!subResourceId.equals(subResource.getId())) continue;
                            if (subResource.getABFs() != null && subResource.getABFs().containsAll(inputABFs))
                                return true;
                        }
                    }
                }
            }
        } catch (Exception ex)  {
            LOG.error(ex.getMessage(), ex);

        }
        return false;
    }


    /**
     *
     * @param resourceId
     * @param subResourceId
     * @param entitlements
     * @param ABF
     * @param authzAmount
     * @return
     */
    public static boolean isAuthorized(String resourceId, String subResourceId, Entitlements entitlements, String ABF, int authzAmount)  {
        try {
            if (entitlements == null) {
                throw new Exception("Invalid Entitlements");
            }
            Pattern ABFPattern = null;
            String regexABF = ABF;
            if (authzAmount > 0)
                regexABF = regexABF +"\\$\\d*\\$";

            ABFPattern = Pattern.compile(regexABF);

            Subject subject = entitlements.getSubject();
            if (subject != null && subject.getABFs() != null && patternMatch(ABFPattern, subject.getABFs(), authzAmount ) )
                return true;


            List<Resource> resources = entitlements.getResources();

            if (resourceId!= null && resources != null)   {
                for (Iterator<Resource> resource = resources.iterator(); resource.hasNext(); ) {
                    Resource ac = resource.next();
                    if (! resourceId.equals(ac.getId())) continue;
                    if (ac.getABFs()!= null && patternMatch(ABFPattern,ac.getABFs(), authzAmount ))  {
                        return true;
                    }
                    List<Resource> subResources = ac.getSubResources();
                    if (subResourceId != null && subResources != null) {
                        for (Iterator<Resource> j = subResources.iterator(); j.hasNext(); ) {
                            Resource subResource = j.next();
                            if (!subResourceId.equals(subResource.getId())) continue;
                            if (subResource.getABFs() != null && patternMatch(ABFPattern,subResource.getABFs(), authzAmount ))
                                return true;
                        }
                    }
                }
            }
        } catch (Exception ex)  {
            LOG.error(ex.getMessage(), ex);

        }
        return false;
    }

    private static boolean patternMatch(Pattern ABFPattern, List<String> lstABFs, int authzAmount)  {

        String ABF = null;
        for (int i = 0; i < lstABFs.size(); i++) {
            ABF = lstABFs.get(i);
            if (ABFPattern.matcher(ABF).matches())  {
                if (authzAmount>0) {
                    int ABFLimit = Integer.valueOf(ABF.substring(ABF.indexOf("$") + 1, ABF.indexOf("$")));
                    if (authzAmount <= ABFLimit)
                        return true;
                    break;
                }
                return false;
            }

        }
        return false;
    }
}
