/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
                    
                    case "PESSOAS":                        
                        tableName = "BTH_PESSOA";
                        tipos = new String[]{"int","int_nullable","char","char_nullable","char","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","char_nullable","int_nullable"};                        
                        sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
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
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName);
                        break;  
                    case "USUARIOSUNID":
                        tableName = "BTH_USUARIOS_UNID";
                        tipos = new String[]{"int","int","char_nullable","int_nullable"};
                        //sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName);
                        break;    
                    case "SOLICITACOES":
                        tableName = "BTH_SOLICITACAO";
                        tipos = new String[]{"int","char","char_nullable","int_nullable","decimal_nullable","int_nullable","char_nullable","int_nullable","char_nullable","int_nullable","char_nullable","char_nullable","char","int","int","char","char_nullable","char_nullable","char","char","char_nullable"};  
                        //sqlUtil.readXML(entrada.toLowerCase(),tipos,tableName);
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName);
                        break;
                        
                    case "PROCESSOS_HISTORICO":
                        tableName = "BTH_PROCESSOS_HISTORICO";
                        tipos = new String[]{"int","int","int","datetime","char","char","int","int_nullable","char_nullable"};  
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName);
                        break;
                    case "HISTORICO_PROCESSO":
                        tableName = "BTH_HISTORICO_PROCESSO";
                        tipos = new String[]{"int","int","int","datetime","char_nullable","int_nullable","char_nullable","datetime","char_nullable","char_nullable"};  
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName);
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
                        sqlUtil.readLargeXML(entrada.toLowerCase(),tipos,tableName);
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
