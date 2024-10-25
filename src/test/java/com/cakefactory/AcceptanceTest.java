package com.cakefactory;

import com.cakefactory.auth.SecurityConfiguration;
import com.cakefactory.client.BrowserClient;
import com.cakefactory.payment.PaymentService;
import com.cakefactory.payment.PendingPayment;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@Import(SecurityConfiguration.class)
public class AcceptanceTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    PaymentService paymentService;

    protected BrowserClient client;

    @BeforeEach
    void setUp() {
        client = new BrowserClient(mockMvc);
        when(paymentService.create(any(), any())).thenReturn(new PendingPayment("1", URI.create("http://localhost")));
        when(paymentService.complete(anyString())).thenReturn("COMPLETED");
    }
}
