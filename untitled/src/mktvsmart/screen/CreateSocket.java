package mktvsmart.screen;

import java.io.IOException;

public class CreateSocket {
    private static java.net.Socket socketHandle;
    private String Address;
    private int Port;

    static
    {
        socketHandle = null;
    }

    public CreateSocket(String s, int i)
    {
        super();
        this.Address = s;
        this.Port = i;
    }

    public void DestroySocket() throws Exception {
        //monenter(this);
        java.net.Socket a = socketHandle;
        label1: {
            Exception a0 = null;
            if (a == null)
            {
                break label1;
            }
            label0: {
                try
                {
                    try
                    {
                        socketHandle.shutdownInput();
                        socketHandle.shutdownOutput();
                        socketHandle.close();
                    }
                    catch(Exception ignoredException)
                    {
                    }
                }
                catch(Exception a1)
                {
                    a0 = a1;
                    break label0;
                }
                socketHandle = null;
                break label1;
            }
            //monexit(this);
            throw a0;
        }
        //monexit(this);
    }

    public java.net.Socket GetSocket() throws IOException {
        //monenter(this);
        java.net.Socket a = socketHandle;
        label0: {
            IOException a0 = null;
            if (a != null)
            {
                break label0;
            }
            try
            {
                socketHandle = new java.net.Socket();
                socketHandle.connect((java.net.SocketAddress)new java.net.InetSocketAddress(this.Address, this.Port), 3000);
                break label0;
            }catch(IOException a1)
            {
                a0 = a1;
            }


            //monexit(this);
            throw a0;
        }
        java.net.Socket a2 = socketHandle;
        //monexit(this);
        return a2;
    }
}
