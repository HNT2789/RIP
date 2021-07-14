package Server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Quang Linh
 */
public class thread extends Thread{
    public void run(){
    
    BufferedReader inFromClient;
        String data;
        String outdata="";
        String check="";
        PrintWriter outToClient;
        try {
                ServerSocket theServer = new ServerSocket(8889);
                while (true)
                {
                            Socket service = theServer.accept();
                            inFromClient = new BufferedReader(new InputStreamReader(service.getInputStream()));
                            data = inFromClient.readLine();
                            check=data.substring(0, 1);
                            outToClient = new PrintWriter(service.getOutputStream());
                        if(check.compareTo("1")==0)
                        {
                                    if(data.substring(1, 2).compareTo("1")==0)
                                    {
                                            AES enAES=new AES();
                                            outdata=enAES.encrypt(data.substring(2), "TVD");
                                    }
                                    else
                                    {
                                            AES deAES=new AES();
                                            outdata=deAES.decrypt(data.substring(2), "TVD");
                                    } 
                        }
                            else if(check.compareTo("2")==0)
                              {
                                    if(data.substring(1, 2).compareTo("1")==0)
                                        {
                                                DES enDES=new DES();
                                                outdata=enDES.encrypt(data.substring(2));
                                        }
                                        else
                                        {
                                               DES deDES=new DES();
                                                outdata=deDES.decrypt(data.substring(2));
                                        } 

                            }
                            else if(check.compareTo("3")==0)
                            {
                                 if(data.substring(1, 2).compareTo("1")==0)
                                        {
                                                RSA enRSA=new RSA();
                                                outdata=enRSA.encrypt(data.substring(2));
                                        }
                                        else
                                        {
                                               RSA deRSA=new RSA();
                                               outdata=deRSA.decrypt(data.substring(2));
                                        } 
                                    
                            }  
                                outToClient.println(outdata);
                                outToClient.flush();
                }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
