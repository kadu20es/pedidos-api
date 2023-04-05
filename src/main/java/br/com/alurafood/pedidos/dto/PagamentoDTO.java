package br.com.alurafood.pedidos.dto;

import br.com.alurafood.pedidos.model.StatusPagamento;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PagamentoDTO {
    private Long id;
    private BigDecimal valor;
    private String nome;
    private String numero;
    private String expiracao;
    private String codigo;
    private StatusPagamento status;
    private Long pedidoId;
    private Long formaDePagamentoId;
}
