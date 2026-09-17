package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import br.edu.unifio.ecommerce.entidades.Produto;
import br.edu.unifio.ecommerce.repositorios.CategoriaRepositorio;




@SpringBootTest
@TestMethodOrder (MethodOrderer.OrderAnnotation.class)
public class ProdutoRepositorioTests {
    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio; 

    @Test 
    @Order (2)
    public void deveBuscarUmProdutoPorId () {
        Produto produto = produtoRepositorio.findById (Integer.parseInt("2")).orElseThrow();
        
        assertNotNull(produto);
        assertEquals("Vestido", produto.getNome());
    }

    @Test
    @Order (1)
    public void deveListarTodosOsProdutos () {
        List<Produto> produtos = produtoRepositorio.findAll(Sort.by("nome"));
        
        assertEquals(5, produtos.size());
        assertEquals("Banco Imobiliario", produtos.get(0).getNome());
        assertEquals("Código Limpo", produtos.get(1).getNome());
    }

    @Test 
    @Order (3)
    public void deveExcluirUmProdutoPorId() {
        Produto produto = new Produto();
        produto.setNome("Iphonen 189");
        produto.setDescricao("189TB");
        produto.setEstoque(Short.parseShort("788"));
        produto.setPreco(new BigDecimal("17899.00"));
        produto.setCategoria(categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow());
        System.out.println("Produto ANtes: " + produto.getId());
        produtoRepositorio.save(produto);
        System.out.println("Produto Depois: " + produto.getId());


        assertTrue(produtoRepositorio.existsById(produto.getId()));
        produtoRepositorio.deleteById(produto.getId());
        assertFalse(produtoRepositorio.existsById(produto.getId()));

    }

    @Test 
    @Order (4)
    public void deveSalvarUmProduto() {
        Produto produto = new Produto();
        produto.setNome("Iphone 14");
        produto.setDescricao("128GB");
        produto.setEstoque(Short.parseShort("78"));
        produto.setPreco(new BigDecimal("17899.00"));
        produto.setCategoria(categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow());
        produtoRepositorio.save(produto);

        assertTrue(produtoRepositorio.existsById(produto.getId()));
        assertEquals("Iphone 14", produtoRepositorio.findById(produto.getId()).orElseThrow().getNome());
    }

    @Test
    @Order (5)
    public void deveAlterarUmProduto(){
        Produto produto = new Produto();
        produto.setNome("Iphone 14 Pro Max");
        produto.setDescricao("1TB");
        produto.setEstoque(Short.parseShort("50"));
        produto.setPreco(new BigDecimal("20000.00"));
        produto.setCategoria(categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow());
        produtoRepositorio.save(produto);

        
    }
}

