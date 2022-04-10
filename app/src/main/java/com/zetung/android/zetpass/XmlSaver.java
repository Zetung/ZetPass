package com.zetung.android.zetpass;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlSaver {

    private final String filename;

    public XmlSaver(String filename){
        this.filename = filename;
    }

    public int saveAllRecord(List<DataRecord> dataRecordList){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try{
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement( "Data");
            doc.appendChild(rootElement);
            for (DataRecord dataRecord: dataRecordList){
                rootElement.appendChild(createNode(doc,dataRecord));
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult fileNew = new StreamResult(new File(android.os.Environment.getExternalStorageDirectory(),filename));
            transformer.transform(source, fileNew);
        }   catch (Exception e){
            return -1;
        }
        return 0;
    }

    private Node createNode(Document file, DataRecord dataRecord){
        Element studNode = file.createElement("record");
        studNode.appendChild(createNodeElem(file,"app",dataRecord.getApp()));
        studNode.appendChild(createNodeElem(file,"login",dataRecord.getLogin()));
        studNode.appendChild(createNodeElem(file,"pass",dataRecord.getPass()));
        return studNode;
    }

    private Node createNodeElem(Document file,String nameTag, String valTag){
        Element node = file.createElement(nameTag);
        node.appendChild(file.createTextNode(valTag));
        return node;
    }

}
