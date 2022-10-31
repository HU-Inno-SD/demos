package nl.tomkemper.bep3.whutsupp.forwarding;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.PooledChannelConnectionFactory;

public class RemoteWhutsuppHost {


    public enum Protocol {
        AMQP, HTTP
    }

    private String hostname;
    private int port;
    private Protocol protocol;

    protected RemoteWhutsuppHost(){}

    public RemoteWhutsuppHost(String hostname, int port, Protocol protocol) {
        this.hostname = hostname;
        this.port = port;
        this.protocol = protocol;
    }

    public RemoteWhutsuppHost(String hostname, int port) {
        this(hostname, port, Protocol.HTTP);
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public static Protocol parseProtocol(String rawValue){
        Protocol result;
        if (rawValue.equalsIgnoreCase("HTTP")) {
            result = Protocol.HTTP;
        } else if (rawValue.equalsIgnoreCase("AMQP") || rawValue.equalsIgnoreCase("AQMP")) {
            result = Protocol.AMQP;
        }else{
            throw new IllegalArgumentException(rawValue);
        }
        return result;
    }

    public void update(ForwardingDTO dto) {
        this.hostname = dto.hostname;
        this.port = dto.port;
        this.protocol = parseProtocol(dto.protocol);
    }

    public org.springframework.amqp.rabbit.connection.ConnectionFactory createConnectionFactory() {
        com.rabbitmq.client.ConnectionFactory cf = new com.rabbitmq.client.ConnectionFactory();
        cf.setHost(this.hostname);
        cf.setPort(this.port);
        return new CachingConnectionFactory(cf);
    }

    @Override
    public String toString() {
        return "RemoteWhutsuppHost{" +
                "hostname='" + hostname + '\'' +
                ", port=" + port +
                ", protocol=" + protocol +
                '}';
    }
}
