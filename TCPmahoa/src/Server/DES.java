package Server;


import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author linhm
 */
public class DES {
    
    
    public String encrypt(String strToEncrypt) {
      try {
            String SECRET_KEY = "12345678";
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            byte[] byteEncrypted = cipher.doFinal(strToEncrypt.getBytes());
            return    Base64.getEncoder().encodeToString(byteEncrypted);
        } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
        }
    
    
    public String decrypt(String strToDecrypt) {
      try {
           String SECRET_KEY = "12345678";
            SecretKeySpec skeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);  
           byte[] byteDecrypted = cipher.doFinal(Base64.getDecoder().decode(strToDecrypt));         
            return new String(byteDecrypted);
            
      } catch (Exception e) {
            System.out.println(e.toString());
      }
      return null;
}
}

