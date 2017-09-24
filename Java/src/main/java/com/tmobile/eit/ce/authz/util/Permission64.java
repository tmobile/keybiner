package com.tmobile.eit.ce.authz.util;

import com.google.common.collect.ImmutableBiMap;
import com.tmobile.eit.ce.authz.reference.ABFCodeMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * Created by ksubram on 3/24/17.
 */
public class Permission64 {

    private static ImmutableBiMap<String,String> ABFMAPPER = ABFCodeMap.getABFMapper();

    /**
     *
     * @param ABFs
     * @return
     */
    public static List<String> encode(List<String> ABFs)   {

        BitSet[] bitSet  = new BitSet[(ABFMAPPER.size()/64)+1];

        for (int i = 0; i < ABFs.size(); i++) {
            String abfString = ABFMAPPER.get(ABFs.get(i));
            if(abfString==null) continue;
            Integer abfCode = Integer.parseInt(abfString);
            int bsGroup = abfCode /64;
            if (bitSet[bsGroup] == null) bitSet[bsGroup] = new BitSet(64);
            bitSet[bsGroup].set(abfCode);
        }
        String[] encodedPermissions = new String[bitSet.length];
        for (int i = 0; i < bitSet.length; i++) {
            if (bitSet[i] == null) continue;
            encodedPermissions[i] = Long.toString(convert(bitSet[i]));
        }
        return Arrays.asList(encodedPermissions);
    }

    /**
     *
     * @param ABFSetCodes
     * @return
     */
    public static List<String> decode(List<String> ABFSetCodes)   {

        List<String> abfStrings = new ArrayList<>();

        for (int i = 0; i < ABFSetCodes.size(); i++) {
            if(ABFSetCodes.get(i) == null ) continue;
            BitSet bs = convert(Long.parseLong(ABFSetCodes.get(i)));
            for (int j = 0; j < bs.length(); j++) {
                if (bs.get(j)==true) {
                    abfStrings.add(ABFMAPPER.inverse().get(j + (64 * i) + ""));
                }
            }
        }
        return abfStrings;
    }

    /**
     *
     * @param ABFSetCodes
     * @return
     */
    public static String[] decodePermissions(String ABFSetCodes)   {

        List<String> abfStrings = new ArrayList<>();
        String[] abfSetCodes = ABFSetCodes.split(",");


        for (int i = 0; i < abfSetCodes.length; i++) {
            BitSet bs = convert(Long.parseLong(abfSetCodes[i]));
            for (int j = 0; j < bs.length(); j++) {
                if (bs.get(j)==true) {
                    abfStrings.add(ABFMAPPER.inverse().get(j + (64 * i) + ""));
                }
            }
        }
        return abfStrings.toArray(new String[0]);
    }

    /**
     *
     * @param ABFs
     * @return
     */
    public static String encodePermissions(String[] ABFs)   {
        StringBuilder encodedPermission = new StringBuilder();

        BitSet[] bitSet  = new BitSet[(ABFMAPPER.size()/64)+1];

        for (int i = 0; i < ABFs.length; i++) {
            String abfString = ABFMAPPER.get(ABFs[i]);
            Integer abfCode = Integer.parseInt(abfString);
            int bsGroup = abfCode /64;
            if (bitSet[bsGroup] == null) bitSet[bsGroup] = new BitSet(64);
            bitSet[bsGroup].set(abfCode);
        }
        for (int i = 0; i < bitSet.length; i++) {
            if (bitSet[i] == null) continue;
            if (i != 0 && encodedPermission.length()>0) encodedPermission.append(",");
            encodedPermission.append(convert(bitSet[i]));

        }
        return encodedPermission.toString();
    }

    private static long convert(BitSet bits) {
        long value = 0L;
        for (int i = 0; i < bits.length(); ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return value;
    }

    private static BitSet convert(long value) {
        BitSet bits = new BitSet();
        int index = 0;
        while (value != 0L) {
            if (value % 2L != 0) {
                bits.set(index);
            }
            ++index;
            value = value >>> 1;
        }
        return bits;
    }
}
