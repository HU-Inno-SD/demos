package nl.hu.ict.inno.fallacies.reliable;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@Component
@Profile("NetworkIsReliable")
public class ReliableFallacyFilter implements Filter, ClientHttpRequestInterceptor {

    private enum Failure {JustSucceed, Before, After}

    Logger logger = LoggerFactory.getLogger(ReliableFallacyFilter.class);


    private final Random random;

    @Value("${reliable.seed}")
    private int seed;

    @Value("${reliable.chance}")
    private int percentageFail;


    public ReliableFallacyFilter() {
        this.random = new Random(this.seed);
    }

    public ReliableFallacyFilter(int seed, int percentageFail) {
        this.seed = seed;
        this.random = new Random(this.seed);
        this.percentageFail = percentageFail;
    }

    private Failure decideFailure() {
        boolean shouldFail = this.random.nextInt(100) < this.percentageFail;
        boolean failBefore = this.random.nextInt(100) < 50;
        boolean failAfter = !failBefore;

        if (!shouldFail) {
            return Failure.JustSucceed;
        } else {
            if (failBefore) {
                return Failure.Before;
            } else {
                return Failure.After;
            }
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Failure f = decideFailure();

        if (f == Failure.Before) {
            logger.info("Server: The network is not reliable (before)");
            throw new RuntimeException("The network is not reliable");
        }
        filterChain.doFilter(servletRequest, servletResponse);
        if (f == Failure.After) {
            logger.info("Server: The network is not reliable (after)");
            throw new RuntimeException("The network is not reliable");
        }

    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        Failure f = decideFailure();

        if (f == Failure.Before) {
            logger.info("Client: The network is not reliable (before)");
            throw new RuntimeException("The network is not reliable");
        }
        ClientHttpResponse resp = execution.execute(request, body);
        if (f == Failure.After) {
            logger.info("Client: The network is not reliable (after)");
            throw new RuntimeException("The network is not reliable");
        }
        return resp;
    }
}
