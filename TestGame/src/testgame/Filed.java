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
import org.w3c.dom.Node;
import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<FieldObject> fieldObjectList = new ArrayList<>();
    public Filed() {
//        try {
//            dbf = DocumentBuilderFactory.newInstance();
//            db = dbf.newDocumentBuilder();
//            document = db.parse("/home/umino/NetBeansProjects/java/TestGame/src/testgame/Field.xml");
//            element = document.getDocumentElement();
//            NodeList nodeList = element.getChildNodes();
//            System.out.println("root name " + element.getTagName());
//            
//            for(int i = 0; i < nodeList.getLength(); i++)
//            {
//                Node node = nodeList.item(i);
//                if(node.getNodeType() == node.ELEMENT_NODE)
//                {
//                    Element ele = (Element)node;
//                    if(ele.getNodeName() == "FieldObject")
//                    {
//                        System.out.println(ele.getAttribute("name"));
//                        NodeList nodeList1 = ele.getChildNodes();
//                        for(int j = 0; j < nodeList1.getLength(); j++)
//                        {
//                            Node node1 = nodeList1.item(j);
//                            if(node1.getNodeType() == node1.ELEMENT_NODE)
//                            {
//                                Element ele1 = (Element)node1;
//                                System.out.println(ele1.getTextContent());
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (ParserConfigurationException pce) {
//            return;
//        } catch (IOException ioe) {
//            return;
//        } catch (SAXException saxe) {
//            return;
//        }
    }
    
    //フィールドオブジェクトxmlを読み込むメソッド
    private void readFieldObjectInfo(Document document)
    {
        ArrayList<String> templList = new ArrayList<>();    //各フィールドオブジェクトの要素を入れる
        try {
             Element element = document.getDocumentElement();   //rootの取得
             NodeList nodelist = element.getChildNodes();       //rootの子要素を取得
             for(int i = 0; i < nodelist.getLength(); i++)
             {
                 Node node = nodelist.item(i);                  
                 if(node.getNodeType() == node.ELEMENT_NODE)
                 {
                     Element fieldObject = (Element)node;
                     if(fieldObject.getNodeName() == "FieldObject") //フィールドオブジェクトの要素を取得
                     {
                         NodeList fieldObjectInfo = fieldObject.getChildNodes();
                         for(int j = 0; j < fieldObjectInfo.getLength(); j++)
                         {
                             Node fieldObjectElement = fieldObjectInfo.item(j);
                             if(fieldObjectElement.getNodeType() == fieldObjectElement.ELEMENT_NODE)
                             {
                                 Element temp = (Element)fieldObjectElement;
                                 templList.add(temp.getTextContent());
                             }
                         }
                     }
                    //fieldobjectの生成
                     //xmlを書き換える必要あり r-> color
                     Color color = new Color(i, i, i)
                     FieldObject fieldElement = new FieldObject(null, null, i, i, i, i);
                 }
             }
        } catch (Exception e ) {
            return;
        }
    }
    
    
}
