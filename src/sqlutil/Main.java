/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import sqlutil.transients.ColumnData;
import sqlutil.transients.TableData;


/**
 *
 * @author gabrielmoreira
 */
public class Main {
                
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("empty-statement")
    public static void main(String[] args) {
                
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
 
        String entrada;
        String tableName = "";
        
        SQLUtil sqlUtil = new SQLUtil("c:\\tmp\\");        
        String[] tipos = {""}; 
        
        do{
            System.out.println("Nome da tabela (S para Sair):");
            try{
                entrada = reader.readLine().toUpperCase(); 
                
                switch(entrada){
                    
                    case "ANDAMENTOS":                        
                        tableName = "BTH_ANDAMENTO";
                        tipos = new String[]{"int","int","datetime","int","int","int_nullable","datetime_nullable","int_nullable","int","int","int_nullable","char_nullable","char","date_nullable","char_nullable","char_nullable","char_nullable","int_nullable","char_nullable"};
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,500000);
                        break;  
                    case "CIDADES":                        
                        tableName = "BTH_CIDADE";
                        tipos = new String[]{"int","int_nullable","char","char_nullable","int_nullable","char_nullable","int_nullable","datetime_nullable"};
                        sqlUtil.readLargeXML2(entrada.toLowerCase(),tipos,tableName,3000);//2500
                        break;
                    case "PARECERES":                        
                        tableName = "BTH_PARECER";
                        tipos = new String[]{"int","int","datetime","int","datetime","int","char_nullable","char_nullable","int","date_nullable","char_nullable","int","char_nullable","char"};
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;      
                    case "PESSOAS":                        
                        tableName = "BTH_PESSOA";
                        tipos = new String[]{"int","int_nullable","char","char_nullable","char","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","int_nullable"};                        
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;
                    case "PESSOAS_NOMES":                        
                        tableName = "BTH_PESSOA_NOME";
                        tipos = new String[]{"int","date","char","char_nullable","char_nullable"};
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;    
                    case "PESSOAS_JURIDICAS":                        
                        tableName = "BTH_PESSOA_JURIDICA";
                        tipos = new String[]{"int","int_nullable","int_nullable","char_nullable","char_nullable","date_nullable","char","char","char_nullable","char_nullable","char_nullable","date_nullable","char_nullable","int"};                        
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;  
                    case "PESSOAS_JURIDICAS_SOCIOS":                        
                        tableName = "BTH_PESSOA_JURIDICA_SOCIO";
                        tipos = new String[]{"int","int","decimal_nullable","date_nullable","date_nullable"};
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;                            
                    case "PESSOAS_FISICAS":                        
                        tableName = "BTH_PESSOA_FISICA";
                        tipos = new String[]{"int","int_nullable","date_nullable","char_nullable","char_nullable","int_nullable","char_nullable","char_nullable","char_nullable","date_nullable","int_nullable","char_nullable","date_nullable","char_nullable","char_nullable","char_nullable","date_nullable","int_nullable","char_nullable","char_nullable","char_nullable","date_nullable"};                        
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;        
                    case "USUARIOS":                        
                        tableName = "BTH_PESSOA";
                        tipos = new String[]{"int","char","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char","char","char_nullable","char_nullable","char","int_nullable"};                        
                        sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        break;
                    case "REQUERENTES":
                        tableName = "BTH_REQUERENTE";
                        tipos = new String[]{"int","char"};  
                        sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        break;
                    case "UNIDADES":
                        tableName = "BTH_UNIDADE";
                        tipos = new String[]{"int","char","char_nullable","int_nullable","char","char_nullable","char_nullable","char","char_nullable","char","date_nullable"};  
                        //sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;  
                    case "USUARIOSUNID":
                        tableName = "BTH_USUARIOS_UNID";
                        tipos = new String[]{"int","int","char_nullable","int_nullable"};
                        //sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;    
                    case "SOLICITACOES":
                        tableName = "BTH_SOLICITACAO";
                        tipos = new String[]{"int","char","char_nullable","int_nullable","decimal_nullable","int_nullable","char_nullable","int_nullable","char_nullable","int_nullable","char_nullable","char_nullable","char","int","int","char","char_nullable","char_nullable","char","char","char_nullable"};  
                        //sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;
                        
                    case "PROCESSOS_HISTORICO":
                        tableName = "BTH_PROCESSOS_HISTORICO";
                        tipos = new String[]{"int","int","int","datetime","char","char","int","int_nullable","char_nullable"};  
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;
                    case "HISTORICO_PROCESSO":
                        tableName = "BTH_HISTORICO_PROCESSO";
                        tipos = new String[]{"int","int","int","datetime","char_nullable","int_nullable","char_nullable","datetime","char_nullable","char_nullable"};  
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;
                    case "MOV_CONVENIOS":
                        tableName = "BTH_MOV_CONVENIOS";
                        tipos = new String[]{"int","int","int","char","char","date_nullable","datetime","datetime_nullable","date_nullable","char_nullable","int_nullable"};  
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,200000);
                        break;                           
                    case "PROCESSOS":
                        tableName = "BTH_PROCESSO";
                        tipos = new String[]{
                                                "int",
                                                "int",
                                                "int",
                                                "int",
                                                "int",
                                                "int",
                                                "date",
                                                "date_nullable",
                                                "date_nullable",
                                                "datetime_nullable",
                                                "datetime_nullable",
                                                "datetime_nullable",
                                                "int",
                                                "char",
                                                "char",
                                                "int_nullable",
                                                "char",
                                                "char_nullable",
                                                "char_nullable",
                                                "char_nullable",
                                                "int",
                                                "char_nullable",
                                                "char_nullable",
                                                "int_nullable",
                                                "char_nullable",
                                                "char",
                                                "int",
                                                "int_nullable",
                                                "char",
                                                "char_nullable",
                                                "date_nullable",
                                                "char",
                                                "char_nullable",
                                                "date_nullable",
                                                "int_nullable",
                                                "int_nullable",
                                                "int_nullable",
                                                "char_nullable",
                                                "int_nullable",
                                                "char",
                                                "int_nullable",
                                                "char_nullable",
                                                "char_nullable"
                                            };  
                        //sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName,500000);
                        break;
                        
                    default:
                        TableData tb = processaArquivoEntrada("cidade.txt");
                        System.out.println(tb.getName());
                        int i=0;
                        while (tb.getColumns().size() > i) {
                            System.out.println(tb.getColumns().get(i).getType());
                            i++ ;
                        }
                        break;
                }
                
               
                                                                                
            }catch(IOException ioE){
                entrada = "S";
            }
        }while(!entrada.equals("S"));
                             
        /*int linha = 0;
        try 
        {    
            
            StringTokenizer stk;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("c:\\tmp\\pessoas.csv"), "UTF-8"));                 
                                    
            FileOutputStream fos = new FileOutputStream(new File("c:\\tmp\\pessoas_novo.sql"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            
            ByteBuffer buffer;                                                 
            String line;
            
            String[] cols;
            String[] tipos = {"int","int","char","char","char","char","char","char","char","char","char","char","int"};
            
            
            
            String sql = "INSERT INTO pessoas VALUES \n\r";
            
            while ((line = br.readLine()) != null) {

                linha++;
                //Convertendo para UTF-8
                //buffer = StandardCharsets.UTF_8.encode(line);
                //line = StandardCharsets.UTF_8.decode(buffer).toString();

                //Tratar cada campo aqui
                //stk = new StringTokenizer(line,",",true);
                cols = line.split(",", -1);
                System.out.println("CAMPOS: "+Integer.toString(cols.length));
                sql+="(";
                for(int i=0;i<cols.length;i++){
                    sql+= Main.trataCampo(cols[i],tipos[i]);
                    if(i<cols.length-1){
                        sql+=",";
                    }
                }
                sql+="),";
                for (String col : cols) {
                    System.out.print(col);
                    System.out.print(" | ");                    
                }
                //while( stk.hasMoreTokens()) {                    
                
                //}
                System.out.println("");
                
                //System.out.println(line);
                bw.write(sql);
                bw.newLine();
                sql = "";
                
            }
            
            br.close();
            bw.close();
                        
        }catch(Exception e){
            System.out.println("Erro linha :" + Integer.toString(linha));
            e.printStackTrace();
        }finally{
            
        }
        */
    }
    
    
    public static TableData processaArquivoEntrada(String nome){        
        
        TableData tableData = new TableData();
        
        
        try{           
            File file = new File("c:\\tmp\\file\\".concat(nome)); 
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer(); 
            String line;
            StringTokenizer sTokenizer;
            List<ColumnData> columns = new ArrayList<>();

            while((line=br.readLine())!=null)  
            {  
                if(line.startsWith("TABLE_NAME:")){
                    tableData.setName(line.replace("TABLE_NAME: ", ""));
                }
                if(line.startsWith("COLUMN_TYPES: ")){
                    line = line.replace("COLUMN_TYPES: ","");
                    sTokenizer = new StringTokenizer(line, ",");
                    while (sTokenizer.hasMoreTokens()) {
                        columns.add(new ColumnData(null,sTokenizer.nextToken()));
                    }
                    tableData.setColumns(columns);                    
                }
            }

            fr.close();    //closes the stream and release the resources  
            System.out.println("Contents of File: ");  
            System.out.println(sb.toString());   //returns a string that textually represents the object  
        
        }catch(Exception e){
            e.printStackTrace();
        }
        return tableData;
    }
    
    
    public static void processaXML(String tabela){
        System.out.println(tabela);
    }
    
    
    public static String trataCampo(String campo,String tipo){
        String valor = "";
        
        switch(tipo){
            case "int":
                valor = campo.replace("'","");
                if(valor.isEmpty()){
                    valor = "NULL";
                }                        
                break;
            case "char":
                valor = campo.replace("'","");
                valor = "'".concat(valor).concat("'");
                break;
        }
                
        return valor;
    }
                
}
