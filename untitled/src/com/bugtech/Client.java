package com.bugtech;

import com.jcraft.jzlib.GsZilb;
import mktvsmart.screen.GMScreenGlobalInfo;
import mktvsmart.screen.GsMobileLoginInfo;
import mktvsmart.screen.channel.ChannelData;
import mktvsmart.screen.dataconvert.model.DataConvertChannelModel;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import sun.plugin2.message.Message;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static java.io.FileDescriptor.in;

/**
 * Created by it on 10.07.2016.
 */
public class Client {
    private JPanel panelMain;
    private JPanel panelSouth;
    private JPanel panelEast;
    private JPanel panelNorth;
    private JPanel panelWest;
    private JPanel panelCenter;
    private JList listRecvPaket;
    private JTextArea textArea1;
    private JTextField tfIP;
    private JTextField tfPort;
    private JButton btnConnect;
    private JButton btnAutoSend;
    private JList listCommands;
    private JButton btnDisconnect;
    private JList listXmlFiles;
    private JButton XMLTestButton;
    private JTextArea textAreaCursor;
    private JTextArea textAreaIterator;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JList listSat;
    private JList listTp;
    private JList listChan;
    private JTextArea textAreaCommands;
    private JTabbedPane tabbedPane3;

    private String ip, port;
    private int iPort;
    private Socket socket = new Socket();
    private InputStream in;
    private OutputStream out;

    public Client() {
        btnAutoSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AutoSend();
            }
        });
        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectToXORO();
            }
        });
        btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisconnectFromXORO();
            }
        });
        XMLTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                XmlTest();
            }
        });
        listCommands.addComponentListener(new ComponentAdapter() {
        });
        listCommands.addContainerListener(new ContainerAdapter() {
        });
        listCommands.addFocusListener(new FocusAdapter() {
        });
        listCommands.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                RemoveSelectedFromList();
            }
        });
    }

    public void RemoveSelectedFromList ()
    {
        if(!listCommands.isSelectionEmpty()) {
            //listCommands.remove(listCommands.getSelectedIndex());
        }
    }

    public void XmlTest ()
    {
        String xmlFile = (String) listXmlFiles.getSelectedValue();
        if (xmlFile == null)
            xmlFile = (String) listXmlFiles.getModel().getElementAt(0);

        xmlFile = System.getProperty("user.dir") + "\\src\\com\\bugtech\\" + new File(xmlFile);

        try {
            XmlPullParserIteratorTest(xmlFile, textAreaIterator);
            XmlPullParserCursorTest(xmlFile, textAreaCursor);
            //XmlDOMTest(xmlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }

    public void XmlDOMTest ( String xmlFile ) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse( xmlFile );
            System.out.println( document.getFirstChild().getTextContent() );
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void XmlPullParserCursorTest(String xmlFile, JTextArea textArea) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(
                new FileInputStream( xmlFile ) );
        StringBuilder spacer = new StringBuilder();

        textArea.append("\nCursor\n\n");

        while ( parser.hasNext() )
        {
            textArea.append( "Event: " + parser.getEventType() );
            switch ( parser.getEventType() )
            {
                case XMLStreamConstants.START_DOCUMENT:
                    textArea.append( "START_DOCUMENT: " + parser.getVersion() + "\n");
                    break;

                case XMLStreamConstants.END_DOCUMENT:
                    textArea.append( "END_DOCUMENT: " + "\n");
                    parser.close();
                    break;

                case XMLStreamConstants.NAMESPACE:
                    textArea.append( "NAMESPACE: " + parser.getNamespaceURI() + "\n");
                    break;

                case XMLStreamConstants.START_ELEMENT:
                    spacer.append( "  " );
                    textArea.append( spacer + "START_ELEMENT: " + parser.getLocalName() + "\n");

                    // Der Event XMLStreamConstants.ATTRIBUTE wird nicht geliefert!
                    for ( int i = 0; i < parser.getAttributeCount(); i++ )
                        textArea.append( spacer + "  Attribut: "
                                + parser.getAttributeLocalName( i )
                                + " Wert: " + parser.getAttributeValue( i ) + "\n");
                    break;

                case XMLStreamConstants.CHARACTERS:
                    if ( ! parser.isWhiteSpace() )
                        textArea.append( spacer + "  CHARACTERS: " + parser.getText() + "\n");
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    textArea.append( spacer + "END_ELEMENT: " + parser.getLocalName() + "\n");
                    spacer.delete( (spacer.length() - 2), spacer.length() );
                    break;

                default:
                    break;
            }
            parser.next();
        }
    }

    public void XmlPullParserIteratorTest(String xmlFile, JTextArea textArea) throws FileNotFoundException, XMLStreamException {
        InputStream in = new FileInputStream( xmlFile );
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader parser = factory.createXMLEventReader( in );

        StringBuilder spacer = new StringBuilder();
        textArea.append("\nIterator\n\n");
        while ( parser.hasNext() )
        {
            XMLEvent event = parser.nextEvent();
            //textArea.append( "Event: " + event.getEventType() );

            switch ( event.getEventType() )
            {
                case XMLStreamConstants.START_DOCUMENT:
                    textArea.append( "START_DOCUMENT:" + "\n");
                    break;
                case XMLStreamConstants.END_DOCUMENT:
                    textArea.append( "END_DOCUMENT:" + "\n");
                    parser.close();
                    break;
                case XMLStreamConstants.START_ELEMENT:
                    StartElement element = event.asStartElement();
                    textArea.append( spacer.append("  ")
                            + "START_ELEMENT: "
                            + element.getName() + "\n");

                    event = parser.nextEvent();

                    textArea.append( spacer.append("  ")
                            + "CHARACTERS: "
                            + event.asCharacters().getData() + "\n");

                    for (Iterator<?> attributes = element.getAttributes();
                         attributes.hasNext(); )
                    {
                        Attribute attribute = (Attribute) attributes.next();
                        textArea.append( spacer + "  Attribut: "
                                + attribute.getName() + " Wert: "
                                + attribute.getValue() + "\n");
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if ( ! characters.isWhiteSpace() )
                        textArea.append( spacer
                                + "  CHARACTERS: "
                                + characters.getData() + "\n");
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    textArea.append( spacer
                            + "END_ELEMENT: "
                            + event.asEndElement().getName() + "\n");
                    spacer.delete( (spacer.length() - 2), spacer.length() );
                    break;
                case XMLStreamConstants.ATTRIBUTE:
                    break;

                default :
                    break;
            }
        }
    }

    private void DisconnectFromXORO() {
        Log("Disconnecting from XORO: " + ip + ":" + iPort + " ...");

        if (socket != null) {
            if (socket.isConnected()) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log("Disconnecting from XORO: " + ip + ":" + iPort + " failed\n"+e);

                }
            }
        }
    }

    public void ConnectToXORO() {
        ip =  tfIP.getText();
        port = tfPort.getText();
        iPort = Integer.parseInt(port);

        Log("Connecting to XORO: " + ip + ":" + iPort + " ...");

        try {
            socket = new Socket(ip, iPort);
            in = socket.getInputStream();
            out = socket.getOutputStream();
            Log("Connected to XORO: " + ip + ":" + iPort + " successful");

        } catch (IOException e) {
            e.printStackTrace();
            Log("Connecting to XORO: " + ip + ":" + iPort + " failed\n" + e);
        }

    }

    public void Log(String msg) {

        System.out.println(msg);
        textArea1.append( "Log " + msg + "\n");
    }

    public void AutoSend() {
        boolean firstMessage = false;

        // http://stackoverflow.com/questions/20432404/lines-of-jtextarea-to-an-arrayliststring

        String s[] = textAreaCommands.getText().split("\\r?\\n");
        ArrayList<String> cmdList = new ArrayList<>(Arrays.asList(s));


        for (int i=0; i< cmdList.size(); i++)
        {
            if(!cmdList.get(i).toString().startsWith("Start") &&
                    !cmdList.get(i).toString().startsWith("<?xml"))
            {
                Log("AutoSend canceled @ Line " + (i+1));
                DisconnectFromXORO();
                return;
            }

            if(i==0) {
                firstMessage = true;
            }
            else
                firstMessage = false;

            SendMessage(GetCommandString(cmdList.get(i).toString()));
            textArea1.append( "RECEIVE MSG " + i + "\n");
            ReceiveMessage(firstMessage);
            textArea1.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            textArea1.repaint();
        }

//        for (int i=0; i< listCommands.getModel().getSize(); i++)
//        {
//            if(!listCommands.getModel().getElementAt(i).toString().startsWith("Start") &&
//                    !listCommands.getModel().getElementAt(i).toString().startsWith("<?xml"))
//            {
//                Log("AutoSend canceled @ Line " + i+1);
//                DisconnectFromXORO();
//                return;
//            }
//
//            if(i==0) {
//                firstMessage = true;
//            }
//            else
//                firstMessage = false;
//
//            SendMessage(GetCommandString(listCommands.getModel().getElementAt(i).toString()));
//            textArea1.append( "RECEIVE MSG " + i + "\n");
//            ReceiveMessage(firstMessage);
//            textArea1.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
//            textArea1.repaint();
//        }
    }

    int messageHeadLength = 16;

    public class MessageHeader {
        int n;
        int n2;
        int arg2;

        public MessageHeader (byte[] array) {

            if (new String(new StringBuilder().append((char)array[0]).append((char)array[1]).append((char)array[2]).append((char)array[3]).toString()).equals("GCDH")) {
                n = ((array[7] << 24 & 0xFF000000) | (array[6] << 16 & 0xFF0000) | (array[5] << 8 & 0xFF00) | (array[4] & 0xFF));
                n2 = ((array[11] << 24 & 0xFF000000) | (array[10] << 16 & 0xFF0000) | (array[9] << 8 & 0xFF00) | (array[8] & 0xFF));
                arg2 = ((array[15] << 24 & 0xFF000000) | (array[14] << 16 & 0xFF0000) | (array[13] << 8 & 0xFF00) | (array[12] & 0xFF));
            }
        }

        public int getN() {
            return n;
        }

        public int getN2() {
            return n2;
        }

        public int getArg2() {
            return arg2;
        }
    }

    MessageHeader currentMsgHeader;
    GsMobileLoginInfo gsMobileLoginInfo;
    DataConvertChannelModel dcChannelModel;

    private void ReceiveMessage(boolean firstMessage) {

        if (!firstMessage) {

            // all Other Messages: zipped/deflated with JZipLib 1.1.0

            byte[] headerData;

            if ((headerData = ReceiveMessageHeader()) != null) {
                currentMsgHeader = new MessageHeader(headerData);

                byte[] rawMsgData = ReceiveMessageData(currentMsgHeader);
                //DisplayReceivedMessage(rawMsgData, currentMsgHeader);
                byte[] uncompressedMsg = UncompressCompleteMsgData(rawMsgData);
                DisplayReceivedMessage(uncompressedMsg, currentMsgHeader);

                switch (currentMsgHeader.getN2()) {
                    case 0:
                        ChannelData.getInstance().initChannelListData(uncompressedMsg);
                        // http://stackoverflow.com/questions/9509208/adding-objects-to-a-jlist
                        //listChan.setModel((ChannelData.getInstance().getmTvChannelList().toArray()));
                        for(int x=0; x<ChannelData.getInstance().getmTvChannelList().size(); x++)
                            System.out.println(ChannelData.getInstance().getmTvChannelList().get(x).toString());
                        break;
                    case 22:
                        ChannelData.getInstance().initSatList(uncompressedMsg);
                        for(int x=0; x<ChannelData.getInstance().getmAllSatList().size(); x++)
                            System.out.println(ChannelData.getInstance().getmAllSatList().get(x).toString());
                        break;
                    case 24:
                        ChannelData.getInstance().initTpList(uncompressedMsg);
                        for(int x=0; x<ChannelData.getInstance().getmAllTpList().size(); x++)
                            System.out.println(ChannelData.getInstance().getmAllTpList().get(x).toString());
                        break;
                    default:
                        break;
                }

            } else
                textArea1.append("RECEIVED MSG !!! FAILED !!!" + "\n");
        }
        else {

            // First Message (Scrambled)

            byte[] recvfirstMsg = ReceiveFirstMessage();
            DisplayReceivedFirstMessage(recvfirstMsg);
            scramble_stb_info_for_broadcast(recvfirstMsg,108);
            DisplayReceivedFirstMessage(recvfirstMsg);
            gsMobileLoginInfo = new GsMobileLoginInfo(recvfirstMsg);
            DisplayReceivedFirstMessage(gsMobileLoginInfo);
            preserveLoginInfo(gsMobileLoginInfo);
        }

    }

    private void preserveLoginInfo(GsMobileLoginInfo paramGsMobileLoginInfo)
    {
        //this.mEditLoginHistoryFile.putListToFile(paramGsMobileLoginInfo, this.mHistoryStbInfoList);
        GMScreenGlobalInfo.setmCurStbInfo(paramGsMobileLoginInfo);
        if(paramGsMobileLoginInfo.getSend_data_type() == 1)
            Log("Parser/Serializer Type: JsonParser");
        else {
            Log("Parser/Serializer Type: XmlParser");
        }

    }

    private void DisplayReceivedFirstMessage(GsMobileLoginInfo gsMobileLoginInfo) {
        textArea1.append( "RECEIVED MSG " + "\n");
        textArea1.append( "\tBEGIN DATA" + "\n");
        textArea1.append( "\t\tgetStb_sn_disp = " + gsMobileLoginInfo.getStb_sn_disp() + "\n");
        textArea1.append( "\t\tgetStb_sn = " + gsMobileLoginInfo.getStb_sn() + "\n");
        textArea1.append( "\t\tgetModel_name = " + gsMobileLoginInfo.getModel_name() + "\n");
        textArea1.append( "\t\tgetMagic_code = " + gsMobileLoginInfo.getMagic_code() + "\n");
        textArea1.append( "\t\tgetStb_ip_address_disp = " + gsMobileLoginInfo.getStb_ip_address_disp() + "\n");
        textArea1.append( "\t\tgetStb_ip_address = " + gsMobileLoginInfo.getStb_ip_address() + "\n");
        textArea1.append( "\t\tgetUpnpIp = " + gsMobileLoginInfo.getUpnpIp() + "\n");
        textArea1.append( "\t\tgetStb_cpu_chip_id = " + gsMobileLoginInfo.getStb_cpu_chip_id() + "\n");
        textArea1.append( "\t\tgetSend_data_type = " + gsMobileLoginInfo.getSend_data_type() + "\n");
        textArea1.append( "\t\tgetIs_current_stb_connected_full = " + gsMobileLoginInfo.getIs_current_stb_connected_full() + "\n");
        textArea1.append( "\t\tgetSend_data_type = " + gsMobileLoginInfo.getSend_data_type() + "\n");
        textArea1.append( "\tEND DATA" + "\n");
    }

    private byte[] ReceiveFirstMessage() {
        int messageDataLength = 108;
        int totalReceivedBytes = 0;
        int numOfBytesLeftToReceive = 108;
        byte[] completeMsgData = new byte[messageDataLength + 8];
        byte[] currentMsgData = new byte[2048];
        while (totalReceivedBytes < messageDataLength) {
            try {
                int receivedBytes = in.read(currentMsgData, 0, Math.min(numOfBytesLeftToReceive, 2048));
                if (receivedBytes != -1) {
                    System.arraycopy(currentMsgData, 0, completeMsgData, totalReceivedBytes, receivedBytes);
                    totalReceivedBytes += receivedBytes;
                    numOfBytesLeftToReceive -= receivedBytes;
                    Log(this.toString() + "RFM: receivedBytes=" + receivedBytes + " numOfBytesLeft=" + numOfBytesLeftToReceive);
                }
                else
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(totalReceivedBytes == messageDataLength)
        {
            return completeMsgData;
        }

        return null;
    }

    public static void scramble_stb_info_for_broadcast(byte[] a, int i)
    {
        int i0 = 0;
        while(i0 < i / 2)
        {
            int i1 = a[i - 1 - i0];
            int i2 = i - 1 - i0;
            int i3 = a[i0];
            a[i2] = (byte)i3;
            a[i0] = (byte)i1;
            int i4 = a[i0];
            int i5 = (byte)(i4 ^ 91);
            a[i0] = (byte)i5;
            int i6 = i - 1 - i0;
            int i7 = a[i - 1 - i0];
            int i8 = (byte)(i7 ^ 91);
            a[i6] = (byte)i8;
            i0 = i0 + 1;
        }
        if (i % 2 != 0)
        {
            int i9 = i / 2;
            int i10 = a[i / 2];
            int i11 = (byte)(i10 ^ 91);
            a[i9] = (byte)i11;
        }
    }

    private void DisplayReceivedFirstMessage(byte[] msgData) {
        textArea1.append( "\tRECEIVED MSG " + "\n");
        textArea1.append( "\t\tBEGIN DATA" + "\n");
        try {
            textArea1.append( new String(msgData, "UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            textArea1.append( "\t\t\tUTF-8 ecoding error"  + "\n");
        }
        textArea1.append( "\t\tEND DATA" + "\n");
    }

    private void DisplayReceivedMessage(byte[] msgData, MessageHeader msgHeader) {
        textArea1.append( "\tRECEIVED MSG " + "\n");
        textArea1.append( "\t\tn=  " + + msgHeader.getN() + "\n");
        textArea1.append( "\t\tn2=  " + + msgHeader.getN2() + "\n");
        textArea1.append( "\t\targ2=  " + + msgHeader.getArg2() + "\n");
        textArea1.append( "\t\tBEGIN DATA" + "\n");
        try {
            textArea1.append( new String(msgData, "UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            textArea1.append( "\t\t\tUTF-8 ecoding error"  + "\n");
        }
        textArea1.append( "\t\tEND DATA" + "\n");
    }

    private byte[] ReceiveMessageHeader() {
        int receivedBytes = 0;
        byte[] headerData = new byte[2048];
        try {
            receivedBytes = in.read(headerData, 0, messageHeadLength);
            if (receivedBytes != -1)
                return headerData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] ReceiveMessageData(MessageHeader msgHeader) {
        int messageDataLength = msgHeader.getN();
        int totalReceivedBytes = 0;
        int numOfBytesLeftToReceive = msgHeader.getN();
        byte[] completeMsgData = new byte[messageDataLength + 8];
        byte[] currentMsgData = new byte[2048];
        while (totalReceivedBytes < messageDataLength) {
            try {
                int receivedBytes = in.read(currentMsgData, 0, Math.min(numOfBytesLeftToReceive, 2048));
                if (receivedBytes != -1) {
                    System.arraycopy(currentMsgData, 0, completeMsgData, totalReceivedBytes, receivedBytes);
                    totalReceivedBytes += receivedBytes;
                    numOfBytesLeftToReceive -= receivedBytes;
                    Log(this.toString() + "RM: receivedBytes=" + receivedBytes + " numOfBytesLeft=" + numOfBytesLeftToReceive);
                }
                else
                    break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if(totalReceivedBytes == messageDataLength)
        {
            return completeMsgData;
        }

        return null;
    }

    byte[] UncompressCompleteMsgData(byte[] recvRawMsgData)
    {
        Log(this.toString() + "Start uncompress " + recvRawMsgData.toString());

        byte[] unCompress = null;
        if (recvRawMsgData.length > 8) {
            try {
                unCompress = GsZilb.UnCompress(recvRawMsgData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //obtain2.arg1 = unCompress.length;
        }

        Log(this.toString() + "Stop uncompress " + recvRawMsgData.toString());

        return unCompress;
    }


    void SendMessage (String msg) {
        textArea1.append( "TRY SENDING" + "\n\t" + msg + "\n");
        byte[] data = null;
        try {
            data = msg.getBytes("UTF-8");
            out.write(data, 0, data.length);
            out.flush();
            textArea1.append( "SENDING DONE" + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String GetCommandString(String command) {

        // Start
        // 0000156  // 7 #-Zeichen
        // End
        // https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html
        // http://stackoverflow.com/questions/473282/how-can-i-pad-an-integers-with-zeros-on-the-left
        try {
            command = command.split("End")[1];
        }catch (Exception e) {
            System.out.println(e.toString());
        }

        int length = command.length();
        String preString = String.format("Start%07dEnd", length);
        return preString+command;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client");
        frame.setContentPane(new Client().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
