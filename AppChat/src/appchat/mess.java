/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appchat;

import javax.swing.JLabel;

/**
 *
 * @author Hieu
 */
public class mess {

    String type;
    String content;
    JLabel test;

    public mess(String ty, String con) {
        this.type = ty;
        this.content = con;
    }

    public JLabel trave() {
        test = new JLabel();
        test.setText(content);
        if (type.equals("me")) {

            test.setBounds(405, 50, 0, 0);
        } else {
            test.setBounds(405, 50, 0, 0);
        }

        return test;
    }
}
