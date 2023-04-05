package br.com.alurafood.pedidos.amqp;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoAMQPConfiguration {

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter);
        return template;
    }

    /**
     * Cria a fila "pagamentos.detalhes-pedido"
     */
    @Bean
    public Queue filaDetalhesPedidos(){
        return QueueBuilder
                .nonDurable("pagamentos.detalhes-pedido")
                .build();
    }

    /**
     * Cria a exchange do tipo Fanout com o nome "pagamentos.ex"
     */
    @Bean
    public FanoutExchange exchange(){
        return ExchangeBuilder
                .fanoutExchange("pagamentos.ex")
                .build();
    }

    /**
     *
     * Liga a fila "pagamentos.detalhes-pedido" à exchange "pagamentos.ex"
     */
    @Bean
    public Binding bindPagamentoPedido(FanoutExchange exchange){
        return BindingBuilder
                .bind(filaDetalhesPedidos())
                .to(exchange);
    }

    /**
     *
     * Cria conexão com o RabbitAdmin para realizar a criação de fila e exchange e linká-las
     */
    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory connection){
        return new RabbitAdmin(connection);
    }

    /**
     *
     * Inicializa a escuta de eventos para o RabbitAdmin
     */
    @Bean
    public ApplicationListener<ApplicationEvent> inicializarAdmin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }


}