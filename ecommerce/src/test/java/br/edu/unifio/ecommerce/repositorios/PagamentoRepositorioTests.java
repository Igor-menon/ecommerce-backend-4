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

import br.edu.unifio.ecommerce.entidades.Pagamento;

@SpringBootTest 
@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class PagamentoRepositorioTests {
    @Autowired 
    private PagamentoRepositorio pagamentoRepositorio;

    @Autowired 
    private PedidoRepositorio pedidoRepositorio;

    @Test 
    @Order (1)
    public void deveListarTodosOsPagamentos () {
        List<Pagamento> pagamentos = pagamentoRepositorio.findAll(Sort.by("tipo"));

        assertEquals(5, pagamentos.size());
        assertEquals("Boleto", pagamentos.get(0).getTipo());
        assertEquals("Cartão de Crédito", pagamentos.get(1).getTipo());
    }

    @Test 
    @Order (2)
    public void deveBuscarUmPagamentoPorId() {
        Pagamento pagamento = pagamentoRepositorio.findById(Integer.parseInt("3")).orElseThrow();

        assertNotNull(pagamento);
        assertEquals("Aprovado", pagamento.getStatus());
    }

    @Test 
    @Order (3)
    public void deveExcluirUmPagamentoPorId() {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("5600.00"));
        pagamento.setData(LocalDateTime.of(2026, 1, 26, 17, 0));
        pagamento.setStatus("Aprovado");
        pagamento.setTipo("Cartão");
        pagamentoRepositorio.save(pagamento);

        assertTrue(pagamentoRepositorio.existsById(pagamento.getId()));
        pagamentoRepositorio.deleteById(pagamento.getId());
        assertFalse(pagamentoRepositorio.existsById(pagamento.getId()));
    }

    @Test 
    @Order (4)
    public void deveSalvarUmPagamento() {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("782.00"));
        pagamento.setData(LocalDateTime.of(2026, 3, 2, 19, 0));
        pagamento.setStatus("Aprovado");
        pagamento.setTipo("Boleto");
        pagamentoRepositorio.save(pagamento);

        assertTrue(pagamentoRepositorio.existsById(pagamento.getId()));
        assertEquals("Aprovado", pagamentoRepositorio.findById(pagamento.getId()).orElseThrow().getStatus());
        pagamentoRepositorio.deleteById(pagamento.getId());
    }

    @Test 
    @Order (5)
    public void deveAlterarUmPagamento() {
        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("782.00"));
        pagamento.setData(LocalDateTime.of(2026, 3, 2, 19, 0));
        pagamento.setStatus("Aprovado");
        pagamento.setTipo("Boleto");
        pagamentoRepositorio.save(pagamento);

        pagamento.setValor(new BigDecimal("742.00"));
        pagamento.setData(LocalDateTime.of(2026, 4, 2, 19, 0));
        pagamento.setStatus("Aprovado");
        pagamento.setTipo("Cartão");
        pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoAlterado = pagamentoRepositorio.findById(pagamento.getId()).orElseThrow();

        assertEquals(new BigDecimal("742.00"), pagamentoAlterado.getValor());
        assertEquals(LocalDateTime.of(2026, 4, 2, 19, 0), pagamentoAlterado.getData());
        assertEquals("Aprovado", pagamentoAlterado.getStatus());
        assertEquals("Cartão", pagamentoAlterado.getTipo());
        pagamentoRepositorio.deleteById(pagamento.getId());
    }
}
