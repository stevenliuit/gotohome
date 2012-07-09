package org.jamie.test.rss;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RssHandler extends DefaultHandler  {

	 /*
     * 通常的RSS源实际上是个xml文件.里面包含多个item节点,每个item节点是一条新闻.
     * 每个item节点至少包含title,link,description,pubDate这四个子节点,其实很多rss源还有补充的节点
     */
    static final String ITEM = "item";
    static final String TITLE = "title";
    static final String LINK = "link";
    static final String DESCRIPTION = "description";
    static final String PUB_DATE = "pubDate";
 
    private Stack<String> currentElement = new Stack<String>();
    private Message currentMessage;
    private List<Message> messgelist = new ArrayList<Message>();;
 
    public void startElement(String uri, String localName, String qName,
            Attributes attrs) throws SAXException {
        currentElement.push(qName);
        System.out.println("start parse '" + currentElement.peek() + "'");
        if (currentElement.peek().equalsIgnoreCase(ITEM)) {
            System.out.println("Create message ");
            this.currentMessage = new Message();
        }
 
    }
 
    public void endElement(String namespaceURI, String localName, String qName)
            throws SAXException {
        System.out.println("end parse element " + currentElement.peek());
 
        if (currentElement.peek().equalsIgnoreCase(ITEM)
                && this.currentMessage != null) {
            this.messgelist.add(this.currentMessage);
            this.currentMessage = null;
        }
        currentElement.pop();
 
    }
 
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String cdata = new String(ch, start, length);
        String tagName = currentElement.peek();
 
        System.out.println("Element '" + tagName + "' contains text: " + cdata);
        if (this.currentMessage != null) {
            if (this.currentElement.peek().equalsIgnoreCase(TITLE)) {
                currentMessage.setTitle(cdata);
            } else if (this.currentElement.peek().equalsIgnoreCase(LINK)) {
                currentMessage.setLink(cdata);
            } else if (this.currentElement.peek().equalsIgnoreCase(DESCRIPTION)) {
                currentMessage.setDescription(cdata);
            } else if (this.currentElement.peek().equalsIgnoreCase(PUB_DATE)) {
                System.out.println("setting pubdate:\t" + cdata);
                currentMessage.setDate(cdata);
            }
        } else {
            System.out.println("null message");
        }
    }
 
    public List<Message> getMessaList() {
        return this.messgelist;
    }
 
    /*
     * 打印解析的消息结果
     */
    public void printMessageList() {
        if (this.messgelist != null) {
            for (Message message : this.messgelist) {
                if (message != null) {
                    System.out.println(message.toString());
                } else {
                    System.out.println("null list");
                }
            }
        }
    }
}
