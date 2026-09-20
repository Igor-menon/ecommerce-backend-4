    package br.edu.unifio.ecommerce.repositorios;

    import static org.junit.jupiter.api.Assertions.assertEquals;
    import static org.junit.jupiter.api.Assertions.assertFalse;
    import static org.junit.jupiter.api.Assertions.assertNotNull;
    import static org.junit.jupiter.api.Assertions.assertTrue;

    import java.math.BigDecimal;
    import java.util.List;

    import org.junit.jupiter.api.MethodOrderer;
    import org.junit.jupiter.api.Order;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.TestMethodOrder;



    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.data.domain.Sort;

    import br.edu.unifio.ecommerce.entidades.ItemPedido;

    @SpringBootTest 
    @TestMethodOrder (MethodOrderer.OrderAnnotation.class)
    public class ItemPedidoRepositorioTests {

        @Autowired 
        private ItemPedidoRepositorio itemPedidoRepositorio;

        @Autowired 
        private PedidoRepositorio pedidoRepositorio;

        @Autowired 
        private ProdutoRepositorio produtoRepositorio;

        @Test 
        @Order (1)
        public void deveListarTodosOsItemPedidos() {
            List<ItemPedido> itemPedidos = itemPedidoRepositorio.findAll(Sort.by("id"));

            assertEquals(5, itemPedidos.size());
            assertEquals(new BigDecimal("87.94"), itemPedidos.get(0).getValorUnitario());
            assertEquals(new BigDecimal("150.00"), itemPedidos.get(1).getValorUnitario());
        }

        @Test 
        @Order (2)
        public void deveBuscarUmItemPedidoPorId() {
        ItemPedido itemPedido = itemPedidoRepositorio.findById(Integer.parseInt("3")).orElseThrow();

        assertNotNull(itemPedido);
        assertEquals(1, itemPedido.getQuantidade());
        assertEquals(new BigDecimal("35.50"), itemPedido.getValorUnitario());
        }

        @Test 
        @Order (4)
        public void deveSalvarUmItemPedido() {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(30);
            itemPedido.setValorUnitario(new BigDecimal("30.00"));
            itemPedido.setPedido(pedidoRepositorio.findById(4).orElseThrow());
            itemPedido.setProduto(produtoRepositorio.findById(3).orElseThrow());
            itemPedidoRepositorio.save(itemPedido);
            assertTrue(itemPedidoRepositorio.existsById(itemPedido.getId()));
            assertEquals(30, itemPedidoRepositorio.findById(itemPedido.getId()).orElseThrow().getQuantidade());
            itemPedidoRepositorio.deleteById(itemPedido.getId());
        }

        @Test 
        @Order (3) 
        public void deveExcluirUmItemPedidoPorId() {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(800);
            itemPedido.setValorUnitario(new BigDecimal("350.00"));
            itemPedido.setPedido(pedidoRepositorio.findById(4).orElseThrow());
            itemPedido.setProduto(produtoRepositorio.findById(3).orElseThrow());
            itemPedidoRepositorio.save(itemPedido);

            assertTrue(itemPedidoRepositorio.existsById(itemPedido.getId()));
            itemPedidoRepositorio.deleteById(itemPedido.getId());
            assertFalse(itemPedidoRepositorio.existsById(itemPedido.getId()));
        }

        @Test
        @Order (5)
        public void deveAlterarUmItemPedido() {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(30);
            itemPedido.setValorUnitario(new BigDecimal("30.00"));
            itemPedidoRepositorio.save(itemPedido);

            itemPedido.setQuantidade(65);
            itemPedido.setValorUnitario(new BigDecimal("38.00"));
            itemPedidoRepositorio.save(itemPedido);

            ItemPedido itemPedidoAlterado = itemPedidoRepositorio.findById(itemPedido.getId()).orElseThrow();

            assertEquals(65, itemPedidoAlterado.getQuantidade());
            assertEquals(new BigDecimal("38.00"), itemPedidoAlterado.getValorUnitario());
            itemPedidoRepositorio.deleteById(itemPedido.getId());
        }
        
    }
