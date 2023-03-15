package nl.tomkemper.helogrpc.client;

import net.devh.boot.grpc.client.inject.GrpcClient;
import nl.tomkemper.hellogrpc.gen.HelloRequest;
import nl.tomkemper.hellogrpc.gen.MyServiceGrpc;
import org.springframework.stereotype.Service;


@Service
public class HelloService {

    @GrpcClient("myService")
    private MyServiceGrpc.MyServiceBlockingStub myServiceStub;

    public String sayHello(String naam){
        HelloRequest request = HelloRequest.newBuilder()
                .setName(naam)
                .build();
        return myServiceStub.sayHello(request).getMessage();
    }
}
