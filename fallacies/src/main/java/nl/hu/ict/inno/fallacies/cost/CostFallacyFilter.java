package nl.hu.ict.inno.fallacies.cost;

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
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

@Component
@Profile("TransportCostZero")
public class CostFallacyFilter implements Filter, ClientHttpRequestInterceptor {

    Logger logger = LoggerFactory.getLogger(CostFallacyFilter.class);

    private int incomingRequests = 0;
    private int outgoingRequests = 0;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        incomingRequests++;
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        outgoingRequests++;
        return execution.execute(request, body);
    }

    public int getIncomingRequests() {
        return incomingRequests;
    }

    public int getOutgoingRequests() {
        return outgoingRequests;
    }
}
