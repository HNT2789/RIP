/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import thanh.toan.hoa.don.dien.luc.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author linhm
 */
public class GetData {
    
    private String Billcode="PD07000091129";
    private String Code;
    private String Data;
    private String Success;
    private String CustomerName;
    private String Address;       
    private String Money;

    
    public String Randomcaptcha()
    {
        Random rd=new Random();
        int cap=100000+rd.nextInt(899999);
        return Integer.toString(cap);
    }
    
    
    
        public static String Gethashcaptcha(String captcha) throws IOException, ParseException, JSONException
        {
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("input", captcha));
            String result = Post("https://www.vban.vn/service/visadirect/gen-captcha-hash", urlParameters);
            JSONObject myResponse = new JSONObject(result);
            return myResponse.getString("Data");
        }
    
    
        public  String GetDatabill(String Billcode, String captcha) throws IOException, ParseException, JSONException
        {
            
            
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("billCode", Billcode));
            urlParameters.add(new BasicNameValuePair("providerCode", "EVN"));
            urlParameters.add(new BasicNameValuePair("captcha", captcha));
            urlParameters.add(new BasicNameValuePair("hash", Gethashcaptcha(captcha)));
            String result = Post("https://www.vban.vn/service/elec/getbill", urlParameters);
            return result;
        }
        
        public static String Post(String url,List urlParameters ) throws IOException, ParseException
        {
            HttpPost post = new HttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            return EntityUtils.toString(response.getEntity());
        }
}

