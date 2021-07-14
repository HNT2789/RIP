/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author linhm
 */
public class InforBillcode {
    private String Code;
    private String Data;
    private boolean Success;
    private String CustomerName;
    private String CustomerAddr;       
    private JSONObject dataJson;
    
    public InforBillcode()
    {
    }
    
    public  InforBillcode(JSONObject dataJson) throws JSONException
    {
        try
        {
         this.dataJson=dataJson;
         this.Code=dataJson.getString("Code");
         this.CustomerAddr=dataJson.getString("CustomerAddr");
         this.CustomerName=dataJson.getString("CustomerName");
         this.Data=Integer.toString(dataJson.getInt("Data"));
         this.Success=dataJson.getBoolean("Success");
        }
        catch(JSONException e)
        {
            this.Data=dataJson.getString("Data");
        }
    }
    
     public String getCustomerAddr() 
    {
        return CustomerAddr;
    }
    
    public String setCustomerAddr() throws JSONException 
    {
        return this.CustomerAddr=dataJson.getString("CustomerAddr");
    }
    
     public String getCustomerName() 
    {
        return CustomerName;
    }
    
    public String setCustomerName() throws JSONException 
    {
        return this.CustomerName=dataJson.getString("CustomerName");
    }
    
     public String getData() 
    {
        return Data;
    }
    
    public String setData() throws JSONException 
    {
        return this.Data=dataJson.getString("Data");
    }
    
     public boolean getSuccess() 
    {
        return Success;
    }
    
    public boolean setSuccess() throws JSONException 
    {
        return this.Success=dataJson.getBoolean("Success");
    }
    
    public String getCode() 
    {
        return Code;
    }
    
    public String setCode() throws JSONException 
    {
        return this.Code=dataJson.getString("Code");
    }
}
