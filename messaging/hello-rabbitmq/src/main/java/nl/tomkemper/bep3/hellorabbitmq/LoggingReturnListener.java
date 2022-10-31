package nl.tomkemper.bep3.hellorabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoggingReturnListener implements ReturnListener {

    private Logger logger = LoggerFactory.getLogger(LoggingReturnListener.class);

    @Override
    public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
        logger.info(String.format("Returning %s, %s, %s, %s", replyCode, replyText, exchange, routingKey));
    }
}
