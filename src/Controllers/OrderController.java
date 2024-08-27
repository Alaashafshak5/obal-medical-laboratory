/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Analysis;
import Entities.Client;
import Entities.Doctor;
import Entities.Order;
import static java.sql.Date.valueOf;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import utilities.DataSource;

/**
 *
 * @author lenovo
 */
public class OrderController {

    private String createString = "insert into `obal`.`ordonnance`(`date`,`idmedecin`,`idclient`,`isPayed`) values (?,?,?,?)";
    private String deleteString = "delete from ordonnance where idordonnance = ?  ;";
    private String resultCreateString = "insert into `obal`.`results` values(?,?,?,?)";
    private String resultDeleteString = "delete from results where idordonnance= ?";
    private String datesString = "select DISTINCT date FROM ORDONNANCE ORDER BY date DESC";
    private String findByDate = "SELECT ordonnance.idclient,ordonnance.idordonnance,ordonnance.date,ordonnance.ispayed," +
"            client.first_name,client.last_name,client.tel,client.localite,medecin.nom,medecin.prenom,medecin.titre,medecin.phone" +
"             from ordonnance join client on ordonnance.idClient=client.idClient " +
"			 join medecin on ordonnance.idMedecin=medecin.idmedecin" +
"             where date=?;";
    //private String findByDateResults="select * from results where results.idordonnance= ? ";
    private String findByDateAnalysis="select analyse.* from analyse,results where analyse.idAnalyse=results.idAnalyse and results.idordonnance=?";
    private String lastID="select idordonnance from ordonnance order by idordonnance desc limit 1;";
    private String findByClient = "select * from ordonnance where idclient = ?";
    
    private PreparedStatement createStmt;
    private PreparedStatement deleteStatement;
    private PreparedStatement resultCreateStatement;
    private PreparedStatement resultDeleteStatement;
    private PreparedStatement dateStatement;
    private PreparedStatement findByDateStatement;
    private PreparedStatement lastIDStatement;
    private PreparedStatement findByDateAnalysisStatement;
    private PreparedStatement findByClientStatment;
//    private PreparedStatement findByDateResultsStatement;
    public OrderController() {

        try {
            createStmt = DataSource.getConnection().prepareStatement(createString);
            deleteStatement = DataSource.getConnection().prepareStatement(deleteString);
            resultCreateStatement = DataSource.getConnection().prepareStatement(resultCreateString);
            resultDeleteStatement = DataSource.getConnection().prepareStatement(resultDeleteString);
            dateStatement = DataSource.getConnection().prepareStatement(datesString);
            findByDateStatement = DataSource.getConnection().prepareStatement(findByDate);
            lastIDStatement=DataSource.getConnection().prepareStatement(lastID);
            findByDateAnalysisStatement=DataSource.getConnection().prepareStatement(findByDateAnalysis);
            findByClientStatment=DataSource.getConnection().prepareStatement(findByClient);


//findByDateResultsStatement=DataSource.getConnection().prepareStatement(findByDateResults);
            
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void create(Order order) throws SQLException {
        Client c = order.getClient();
        int x = c.getId();
        createStmt.setDate(1, valueOf(order.getDate()));
        createStmt.setString(2, order.getDoctor().getId());
        createStmt.setInt(3, x);
        createStmt.setInt(4, 0);
        createStmt.executeUpdate();
        for (Analysis a : order.getListOrders()) {
            int z = OrderController.instance.getLastID();
            int s = a.getId();
            resultCreateStatement.setDouble(1, 0);
            resultCreateStatement.setDouble(2, 0);
            resultCreateStatement.setInt(3, z);
            resultCreateStatement.setInt(4, s);
            resultCreateStatement.executeUpdate();
        }

    }

    public void delete(Order order) throws SQLException {
        
        //for (Analysis a : order.getListOrders()) {
            resultDeleteStatement.setString(1, String.valueOf(order.getId()));
            resultDeleteStatement.executeUpdate();
       // }
        deleteStatement.setString(1, String.valueOf(order.getId()));
        deleteStatement.executeUpdate();

    }

    public void updateAnalysis(Order order) throws SQLException{
        
        resultDeleteStatement.setString(1, String.valueOf(order.getId()));
        resultDeleteStatement.executeUpdate();
        
        for (Analysis a : order.getListOrders()) {
            int s = a.getId();
            resultCreateStatement.setDouble(1, 0);
            resultCreateStatement.setDouble(2, 0);
            resultCreateStatement.setInt(3, order.getId());
            resultCreateStatement.setInt(4, s);
            resultCreateStatement.executeUpdate();
        }

        
    }
    public List<Date> date() throws SQLException {
        List<Date> list = new ArrayList();
        ResultSet set = dateStatement.executeQuery();
        while (set.next()) {
            list.add(set.getDate(1));
        }
        set.close();

        return list;
    }

    public List<Order> findByDate(Date d) throws SQLException {
        findByDateStatement.setDate(1, (java.sql.Date) d);
        List<Order> list = new ArrayList();
        ResultSet set = findByDateStatement.executeQuery();
        int id;
        Client client;
        Doctor doctor;
        Boolean paid;
        Order o;
        Analysis a;
        while (set.next()) {
            List<Analysis> listAnalysis=new ArrayList();
            id = set.getInt(2);
            client = new Client(set.getInt(1), set.getString(5), set.getString(6), set.getString(7), set.getString(8));
            doctor=new Doctor(null,set.getString(10), set.getString(9), set.getString(12), null, set.getString(11));
            paid = set.getInt(4)==1;
            findByDateAnalysisStatement.setInt(1,id);
            ResultSet sett=findByDateAnalysisStatement.executeQuery();
            while(sett.next()){
                a=new Analysis(sett.getInt(1), sett.getString(2), sett.getString(3), sett.getString(4), (float) sett.getDouble(5));
                listAnalysis.add(a);
            }
            o=new Order(id,valueOf(d.toString()).toLocalDate(), paid, client,doctor,listAnalysis);
            list.add(o);
        }

        return list;

    }
    
    public int getLastID() throws SQLException{
        ResultSet set=lastIDStatement.executeQuery();
        while(set.next()){
            return set.getInt(1);
            
        }   
        return 0;
    }
    public List<Order> findByClient(Client cl) throws SQLException{
        findByClientStatment.setInt(1, cl.getId());
        ResultSet res = findByClientStatment.executeQuery();
        List<Order> ls=new ArrayList();
        Order o;
        Boolean isPaid;
        while(res.next()){
            isPaid = (res.getInt(3) == 1)? true : false;
            o = new Order(res.getInt(1), res.getDate(2).toLocalDate(),isPaid,null,null,null);
            ls.add(o);
        }
        return ls;
    }
    

    public static final OrderController instance = new OrderController();

    public static void main(String[] args) throws SQLException {
        
        Order order = new Order();
        order.setClient(new Client(2));
    }
}
