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

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (this.random.nextInt(100) < this.percentageFail) {
            logger.info("Server: The network is not reliable");
            throw new RuntimeException("The network is not reliable");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (this.random.nextInt(100) < this.percentageFail) {
            logger.info("Client: The network is not reliable");
            throw new RuntimeException("The network is not reliable");
        } else {
            return execution.execute(request, body);
        }
    }
}
