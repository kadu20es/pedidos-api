package br.com.alurafood.pedidos.controller;

import br.com.alurafood.pedidos.dto.PedidoDTO;
import br.com.alurafood.pedidos.dto.StatusDTO;
import br.com.alurafood.pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@OpenAPIDefinition(
        info = @Info(
                title = "Título da api",
                description = "Descrição da api",
                version = "${api.version}",
                contact = @Contact(name = "Kadu", url = "https://orgulhogeek.net", email = "kadu20es@gmail.com")
        ),
        servers = @Server(
                url = "host/api/issue/v1",
                description = "Descrição do server",
                variables = {
                        @ServerVariable(name = "serverUrl", defaultValue = "localhost"),
                        @ServerVariable(name = "serverHttpPort", defaultValue = "8081")
                }))
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(
            summary = "Endpoint issue",
            description = "Retorna todos os pedidos",
            tags = {
                    "pedidos"
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Operação de retorno realizada com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Erro ao obter listagem")
            }
    )
    @GetMapping()
    public List<PedidoDTO> listarTodos(){
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> listarPorId(@PathVariable @NotNull Long id){
        PedidoDTO dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity<PedidoDTO> realizarPedido(@RequestBody @Valid PedidoDTO dto, UriComponentsBuilder uriBuilder){
        PedidoDTO pedidoRealizado = service.criarPedido(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}")
                .buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizarStatus(@PathVariable Long id, @RequestBody StatusDTO status){
        PedidoDTO dto = service.atualizarStatus(id, status);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<PedidoDTO> aprovarPagamento(@PathVariable @NotNull Long id){
        service.aprovaPagamentoPedido(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta){
        return String.format("Requisição respondida pela instância executando na porta %s", porta);
    }

}
