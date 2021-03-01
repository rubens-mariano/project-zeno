/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import com.xzq.osc.JocGroupPane;

/**
 *
 * @author rubens
 */
public class MenuServices {
    
    public void onMenuExpandedAction(JocGroupPane pane1, JocGroupPane pane2, JocGroupPane pane3, JocGroupPane pane4, 
                                    JocGroupPane pane5, JocGroupPane pane6, JocGroupPane pane7, JocGroupPane pane8, JocGroupPane pane9) {
        if (pane1.isExpanded()) {
            pane1.setExpanded(false);
        } else {
            pane1.setExpanded(true);
            pane2.setExpanded(false);
            pane3.setExpanded(false);
            pane4.setExpanded(false);
            pane5.setExpanded(false);
            pane6.setExpanded(false);
            pane7.setExpanded(false);
            pane8.setExpanded(false);
            pane9.setExpanded(false);
        }
    }
    
    public void onSubMenuExpandedAction(JocGroupPane pane1, JocGroupPane pane2, JocGroupPane pane3, JocGroupPane pane4, 
                                    JocGroupPane pane5, JocGroupPane pane6, JocGroupPane pane7, JocGroupPane pane8, JocGroupPane pane9) {
        if (pane1.isExpanded()) {
            pane1.setExpanded(false);
        } else {
            pane1.setExpanded(true);
            pane2.setExpanded(true);
            pane3.setExpanded(false);
            pane4.setExpanded(false);
            pane5.setExpanded(false);
            pane6.setExpanded(false);
            pane7.setExpanded(false);
            pane8.setExpanded(false);
            pane9.setExpanded(false);
        }
    }
}
