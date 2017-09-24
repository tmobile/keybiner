package com.tmobile.eit.ce.authz.util;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Compression util class which leverage JDK deflate and inflate. Also it provides, Base64 encoded string.
 * Created by ksubram on 3/29/17.
 */
public class Compression {

    public static String compress(String stringToCompress) throws UnsupportedEncodingException
    {
        byte[] compressedData = new byte[1024];
        byte[] stringAsBytes = stringToCompress.getBytes("UTF-8");

        Deflater compressor = new Deflater();
        compressor.setInput(stringAsBytes);
        compressor.finish();
        int compressedDataLength = compressor.deflate(compressedData);

        byte[] bytes = Arrays.copyOf(compressedData, compressedDataLength);
        return Base64.encodeBase64String(bytes);
    }

    public static String decompressToString(String base64String) throws UnsupportedEncodingException, DataFormatException
    {
        byte[] compressedData = Base64.decodeBase64(base64String);

        Inflater deCompressor = new Inflater();
        deCompressor.setInput(compressedData, 0, compressedData.length);
        byte[] output = new byte[1024];
        int decompressedDataLength = deCompressor.inflate(output);
        deCompressor.end();

        return new String(output, 0, decompressedDataLength, "UTF-8");
    }
}
