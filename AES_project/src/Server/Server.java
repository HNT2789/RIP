/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Mon
 */
public class Server {

    public static void main(String[] args) {
        Server sr = new Server();
    }

    public Server() {
        ServerSocket myserver = null;
        try {
            myserver = new ServerSocket(5555);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                Socket sk = myserver.accept();
//            DataOutputStream os = new DataOutputStream(sk.getOutputStream());
//            DataInputStream is = new DataInputStream(sk.getInputStream());
                ReadServer rs = new ReadServer(sk);
                rs.start();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class ReadServer extends Thread {

    Socket sk;
    DataInputStream is;
    DataOutputStream os;

    public ReadServer(Socket sk) {
        this.sk = sk;
        try {
            is = new DataInputStream(sk.getInputStream());
            os = new DataOutputStream(sk.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ReadServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            String luachon;
            String text;
            String key;
            boolean thoat = true;
            while (thoat) {
                luachon = is.readUTF();
                switch (luachon) {
                    case "mahoa":
                        text = is.readUTF();
                        key = is.readUTF();
                        os.writeUTF(mahoa(text, key));
                        break;
                    case "giaima":
                        text = is.readUTF();
                        key = is.readUTF();
                        os.writeUTF(giaima(text, key));
                        break;
                    default:
                        thoat = false;
                        break;
                }
            }
            os.close();
            is.close();
            sk.close();
        } catch (Exception e) {

        }
    }

    public String mahoa(String strToEncrypt, String myKey) {
        String ketqua = "";
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(myKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] byteEncrypted = cipher.doFinal(strToEncrypt.getBytes());
            ketqua = Base64.getEncoder().encodeToString(byteEncrypted);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ketqua;
    }

    public String giaima(String strToDecrypt, String myKey) {
        String ketqua = "";
        try {
            SecretKeySpec skeySpec = new SecretKeySpec(myKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));
            ketqua = new String(byteDecrypted);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return ketqua;
    }
}
//+7ZlfReRyaBJ2MIj1mEjhg==
//TVD
//teamviet.com
