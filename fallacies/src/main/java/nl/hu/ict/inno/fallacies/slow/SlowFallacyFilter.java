package nl.hu.ict.inno.fallacies.slow;

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
@Profile("NetworkIsSlow")
public class SlowFallacyFilter implements Filter, ClientHttpRequestInterceptor {

    Logger logger = LoggerFactory.getLogger(SlowFallacyFilter.class);

    private final Random random;

    @Value("${slow.seed}")
    private int seed;

    @Value("${slow.chance}")
    private int percentageSlow;

    @Value("${slow.delayMs}")
    private int delayMs = 500;

    public SlowFallacyFilter() {
        this.random = new Random(this.seed);
    }

    public SlowFallacyFilter(int seed, int percentageFail) {
        this.seed = seed;
        this.random = new Random(this.seed);
        this.percentageSlow = percentageFail;
    }

    private void delay() {
        try {
            Thread.sleep(this.delayMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (this.random.nextInt(100) < this.percentageSlow) {
            logger.info("Server is slow");
            this.delay();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (this.random.nextInt(100) < this.percentageSlow) {
            logger.info("Client is slow");
            this.delay();
        }
        return execution.execute(request, body);
    }
}
