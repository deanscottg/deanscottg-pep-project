package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {
    public Message getMessageById(int id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM Message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message (resultSet.getInt("message_id"),resultSet.getInt("posted_by"),
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
                Message message = new Message (resultSet.getInt("message_id"),resultSet.getInt("posted_by"),
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
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1,message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                if(pkeyResultSet.next()){
                    int generated_message_id = pkeyResultSet.getInt("message_id");
                   return new Message(generated_message_id, message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());

                }
            return message;
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    // public void updateMessage(int id, Message message){
    //     Connection connection = ConnectionUtil.getConnection();
    //     try {
    //         String sql = "UPDATE Message SET message_text = ? WHERE message_id = ?;";
    //         PreparedStatement preparedStatement = connection.prepareStatement(sql);
    //         preparedStatement.setString(1,message.getMessage_text());
    //         preparedStatement.executeUpdate();
    //     } catch(SQLException e){
    //         System.out.println(e.getMessage());
    //     }

    // }

    public Message updateMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            if(message.message_text != null
            && message.message_text.length() <= 255
            && message.message_text.length() > 0 )
            { 
                String sql = "UPDATE Message SET message_text = ? WHERE message.message_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,message.message_text);
                preparedStatement.setInt(2,message.message_id);
                preparedStatement.executeUpdate();
                return getMessageById(message.message_id);

                }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Message deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM Message WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message deletedMessage = new Message(resultSet.getInt("message_id"),resultSet.getInt("posted_by"),
                resultSet.getString("message_text"),
                resultSet.getLong("time_posted_epoch"));
                System.out.println("delete " + deletedMessage);
                return deletedMessage;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;

    }

    public List<Message> getAllMessagesByAccount(int account_id) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try{
            String sql = "SELECT * FROM Message WHERE posted_by = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Message message = new Message (resultSet.getInt("message_id"),resultSet.getInt("posted_by"),
                resultSet.getString("message_text"),
                resultSet.getLong("time_posted_epoch"));
                messages.add(message);
                
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;

    }
}
