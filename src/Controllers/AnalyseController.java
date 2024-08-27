/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Analysis;
import Entities.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utilities.DataSource;

/**
 *
 * @author lenovo
 */
public class AnalyseController {
    
    private String findAll="select * from analyse";
    private String findByOrder = "select Analyse.idAnalyse , Analyse.name , Analyse.unite , Analyse.valeur , Analyse.price ,results.results from analyse, results where results.idordonnance = ? and results.idanalyse = analyse.idanalyse;";
    private String addResult = "update results set results = ? where idordonnance = ? and idanalyse = ?;";
    private PreparedStatement addResultStatement;
    private PreparedStatement findAllStatement;
    private PreparedStatement findByOrderStatement;

    private AnalyseController() {
        try {
            findAllStatement=DataSource.getConnection().prepareStatement(findAll);
            findByOrderStatement=DataSource.getConnection().prepareStatement(findByOrder);
            addResultStatement=DataSource.getConnection().prepareStatement(addResult);
        } catch (SQLException ex) {
            Logger.getLogger(AnalyseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Analysis> findAll()throws SQLException{
        List<Analysis> list=new ArrayList();
        
        ResultSet set=findAllStatement.executeQuery();
        String name;
        float price;
        int id;
        String unit;
        String value;
        while(set.next()){
            id=set.getInt(1);
            name=set.getString(2);
            unit=set.getString(3);
            value=set.getString(3);
            price=(float) set.getDouble(5);
            list.add(new Analysis(id,name,unit,value,price));
        }
            
        
        
        return list;
    }
    public Order findByOrder(Order o) throws SQLException{
        
        findByOrderStatement.setInt(1,o.getId());
        ResultSet res = findByOrderStatement.executeQuery();
        Analysis a;
        while(res.next()){
            a = new Analysis(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getFloat(5));
            a.setResult(res.getFloat(6));
            o.listAnalysis.add(a);
        }
        return o;
        
    }
    public Void addResult(Order o) throws SQLException{
        
        for(Analysis a : o.listAnalysis){
            addResultStatement.setDouble(1,a.getResult());
            addResultStatement.setInt(2,o.getId());
            addResultStatement.setInt(3,a.getId());
            addResultStatement.executeUpdate();
            
        }
        return null;
    }
    public static final AnalyseController instance=new AnalyseController() ;
    
}
