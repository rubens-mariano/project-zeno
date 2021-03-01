/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author rubens
 */
public class DateService {
    public String formatDate(JXDatePicker data) {
        String retorno;
        SimpleDateFormat sdf;
        if(data.getDate() == null) {
            retorno = null;
        } else {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            retorno = sdf.format(data.getDate());
        }
       return retorno;
    }
    
        public Date formatDate(String data) {
            Date date = null;
            try {
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = new Date(sdf.parse(data).getTime());
                
            } catch (ParseException e) {
            }
       return date;
    }
}
