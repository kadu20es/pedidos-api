package br.com.alurafood.pedidos.amqp;

import br.com.alurafood.pedidos.dto.PagamentoDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

@Log4j2
@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void receberMensagem(PagamentoDTO pagamento, Message message){
        //DateTimeFormatter datef1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        String mensagem = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor R$: %s
                Status: %s
                """.formatted(pagamento.getId(), pagamento.getPedidoId(), pagamento.getValor(), pagamento.getStatus());

        //log.info(LocalDateTime.now().format(datef1) +  mensagem);
        log.info(mensagem);
    }

}
