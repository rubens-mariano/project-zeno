/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rubens
 */
public class ConnectionService {
    private Connection conn;
    public Connection open() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://192.168.0.5:5432/altimar_sam3?autoReconnect=true","postgres","postgres");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return conn;
    }
}
