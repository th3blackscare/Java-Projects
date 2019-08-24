package com.otfayoum.utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class user {
    private static String FName;

    private static String Address;
    private static String Phone_number;
    private static String Email;
    private static String Role;
    private static String user;
    private static int Id;

    private static String LName;
    private static String LAddress;
    private static String LPhone_number;
    private static String LEmail;
    private static String LRole;
    private static String Luser;
    private static int LId;

    Connection connection ;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    PreparedStatement p ;

    public user(){ connection = ConnectionUI.ConnDB(); }
    //Login Method
    public String login(String username, String password){
        String sql = "SELECT * FROM system_users Where usern = ? and password = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return "Error";
            } else {
                this.LId = resultSet.getInt("id");
                getLUser(getLID());
                this.Luser = username;
                return "Success";
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return "Exception";
        }
    }


    // update user data
    public boolean updateData(int id, String name,String Address,String Phone_number,String Role,String Email){
        String sql = "UPDATE system_users_info SET Name= ?, Address= ?, Phone_Number= ?, Role= ?, Email_Address= ? WHERE id = ?";
        String sql1 = "SELECT * FROM system_users_info WHERE id = ?";
        try {
            p = connection.prepareStatement(sql1);
            p.setInt(1,id);
            resultSet = p.executeQuery();
            if(resultSet.next()) {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(6, id);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, Address);
                preparedStatement.setString(3, Phone_number);
                preparedStatement.setString(4, Role);
                preparedStatement.setString(5, Email);
                preparedStatement.executeUpdate();
                return true;
            }
            else{
                return false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get query user data
    public void getUser(int Id){
        String sql_system_sel = "SELECT * FROM system_users_info WHERE id = ? ";
        try{
            preparedStatement = connection.prepareStatement(sql_system_sel);
            preparedStatement.setInt(1,Id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.Id = Id;
                this.FName = resultSet.getString("Name");
                this.Address = resultSet.getString("Address");
                this.Phone_number = resultSet.getString("Phone_Number");
                this.Role = resultSet.getString("Role");
                this.Email = resultSet.getString("Email_Address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getUser(String Name){
        String sql_system_sel = "SELECT * FROM system_users_info WHERE Name = ? ";
        try{
            preparedStatement = connection.prepareStatement(sql_system_sel);
            preparedStatement.setString(1,Name);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.Id = resultSet.getInt("id");
                this.FName = resultSet.getString("Name");
                this.Address = resultSet.getString("Address");
                this.Phone_number = resultSet.getString("Phone_Number");
                this.Role = resultSet.getString("Role");
                this.Email = resultSet.getString("Email_Address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //private void setID(int id){ this.Id = id; }
    public int getID(){return Id;}
    public String getName(){ return FName;}
    public String getAddress(){return Address;}
    public String getPhone_number(){return Phone_number;}
    public String getEmail(){return Email;}
    public String getRole(){return Role;}
    public String getUserName(int Id){
        String sql_system_sel = "SELECT * FROM system_users WHERE id = ? ";
        try{
            preparedStatement = connection.prepareStatement(sql_system_sel);
            preparedStatement.setInt(1,Id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = resultSet.getString("usern");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return "error";
        }
    }

    // Get Loged User Data
    public void getLUser(int Id){
        String sql_system_sel = "SELECT * FROM system_users_info WHERE id = ? ";
        try{
            preparedStatement = connection.prepareStatement(sql_system_sel);
            preparedStatement.setInt(1,Id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                this.LName = resultSet.getString("Name");
                this.LAddress = resultSet.getString("Address");
                this.LPhone_number = resultSet.getString("Phone_Number");
                this.LRole = resultSet.getString("Role");
                this.LEmail = resultSet.getString("Email_Address");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Set Data Static for Loged User
    public static int getLID(){return LId;}
    public static String getLName(){ return LName;}
    public static String getLAddress(){return LAddress;}
    public static String getLPhone_number(){return LPhone_number;}
    public static String getLEmail(){return LEmail;}
    public static String getLRole(){return LRole;}
    public static String getLUserName(){return Luser;}

}

