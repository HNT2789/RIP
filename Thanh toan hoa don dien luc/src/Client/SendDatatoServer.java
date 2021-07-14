/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import com.sun.management.jmx.Trace;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author linhm
 */
public class SendDatatoServer {
    
    
    
    public String send(String Data)
    {
        BufferedReader inUser;
        Socket ClientSocket=null;
        PrintWriter OutToServer=null;
        BufferedReader inServer=null;
        String receiveData=null;
        try
        {
            inUser=new BufferedReader(new InputStreamReader(System.in));
            ClientSocket=new Socket("localhost",8889);
            OutToServer=new PrintWriter(ClientSocket.getOutputStream());
            OutToServer.println(Data);
            OutToServer.flush();
            inServer=new BufferedReader(new InputStreamReader(ClientSocket.getInputStream()));
            receiveData=inServer.readLine();

            //System.out.println("\n"+receiveData);
            
            return receiveData;
        } catch (IOException ex) {
            Logger.getLogger(SendDatatoServer.class.getName()).log(Level.SEVERE, null, ex);
        }finally
       {
            if(inServer != null){
                try {
                    inServer.close();
                } catch (IOException ex) {
                    Logger.getLogger(SendDatatoServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(OutToServer != null){
                OutToServer.close();
            }
        }
        return null;
    }
    
}
