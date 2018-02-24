package com.example.student.a20180224_05xml;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by student on 2018/2/24.
 */

public class MyDataHandler extends DefaultHandler {
    boolean isTitle = false;
    boolean isItem = false;
    boolean isLink = false;
    StringBuilder sb;
    StringBuilder linkSB;
    public ArrayList<String> titles = new ArrayList();
    public ArrayList<String> links = new ArrayList();
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (qName.equals("title"))
        {
            isTitle = true;
        }
        if (qName.equals("item"))
        {
            sb = new StringBuilder();
            isItem = true;
        }
        if (qName.equals("link"))
        {
            linkSB = new StringBuilder();
            isLink = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if (qName.equals("title"))
        {
            isTitle = false;
        }
        if (qName.equals("link"))
        {
            isLink = false;
        }
        if (qName.equals("item"))
        {
            isItem = false;
            titles.add(sb.toString());
            links.add(linkSB.toString());
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if (isTitle && isItem)
        {
            // Log.d("NET", new String(ch, start, length));
            sb.append(new String(ch, start, length));
        }
        if (isLink && isItem)
        {
            linkSB.append(new String(ch, start, length));
        }
    }
}


