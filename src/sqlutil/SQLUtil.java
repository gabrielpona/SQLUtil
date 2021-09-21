/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlutil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author gabrielmoreira
 */
public class SQLUtil {
    
    private File fXmlFile;
    private String path;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    
               
    public SQLUtil(String path) {
        try{
            this.path = path;                   
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
                        
        }catch(ParserConfigurationException e){
           Logger.getLogger(SQLUtil.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
    public void test(){
        
        String fileLocation = "c:\\tmp\\processos.xml";
                
        final String ROW_ELEMENT = "row";
        final String COLUMN_ELEMENT = "column";
        
        try{
           
            FileInputStream fileInputStream = new FileInputStream(fileLocation);
            XMLStreamReader xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(fileInputStream);
            
            while (xmlStreamReader.hasNext()) {

                
                
                int eventCode = xmlStreamReader.next();

                if ((XMLStreamConstants.END_ELEMENT == eventCode)
                            && xmlStreamReader.getLocalName().equalsIgnoreCase(ROW_ELEMENT)) {
                             System.out.println("Fechando Linha");
                             break;
                         
                }else{
                    
                    if ((XMLStreamConstants.START_ELEMENT == eventCode)
                    && xmlStreamReader.getLocalName().equalsIgnoreCase(ROW_ELEMENT)) {

                    System.out.println("Abrindo Linha");
                    
                        while (xmlStreamReader.hasNext()) {
                            
                            eventCode = xmlStreamReader.next();
                            
                            if ((XMLStreamConstants.END_ELEMENT == eventCode)
                                && xmlStreamReader.getLocalName().equalsIgnoreCase(COLUMN_ELEMENT)) {
                                System.out.println("Fechando Coluna");
                                break;
                            }else{
                                
                                if ((XMLStreamConstants.START_ELEMENT == eventCode)
                                    && xmlStreamReader.getLocalName().equalsIgnoreCase(COLUMN_ELEMENT)) {
                                    
                                    System.out.println(xmlStreamReader.getElementText());
                                    
                                }
                                
                                
                            }
                                                        
                        }
                        
                    }
                                                            
                }
                 
                
          }
            
        }catch(Exception e){
            Logger.getLogger(SQLUtil.class.getName()).log(Level.SEVERE, null, e);
        }
                                
    }
    
    public void readLargeXML (String filename,String[] fieldTypes,String tableName){
                    
        
        String fileLocation = this.path.concat("teste\\").concat(filename).concat(".xml");
        
        FileInputStream fileInputStream;
        XMLStreamReader xmlStreamReader;
        
        final String ROW_ELEMENT = "row";
        final String COLUMN_ELEMENT = "column";
        
        final String insertStatement = "INSERT INTO ".concat(tableName).concat(" values ");   
        
        int eventCode;
        
        FileOutputStream fos;
        BufferedWriter bw = null;                                                                     
        String line = "";
        
        int cont = 0;
        int columnCount = 0;
        int fileNumber = 0;
        
        try{
            
            fileInputStream = new FileInputStream(fileLocation);
            xmlStreamReader = XMLInputFactory.newInstance().createXMLStreamReader(fileInputStream);
            
            while (xmlStreamReader.hasNext()) {
                                                                                                                                                             
                eventCode = xmlStreamReader.next();
                                
                //Abrindo Linha
                if ((XMLStreamConstants.START_ELEMENT == eventCode) && xmlStreamReader.getLocalName().equalsIgnoreCase(ROW_ELEMENT)) {
                           
                        if(cont == 0){
                            fos = new FileOutputStream(new File(this.path.concat("teste\\").concat(tableName).concat("_convertido(".concat(Integer.toString(++fileNumber).concat(").sql")))));
                            bw = new BufferedWriter(new OutputStreamWriter(fos));                                                                                    
                        }
                                                
                        line = "(";
                        columnCount = 0;
                        while (xmlStreamReader.hasNext()) {    
                            
                            eventCode = xmlStreamReader.next();                            
                            
                            if ((XMLStreamConstants.END_ELEMENT == eventCode) && xmlStreamReader.getLocalName().equalsIgnoreCase(COLUMN_ELEMENT)) {                                                               
                                System.out.println("FECHA");
                                break;
                            }else{
                            
                                if ((XMLStreamConstants.START_ELEMENT == eventCode) && xmlStreamReader.getLocalName().equalsIgnoreCase(COLUMN_ELEMENT)) {
                                    line = line.concat(this.trataCampo(xmlStreamReader.getElementText(), fieldTypes[columnCount]));                                    
                                    if(columnCount < fieldTypes.length-1){
                                        line = line.concat(",");
                                    }else{
                                        //System.out.println(line);
                                        break;
                                    }
                                    //System.out.println("Linha: ".concat(Integer.toString(cont)).concat(" Coluna: ").concat(Integer.toString(columnCount)));                                    
                                    columnCount++;                                  
                                }                                
                            }                                                        
                        }                                                                            
                }else{
                    
                    //Fechando Linha
                    if ((XMLStreamConstants.END_ELEMENT == eventCode) && xmlStreamReader.getLocalName().equalsIgnoreCase(ROW_ELEMENT)) {
                    
                        cont++;                         
                        line = line.concat(");");

                        if(cont >= 10000){                                                        
                            cont = 0;
                            bw.write(insertStatement);
                            bw.write(line);
                            bw.newLine();
                            bw.flush();
                            bw.close();                            
                        }else{  
                            bw.write(insertStatement);
                            bw.write(line);
                            bw.newLine();                            
                        }                
                        System.out.println(insertStatement);
                        System.out.println(line);
                    }                                                            
                }                                                                                                                                                                                
            }
            
            if ( bw != null){
                bw.flush();
                bw.close();
            }
                                               
        }catch(Exception e){
            Logger.getLogger(SQLUtil.class.getName()).log(Level.SEVERE, null, e);
        }
                                
    }
    
    public void readXML(String filename,String[] fieldTypes,String tableName){                
        Document doc;
        //String sql = "INSERT INTO ".concat(tableName).concat(" values \n\r");
        this.fXmlFile = new File(this.path.concat(filename).concat(".xml"));
        
        FileOutputStream fos;
        BufferedWriter bw = null;                                                                     
        String line;
        
        int cont = 0;
        int fileNumber = 0;
        
        try {
                                                            
            doc = dBuilder.parse(this.fXmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList rows = doc.getElementsByTagName("row"); 
            NodeList columns;            
            Element eRow;
            Element eColumn;
            for (int i = 0; i < rows.getLength(); i++) {                 
                                
                if(cont == 0){
                    fos = new FileOutputStream(new File(this.path.concat(filename).concat("_convertido(".concat(Integer.toString(++fileNumber).concat(").sql")))));
                    bw = new BufferedWriter(new OutputStreamWriter(fos));
                    line = "INSERT INTO ".concat(tableName).concat(" values ");             
                    bw.write(line);
                    bw.newLine();
                }
                
                eRow = (Element)rows.item(i);                                
                columns = eRow.getElementsByTagName("column");                
                line = "(";
                System.out.println("Convertendo linha ".concat(Integer.toString(i+1)));
                for (int j = 0; j < columns.getLength(); j++) {       
                    eColumn = (Element)columns.item(j);
                    
                    if (eColumn.getNodeType() == Node.ELEMENT_NODE) {
                        line = line.concat(this.trataCampo(eColumn.getTextContent(), fieldTypes[j]));
                        if(j<columns.getLength()-1){
                            line = line.concat(",");
                        }
                    }
                                                                           
                }
                
                cont++;                
                if(cont >= 10000){
                    line = line.concat(");");
                    cont = 0;
                    bw.write(line);
                    bw.newLine();
                    bw.flush();
                    bw.close();
                }else{
                    
                    if(i<rows.getLength()-1){
                        line = line.concat("),");
                    }else{
                        line = line.concat(");");
                    }                    
                    bw.write(line);
                    bw.newLine();
                }
                                                                                                                                
            }
            
            bw.flush();
            bw.close();
                                    
        } catch (SAXException | IOException ex) {
            Logger.getLogger(SQLUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
                        
    }
    
    private String trataCampo(String campo,String tipo){
        String valor = "";
        
        switch(tipo){
            case "int":
                valor = campo.replace("'","");
                if(valor.isEmpty()){
                    valor = "0";
                }                        
                break;
            case "int_nullable":
                valor = campo.replace("'","");
                if(valor.isEmpty()){
                    valor = "NULL";
                }                        
                break;
            case "char":
                valor = campo.replace("'","");
                if(campo.length() < 50){
                    valor = valor.replace("\n","");
                    valor = valor.replace("\r","");
                }
                valor = "'".concat(valor).concat("'");
                if (valor.endsWith("\\'")){
                    valor = valor.replace("\\'","'");
                }
                break;
            case "char_nullable":
                valor = campo.replace("'","");
                if(campo.length() < 50){
                    valor = valor.replace("\n","");
                    valor = valor.replace("\r","");
                }                                
                if(valor.isEmpty()){
                    valor = "NULL";
                }else{
                    valor = "'".concat(valor).concat("'");
                }                
                if (valor.endsWith("\\'")){
                    valor = valor.replace("\\'","'");
                }
                break;    
            case "date":    
            case "date_nullable":
                valor = campo.replace("'",""); 
                if(valor.isEmpty()){
                    valor = "NULL";
                }else{
                    valor = "'".concat(valor).concat("'");                            
                }
                break;    
                
            case "datetime":    
            case "datetime_nullable":
                valor = campo.replace("'",""); 
                if(valor.isEmpty()){
                    valor = "NULL";
                }else{
                    valor = "'".concat(valor).concat("'");                            
                }
                break;
            
            case "decimal":
                valor = campo.replace("'","");
                valor = valor.replace(",",".");
                if(valor.isEmpty()){
                    valor = "0.0";
                }
                break;
            case "decimal_nullable":
                valor = campo.replace("'","");
                valor = valor.replace(",",".");
                if(valor.isEmpty()){
                    valor = "NULL";
                }
                break;                                                
        }
                
        return valor;
    }
}
