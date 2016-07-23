package com.bugtech;

import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/**
 * Created by it on 14.07.2016.
 */
public class MyImplementations {

    public static byte[] UnCompress(byte[] compr) throws IOException, DataFormatException {
        try {
            // https://docs.oracle.com/javase/7/docs/api/java/util/zip/Deflater.html
            // Decompress the bytes
            Inflater decompresser = new Inflater();
            decompresser.setInput(compr, 0, compr.length);
            byte[] result = new byte[3145728];
            int resultLength = decompresser.inflate(result);
            decompresser.end();

            System.out.println("Inflater: total uncompressed data length = " + resultLength);

            byte[] uncompr = new byte[resultLength];
            System.arraycopy(result, 0, uncompr, 0, resultLength);

            return uncompr;
        }catch(DataFormatException a5)
        {
            a5.printStackTrace();
            throw a5;
        }


    }

}
