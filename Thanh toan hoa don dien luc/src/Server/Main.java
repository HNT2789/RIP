/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author linhm
 */
public class Main {
    public static void main(String args[]) throws IOException{
        try
        {
        ServerSocket theServer = new ServerSocket(8889);
        while (true)
                {
                     Socket client = theServer.accept();
                     System.out.println("New client connected " + client.getInetAddress().getHostAddress());
                     ClientHandler cliensocket=new ClientHandler(client);
                     new Thread(cliensocket).start();
                     
                }
        }
        catch(SocketException e)
        {
            
        }
         catch (IOException ex) 
        {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
