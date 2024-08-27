/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import utilities.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ClientController {
    private String createString = "INSERT INTO `obal`.`client` (`first_name`, `last_name`, `tel`, `localite`) VALUES (?, ?, ?, ?);";
    private String updateString = "UPDATE `obal`.`client` SET `first_name` = ?, `last_name` = ?, `tel` = ?, `localite` = ? WHERE (`idClient` = ?);";
    private String findAllString = "select * from Client";
    private String findByKeyString = "select * from Client where idClient = ?";
    private String deleteByKeyString = "delete from Client where idClient = ?";
    private String findByLikeString = "select * from Client where lower(first_name) like lower(?) and lower(last_name) like lower(?)";
    
    
    private PreparedStatement createStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement findAllStmt;
    private PreparedStatement findByKeyStmt;
    private PreparedStatement deleteByKeyStmt;
    private PreparedStatement findByLikeStmt;

    private ClientController() {
        try {
            createStmt = DataSource.getConnection().prepareStatement(createString);
            updateStmt = DataSource.getConnection().prepareStatement(updateString);
            findAllStmt = DataSource.getConnection().prepareStatement(findAllString);
            findByKeyStmt = DataSource.getConnection().prepareStatement(findByKeyString);
            deleteByKeyStmt = DataSource.getConnection().prepareStatement(deleteByKeyString);
            findByLikeStmt = DataSource.getConnection().prepareStatement(findByLikeString);
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void create(Client client) throws SQLException {
        createStmt.setString(1, client.getFirstName());
        createStmt.setString(2, client.getLastName());
        createStmt.setString(3, client.getPhone());
        createStmt.setString(4, client.getLocation());
        createStmt.executeUpdate();
    }
    public void update(Client client) throws SQLException {
        updateStmt.setString(1, client.getFirstName());
        updateStmt.setString(2, client.getLastName());
        updateStmt.setString(3, client.getPhone());
        updateStmt.setString(4, client.getLocation());
        updateStmt.setString(5, String.valueOf(client.getId()));
        updateStmt.executeUpdate();
    }
    public List<Client> findAll() {
        List<Client> ls = new ArrayList();
        try {

            ResultSet set = findAllStmt.executeQuery();
            int id;
            String firstName;
            String lastName;
            String phone;
            String location;
            
            while (set.next()) {
                id = Integer.parseInt(set.getString(1));
                firstName = set.getString(2);
                lastName = set.getString(3);
                phone = set.getString(4);
                location = set.getString(5);
                ls.add(new Client( id,firstName, lastName ,phone,location));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    public Client findByKey(int id) {
        try {
            findByKeyStmt.setString(1,  String.valueOf(id));
            ResultSet set = findByKeyStmt.executeQuery();
            if (set.next()) {
                return new Client(id, set.getString(2), set.getString(3),set.getString(4),set.getString(5));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public void deleteByKey(Client client) throws SQLException {
        deleteByKeyStmt.setString(1, String.valueOf(client.getId()));
        deleteByKeyStmt.executeUpdate();
    }
    public List<Client> findByLike(String firstName) {
        List<Client> ls = new ArrayList();
        String fName,lName;
        if(firstName==null){
            fName="";
            lName="";
        }
        else{
        String[] table = firstName.split(" ");
       
            fName = table[0];
        try{ 
            lName = table[1];
        }
        catch(ArrayIndexOutOfBoundsException ex){
            lName="";
        }
        }
        
        try {
                findByLikeStmt.setString(1, "%" + fName + "%");
                findByLikeStmt.setString(2, "%" + lName + "%");
            
            ResultSet set = findByLikeStmt.executeQuery();
            while (set.next()) {
                Client c = new Client(Integer.parseInt(set.getString(1)), set.getString(2), set.getString(3),set.getString(4),set.getString(5));
                ls.add(c);
               // System.out.println(c);
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }
    
    
    
    public static final ClientController instance = new ClientController();
    
}
