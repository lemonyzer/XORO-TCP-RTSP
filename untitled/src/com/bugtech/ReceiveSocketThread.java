package com.bugtech;

import java.io.InputStream;

/**
 * Created by it on 10.07.2016.
 */
public class ReceiveSocketThread extends Thread {

    InputStream inStream;

    public ReceiveSocketThread(final InputStream inStream) {

        this.inStream = inStream;
    }

}
