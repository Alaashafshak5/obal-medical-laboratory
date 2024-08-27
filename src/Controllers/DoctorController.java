/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Client;
import utilities.DataSource;
import Entities.Doctor;
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
public class DoctorController {

    private String createString = "INSERT INTO `obal`.`medecin` (`idMedecin`, `titre`, `nom`, `prenom`, `localite`, `phone`) VALUES (?,?,?,?,?,?);";
    private String updateString = "UPDATE `obal`.`medecin` SET `nom` = ?, `prenom` = ?, `titre` = ?,`phone`=?, `localite` = ? WHERE (`idMedecin` = ?);";
    private String findAllString = "select * from medecin";
    private String findByKeyString = "select * from medecin where idMedecin = ?";
    private String deleteByKeyString = "delete from medecin where idMedecin = ?";
    private String findByLikeString = "select * from medecin where lower(nom) like lower(?) and lower(prenom) like lower(?)";

    private PreparedStatement createStmt;
    private PreparedStatement updateStmt;
    private PreparedStatement findAllStmt;
    private PreparedStatement findByKeyStmt;
    private PreparedStatement deleteByKeyStmt;
    private PreparedStatement findByLikeStmt;

    private DoctorController() {
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

    public void create(Doctor doctor) throws SQLException {
        String code;
        if(doctor.getFirstName().length()<3){
         if(doctor.getLastName().length()<3){
             code=doctor.getFirstName()+doctor.getLastName();
         }
         else 
           code=doctor.getFirstName()+doctor.getLastName().substring(0, 3);
        }else
            if(doctor.getLastName().length()<3){
                code= doctor.getFirstName().substring(0, 3)+doctor.getLastName();
            }else
        code = doctor.getFirstName().substring(0, 3) + doctor.getLastName().substring(0, 3);
        code = code.toUpperCase();
        createStmt.setString(1, code);
        createStmt.setString(2, doctor.getTitle());
        createStmt.setString(3, doctor.getFirstName());
        createStmt.setString(4, doctor.getLastName());
        createStmt.setString(5, doctor.getLocation());
        createStmt.setString(6, String.valueOf(doctor.getPhone()));
        createStmt.executeUpdate();
    }

    public void update(Doctor doctor) throws SQLException {
        updateStmt.setString(1, doctor.getFirstName());
        updateStmt.setString(2, doctor.getLastName());
        updateStmt.setString(3, doctor.getTitle());
        updateStmt.setString(4, String.valueOf(doctor.getPhone()));
        updateStmt.setString(5, doctor.getLocation());
        updateStmt.setString(6, doctor.getId());
        updateStmt.executeUpdate();
    }

    public List<Doctor> findAll() {
        List<Doctor> ls = new ArrayList();
        try {

            ResultSet set = findAllStmt.executeQuery();
            String id;
            String firstName;
            String lastName;
            String phone;
            String location;
            String title;

            while (set.next()) {
                id = set.getString(1);
                firstName = set.getString(4);
                lastName = set.getString(3);
                phone = set.getString(6);
                location = set.getString(5);
                title = set.getString(2);

                ls.add(new Doctor(id, firstName, lastName, phone, location, title));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public Doctor findByKey(String id) {
        try {
            findByKeyStmt.setString(1, id);
            ResultSet set = findByKeyStmt.executeQuery();
            if (set.next()) {
                return new Doctor(id, set.getString(3), set.getString(4), set.getString(6), set.getString(5), set.getString(2));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deleteByKey(Doctor doctor) throws SQLException {
        deleteByKeyStmt.setString(1, doctor.getId());
        deleteByKeyStmt.executeUpdate();
    }

    public List<Doctor> findByLike(String firstName) {
        List<Doctor> ls = new ArrayList();
        String fName, lName;
        if (firstName == null) {
            fName = "";
            lName = "";
        } else {
            String[] table = firstName.split(" ");
            fName = table[0];
            try {
                lName = table[1];
            } catch (ArrayIndexOutOfBoundsException ex) {
                lName = "";
            }
        }
        try {
           
                findByLikeStmt.setString(1, "%" + fName + "%");
                findByLikeStmt.setString(2, "%" + lName + "%");
            
            ResultSet set = findByLikeStmt.executeQuery();
            while (set.next()) {
                Doctor d = new Doctor(set.getString(1), set.getString(3), set.getString(4), set.getString(6), set.getString(5), set.getString(2));
                ls.add(d);
                // System.out.println(c);
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ls;
    }

    public static final DoctorController instance = new DoctorController();

}
