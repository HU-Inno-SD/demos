package nl.tomkemper.bep3.hellorabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class LoggingConsumer implements Consumer {


    private Logger logger;

    public LoggingConsumer(){
        logger = LoggerFactory.getLogger(LoggingConsumer.class);
    }

    public LoggingConsumer(String name){
        logger = LoggerFactory.getLogger(name);
    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        logger.info(String.format("%s: %s", "handleConsumeOk", consumerTag));
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        logger.info(String.format("%s: %s", "handleCancelOk", consumerTag));
    }

    @Override
    public void handleCancel(String consumerTag) {
        logger.info(String.format("%s: %s", "handleCancel", consumerTag));
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        logger.info(String.format("%s: %s", "handleShutdownSignal", consumerTag));
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        logger.info(String.format("%s: %s", "handleRecoverOk", consumerTag));
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {

        logger.info(String.format("%s: %s", "handleDelivery", consumerTag));

        logger.info(StandardCharsets.UTF_8.decode(ByteBuffer.wrap(body)).toString());
    }
}
