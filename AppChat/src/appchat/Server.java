/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Hieu
 */
public class Server {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    static ArrayList<Socket> maykhach = new ArrayList<>();

    public Server() throws IOException {
        ServerSocket myServer = new ServerSocket(5555);
        Socket clientSocket;
        while (true) {
            clientSocket = new Socket();
            clientSocket = myServer.accept();
            maykhach.add(clientSocket);
            ReadServer clien = new ReadServer(clientSocket);
//            ReadServer clien = new ReadClient(clientSocket);
            clien.start();
        }
    }

    public static void main(String[] args) throws IOException {
        Server sieuserver = new Server();
    }

}

class ReadServer extends Thread {

    Socket cli;
    String us = null;

    public ReadServer(Socket cli) {
        this.cli = cli;
    }

    @Override
    public void run() {
        DataInputStream is = null;
        DataOutputStream os = null;

        try {
            is = new DataInputStream(cli.getInputStream());
            us = is.readUTF();
            while (true) {
                String mes = is.readUTF();
                for (Socket soc : Server.maykhach) {
                    if (!soc.equals(cli)) {
                        os = new DataOutputStream(soc.getOutputStream());
                        os.writeUTF("[" + us + "]: " + mes);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
