package nl.tomkemper.hellogrpc.server;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import nl.tomkemper.hellogrpc.gen.MyServiceGrpc;
import nl.tomkemper.hellogrpc.gen.HelloReply;
import nl.tomkemper.hellogrpc.gen.HelloRequest;

@GrpcService
public class HelloService extends MyServiceGrpc.MyServiceImplBase {

    @Override
    public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello " + request.getName())
                .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
