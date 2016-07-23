package com.bugtech;

import java.io.UnsupportedEncodingException;

public class MessageHeader {

    public static int messageHeadLength = 16;


    boolean isValid = false;
    int n;
    int n2;
    int arg2;

    public MessageHeader (byte[] array) {

        setIsValid(array);

        n = ((array[7] << 24 & 0xFF000000) | (array[6] << 16 & 0xFF0000) | (array[5] << 8 & 0xFF00) | (array[4] & 0xFF));
        n2 = ((array[11] << 24 & 0xFF000000) | (array[10] << 16 & 0xFF0000) | (array[9] << 8 & 0xFF00) | (array[8] & 0xFF));
        arg2 = ((array[15] << 24 & 0xFF000000) | (array[14] << 16 & 0xFF0000) | (array[13] << 8 & 0xFF00) | (array[12] & 0xFF));
    }

    public boolean IsValid ()
    {
        return isValid;
    }

    void setIsValid(byte[] array) {

        byte[] begin = new byte[4];
        System.arraycopy(array,0,begin,0,4);

        String beginStr = null;
        try {
            beginStr = new String(begin, "UTF-8");
            System.out.println(beginStr);
            if ( beginStr.equals("GCDH")) {
                isValid = true;
            }
            else {
                isValid = false;
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println(begin);
            e.printStackTrace();
            isValid = false;
        }

    }

    // message length
    public int getN() {
        return n;
    }

    // command id
    public int getN2() {
        return n2;
    }

    //
    public int getArg2() {
        return arg2;
    }
}
