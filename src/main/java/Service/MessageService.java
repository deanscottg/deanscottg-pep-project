package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO){
        this.messageDAO = messageDAO;
    }

    public Message addMessage(Message message){
        if(messageDAO.getMessageById(message.getMessage_id()) != null || message.message_text == ""){
            return null;
        }
        else {
            Message persistedMessage = messageDAO.insertMessage(message);
            return persistedMessage;
        }
        
    }

    public List<Message> getAllMessages(){
        List newMessageList = messageDAO.getAllMessages();
        return newMessageList;
    }

    public Message getMessageById(int id){
        Message fetchedMessage = messageDAO.getMessageById(id);
        if(fetchedMessage != null){
            return fetchedMessage;
        } 
        return null;
        
    }

    public Message updateMessage(Message message){
        Message updatedMessage = messageDAO.updateMessage(message);
        if(updatedMessage != null) return updatedMessage; 
        return null;  
     
    }
    public Message deleteMessage(int message_id){
        Message deletedMessage = messageDAO.getMessageById(message_id);
        if(deletedMessage == null){
            return null;
        }
        else {
            return messageDAO.deleteMessageById(message_id);
        }
    }
}
