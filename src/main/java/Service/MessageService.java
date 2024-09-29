package Service;

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
}
