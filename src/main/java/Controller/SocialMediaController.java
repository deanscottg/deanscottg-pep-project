package Controller;

import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
     AccountService accountService;
     MessageService messageService;

        public SocialMediaController(){
            this.accountService = new AccountService();
            this.messageService = new MessageService();
     }
        
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::postAccountHandler);
        app.post("/login", this::postVerifyAccountHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages", this::getAllMessagesHandler);
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        // app.get("/accounts/{account_id}/messages", this::exampleHandler);
        // app.start(8080);


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void postAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.addAccount(account);
        if(addedAccount != null){
            context.status(200).json(mapper.writeValueAsString(addedAccount));
        }
        else{
            context.status(400);
        }
        
    }
    private void postVerifyAccountHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account addedAccount = accountService.verifyAccount(account);
        if(addedAccount != null){
            context.status(200).json(mapper.writeValueAsString(addedAccount));
        }
        else{
            context.status(401);
        }
        
    }

    private void postMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message persistedMessage = messageService.addMessage(message);
            if(persistedMessage!= null){
                context.status(200).json(mapper.writeValueAsString(persistedMessage));
            } else {
                context.status(400);
            }
    }

    private void getAllMessagesHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.status(200).json(messages);
    }

    private void getMessageByIdHandler(Context context){
        Message fetchedMessage = messageService.getMessageById(Integer.parseInt(context.pathParam("message_id")));
        if(fetchedMessage != null){
            context.status(200).json(fetchedMessage);
        }
        else {
            context.status(400);
        }
    }

    private void updateMessageHandler(Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        int message_id = Integer.parseInt(context.pathParam("message_id"));
        message.setMessage_id(message_id);
        Message updatedMessage = messageService.updateMessage(message);
        if(updatedMessage == null) {
            context.status(400);
        } else {
            context.status(200).json(mapper.writeValueAsString(updatedMessage));
        }
    }
    private void deleteMessageByIdHandler(Context context){
        
        int message_id = Integer.parseInt(context.pathParam("message_id"));
            if(messageService.deleteMessage(message_id) == null){
                context.status(200);
            }
            else {
                context.json(messageService.deleteMessage(message_id));
            }
    }
}