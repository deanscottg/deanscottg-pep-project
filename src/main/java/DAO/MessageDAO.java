package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Message WHERE id =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message (resultSet.getInt("posted_by"),
                resultSet.getString("message_text"),
                resultSet.getLong("time_posted_epoch"));
                return message;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    
        return null;
    }

    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message (resultSet.getInt("posted_by"),
                resultSet.getString("message_text"),
                resultSet.getLong("time_posted_epoch"));
                messages.add(message);
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }




    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO Message (posted_by, message_text, time_posted_epoch) VALUES(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1,message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            return message;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    public void updateMessage(int id, Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "UPDATE Message SET message_text = ? WHERE message_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,message.getMessage_text());
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
