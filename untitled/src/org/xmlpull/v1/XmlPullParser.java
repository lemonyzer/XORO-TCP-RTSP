package org.xmlpull.v1;

import javax.xml.stream.*;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

/**
 * Created by it on 19.07.2016.
 */
public class XmlPullParser {


    public static final int END_DOCUMENT = 1;
    public static final int DOCDECL = 10;
    public static final int COMMENT = 9;
    public static final int CDSECT = 5;
    public static final int END_TAG = 3;
    public static final int ENTITY_REF = 6;
    public static final int IGNORABLE_WHITESPACE = 7;
    public static final int PROCESSING_INSTRUCTION = 8;
    public static final int START_DOCUMENT = 0;
    public static final int START_TAG = 2;
    public static final int TEXT = 4;


    // XMLParser: JavaSE
    // XMLEventId: Android

    public static int android2javase(int eventId) {

        switch (eventId) {
            case START_DOCUMENT:
                return XMLStreamConstants.START_DOCUMENT;
            case END_DOCUMENT:
                return XMLStreamConstants.END_DOCUMENT;
            case START_TAG:
                return XMLStreamConstants.START_ELEMENT;
            case END_TAG:
                return XMLStreamConstants.END_ELEMENT;
            default:
                return -1;
        }

    }


    // XMLParser: Android
    // XMLEventId: JavaSE

    public static int javase2android(int eventId) {

        switch (eventId) {
            case XMLStreamConstants.START_DOCUMENT:
                return START_DOCUMENT;
            case XMLStreamConstants.END_DOCUMENT:
                return END_DOCUMENT;
            case XMLStreamConstants.START_ELEMENT:
                return START_TAG;
            case XMLStreamConstants.END_ELEMENT:
                return END_TAG;
            case XMLStreamConstants.CHARACTERS:
                return TEXT;
            case XMLStreamConstants.ATTRIBUTE:
                return XMLStreamConstants.ATTRIBUTE;                   // ACHTUNG
            default:
                return -1;
        }

    }


    XMLStreamReader xmlPullParser;
    //XMLEvent event;

    public XmlPullParser() {

    }


    public void setInput(InputStream inputStream, String s) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            xmlPullParser = factory.createXMLStreamReader(inputStream, s);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public int getEventType() {

        int eventType =  xmlPullParser.getEventType();
        System.out.println(this.toString() + " getEventType() == " + eventType);
        System.out.println(this.toString() + " jave2android() == " + javase2android(eventType));

        return javase2android(eventType);
    }

    public int next() {
        try {
            int nativId = xmlPullParser.next();
            //System.out.print("\n" + this.toString() + " next() == \nJAVASE: " + nativId + "\nAndroid: " + javase2android(nativId) + " <-- return value" + "\n" );
            switch (nativId) {
                case XMLStreamConstants.START_DOCUMENT:
                    //System.out.print("START_DOCUMENT\n");
                    return START_DOCUMENT;
                case XMLStreamConstants.END_DOCUMENT:
                    //System.out.print("END_DOCUMENT\n");
                    return END_DOCUMENT;
                case XMLStreamConstants.END_ELEMENT:
                    //System.out.print("END_ELEMENT\n");
                    return END_TAG;
                case XMLStreamConstants.START_ELEMENT:
                    //System.out.print("START_ELEMENT\n");
                    return START_TAG;
                case XMLStreamConstants.ATTRIBUTE:
                    System.out.println("ATTRIBUT FOUND, NOT ALLOWED!");
                    for ( int i = 0; i < xmlPullParser.getAttributeCount(); i++ )
                        System.out.println( "  Attribut: " + xmlPullParser.getAttributeLocalName( i ) + " Wert: " + xmlPullParser.getAttributeValue( i ) );
                    return XMLStreamConstants.ATTRIBUTE;

                case XMLStreamConstants.CHARACTERS:
                    //System.out.println("CHARACTERS FOUND, NOT ALLOWED!");
                    //System.out.println("EVENT CHARACTERS: should not happen!!! Characters=" + xmlPullParser.getText());
                    return TEXT;
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        System.out.print("_END_DOCUMENT");
        return XMLStreamConstants.END_DOCUMENT;
    }

    public String getName() {
        return String.valueOf(xmlPullParser.getName());
    }

    public String getText() {
        if(xmlPullParser.isEndElement()) {
            System.out.println(xmlPullParser.getName() + " has no CHARACTERS/TEXT");
            return "";
        }
        else {
            return xmlPullParser.getText();
        }
    }
}
