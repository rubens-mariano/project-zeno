/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.awt.HeadlessException;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.entities.Banco;

/**
 *
 * @author rubens
 */
public class TableService {
    
    public void onGetValueTableAction(JTable table, Date date) {
        try {
            Banco banco = new Banco();
            for (int i = 0; i < table.getRowCount(); i++) {
                banco.saveData(
                        table.getValueAt(i, 1).toString(), 
                        table.getValueAt(i, 2).toString(), 
                        table.getValueAt(i, 3).toString(), 
                        date
                );
            }
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            this.onDrainOutTableAction(table);
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados");
        }
    }
    
    public void onSetValueTableAction(JTable table, Date data) {
        try {
            this.onDrainOutTableAction(table);
            Banco banco = new Banco();
            List<Banco> bankList;
            bankList = banco.searchBank(data);

            if (bankList.isEmpty()) {
                bankList = banco.searchBank();
                
                for (Banco bankResultList : bankList) {
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(new Object[] {
                        bankResultList.getId(), bankResultList.getCodigo(), bankResultList.getDescricao(),
                        bankResultList.getSaldoAtual(), bankResultList.getSaldoAnterior()
                    });
                }
            } else {
                for (Banco bankResultList : bankList) {
                    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
                    tableModel.addRow(new Object[] {
                        bankResultList.getId(), bankResultList.getCodigo(), bankResultList.getDescricao(),
                        bankResultList.getSaldoAtual(), bankResultList.getSaldoAnterior()
                    });
                }
            }
        } catch (Exception e) {
        }
    }
    
    public void onDrainOutTableAction(JTable table) {
        while (table.getModel().getRowCount() > 0) {
            ((DefaultTableModel) table.getModel()).removeRow(0);
        }
    }
}
