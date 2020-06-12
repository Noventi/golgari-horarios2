package org.conection_db;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import sun.awt.AWTAccessor;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, SQLException {

        Scanner sc = new Scanner(System.in);
        String cadena;
        File archivo2 = new File("src/main/resources/configuracion.txt");
        int a = 0, b = 0;
        ArrayList<String> cadenasDeDatos = new ArrayList<String>();
        ArrayList<String> datos = new ArrayList<String>();

        try{
            String path = archivo2.getCanonicalPath();
            File archivo = new File(path);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            while((cadena=br.readLine())!=null){
                if(a>0){
                    String [] dividiendo = cadena.split(",");
                    cadenasDeDatos.add(dividiendo[1]);
                }
                a++;
            }
        }catch (FileNotFoundException e){
            System.out.println("No se encontro nada mi chavo");
        }

        CargaScript cScript = new CargaScript();

        if(cadenasDeDatos.get(4).equals("excel") || cadenasDeDatos.get(4).equals("Excel") || cadenasDeDatos.get(4).equals("xlsx")){
            ArchivoXLSX arcXlsx = new ArchivoXLSX();
            arcXlsx.lecturaDatos(cadenasDeDatos.get(5)+".xlsx",cadenasDeDatos.get(0),cadenasDeDatos.get(1),cadenasDeDatos.get(3),cadenasDeDatos.get(2));
        }else if (cadenasDeDatos.get(4).equals("txt") || cadenasDeDatos.get(4).equals("Txt") || cadenasDeDatos.get(4).equals("Archivo de texto")){
            ArchivoTXT arcTXT = new ArchivoTXT();
            arcTXT.lecturaDatosTxt();
        }
    }
}
