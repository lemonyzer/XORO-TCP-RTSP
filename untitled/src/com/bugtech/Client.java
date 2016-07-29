package com.bugtech;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp;
import com.sun.jndi.toolkit.url.Uri;
import mktvsmart.screen.CreateSocket;
import mktvsmart.screen.GMScreenGlobalInfo;
import mktvsmart.screen.GsMobileLoginInfo;
import mktvsmart.screen.GsSendSocket;
import mktvsmart.screen.channel.ChannelData;
import mktvsmart.screen.channel.Sat2ipUtil;
import mktvsmart.screen.dataconvert.model.*;
import mktvsmart.screen.dataconvert.parser.DataParser;
import mktvsmart.screen.dataconvert.parser.ParserFactory;
import mktvsmart.screen.dataconvert.parser.SerializedDataModel;
import mktvsmart.screen.exception.ProgramNotFoundException;
import mktvsmart.screen.pvr2small.Pvr2smallData;
import mktvsmart.screen.socketthread.UdpSocketReceiveBroadcastThread;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.zip.DataFormatException;

/**
 * Created by it on 10.07.2016.
 */
public class Client {

    private static Client INSTANCE;

    public static Client getInstance()
    {
        if(INSTANCE != null)
            return INSTANCE;

        return null;
    }

    public final static String BundleSentDataString = "SentData";

    public void AddSentCommand(SerializedDataModel a, int startIndex, int cmdLength, int whatCmdId) {
        Message currentSendingMessage = new Message();
        currentSendingMessage.what = whatCmdId;
        currentSendingMessage.arg1 = cmdLength;
        currentSendingMessage.obj = a.dataType;
        Bundle sentData = new Bundle();
        sentData.putByteArray(BundleSentDataString, a.serializedData);
        currentSendingMessage.setData(sentData);

        sentMessageListModel.addElement(currentSendingMessage);
    }

    public void AddSentCommand(byte[] a, int startIndex, int cmdLength, int whatCmdId) {
        Message currentSendingMessage = new Message();
        currentSendingMessage.what = whatCmdId;
        currentSendingMessage.arg1 = cmdLength;
//        currentSendingMessage.obj =
        Bundle sentData = new Bundle();
        sentData.putByteArray(BundleSentDataString, a);
        currentSendingMessage.setData(sentData);

        sentMessageListModel.addElement(currentSendingMessage);
    }

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
    private JPanel JPanelControls;
    private JButton btnGenerateCommands;
    private JTextArea textAreaGeneratedCommands;
    private JTextField tfGenCmdTo;
    private JTextField tfGenCmdFrom;
    private JCheckBox checkBoxCustomLoginProcess;
    private JButton btnStartStream;
    private JList listStbBroadcast;
    private JTabbedPane tabbedPane4;
    private JList listMessageReceived;
    private JList listMessageSent;
    private JButton btnStreamRequest;
    private JCheckBox checkBoxPlayType;
    private JTextArea textAreaRTSP;
    private JButton btnStopStreaming;
    private JButton btnStreamRequest1012;
    private JCheckBox checkBoxSetupBlocked;
    private JCheckBox checkBoxBuiltInPlayer;
    private static br.com.voicetechnology.rtspclient.test.Sat2IP_Rtsp sRtsp;


    private String ip;
    private int port;
    private Socket socket = new Socket();
    private InputStream in;
    private OutputStream out;

    public Client() {

        INSTANCE = this;

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
        listCommands.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                RemoveSelectedFromList();
            }
        });
        listChan.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!e.getValueIsAdjusting())
                    ChannelChangeRequest_1000();
            }

        });
        btnGenerateCommands.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GenerateCommands();
            }
        });

        mChannelData = ChannelData.getInstance();
        customChannelList = new ArrayList<DataConvertChannelModel>();

        UdpSocketReceiveBroadcastThread udpSocketReceiveBroadcastThread = new UdpSocketReceiveBroadcastThread(this);
        udpSocketReceiveBroadcastThread.start();

        stbDiscoveryListModel = new DefaultListModel<GsMobileLoginInfo>();
        stbDeviceDiscoveryList = new ArrayList<GsMobileLoginInfo>();

        InitMessageLists ();
        btnStartStream.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnPlayStreamClicked();
            }
        });
        btnStreamRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartStreamRequestClicked();
            }
        });
        btnStopStreaming.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopStream();
            }
        });
        btnStreamRequest1012.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStopStreamRequestClicked();
            }
        });
    }

    private void btnStopStreamRequestClicked() {

        if(!tcpSocket.isConnected())
            return;

        GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
    }

    private void btnStartStreamRequestClicked() {

        bPlayWithOherPlayer = true;

        int selectedIndex = listChan.getSelectedIndex();
        if( listChan.getModel().getSize() > 0 &&
                selectedIndex >= 0 &&
                selectedIndex < listChan.getModel().getSize()) {
            sendSat2ipChannelRequestToStb(selectedIndex);
        }
    }

    private void btnPlayStreamClicked() {

        if(!tcpSocket.isConnected())
            return;

        playStream(listChan.getSelectedIndex());

    }

    private void playStream(int value) {
//        final FindPlayerAndPlayChannel findPlayerAndPlayChannel = new FindPlayerAndPlayChannel((Context)this.getParent());
//        findPlayerAndPlayChannel.implementPlayByDesignatedPlayer(this.mPlayByDesignatedPlayer);
//        findPlayerAndPlayChannel.selectPlayer(n);
        if(checkBoxBuiltInPlayer.isSelected())
        {
            //com.mktech.player

            // private PlayByDesignatedPlayer mPlayByDesignatedPlayer = new 4(this);    (GsChannelListActivity$4)
            // playStream(int chanId)
            //      findPlayerAndPlayChannel = new FindPlayerAndPlayChannel((Context)this.getParent());
            //      findPlayerAndPlayChannel.implementPlayByDesignatedPlayer(mPlayByDesignatedPlayer)
            //      findPlayerAndPlayChannel.selectPlayer(int chanId)
            //          selectPlayer(int chanId)
            //              if(mPlayByDesignatedPlayer == mktech.player
            //                  otherPlatformPlay(int chanId)                                   (access$3)
            //              else
            //                  startPlayStream( int chanId, ... )                              (access$4)


            // playType = 1 ... ?
            GMScreenGlobalInfo.playType = 1;
            bPlayWithOherPlayer = true;
            GsPvr2SmallActivityStartPlayStream(value);
            // GsPvr2SmallAcitivity.startPlayStream(value);
        }
        else
        {
            // playType = 2 ... ?
            GMScreenGlobalInfo.playType = 2;
            // bPlayWithOherPlayer = false ???
            startPlayStream(value);
        }
    }

    private void otherPlatformPlay(int var1) {
//        var1 = this.mChannelData.getIndexByProgIdInCurTvRadioProgList(((DataConvertChannelModel)this.channelListAdapter.getItem(var1)).GetProgramId());
        GMScreenGlobalInfo.playType = 2;
        this.bPlayWithOherPlayer = false;
//        Intent var2 = new Intent("android.intent.action.VIEW");
//        var2.setClass(this.getParent(), LivePlayActivity.class);
//        var2.putExtra("position", var1);
//        var2.setFlags(268435456);
//        this.getApplication().startActivity(var2);
    }


    // GsPvr2SmallActivity.startPlayStream
    Pvr2smallData mPvr2SmallData;
    private void GsPvr2SmallActivityStartPlayStream(final int n) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                sRtsp=  new Sat2IP_Rtsp();
                //sRtsp.set_eof_listener((Sat2IP_Rtsp.EndOfFileListener));
//                if (mPvr2SmallData.getPvr2smallList().get(n).getmPvrCrypto() == 1) {
//                    //DVBtoIP.getChannelUserKey(tcpSocket.getInetAddress().toString().substring(1));
//                }
//                final String playUrlBase = Pvr2smallData.getInstance().getPlayUrlBase(n, tcpSocket.getInetAddress().toString());
//                final String playUrlQuery = Pvr2smallData.getInstance().getPlayUrlQuery();
                final String playUrlBase = Sat2ipUtil.getRtspUriBase(tcpSocket.getInetAddress().toString());
                final String playUrlQuery = Sat2ipUtil.getRtspUriQuery(mCurrentChannelList.get(n));
                if(checkBoxPlayType.isSelected())
                    GMScreenGlobalInfo.playType = 1;
                else
                    GMScreenGlobalInfo.playType = 2;

                if (!sRtsp.setup_blocked(playUrlBase, playUrlQuery)) {
                    Log.d("GsPvr2SmallActivityStartPlayStream", "setup_blocked FAILED");
//                    if (this.this$0.waitDialog.isShowing()) {
//                        this.this$0.waitDialog.dismiss();
//                    }
//                    final CommonErrorDialog commonErrorDialog = new CommonErrorDialog((Context)this.this$0.getParent());
//                    commonErrorDialog.setmContent(this.this$0.getResources().getString(2131427592));
//                    commonErrorDialog.show();
                    sRtsp.teardown();
                    sRtsp = null;
                    return;
                }
                Log.d("GsPvr2SmallActivityStartPlayStream", "setup_blocked SUCCESS");
                sendSat2ipChannelRequestToStb(n);


//                DVBtoIP.initResourceForPlayer(sRtsp.get_rtp_port(), FindPlayerAndPlayChannel.getRtspPipeFilePath((Context)this.this$0), 1);
//                this.runOnUiThread((Runnable)new Runnable() {
//                    final /* synthetic */ GsPvr2SmallActivity$11 this$1;
//                    private final /* synthetic */ Intent val$playIntent;
//
//                    GsPvr2SmallActivity$11$1() {
//                        this.this$1 = this$1;
//                        super();
//                    }
//
//                    @Override
//                    public void run() {
////                        if (this.this$1.this$0.waitDialog.isShowing()) {
////                            this.this$1.this$0.waitDialog.dismiss();
////                        }
////                        while (true) {
////                            try {
////                                this.this$1.this$0.startActivity(intent);
////                                Toast.makeText((Context)this.this$1.this$0, 2131427593, 1).show();
////                            }
////                            catch (ActivityNotFoundException ex) {
////                                System.out.println("MX Player activity not found");
////                                GsPvr2SmallActivity.this.stopStream();
////                                continue;
////                            }
////                            break;
////                        }
//                    }
//                });
            }
        }).start();
    }

    private void startPlayStream(final int n) {
//        if (this.waitDialog.isShowing()) {
//            this.waitDialog.dismiss();
//        }
//        this.waitDialog = DialogBuilder.showProgressDialog(this.getParent(), 2131427641, 2131427520, false, GMScreenGlobalInfo.getmWaitDialogTimeOut(), this.timeOutRun);
//        this.abtainPlayUrl(n, intent);
        abtainPlayUrl(n);
    }

//    private void abtainPlayUrl(int i)
//    {
//        new Thread((Runnable)(Object)new mktvsmart.screen.channel.GsChannelListActivity$14(this, i, a)).start();
//    }

    // Luyten
    private void abtainPlayUrl(final int n) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                    default: {
                        GMScreenGlobalInfo.playType = 2;
//                        sendSat2ipChannelRequestToStb(n);
//                        mPlayIntent = intent;
                        Log.d("abtainPlayUrl", "getCurStbPlatform = " + GMScreenGlobalInfo.getCurStbPlatform() + " is not implemented!");
                        break;
                    }
                    case 8:
                    case 9: {
                        sRtsp = new Sat2IP_Rtsp();
                        final String rtspUriBase = Sat2ipUtil.getRtspUriBase(tcpSocket.getInetAddress().toString());
                        final String rtspUriQuery = Sat2ipUtil.getRtspUriQuery(mCurrentChannelList.get(n));
                        Log.d("abtainPlayUrl", "rtspUriBase = "+ rtspUriBase);
                        Log.d("abtainPlayUrl", "rtspUriQuery = "+ rtspUriQuery);
                        if(checkBoxPlayType.isSelected())
                            GMScreenGlobalInfo.playType = 1;
                        else
                            GMScreenGlobalInfo.playType = 2;

                        if(checkBoxSetupBlocked.isSelected()){
                            if (!sRtsp.setup_blocked(rtspUriBase, rtspUriQuery)) {
                                Log.d("abtainPlayUrl", "setup_blocked FAILED");

                                if (((DataConvertChannelModel)listChan.getModel().getElementAt(n)).GetIsProgramScramble() == 0) {
                                    Log.d("abtainPlayUrl", "currentChannel isProgramScrambled == 0 -> msg.what 4");
//                                mainHandler.sendMessage(mainHandler.obtainMessage(4, (Object).this.getString(2131427592)));
                                }
                                sRtsp.teardown();
                                sRtsp = null;
                                return;
                            }
                            Log.d("abtainPlayUrl", "setup_blocked SUCCESS");
                            sendSat2ipChannelRequestToStb(n);
    //                        DVBtoIP.initResourceForPlayer(GsChannelListActivity.sRtsp.get_rtp_port(), FindPlayerAndPlayChannel.getRtspPipeFilePath((Context)GsChannelListActivity.this), 2);
    //                        intent.setDataAndType(Uri.parse("file://" + FindPlayerAndPlayChannel.getRtspPipeFilePath((Context)GsChannelListActivity.this)), "video/*");
    //                        GsChannelListActivity.this.mainHandler.sendMessage(GsChannelListActivity.this.mainHandler.obtainMessage(3, (Object)intent));
                            break;
                        }
                        else{
                            // not blocked
                            sRtsp.setup_not_blocked(rtspUriBase, rtspUriQuery);
                            sendSat2ipChannelRequestToStb(n);
                            break;
                        }
                    }
                }
                bPlayWithOherPlayer = true;
            }
        }).start();
    }

    boolean bPlayWithOherPlayer = false;

    void InitMessageLists() {
        // receiving
        receiveMessageListModel = new DefaultListModel<Message>();
        receiveMessageList = new ArrayList<Message>();
        listMessageReceived.setModel(receiveMessageListModel);
        // sending
        sentMessageListModel = new DefaultListModel<Message>();
        sentMessageList = new ArrayList<Message>();
        listMessageSent.setModel(sentMessageListModel);
    }

    DefaultListModel<Message> receiveMessageListModel;
    List<Message> receiveMessageList;
    DefaultListModel<Message> sentMessageListModel;
    List<Message> sentMessageList;

    DefaultListModel<GsMobileLoginInfo> stbDiscoveryListModel;
    List<GsMobileLoginInfo> stbDeviceDiscoveryList;

    public void AddDiscoveredStbDeviceToList(GsMobileLoginInfo stbDevice) {
        stbDeviceDiscoveryList.add(stbDevice);
        InitJList(stbDeviceDiscoveryList,listStbBroadcast,stbDiscoveryListModel);
    }

    public void UpdateDiscoveredStbList(List<GsMobileLoginInfo> newStbDeviceList) {
        stbDeviceDiscoveryList = newStbDeviceList;
        InitJList(stbDeviceDiscoveryList,listStbBroadcast,stbDiscoveryListModel);
    }

    public void GenerateCommands() {

        int from = Integer.parseInt(tfGenCmdFrom.getText());
        int to = Integer.parseInt(tfGenCmdTo.getText());

        StringBuilder strBuider = new StringBuilder();

        // clear
        textAreaGeneratedCommands.setText("");

        for(int i=from; i< to; i++) {

            switch (i)
            {
                case 0:
                    // Channel List
                    continue;
                case 12:
                    // Fav List
                    continue;
                case 20:
                    // Control Model
                    continue;
                case 22:
                    // Sat List
                    continue;
                case 24:
                    // Transponder List
                    continue;
                default:
                    break;
            }

            //String cmd = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request=\""+ i + "\"><parm></parm></Command>";
            String cmd = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request=\"" + i + "\" />";
            textAreaGeneratedCommands.append(cmd + "\n");

        }

    }

    public void ChannelChangeRequest_1000 () {
        if(!listChan.isSelectionEmpty()) {
            DataConvertChannelModel currentPlayingProgram = ChannelData.getInstance().getCurrentPlayingProgram();
            if(currentPlayingProgram != null) {
                DataConvertChannelModel reqChannel = (DataConvertChannelModel) listChan.getSelectedValue();
                if(currentPlayingProgram.GetProgramId() == reqChannel.GetProgramId()) {
                    return;
                }
                else {
                    Log( "------------------------------------------------------------------------------------\n" +
                            "ProgramId = " + reqChannel.GetProgramId() +
                            "\nProgramName = " + reqChannel.getProgramName() +
                            "\nSat " + mChannelData.GetSatSubStringByPrgoramId(reqChannel.GetProgramId()) +
                            "\nSat " + mChannelData.getmAllSatList().get( Integer.parseInt (mChannelData.GetSatSubStringByPrgoramId (reqChannel.GetProgramId ()) ) ).toString() +
//                            "\nSat " + satList.get ( Integer.parseInt (mChannelData.GetSatSubStringByPrgoramId (reqChannel.GetProgramId ()) ) ).toString() +
                            "\n------------------------------------------------------------------------------------"
                    );
                    // send cmd 1000 change programm
//                    String cmd = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request=\"1000\"><parm><TvState>0</TvState><ProgramId>" + reqChannel.GetProgramId()  +"</ProgramId></parm></Command>";
//                    try {
//                        socket.setSoTimeout(3000);
//                    } catch (SocketException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        byte[] changeChannelCmd = cmd.getBytes("UTF-8");
//                        GsSendSocket.sendSocketToStb(changeChannelCmd, socket, 0, changeChannelCmd.length, 1000);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
                    trueNewChannelInStb(reqChannel.GetProgramIndex());
                }
            }
        }
    }

    public void ChannelChangeRequest () {
        if(!listChan.isSelectionEmpty()) {
            DataConvertChannelModel currentPlayingProgram = ChannelData.getInstance().getCurrentPlayingProgram();
            if(currentPlayingProgram != null) {
                DataConvertChannelModel reqChannel = (DataConvertChannelModel) listChan.getSelectedValue();
                if(currentPlayingProgram.GetProgramId() == reqChannel.GetProgramId()) {
                    return;
                }
                else {
                    Log( "ProgramId = " + reqChannel.GetProgramId() + "\nProgramName = " + reqChannel.getProgramName());
                    // send cmd 1000 change programm
                    // <?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request="1000"><parm><TvState>0</TvState><ProgramId>00030028110301</ProgramId></parm></Command>
                    // <?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request="1000"><parm><TvState>0</TvState><ProgramId>00030031912060</ProgramId></parm></Command>
                    String cmd = "<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request=\"1000\"><parm><TvState>0</TvState><ProgramId>" + reqChannel.GetProgramId()  +"</ProgramId></parm></Command>";
                    SendMessage(cmd);
                }
            }
        }
    }

    // switch program request 1000
    private void trueNewChannelInStb(int i)
    {
        try
        {
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a = (mktvsmart.screen.dataconvert.model.DataConvertChannelModel)this.listChan.getSelectedValue();
            String s = a.getProgramName();
            String s0 = a.GetProgramId();
            mktvsmart.screen.dataconvert.model.DataConvertChannelModel a0 = new mktvsmart.screen.dataconvert.model.DataConvertChannelModel();
            java.util.ArrayList a1 = new java.util.ArrayList();
            this.parser = mktvsmart.screen.dataconvert.parser.ParserFactory.getParser();
            a0.SetProgramIndex(i);
            a0.setProgramName(s);
            a0.SetProgramId(s0);
            a0.setChannelTpye(a.getChannelTpye());
            ((java.util.List)(Object)a1).add((Object)a0);
            SerializedDataModel serializedDataModel = this.parser.serialize((java.util.List)(Object)a1, 1000);

//            byte[] a2 = this.parser.serialize((java.util.List)(Object)a1, 1000).getBytes("UTF-8");
            this.tcpSocket.setSoTimeout(3000);
            mktvsmart.screen.GsSendSocket.sendSocketToStb(serializedDataModel, this.tcpSocket, 0, 1000);
//            mktvsmart.screen.GsSendSocket.sendSocketToStb(a2, this.tcpSocket, 0, a2.length, 1000);
        }
        catch(Exception a3)
        {
            a3.printStackTrace();
            return;
        }
    }

// start stream request 1009
    private void sendSat2ipChannelRequestToStb(int i)
    {
        java.util.ArrayList a = new java.util.ArrayList();
        this.currentSat2ipChannelProgramId = ((DataConvertChannelModel)this.listChan.getModel().getElementAt(i)).GetProgramId();
        label0: {
            mktvsmart.screen.exception.ProgramNotFoundException a0 = null;
            try
            {
                try
                {
                    a.add(this.listChan.getModel().getElementAt(i));
                    SerializedDataModel serializedDataModel = this.parser.serialize((java.util.List)(Object)a, 1009);
//                    byte[] a1 = this.parser.serialize((java.util.List)(Object)a, 1009).getBytes("UTF-8");
                    byte[] a1 = serializedDataModel.serializedDataAsString.getBytes("UTF-8");
                    this.tcpSocket.setSoTimeout(3000);
//                    mktvsmart.screen.GsSendSocket.sendSocketToStb(a1, this.tcpSocket, 0, a1.length, 1009);
                    mktvsmart.screen.GsSendSocket.sendSocketToStb(serializedDataModel, this.tcpSocket, 0, 1009);
                    break label0;
                }
                catch(mktvsmart.screen.exception.ProgramNotFoundException a2)
                {
                    a0 = a2;
                }
            }
            catch(Exception a3)
            {
                a3.printStackTrace();
                return;
            }
            a0.printStackTrace();
            return;
        }
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
        Log("Disconnecting from XORO: " + ip + ":" + port + " ...");

        if (socket != null) {
            if (socket.isConnected()) {
                try {
                    if(receiveSocketThread != null) {
                        receiveSocketThread.interrupt();
                        receiveSocketThread.stop();
                    }
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log("Disconnecting from XORO: " + ip + ":" + port + " failed\n"+e);

                }
            }
        }
    }

    public void ConnectToXORO() {
        ip =  tfIP.getText();
        port = Integer.parseInt(tfPort.getText());

        Log("Connecting to XORO: " + ip + ":" + port + " ...");

        try {

            socket = new CreateSocket(ip, port).GetSocket();
            Log("Connected to XORO: " + ip + ":" + port + " successful");

            socket.setSoTimeout(4000);

            in = socket.getInputStream();
            out = socket.getOutputStream();


            Log("Login to XORO: " + ip + ":" + port);

            // login string
            // iOS
            // Start0000081End<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request="998" />
            // Android
            //Start0000156End<?xml version='1.0' encoding='UTF-8' standalone='yes' ?><Command request="998"><data>GT-I9100</data><uuid>359778043456670-98:0C:82:A4:AC:4C</uuid></Command>

            ArrayList loginDataList = new ArrayList();
            HashMap loginData = new HashMap();
            loginData.put("data", "GT-I9100");
            loginData.put("uuid", "123456789012345-98:0C:82:AA:AA:AA");
            //loginData.put("uuid", "359778043456670-98:0C:82:A4:AC:4C");

            loginDataList.add(loginData);

            SerializedDataModel loginDataModel;
            loginDataModel = new mktvsmart.screen.dataconvert.parser.XmlParser().serialize((java.util.List)(Object)loginDataList, 998);
//            byte[] loginCmd = new mktvsmart.screen.dataconvert.parser.XmlParser().serialize((java.util.List)(Object)loginDataList, 998).getBytes();
            byte[] loginCmd = loginDataModel.serializedDataAsString.getBytes();

//            GsSendSocket.sendSocketToStb(loginCmd, socket, 0, loginCmd.length, 998);
            GsSendSocket.sendSocketToStb(loginDataModel, socket, 0, 998);

            Thread.sleep(300L);

            ReceiveLoginResponse();


        } catch (IOException e) {
            e.printStackTrace();
            Log("Connecting to XORO: " + ip + ":" + port + " failed\n" + e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log("Connect Thread interrupted");
        }

        if(gsMobileLoginInfo != null) {

            Log("Login to XORO: " + ip + ":" + port + " successful");
            LoginSuccessful();
        }
    }

    public void DisplayMesssage(Message msg)
    {
        textArea1.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        textArea1.append( "\tRECEIVED MSG " + "\n");
        textArea1.append( "\t\twhat=  " + + msg.what + "\n");
        textArea1.append( "\t\targ1=  " + + msg.arg1 + "\n");
        textArea1.append( "\t\targ2=  " + + msg.arg2 + "\n");
        textArea1.append( "\t\tBEGIN DATA" + "\n");
        try {
            if(msg.getData() != null)
                if(msg.getData().getByteArray("ReceivedData") != null)
                    textArea1.append( new String(msg.getData().getByteArray("ReceivedData"), "UTF-8") + "\n");
                else
                    textArea1.append( "NO 'ReceivedData' DATA\n");
            else
                textArea1.append( "NO DATA\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            textArea1.append( "\t\t\tUTF-8 ecoding error"  + "\n");
        }
        textArea1.append( "\t\tEND DATA" + "\n");
    }

    ReceiveSocketThread receiveSocketThread;

    public void LoginSuccessful()
    {
        try {
            tcpSocket = new CreateSocket((String)null, 0).GetSocket();
            receiveSocketThread = new ReceiveSocketThread(tcpSocket.getInputStream(), this);
            receiveSocketThread.start();

            if (!checkBoxCustomLoginProcess.isSelected())
                RequestInitializationData(tcpSocket);
            else
                CustomCommands(tcpSocket);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CustomCommands(Socket socket) {
        AutoSend();
    }


    public void RequestInitializationData(Socket socket) {

        // 998: available Login Data
        //      getSend_data_type = 0
        //      platform_id
        //      getmSatEnable
        //      ...

        // 23:  Max Channel per Request == 100

        // 16:  cur_channel_list_type
        //      cur_channel_type
        //      ...

        // 20:  pwd_lock_mode
        //      ...

        // 22:  Sat List

        // 24:  Tp List

        // 0:   Channel List part1

        // 12:  Channel List Types - Fav Groupes


        GsSendSocket.sendOnlyCommandSocketToStb(socket,23);     // Max Channel per Request == 100

        /* GsSendSocket.sendOnlyCommandSocketToStb(socket,23);
        <?xml version="1.0" encoding="UTF-8"?>
        <Command>
          <parm>
            <Data>100</Data>
          </parm>
        </Command>
        */

        GsSendSocket.sendOnlyCommandSocketToStb(socket,16);

        /* GsSendSocket.sendOnlyCommandSocketToStb(socket,16);
        <?xml version="1.0" encoding="UTF-8"?>
        <Command>
          <parm>
            <cur_channel_list_type>0</cur_channel_list_type>
            <max_password_num>4</max_password_num>
            <cur_channel_type>0</cur_channel_type>
            <is_support_pvr2ip_server>0</is_support_pvr2ip_server>
            <is_sds_open>0</is_sds_open>
          </parm>
        </Command>
        */

        GsSendSocket.sendOnlyCommandSocketToStb(socket,20);

        /* GsSendSocket.sendOnlyCommandSocketToStb(socket,20);

        <?xml version="1.0" encoding="UTF-8"?>
        <Command>
          <parm>
            <PasswordLock>0</PasswordLock>
            <InstallationLock>1</InstallationLock>
            <EditChannelLock>1</EditChannelLock>
            <SettingsLock>1</SettingsLock>
            <AgeRating>0</AgeRating>
            <PowerMode>1</PowerMode>
          </parm>
        </Command>

         */

        if(GMScreenGlobalInfo.getCurStbInfo().getmSatEnable() == 1)
            GsSendSocket.sendOnlyCommandSocketToStb(socket,22);

        /* GsSendSocket.sendOnlyCommandSocketToStb(socket,22);

        <?xml version="1.0" encoding="UTF-8"?>
        <Command>
          <parm>
            <SatName>Nilesat</SatName>
            <SatNo>0</SatNo>
            <SatAngle>70</SatAngle>
            <SatDir>1</SatDir>
          </parm>
          <parm>
            <SatName>Badr 4/5/6</SatName>
            <SatNo>1</SatNo>
            <SatAngle>260</SatAngle>
            <SatDir>0</SatDir>
          </parm>
          <parm>
            <SatName>Hotbird</SatName>
            <SatNo>2</SatNo>
            <SatAngle>130</SatAngle>
            <SatDir>0</SatDir>
          </parm>
          <parm>
            <SatName>ASTRA</SatName>
            <SatNo>3</SatNo>
            <SatAngle>192</SatAngle>
            <SatDir>0</SatDir>
          </parm>
        </Command>

         */

        GsSendSocket.sendOnlyCommandSocketToStb(socket,24);

        /* GsSendSocket.sendOnlyCommandSocketToStb(socket,24);

        <?xml version="1.0" encoding="UTF-8"?>
        <Command>
          <parm>
            <TpIndex>0</TpIndex>
            <SatIndex>0</SatIndex>
            <SystemRate>22000</SystemRate>
            <Pol>1</Pol>
            <Fec>3</Fec>
            <Freq>10719</Freq>
          </parm>
        </Command>

         */

        requestProgramListFromTo(0, GMScreenGlobalInfo.getMaxProgramNumPerRequest() - 1 );

        GsSendSocket.sendOnlyCommandSocketToStb(socket,12);

        /* GsSendSocket.sendOnlyCommandSocketToStb(socket,12);

        <?xml version="1.0" encoding="UTF-8"?>
        <Command>
          <favMaxNum>8</favMaxNum>
          <parm>
            <favorGroupName>News</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Movies</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Music</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Sports</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Education</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Weather</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Children</favorGroupName>
          </parm>
          <parm>
            <favorGroupName>Culture</favorGroupName>
          </parm>
        </Command>

         */

    }

    public void Log(String msg) {

        System.out.println(msg);
        textArea1.append( "Log " + msg + "\n");
    }

    boolean useReceiveSocketThread = true;

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

//            if(i==0) {
//                firstMessage = true;
//            }
//            else
//                firstMessage = false;

            SendMessage(GetCommandString(cmdList.get(i).toString()));

            if(!useReceiveSocketThread) {
                textArea1.append("RECEIVE MSG " + i + "\n");
                ReceiveMessage(firstMessage);
                textArea1.append("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
                textArea1.repaint();
            }
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



    MessageHeader currentMsgHeader;
    GsMobileLoginInfo gsMobileLoginInfo;
    DataConvertChannelModel dcChannelModel;

    ChannelData mChannelData;
    List<DataConvertChannelModel> mCurrentChannelList;
    int currentChannelListType=0;
    boolean mGetChannelListWhenLogin = true;
    List<DataConvertChannelModel> mOriginalChannelListModels;
    int passwordType = 0;
    private DataParser parser;
    byte[] dataBuff;
    Socket tcpSocket;


    void inputPermissionPassword() {
        System.out.println("inputPermissionPassword ... not implemented");
    }

    private void setCurrentChannelListDispIndex() {
        for (int i = 0; i < this.mCurrentChannelList.size(); ++i) {
            this.mCurrentChannelList.get(i).setCurrentChannelListDispIndex(i + 1);
        }
    }

    private void requestProgramListFromTo(final int n, final int n2) {
        try {
            final ArrayList<DataConvertChannelModel> list = new ArrayList<DataConvertChannelModel>();
            final DataConvertChannelModel dataConvertChannelModel = new DataConvertChannelModel();
            final DataConvertChannelModel dataConvertChannelModel2 = new DataConvertChannelModel();
            dataConvertChannelModel.SetProgramIndex(n);
            dataConvertChannelModel2.SetProgramIndex(n2);
            list.add(dataConvertChannelModel);
            list.add(dataConvertChannelModel2);
            parser = ParserFactory.getParser();
//            GsSendSocket.sendSocketToStb(this.dataBuff = this.parser.serialize(list, 0).getBytes("UTF-8"), this.tcpSocket, 0, this.dataBuff.length, 0);
            GsSendSocket.sendSocketToStb(this.dataBuff = this.parser.serialize(list, 0).serializedDataAsString.getBytes("UTF-8"), this.tcpSocket, 0, this.dataBuff.length, 0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ReceiveChannelListProcess(final Message message) {
        if (message.arg1 > 0) {
            final int totalProgramNum = mChannelData.getTotalProgramNum();
            final byte[] byteArray = message.getData().getByteArray("ReceivedData");
            if (byteArray == null) {
                Log.e("GSChannelListActivity", "recvData = " + byteArray);
            }
            else {
                final List<DataConvertChannelModel> initChannelListData = this.mChannelData.initChannelListData(message);
//                final List<DataConvertChannelModel> initChannelListData = this.mChannelData.initChannelListData(byteArray);
//                if (this.waitDialog.isShowing()) {
//                    this.waitDialog.dismiss();
//                }
                if (initChannelListData.size() != 0 || totalProgramNum != this.mChannelData.getTotalProgramNum()) {
                    mCurrentChannelList = this.mChannelData.getChannelListByProgramType(this.mChannelData.getChannelListByTvRadioType(), this.currentChannelListType);
                    if (this.mCurrentChannelList == null || this.mCurrentChannelList.isEmpty()) {
                        mCurrentChannelList = this.mChannelData.getChannelListByProgramType(this.mChannelData.getChannelListByTvRadioType(), 0);
                        currentChannelListType = 0;
                    }
                    if (this.mGetChannelListWhenLogin) {
                        switch (DataConvertChannelTypeModel.getCurrent_channel_tv_radio_type()) {
                            case 0: {
                                //this.TypeSwitch.setText(this.getResources().getText(2131427585));
                                break;
                            }
                            case 1: {
                                //this.TypeSwitch.setText(this.getResources().getText(2131427584));
                                break;
                            }
                        }
                        switch (GMScreenGlobalInfo.getCurStbPlatform()) {
                            default: {
                                if (this.currentChannelListType >= 0 && this.currentChannelListType <= 3) {
                                    //this.titleText.setText((CharSequence)this.getResources().getStringArray(2131558412)[this.currentChannelListType]);
                                    break;
                                }
                                if (this.currentChannelListType >= 4 && this.currentChannelListType <= 11) {
                                    //this.titleText.setText((CharSequence)GMScreenGlobalInfo.favType.get(this.currentChannelListType - 4));
                                    break;
                                }
                                break;
                            }
                            case 30:
                            case 31:
                            case 32:
                            case 71:
                            case 72:
                            case 74: {
                                if (this.currentChannelListType >= 0 && this.currentChannelListType <= 3) {
                                    //this.titleText.setText((CharSequence)this.getResources().getStringArray(2131558412)[this.currentChannelListType]);
                                    break;
                                }
                                if (this.currentChannelListType >= 4) {
                                    for (final DataConvertFavorModel dataConvertFavorModel : (List<DataConvertFavorModel>)GMScreenGlobalInfo.favGroups) {
                                        if (dataConvertFavorModel.getFavorTypeID() == this.currentChannelListType - 4) {
                                            //this.titleText.setText((CharSequence)dataConvertFavorModel.GetFavorName());
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    this.setCurrentChannelListDispIndex();
                    mOriginalChannelListModels = (List<DataConvertChannelModel>) this.mCurrentChannelList;
//                    if (this.mEnterSearchFlag) {
//                        GsChannelListActivity.access$81(this, true);
//                        this.findChannel();
//                    }
//                    else {
//                        channelListAdapter = new list_single_button_adapter((Context)this));
//                        this.ChannelListView.setAdapter((ListAdapter)this.channelListAdapter);
//                        this.adjustSelectionOfChannelListView(true);
//                    }
                    if (this.mGetChannelListWhenLogin) {
                        int n = 0;
                        for (final DataConvertChannelModel dataConvertChannelModel : (List<DataConvertChannelModel>) this.mChannelData.getmTvChannelList()) {
                            if (dataConvertChannelModel.getmWillBePlayed() == 1 && dataConvertChannelModel.getLockMark() == 1) {
                                if (true) {
                                    passwordType = 2;
                                    this.inputPermissionPassword();
                                    n = 1;
                                    break;
                                }
                                break;
                            }
                        }
                        if (n == 0) {
                            for (final DataConvertChannelModel dataConvertChannelModel2 : (List<DataConvertChannelModel>) this.mChannelData.getmRadioChannelList()) {
                                if (dataConvertChannelModel2.getmWillBePlayed() == 1 && dataConvertChannelModel2.getLockMark() == 1) {
                                    if (true) {
                                        passwordType = 2;
                                        this.inputPermissionPassword();
                                        break;
                                    }
                                    break;
                                }
                            }
                        }
                        mGetChannelListWhenLogin = false;
                    }
                    if (initChannelListData != null && initChannelListData.size() == GMScreenGlobalInfo.getMaxProgramNumPerRequest()) {
                        requestProgramListFromTo(this.mChannelData.getTotalProgramNum(), this.mChannelData.getTotalProgramNum() + GMScreenGlobalInfo.getMaxProgramNumPerRequest() - 1);
                        return;
                    }

                    // complete channel list received
                    ChannelListComplete();

//                    if (GMScreenGlobalInfo.getCurStbInfo().getmSatEnable() == 1) {
//                        this.msgProc.postEmptyMessage(4115);
//                    }
                }
                else if (initChannelListData.size() == 0) {
//                    Toast.makeText((Context)this, 2131427648, 0).show();
//                    if (!this.mGetChannelListWhenLogin) {
//                        this.mCurrentChannelList.removeAll(this.mCurrentChannelList);
//                        this.channelListAdapter.notifyDataSetChanged();
//                    }
                }
            }
        }
        else if (message.arg1 == 0 && message.arg2 == -1) {
            this.requestProgramListFromTo(this.mChannelData.getTotalProgramNum(), this.mChannelData.getTotalProgramNum() + GMScreenGlobalInfo.getMaxProgramNumPerRequest() - 1);
        }
    }

    List<DataConvertSatModel> satList;

    public void ChannelListComplete() {

        // this.msgProc.setOnMessageProcess(4115, (Activity)this, (MessageProcessor$PerformOnForeground)new GsMainTabHostActivity.GsMainTabHostActivity$2(this));
        //  GsMainTabHostActivity$2
        satList = ChannelData.getSatList(mChannelData.getmAllSatList(), mChannelData.getChannelListByTvRadioType(), "All_Satellites");

        java.util.List channelList = ChannelData.getInstance().getmTvChannelList();

        // http://stackoverflow.com/questions/16004005/how-to-hold-data-within-a-defaultlistmodel-in-java
        DefaultListModel<DataConvertChannelModel> chanListModel = new DefaultListModel<DataConvertChannelModel>();
        InitJList(channelList,listChan,chanListModel);
    }


    boolean isSat2ipStarted = false;
    private String currentSat2ipChannelProgramId = "";
//    private static Sat2IP_Rtsp sRtsp;


    private void stopStream() {

        Log.d("Client", "stopStream @ Platform: "+ GMScreenGlobalInfo.getCurStbPlatform());

        switch(GMScreenGlobalInfo.getCurStbPlatform()) {
            case 8:
            case 9:
                if(sRtsp != null) {
                    sRtsp.teardown();
                    sRtsp = null;
                    //DVBtoIP.destroyResourceForPlayer();
                    this.isSat2ipStarted = false;
                    this.currentSat2ipChannelProgramId = "";
                    GMScreenGlobalInfo.playType = 0;
                    GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
                    return;
                }
                break;
            default:
                GMScreenGlobalInfo.playType = 0;
                GsSendSocket.sendOnlyCommandSocketToStb(this.tcpSocket, 1012);
        }

    }

//    private void adjustSelectionOfChannelListView(boolean var1) {
//        int var2 = 0;
//
//        for(Iterator var4 = this.mCurrentChannelList.iterator(); var4.hasNext(); ++var2) {
//            if(((DataConvertChannelModel)var4.next()).getIsPlaying() == 1) {
//                if(var1) {
//                    if(var2 > 5) {
//                        this.ChannelListView.setSelection(var2 - 3);
//                    } else {
//                        this.ChannelListView.setSelection(0);
//                    }
//                } else if(var2 < this.mFirstVisibleChannelIdex || var2 > this.mLastVisibleChannelIndex) {
//                    if(var2 > this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex) {
//                        int var3 = (this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex) / 2;
//                        this.ChannelListView.setSelection(var2 - var3);
//                    } else {
//                        this.ChannelListView.setSelection(0);
//                    }
//                }
//                break;
//            }
//        }
//
//        if(var2 == this.mCurrentChannelList.size()) {
//            this.ChannelListView.setSelection(0);
//        }
//
//    }


    private void adjustSelectionOfChannelListView(boolean b)
    {
        java.util.Iterator a = this.mCurrentChannelList.iterator();
        int index=0;

        for (DataConvertChannelModel current : mCurrentChannelList) {

            if(current.getIsPlaying() == 1) {
                listChan.setSelectedIndex(index);
            }
            index ++;
        }

    }

//    private void adjustSelectionOfChannelListView(boolean b)
//    {
//        java.util.Iterator a = this.mCurrentChannelList.iterator();
//        int i = 0;
//        Object a0 = a;
//        while(true)
//        {
//            label0: if (((java.util.Iterator)a0).hasNext())
//            {
//                if (((mktvsmart.screen.dataconvert.model.DataConvertChannelModel)((java.util.Iterator)a0).next()).getIsPlaying() != 1)
//                {
//                    i = i + 1;
//                    continue;
//                }
//                if (b)
//                {
//                    if (i <= 5)
//                    {
//                        this.ChannelListView.setSelection(0);
//                    }
//                    else
//                    {
//                        this.ChannelListView.setSelection(i - 3);
//                    }
//                }
//                else
//                {
//                    int i0 = this.mFirstVisibleChannelIdex;
//                    label1: {
//                        if (i < i0)
//                        {
//                            break label1;
//                        }
//                        if (i <= this.mLastVisibleChannelIndex)
//                        {
//                            break label0;
//                        }
//                    }
//                    if (i <= this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex)
//                    {
//                        this.ChannelListView.setSelection(0);
//                    }
//                    else
//                    {
//                        int i1 = (this.mLastVisibleChannelIndex - this.mFirstVisibleChannelIdex) / 2;
//                        this.ChannelListView.setSelection(i - i1);
//                    }
//                }
//            }
//            if (i == this.mCurrentChannelList.size())
//            {
//                this.ChannelListView.setSelection(0);
//            }
//            return;
//        }
//    }



    // channellistactivity$17
    public void ReceiveChannelUpdate(Message var1) {
        if(var1.arg1 > 0) {
            byte[] var2 = var1.getData().getByteArray("ReceivedData");
            if(var2 == null) {
                Log.e("GSChannelListActivity", "recvData = " + var2);
            } else {
                DataParser var3 = ParserFactory.getParser();
                List var6 = null;

                label59: {
                    List var7;
                    try {
//                        var7 = var3.parse(new ByteArrayInputStream(var2, 0, var2.length), 15);
                        var7 = var3.parse(var1, 15);
                    } catch (Exception var5) {
                        var5.printStackTrace();
                        break label59;
                    }

                    var6 = var7;
                }

                String var9 = (String)var6.get(0);
                Log.d("ReceiveChannelUpdate", var9);
                if(mChannelData.getmTvChannelList() != null) {
                    DataConvertChannelModel var8;
                    Log.d("ReceiveChannelUpdate", "isSat2ipStarted == " + (isSat2ipStarted ? "true" : "false"));
                    if(this.isSat2ipStarted) {
                        try {
                            DataConvertChannelModel var11 = mChannelData.getProgramByProgramId(currentSat2ipChannelProgramId);
                            var8 = mChannelData.getProgramByProgramId((String)var6.get(0));
                            if(!mChannelData.canSat2ipChannelPlay(var8, var11)) {
                                stopStream();
                                //Toast.makeText(this.this$0, 2131427600, 0).show();
                            }
                        } catch (ProgramNotFoundException var4) {
                            Log.d("ProgramNotFoundException", var4.getMessage());
                            var4.printStackTrace();
                        }
                    }


                    for(int loop = 0; loop < mChannelData.getmTvChannelList().size(); ++loop) {
                        var8 = (DataConvertChannelModel)mChannelData.getmTvChannelList().get(loop);
                        if(var9.equals(var8.GetProgramId())) {
                            Log.d("ReceiveChannelUpdate", var8.getProgramName() + " is active!");
                            var8.setIsPlaying(1);
                            //currentPlayingProgram = var8;
                        } else {
                            var8.setIsPlaying(0);
                        }
                    }

                    for(int loop = 0; loop < mChannelData.getmRadioChannelList().size(); ++loop) {
                        var8 = (DataConvertChannelModel)mChannelData.getmRadioChannelList().get(loop);
                        if(var9.equals(var8.GetProgramId())) {
                            Log.d("ReceiveChannelUpdate", var8.getProgramName() + " is active!");
                            var8.setIsPlaying(1);
                        } else {
                            var8.setIsPlaying(0);
                        }
                    }
                }

                if(listChan.getModel() != null)
                    adjustSelectionOfChannelListView(false);

                // from jd-gui standalone
//                if (GsChannelListActivity.this.channelListAdapter == null) {
//                    //GsChannelListActivity.this.channelListAdapter.notifyDataSetChanged();
//                    //GsChannelListActivity.this.adjustSelectionOfChannelListView(false);
//                    return;
//                }
//
//
//                if(GsChannelListActivity.access$15(this.this$0) != null) {
//                    GsChannelListActivity.access$15(this.this$0).notifyDataSetChanged();
//                    GsChannelListActivity.access$61(this.this$0, false);
//                    return;
//                }
            }
        }

    }

    List customChannelList;

    public void String2Binary(String string) {

        Log.d("String2Binary", string);

        byte[] array = string.getBytes();

        String beginByte = "1000011";

        int y=0;
        for (int i=0; i< array.length;i++){

            String binaryString = Integer.toBinaryString(0x100 + array[i]).substring(1);

            int offset = 0;
            if (binaryString.equals(beginByte)) {


                // ASCII
                // o = 111
                // ? = 63
                // = = 61
                // encodingOffset = 3
                // beginByte = 1101111

                // Windows-1252
                //
                // encodingOffset = 2       /// <--- variable!
                // beginByte = 1000011

                int encodingOffset = 3;


                for (offset=1; offset < encodingOffset; offset++) {
                    binaryString += "," + Integer.toBinaryString(0x100 + array[i+offset]).substring(1);
                }
                offset--;   // kopfgesteuerte schleife

            }
            else {


            }


            System.out.println("String: \t" + y + " / " + string.length());
            System.out.println("Array : \t" + i + " / " + array.length);
//            System.out.println(string.charAt(y) + " = " + binaryString + " byte2ascii " + (char)Integer.parseInt(array[i]));
            System.out.println(string.charAt(y) + " = " + binaryString + " ; byte2int " + (int)array[i]  + " ; byte2ascii " + (char)array[i]);
            System.out.println();
            //System.out.println(string.substring(i,i+1) + " = " + binaryString + " = " + string.subSequence(y,y+1));

            i += offset;
            y++;
        }

//        for(int i=0; i<string.length(); i++)
//            Log.d(string.substring(i,i+1), " = " + string.subSequence(i,i+1));

    }


//    public void HandleChannelListResponse(MessageHeader currentMsgHeader, byte[] uncompressedMsg) {
//        java.util.List channelList = ChannelData.getInstance().initChannelListData(uncompressedMsg);
//        // http://stackoverflow.com/questions/9509208/adding-objects-to-a-jlist
//        //listChan.setModel((ChannelData.getInstance().getmTvChannelList().toArray()));
//
//        for (Object chan : channelList) {
//            customChannelList.add ((DataConvertChannelModel)chan);
//            Log.d("HandleChannelListResponse", "Added " + ((DataConvertChannelModel)chan).getProgramName());
//            String2Binary(((DataConvertChannelModel)chan).getProgramName());
//        }
//
//        // Add to Channellist
//        //customChannelList.addAll(channelList);
//
//        /*
//        // Doesnt work if channel request starts anywhere
//        //java.util.List channelList = ChannelData.getInstance().getmTvChannelList();
//        */
//
//        if(channelList == null) {
//            System.out.println("getmTvChannelList == null");
//        }else
//        {
//            System.out.println("getmTvChannelList.size() == " + channelList.size());
//        }
//
//
//        // http://stackoverflow.com/questions/16004005/how-to-hold-data-within-a-defaultlistmodel-in-java
//        DefaultListModel<DataConvertChannelModel> chanListModel = new DefaultListModel<DataConvertChannelModel>();
//        InitJList(customChannelList,listChan,chanListModel);
//    }

    public void HandleResponse(MessageHeader currentMsgHeader, Message responseMsg) {
        switch (currentMsgHeader.getN2()) {
            case 0:

                ReceiveChannelListProcess(responseMsg);
                //HandleChannelListResponse(currentMsgHeader, responseMsg.getData().getByteArray("ReceivedData"));

                break;
            case 3:

                ReceiveChannelUpdate(responseMsg);

                break;
            case 22:
                // Sat List
                ChannelData.getInstance().initSatList(responseMsg);
                java.util.List satList = ChannelData.getInstance().getmAllSatList();
                DefaultListModel<DataConvertChannelModel> satListModel = new DefaultListModel<DataConvertChannelModel>();
                InitJList(satList,listSat,satListModel);

                break;
            case 24:
                // Tp List
                ChannelData.getInstance().initTpList(responseMsg);
                java.util.List tpList = ChannelData.getInstance().getmAllTpList();
                DefaultListModel<DataConvertChannelModel> tpListModel = new DefaultListModel<DataConvertChannelModel>();
                InitJList(tpList,listTp,tpListModel);

                break;

            case 1009:
                DoSat2IpChannelPlay(responseMsg);
                break;

            default:
                break;
        }
    }

    private void DoSat2IpChannelPlay(Message message) {
        final byte[] byteArray = message.getData().getByteArray("ReceivedData");
        if (byteArray != null && bPlayWithOherPlayer) {
            parser = ParserFactory.getParser();
            Log.d("GsChannelListActivity", new String(byteArray));
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray, 0, message.arg1);
            Map map;
            try {
                map = (HashMap) parser.parse(message, 16).get(0);
                if (map.get("success") == null) {
                    Log.e("DoSat2IpChannelPlay","map.get('success') == null");
                    //this.this$0.mainHandler.sendMessage(this.this$0.mainHandler.obtainMessage(4, (Object)this.this$0.getString(2131427592)));
                    return;
                }
            }
            catch (Exception ex) {
                //this.this$0.mainHandler.sendMessage(this.this$0.mainHandler.obtainMessage(4, (Object)this.this$0.getString(2131427592)));
                ex.printStackTrace();
                return;
            }
            if ((Integer)map.get("success") != 1) {
                String string = "";
//                String string = this.this$0.getString(2131427592);
                if (map.get("errormsg") != null) {
                    string = (String)map.get("errormsg");
                }
                else if (map.get("url") == null || ((String)map.get("url")).length() <= 0) {
                    string = "Get play url fail";
                }
                Log.e("DoSat2IpChannelPlay","string = " + string);
                //this.this$0.mainHandler.sendMessage(this.this$0.mainHandler.obtainMessage(4, (Object)string));
                return;
            }
            final String s = (String)map.get("url");
            if (s == null) {
                //this.this$0.mainHandler.sendMessage(this.this$0.mainHandler.obtainMessage(4, (Object)this.this$0.getString(2131427592)));
                Log.e("DoSat2IpChannelPlay","(String)map.get(\"url\") = NULL");
                return;
            }
            Log.d("GsChannelListActivity", "play url : " + s);
            Uri uri = null;
            try {
                uri = new Uri(s);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.e("DoSat2IpChannelPlay", "uri: MalformedURLException");

            }
            Log.d("DoSat2IpChannelPlay", "uri = " + uri);
//            if (this.this$0.mPlayIntent != null) {
//                this.this$0.mPlayIntent.setDataAndType(Uri.parse(s), "video/*");
//                this.this$0.mainHandler.sendMessage(this.this$0.mainHandler.obtainMessage(3, (Object)this.this$0.mPlayIntent));
//            }
        }
    }

    public void Special(android.os.Message a)
    {
        int i = 0;
        switch(a.what){
            case 2019: {
                i = 22;
                break;
            }
            case 2013: {
                i = 12;
                break;
            }
            case 2009: {
                i = 23;
                break;
            }
            case 2004: {
                i = 17;
                break;
            }
            case 2001: {
                i = 3;
                break;
            }
            default: {
                i = 9999;
            }
        }
        mktvsmart.screen.GsSendSocket.sendOnlyCommandSocketToStb(tcpSocket, i);
    }


    public void HandleResponse(Message responseMsg) {

        DisplayMesssage(responseMsg);
        receiveMessageListModel.addElement(responseMsg);

        switch (responseMsg.what) {

            case 2019:
            case 2013:
            case 2009:
            case 2004:
            case 2001:
            {
                Special(responseMsg);
                break;
            }

            case 0:
                ReceiveChannelListProcess(responseMsg);
                //HandleChannelListResponse(currentMsgHeader, responseMsg.getData().getByteArray("ReceivedData"));
                break;

            case 3:
                ReceiveChannelUpdate(responseMsg);
                break;

            case 22:
                // Sat List
                ChannelData.getInstance().initSatList(responseMsg);
                java.util.List satList = ChannelData.getInstance().getmAllSatList();
                DefaultListModel<DataConvertChannelModel> satListModel = new DefaultListModel<DataConvertChannelModel>();
                InitJList(satList,listSat,satListModel);
                break;

            case 24:
                // Tp List
                ChannelData.getInstance().initTpList(responseMsg);
                java.util.List tpList = ChannelData.getInstance().getmAllTpList();
                DefaultListModel<DataConvertChannelModel> tpListModel = new DefaultListModel<DataConvertChannelModel>();
                InitJList(tpList,listTp,tpListModel);
                break;
            
            case 1009:
                DoSat2IpChannelPlay(responseMsg);
                break;
            
            default:
                break;
        }
    }

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

                Bundle bndl = new Bundle();
                bndl.putByteArray("ReceivedData", uncompressedMsg);

                HandleResponse(currentMsgHeader, new Message(bndl));

            } else
                textArea1.append("RECEIVED MSG !!! FAILED !!!" + "\n");
        }
        else {

            ReceiveLoginResponse();

        }

    }

    private <T> void InitJList(java.util.List<T> list, JList jList, DefaultListModel<T> listModel) {
        // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_07_001.htm#mj9078abc2bd800d30d4ced5d5411f280a

        int selectedItemIndex = -1;

        if (list != null) {
            listModel.clear();
            for(int x=0; x<list.size(); x++)
            {
                listModel.addElement((T)list.get(x));
                if(list.get(x) instanceof DataConvertChannelModel) {
                    if(((DataConvertChannelModel) list.get(x)).getIsPlaying() == 1)
                        selectedItemIndex = x;
                }
                //Log(list.get(x).toString());
            }
        }
        jList.setModel(listModel);
        if(selectedItemIndex >= 0)
            jList.setSelectedIndex(selectedItemIndex);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setCellRenderer(new ChannelCellRenderer());               // cell Renderer
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
        textArea1.append( "\t\tgetStb_model_id = " + gsMobileLoginInfo.getStb_model_id() + "\n");
        textArea1.append( "\t\tgetStb_customer_id = " + gsMobileLoginInfo.getStb_customer_id() + "\n");
        try {
            textArea1.append( "\t\tgetStb_flash_id = " + gsMobileLoginInfo.getStb_flash_id() + " -- " + new String(gsMobileLoginInfo.getStb_flash_id(),"UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            textArea1.append( "\t\tgetStb_cpu_chip_id = " + gsMobileLoginInfo.getStb_cpu_chip_id() + " -- " + new String(gsMobileLoginInfo.getStb_cpu_chip_id(), "UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        textArea1.append( "\t\tgetStb_sn_disp = " + gsMobileLoginInfo.getStb_sn_disp() + "\n");
        try {
            textArea1.append( "\t\tgetStb_sn = " + gsMobileLoginInfo.getStb_sn()+ " -- " + new String(gsMobileLoginInfo.getStb_sn(), "UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        textArea1.append( "\t\tgetStb_ip_address_disp = " + gsMobileLoginInfo.getStb_ip_address_disp() + "\n");
        textArea1.append( "\t\tgetStb_ip_address = " + gsMobileLoginInfo.getStb_ip_address() + "\n");
        textArea1.append( "\t\tgetModel_name = " + gsMobileLoginInfo.getModel_name() + "\n");
        textArea1.append( "\t\tgetSw_version = " + gsMobileLoginInfo.getSw_version() + "\n");
        textArea1.append( "\t\tgetSw_sub_version = " + gsMobileLoginInfo.getSw_sub_version() + "\n");
        textArea1.append( "\t\tgetMagic_code = " + gsMobileLoginInfo.getMagic_code() + "\n");
        textArea1.append( "\t\tgetUpnpIp = " + gsMobileLoginInfo.getUpnpIp() + "\n");
        textArea1.append( "\t\tgetUpnpPort = " + gsMobileLoginInfo.getUpnpPort() + "\n");
        textArea1.append( "\t\tgetSend_data_type = " + gsMobileLoginInfo.getSend_data_type() + "\n");
        textArea1.append( "\t\tgetIs_current_stb_connected_full = " + gsMobileLoginInfo.getIs_current_stb_connected_full() + "\n");
        textArea1.append( "\t\tgetSend_data_type = " + gsMobileLoginInfo.getSend_data_type() + "\n");
        textArea1.append( "\t\tgetPlatform_id = " + gsMobileLoginInfo.getPlatform_id() + "\n");
        textArea1.append( "\t\tgetmSat2ipEnable = " + gsMobileLoginInfo.getmSat2ipEnable() + "\n");
        textArea1.append( "\t\tgetClient_type = " + gsMobileLoginInfo.getClient_type() + "\n");
        textArea1.append( "\t\tgetmSatEnable = " + gsMobileLoginInfo.getmSatEnable() + " <----IMPORTANT-----\n");
        try {
            textArea1.append( "\t\tgetReserved_1 = " + gsMobileLoginInfo.getReserved_1() + " -- " + new String(gsMobileLoginInfo.getReserved_1(), "UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            textArea1.append( "\t\tgetReserved_3 = " + gsMobileLoginInfo.getReserved_3() + " -- " + new String(gsMobileLoginInfo.getReserved_3(), "UTF-8") + "\n");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        textArea1.append( "\tEND DATA" + "\n");
    }

    private GsMobileLoginInfo ReceiveLoginResponse() {

        // First Message (Scrambled)

        byte[] recvfirstMsg = ReceiveFirstMessage();
        DisplayReceivedFirstMessage(recvfirstMsg);
        scramble_stb_info_for_broadcast(recvfirstMsg,108);
        DisplayReceivedFirstMessage(recvfirstMsg);
        gsMobileLoginInfo = new GsMobileLoginInfo(recvfirstMsg);
        DisplayReceivedFirstMessage(gsMobileLoginInfo);
        preserveLoginInfo(gsMobileLoginInfo);
        return gsMobileLoginInfo;
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
                    Log(this.toString() + " RFM: receivedBytes=" + receivedBytes + " numOfBytesLeft=" + numOfBytesLeftToReceive);
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
            receivedBytes = in.read(headerData, 0, MessageHeader.messageHeadLength);
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
                    Log(this.toString() + " RM: receivedBytes=" + receivedBytes + " numOfBytesLeft=" + numOfBytesLeftToReceive);
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
        Log(this.toString() + " Start uncompress " + recvRawMsgData.length + " Bytes " + recvRawMsgData.toString());

        byte[] unCompress = null;
        if (recvRawMsgData.length > 8) {
            try {
                unCompress = MyImplementations.UnCompress(recvRawMsgData);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (DataFormatException e) {
                e.printStackTrace();
            }
            //obtain2.arg1 = unCompress.length;
        }

        Log(this.toString() + " Stop uncompress " + recvRawMsgData.length + " Bytes " + "resulting in " + unCompress.length + " Bytes");

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

        // Run the GUI codes on the event-dispatching thread for thread-safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Client");
                frame.setContentPane(new Client().panelMain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setLocationRelativeTo(null); // center on screen
                frame.setVisible(true);            // show it
            }
        });


    }
}
