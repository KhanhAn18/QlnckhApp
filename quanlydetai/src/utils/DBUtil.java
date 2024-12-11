package utils;

import java.sql.*;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtil
{
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connect = null;
    private static final String URL = "jdbc:mysql://localhost:3306/qlnckh";

    public static void connectDB()
    {
        try
        {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(URL, "root", "KhanhAn1802+");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void disconnectDB()
    {
        try
        {
            if(connect != null && !connect.isClosed())
            {
                connect.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static ResultSet ExecuteQuery(String query) throws SQLException
    {
        Statement statement = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try
        {
            connectDB();
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            if(resultSet != null)
            {
                resultSet.close();
            }
            if(statement != null)
            {
                statement.close();
            }
            disconnectDB();
        }
        return crs;
    }

    public static void ExecuteUpdate(String query) throws SQLException
    {
        Statement statement = null;
        try
        {
            connectDB();
            statement = connect.createStatement();
            statement.executeUpdate(query);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            if(statement != null)
            {
                statement.close();
            }
            disconnectDB();
        }
    }
}