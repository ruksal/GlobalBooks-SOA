package com.globalbooks.shipping;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ShippingConsumer {

    @RabbitListener(queues = "shipping.create.q")
    public void handleShippingCreated(String message) {
        try {
            System.out.println("Received shipping create message: " + message);
            // In real service: call shipping provider API, update DB, emit shipping.created
        } catch (Exception e) {
            // Send to DLQ
            throw new org.springframework.amqp.AmqpRejectAndDontRequeueException(
                "Failed to process shipping message", e
            );
        }
    }
}

@Configuration
public class ShippingConfig {

    @Bean
    public Queue shippingQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", "dlx");
        args.put("x-dead-letter-routing-key", "shipping.dlq");
        return new Queue("shipping.create.q", true, false, false, args);
    }

    @Bean
    public Queue shippingDLQ() {
        return new Queue("shipping.dlq", true);
    }
}