package nl.hu.todss.socketsdemo;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {


    @MessageMapping("/new-message")
    @SendTo("/topic/chat")
    public Message chat(Message input){
        System.out.println("Hij dut 't!");
        Message output = new Message(input.getSender(), "Output: " + input.getContent());

        return output;
    }
}
