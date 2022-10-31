package nl.tomkemper.bep3.whutsupp.forwarding;

import nl.tomkemper.bep3.whutsupp.ChatMessage;
import nl.tomkemper.bep3.whutsupp.Student;
import nl.tomkemper.bep3.whutsupp.Whutsupp;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.data.annotation.Id;
import org.springframework.web.client.RestTemplate;

public class RemoteForwarding {
    @Id
    private long studentNr;
    private RemoteWhutsuppHost remoteHost;

    public RemoteForwarding(long studentNr, RemoteWhutsuppHost remoteHost) {
        this.studentNr = studentNr;
        this.remoteHost = remoteHost;
    }

    public long getStudentNr() {
        return studentNr;
    }

    public RemoteWhutsuppHost getRemoteHost() {
        return remoteHost;
    }

    public void update(ForwardingDTO dto) {
        this.remoteHost.update(dto);
    }

    @Override
    public String toString() {
        return "RemoteForwarding{" +
                "studentNr=" + studentNr +
                ", remoteHost=" + remoteHost +
                '}';
    }

    public void forward(Student student, ChatMessage chatMessage) {

        if (this.remoteHost.getProtocol() == RemoteWhutsuppHost.Protocol.HTTP) {
            RestTemplate rt = new RestTemplate();
            String host = this.remoteHost.getHostname();
            int port = this.remoteHost.getPort();

            try {
                String url = String.format("http://%s:%s/chat/%s", host, port, student.getStudentNr());
                rt.postForLocation(url, chatMessage.butForReceiver(student.getStudentNr()));
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        } else if (this.remoteHost.getProtocol() == RemoteWhutsuppHost.Protocol.AMQP) {
            ConnectionFactory cf = this.remoteHost.createConnectionFactory();
            RabbitTemplate rt = new RabbitTemplate(cf);
            rt.setMessageConverter(new Jackson2JsonMessageConverter());
            rt.convertAndSend(Whutsupp.INCOMING_QUEUE, chatMessage.butForReceiver(student.getStudentNr()));
        }

    }
}
