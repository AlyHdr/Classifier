
package classifier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public DataBaseHandler() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=dataSet;integratedSecurity=true;");
            st = con.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Set> getAthletesTraining()
    {
        ArrayList<Set> setTraining=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from athletes where type='TR'");
            
            while(rs.next())
            {
                ArrayList<Double> atts=new ArrayList<>();
                atts.add(rs.getDouble(3));
                atts.add(rs.getDouble(4));
                setTraining.add(new Set(rs.getString(2),atts ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTraining;
    }
    public ArrayList<Set> getAthletesTest()
    {
        ArrayList<Set> setTraining=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from athletes where type='TS'");
            
            while(rs.next())
            {
                ArrayList<Double> atts=new ArrayList<>();
                atts.add(rs.getDouble(3));
                atts.add(rs.getDouble(4));
                setTraining.add(new Set(rs.getString(2),atts ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTraining;
    }
    public ArrayList<Set> getIrisTest()
    {
        ArrayList<Set> setTraining=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from iris where type='TS'");
            
            while(rs.next())
            {
                ArrayList<Double> atts=new ArrayList<>();
                atts.add(rs.getDouble(1));
                atts.add(rs.getDouble(2));
                atts.add(rs.getDouble(3));
                atts.add(rs.getDouble(4));
                setTraining.add(new Set(rs.getString(5),atts ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTraining;
    }
    public ArrayList<Set> getIrisTrainings()
    {
                ArrayList<Set> setTraining=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from iris where type='TR'");
            
            while(rs.next())
            {
                ArrayList<Double> atts=new ArrayList<>();
                atts.add(rs.getDouble(1));
                atts.add(rs.getDouble(2));
                atts.add(rs.getDouble(3));
                atts.add(rs.getDouble(4));
                setTraining.add(new Set(rs.getString(5),atts ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTraining;

    }
     public ArrayList<Set> getCancerTest()
    {
        ArrayList<Set> setTraining=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from cancer where type='TS'");
            
            while(rs.next())
            {
                ArrayList<Double> atts=new ArrayList<>();
                for(int i=2;i<=31;i++)
                    atts.add(rs.getDouble(i));
                setTraining.add(new Set(rs.getString(1),atts ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTraining;
    }
    public ArrayList<Set> getCancerTrainings()
    {
                ArrayList<Set> setTraining=new ArrayList<>();
        try {
            rs=st.executeQuery("select * from cancer where type='TR'");
            
            while(rs.next())
            {
                ArrayList<Double> atts=new ArrayList<>();
                for(int i=2;i<=31;i++)
                    atts.add(rs.getDouble(i));
                setTraining.add(new Set(rs.getString(1),atts ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTraining;

    }
}
