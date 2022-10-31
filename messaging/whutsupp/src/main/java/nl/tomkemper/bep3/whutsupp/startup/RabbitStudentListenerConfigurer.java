package nl.tomkemper.bep3.whutsupp.startup;

import nl.tomkemper.bep3.whutsupp.Klas;
import nl.tomkemper.bep3.whutsupp.KlasRepository;
import nl.tomkemper.bep3.whutsupp.Student;
import nl.tomkemper.bep3.whutsupp.StudentListener;
import nl.tomkemper.bep3.whutsupp.forwarding.RemoteForwardingRepository;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MethodRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import static nl.tomkemper.bep3.whutsupp.Whutsupp.*;

@Configuration
public class RabbitStudentListenerConfigurer implements RabbitListenerConfigurer {
    private final RabbitAdmin admin;
    private final KlasRepository klassen;
    private final RemoteForwardingRepository forwarders;
    private final RabbitTemplate rabbitTemplate;

    public RabbitStudentListenerConfigurer(RabbitAdmin admin, RabbitTemplate rabbitTemplate, KlasRepository klassen, RemoteForwardingRepository forwarders) {
        this.admin = admin;
        this.klassen = klassen;
        this.forwarders = forwarders;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        System.out.println("Woop Woop Konijntjes!");
        System.out.println("Stekker zit erin");
        admin.declareExchange(new DirectExchange(PM_EXCHANGE));
        admin.declareExchange(new FanoutExchange(ANNOUNCE_EXCHANGE));
        admin.declareExchange(new DirectExchange(INCOMING_EXCHANGE));
        admin.declareExchange(new DirectExchange(OUTGOING_EXCHANGE));

        admin.declareQueue(new Queue(INCOMING_QUEUE));
        admin.declareBinding(new Binding(
                INCOMING_QUEUE,
                Binding.DestinationType.QUEUE,
                INCOMING_EXCHANGE, INCOMING_QUEUE, null));

        admin.declareQueue(new Queue(OUTGOING_QUEUE));
        admin.declareBinding(new Binding(
                OUTGOING_QUEUE,
                Binding.DestinationType.QUEUE,
                OUTGOING_EXCHANGE, OUTGOING_QUEUE, null));

        for (Klas k : this.klassen.findAll()) {
            for (Student s : k.students()) {
                String queueName = Student.getRoutingKey(s);
                admin.declareQueue(new Queue(queueName));
                admin.declareQueue(new Queue(queueName + ".local"));
                admin.declareBinding(new Binding(
                        queueName,
                        Binding.DestinationType.QUEUE,
                        PM_EXCHANGE, queueName, null));
                admin.declareBinding(new Binding(
                        queueName + ".local",
                        Binding.DestinationType.QUEUE,
                        PM_EXCHANGE, queueName, null));
                admin.declareBinding(new Binding(
                        queueName,
                        Binding.DestinationType.QUEUE,
                        ANNOUNCE_EXCHANGE, "fanout-" + queueName, null));

                MethodRabbitListenerEndpoint endpoint = new MethodRabbitListenerEndpoint();
                StudentListener listener = new StudentListener(s, this.forwarders, this.rabbitTemplate);
                endpoint.setId(queueName);
                endpoint.setQueueNames(queueName);
                endpoint.setBean(listener);
                endpoint.setMessageHandlerMethodFactory(new DefaultMessageHandlerMethodFactory()); //peak Java dit...
                try {
                    endpoint.setMethod(StudentListener.class.getMethod("processMessage", Message.class));
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                registrar.registerEndpoint(endpoint);
            }
        }
    }
}
