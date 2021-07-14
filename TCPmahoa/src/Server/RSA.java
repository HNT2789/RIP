package Server;


import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
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
public class RSA 
{
   
    
    public String encrypt(String strToEncrypt) {
      try {
                    // Đọc file chứa public key
                    FileInputStream fis = new FileInputStream("publicKey.rsa");
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    fis.close();

                    // Tạo public key
                    X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PublicKey pubKey = factory.generatePublic(spec);

                    // Mã hoá dữ liệu
                    Cipher c = Cipher.getInstance("RSA");
                    c.init(Cipher.ENCRYPT_MODE, pubKey);
                    byte encryptOut[] = c.doFinal(strToEncrypt.getBytes());
                   return Base64.getEncoder().encodeToString(encryptOut);


            } catch (Exception ex) {
                    ex.printStackTrace();
            }
        return null;
	
}
    
     public String decrypt(String strToDecrypt) {
     try {
                    // Đọc file chứa private key
                    FileInputStream fis = new FileInputStream("privateKey.rsa");
                    byte[] b = new byte[fis.available()];
                    fis.read(b);
                    fis.close();

                    // Tạo private key
                    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PrivateKey priKey = factory.generatePrivate(spec);

                    // Giải mã dữ liệu
                    Cipher c = Cipher.getInstance("RSA");
                    c.init(Cipher.DECRYPT_MODE, priKey);
                    byte decryptOut[] = c.doFinal(Base64.getDecoder().decode(strToDecrypt));
                   return new String(decryptOut);
            } catch (Exception ex) 
            {
                    ex.printStackTrace();
            }
        return null;
}
}

