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
        else return messageDAO.insertMessage(message);
        
    }

    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
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
    public Message deleteMessage(int id){
        Message deletedMessage = messageDAO.getMessageById(id);
        if(deletedMessage != null) return deletedMessage;
        return null;
    }

    public List<Message> getAllMessagesByAccount(int account_id) {
        return messageDAO.getAllMessagesByAccount(account_id);
    }
}
