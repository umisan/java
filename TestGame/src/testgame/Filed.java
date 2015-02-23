/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgame;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 *
 * @author umino
 */
public class Filed {
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document document;
    private Element element;
    public Filed() {
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            document = db.parse("/home/umino/NetBeansProjects/java/TestGame/src/testgame/Field.xml");
            element = document.getDocumentElement();
            System.out.println("root name " + element.getTagName());
        } catch (ParserConfigurationException pce) {
            return;
        } catch (IOException ioe) {
            return;
        } catch (SAXException saxe) {
            return;
        }
    }
    
    
    
}
