package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import model.User;
import utils.DBUtil;


public class UserDAO
{
    private UserDAO()
    {
    }
    public static UserDAO Instance()
    {
        return SingletonHelper.INSTANCE;
    }
    private static class SingletonHelper
    {
        private static final UserDAO INSTANCE = new UserDAO();
    }
    // Thêm tài khoản
    public void insert(User user)
    {
        String query = "INSERT INTO employee(username,password,question,answer,date) VALUES"
                       + "('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getQuestion() + "','"
                       + user.getAnswer() + "','" + user.getDate() + "')";
        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Cập nhật mật khẩu
    public void update(String username, String password)
    {
        String query = "UPDATE employee SET "
                       + "password='" + password + "' "
                       + "WHERE username='" + username + "'";
        
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Xóa tài khoản
    public void delete(String username)
    {
        String query = "DELETE FROM employee WHERE username='" + username + "'";
        try
        {
            DBUtil.ExecuteUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    // Kiểm tra password có chính xác không
    public boolean isPasswordValid(String username, String password) throws SQLException
    {
        String query = "SELECT * FROM employee WHERE username='" + username 
                        + "' AND password='" + password + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
    // Kiểm tra username có tồn tại không
    public boolean isUsernameExist(String username) throws SQLException
    {
        String query = "SELECT * FROM employee WHERE username='" + username + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
    // Kiểm tra câu trả lời có khớp với câu hỏi không
    public boolean isAnswerValid(String username, String answer) throws SQLException
    {
        String query = "SELECT * FROM employee WHERE username='" + username 
                        + "' AND answer='" + answer + "'";
        ResultSet resultSet = DBUtil.ExecuteQuery(query);
        return resultSet.next();
    }
}