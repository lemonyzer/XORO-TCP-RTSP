package org.xmlpull.v1;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created by it on 18.07.2016.
 */
public class XmlSerializer {

    XMLStreamWriter writer = null;


    public void attribute(String pre, String name, String value) {
        try {
            writer.writeAttribute(name, value);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void text (String text)
    {
        try {
            writer.writeCharacters(text);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void startTag(String pre, String name) {
        try {
            writer.writeStartElement(name);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void endTag(String pre, String name) {
        try {
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void startDocument() {
        try {
            writer.writeStartDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void startDocument(String encode, String version) {
        try {
            writer.writeStartDocument(encode, version);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void startDocument(String encode, Boolean standaloneValue) {
        try {
            writer.writeStartDocument(encode);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void endDocument() {
        try {
            writer.writeEndDocument();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void setOutput(java.io.Writer out) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            writer = factory.createXMLStreamWriter( out );
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void setWriter(XMLStreamWriter writer) {
        this.writer = writer;
    }

}
