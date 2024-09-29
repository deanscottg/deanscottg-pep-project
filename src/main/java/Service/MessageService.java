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
        if(messageDAO.getMessageById(message.getMessage_id()) != null){
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
        return fetchedMessage;
    }

    public Message updateMessage(int message_id, Message message){
        if(messageDAO.getMessageById(message_id) == null){
            return null;
        } 
        messageDAO.updateMessage(message_id,message);
        return messageDAO.getMessageById(message_id);
    }
}
