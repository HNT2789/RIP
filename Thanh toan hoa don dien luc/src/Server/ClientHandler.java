/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Client.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hc.core5.http.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author linhm
 */
public class ClientHandler implements Runnable{
    private Socket clientsocket;
    private BufferedReader in;

    
    public  ClientHandler(Socket clientsocket) throws IOException
    {
        this.clientsocket=clientsocket;
    }
    @Override
    public void  run()
    {
        
            PrintWriter out = null;
            BufferedReader in = null;
            String check="";
            String outdata="";
            try {
                out = new PrintWriter(clientsocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
                String data;
                while ((data = in.readLine()) != null) 
                {
                        GetData get=new GetData();
                        outdata=get.GetDatabill(data,get.Randomcaptcha());
                        
                        out = new PrintWriter(clientsocket.getOutputStream());
                        out.println(outdata);
                        out.flush();

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (in != null)
                        in.close();
                    clientsocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        try {
            clientsocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    
}
