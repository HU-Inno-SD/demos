package nl.tomkemper.bep3.whutsupp.forwarding;

public class ForwardingDTO {
    public long studentNr;
    public String hostname;
    public int port;
    public String protocol;

    public ForwardingDTO() {
    }

    public ForwardingDTO(RemoteForwarding remoteForwarding) {
        this.studentNr = remoteForwarding.getStudentNr();
        this.hostname = remoteForwarding.getRemoteHost().getHostname();
        this.port = remoteForwarding.getRemoteHost().getPort();
        this.protocol = remoteForwarding.getRemoteHost().getProtocol().toString();
    }

    public RemoteForwarding toNewForwarding() {
        return new RemoteForwarding(this.studentNr, new RemoteWhutsuppHost(this.hostname, this.port, RemoteWhutsuppHost.parseProtocol(this.protocol)));
    }
}
