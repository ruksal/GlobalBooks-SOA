package com.globalbooks.payments;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Component
public class PaymentsProducer {

    private final RabbitTemplate rabbit;
    private final String exchange;
    private final String routingKey;

    public PaymentsProducer(RabbitTemplate rabbit,
                            @Value("${payments.exchange:orders.events}") String exchange,
                            @Value("${payments.routingkey:order.created}") String routingKey) {
        this.rabbit = rabbit;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void publishOrderCreated(String orderJson) {
        try {
            rabbit.convertAndSend(exchange, routingKey, orderJson);
        } catch (Exception e) {
            System.err.println("Failed to publish order: " + e.getMessage());
            // Optional: store message for retry or logging
        }
    }
}

@Configuration
public class PaymentsConfig {

    @Bean
    public Queue paymentsQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dlx");
        args.put("x-dead-letter-routing-key", "payments.dlq");
        return new Queue("payments.authorize.q", true, false, false, args);
    }

    @Bean
    public Queue paymentsDLQ() {
        return new Queue("payments.dlq", true);
    }
}