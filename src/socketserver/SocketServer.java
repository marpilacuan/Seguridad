/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socketserver;

import java.io.IOException;
/**
 *
 * @author Mar
 */
public class SocketServer {
  
    
    public static void main(String[] args) throws IOException {
        Servidor server = new Servidor();
        System.out.println("Hola");
        server.establecerConexion(5555);
        
    }
    
}
