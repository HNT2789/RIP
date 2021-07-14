/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author linhm
 */
public class Main {
    public static void main(String[] args) throws JSONException {
        String a="Mã khách hàng không tồn tại hoặc chưa được hỗ trợ. Quý khách vui lòng kiểm tra lại. ĐT hỗ trợ 1900 5555 77.";

        ;
        
                                       JOptionPane.showMessageDialog(null, a.substring(14, 27));
        
        
    }
}
