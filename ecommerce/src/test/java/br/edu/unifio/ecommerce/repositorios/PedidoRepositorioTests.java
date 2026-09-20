package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
 

import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest 
@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class PedidoRepositorioTests {

    @Autowired 
    private PedidoRepositorio pedidoRepositorio;

    @Autowired 
    private ClienteRepositorio clienteRepositorio;

    @Test 
    @Order (1)
    public void deveListarTodasOsPedidos() {
        List<Pedido> pedidos = pedidoRepositorio.findAll(Sort.by("id"));
    
        assertEquals(5, pedidos.size());
        assertEquals("Entregue", pedidos.get(0).getStatus());
        assertEquals("Pendente", pedidos.get(1).getStatus());
    }

    @Test 
    @Order (2)
    public void deveBuscarUmPedidoPorId() {
        Pedido pedido = pedidoRepositorio.findById(Integer.parseInt("1")).orElseThrow();

        assertNotNull(pedido);
        assertEquals("Entregue", pedido.getStatus());
        assertEquals(new BigDecimal("750.00"), pedido.getValorTotal());
    }

    @Test
    @Order (3)
    public void deveExcluirUmPedidoPorId() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 22, 10, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("890.00"));
        pedido.setCliente(clienteRepositorio.findById(1).orElseThrow());

        pedidoRepositorio.save(pedido);

        assertTrue(pedidoRepositorio.existsById(pedido.getId()));
        pedidoRepositorio.deleteById(pedido.getId());
        assertFalse(pedidoRepositorio.existsById(pedido.getId()));
    }

    @Test 
    @Order (4)
    public void deveSalvarUmPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 14, 18, 0));
        pedido.setStatus("Cancelada");
        pedido.setValorTotal(new BigDecimal("1850.00"));
        pedido.setCliente(clienteRepositorio.findById(3).orElseThrow());

        pedidoRepositorio.save(pedido);

        assertTrue(pedidoRepositorio.existsById(pedido.getId()));
        assertEquals("Cancelada", pedidoRepositorio.findById(pedido.getId()).orElseThrow().getStatus());
        pedidoRepositorio.deleteById(pedido.getId());
    }

    @Test 
    @Order (5)
    public void deveAlterarUmPedido() {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 14, 18, 0));
        pedido.setStatus("Cancelada");
        pedido.setValorTotal(new BigDecimal("1850.00"));
        pedido.setCliente(clienteRepositorio.findById(3).orElseThrow());

        pedidoRepositorio.save(pedido);

        pedido.setData(LocalDateTime.of(2026, 5, 19, 23, 0));
        pedido.setStatus("Pendente");
        pedido.setValorTotal(new BigDecimal("1250.00"));

        pedidoRepositorio.save(pedido);

        Pedido pedidoAlterado = pedidoRepositorio.findById(pedido.getId()).orElseThrow();

        assertEquals(LocalDateTime.of(2026, 5, 19, 23, 0), pedidoAlterado.getData());
        assertEquals("Pendente", pedidoAlterado.getStatus());
        assertEquals(new BigDecimal("1250.00"), pedidoAlterado.getValorTotal());
        pedidoRepositorio.deleteById(pedido.getId());
    }
}
