/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mar
 */
public class Servidor {
    
    private Socket socket;
    private ServerSocket servidor;
    
    private OutputStream os;
    private InputStream is;
    
    private DataOutputStream salida;
    private DataInputStream entrada;
    
    private boolean opcion = true;
    
    private Scanner scanner;
    private String cadena;
    
    public void establecerConexion(int puerto){
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("El servidor esta escuchando el el puerto: " + puerto);
            socket = servidor.accept();
            
            Thread hilo = new Thread(new Runnable() {
            @Override
                public void run() {
                    while(opcion){
                        System.out.println("SERVIDOR: ");
                        recibirDatos();
                    }   
                }
            });
            hilo.start();
            
            while(opcion){
                scanner = new Scanner(System.in);
                cadena = scanner.nextLine();
                if(!cadena.equals("CLIENTE: fin")){
                    enviarDatos("SERVIDOR: " + cadena);
                }else{
                    opcion = false;
                }
            }
            
        } catch (Exception ex) {
            System.out.println("Error al abrir los sockets");
        }
    }
    
    public void enviarDatos(String datos){
        try {
            os = socket.getOutputStream();
            salida = new DataOutputStream(os);
            salida.writeUTF(datos);
            salida.flush();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void recibirDatos(){
        try {
            is = socket.getInputStream();
            entrada = new DataInputStream(is);
            System.out.println(entrada.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cerrar(){
        try {
            salida.close();
            entrada.close();
            socket.close();
            servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   
    
}
