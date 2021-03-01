/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entities;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.service.ConnectionService;

/**
 *
 * @author rubens
 */
public class Banco {
    private int id;
    private String codigo;
    private String descricao;
    private String saldoAtual;
    private String saldoAnterior;
    private Date dataSaldo;
    
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public void close() {
        if (pstmt != null) {
            try {
                pstmt.close();
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public List<Banco> searchBank() {
        List<Banco> list = new ArrayList<>();
        try {

            String sql = "SELECT da10codigo, da10nome, considerasaldo FROM da10aux "
                        + "INNER JOIN da10 ON da10id = da10auxfk "
                        + "INNER JOIN aa65 ON aa65id = da10empresa "
                        + "WHERE aa65codigo = '002' AND considerasaldo = 'S'"
                        + "ORDER BY da10codigo";

            this.connection = new ConnectionService().open();
            this.pstmt = this.connection.prepareStatement(sql);
            this.rs = this.pstmt.executeQuery();

            while (rs.next()) {
                Banco banco = new Banco();
                banco.setCodigo(rs.getString("da10codigo"));
                banco.setDescricao(rs.getString("da10nome"));
                banco.setSaldoAtual("R$ 0,00");
                
                list.add(banco);
            }
            this.close();
        } catch (SQLException e) {
            System.out.println("Erro 1");
        }
        return list;
    }
    
    public List<Banco> searchBank(Date data) {
        List<Banco> list = new ArrayList<>();
        try {

            String sql = "SELECT id_saldo, codigo_banco, nome_banco, "
                        + "saldo_atual, data_saldo "
                        + "FROM aux_saldo_bancario "
                        + "WHERE data_saldo = '" + data + "' "
                        + "ORDER BY codigo_banco";

            this.connection = new ConnectionService().open();
            this.pstmt = this.connection.prepareStatement(sql);
            this.rs = this.pstmt.executeQuery();

            while (rs.next()) {
                Banco banco = new Banco();
                banco.setId(rs.getInt("id_saldo"));
                banco.setCodigo(rs.getString("codigo_banco"));
                banco.setDescricao(rs.getString("nome_banco"));
                banco.setSaldoAtual(rs.getString("saldo_atual"));
                banco.setDataSaldo(rs.getDate("data_saldo"));
                
                list.add(banco);
            }
            this.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void saveData(String codigo, String nome, String saldo, Date data) {
        
        try {
            String sql = "INSERT INTO aux_saldo_bancario(codigo_banco, nome_banco, saldo_atual, saldo_total, data_saldo) "
                   + "VALUES (?, ?, ?, ?, ?)";
            
            this.connection = new ConnectionService().open();
            this.pstmt = this.connection.prepareStatement(sql);
            
            this.pstmt.setString(1, codigo);
            this.pstmt.setString(2, nome);
            this.pstmt.setString(3, saldo);
            this.pstmt.setDouble(4, 0);
            this.pstmt.setDate(5, data);
            
            pstmt.execute();
            pstmt.close();

            this.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(String saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public String getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(String saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public Date getDataSaldo() {
        return dataSaldo;
    }

    public void setDataSaldo(Date dataSaldo) {
        this.dataSaldo = dataSaldo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
