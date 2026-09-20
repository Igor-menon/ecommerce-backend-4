package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest 

public class CategoriaRepositorioTests {
    @Autowired 
    private CategoriaRepositorio categoriaRepositorio;

    @Test 
    @Order (2)
    public void deveBuscarUmaCategoriaPorId () {
        Categoria categoria = categoriaRepositorio.findById (Short.parseShort("2")).orElseThrow();
        
        assertNotNull(categoria);
        assertEquals("Livros", categoria.getNome());
    }

     
    @Test
    @Order (1)
    public void deveListarTodasAsCategorias () {
        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("nome"));

        assertEquals(5, categorias.size());
        assertEquals("Cadernos", categorias.get(0).getNome());
        assertEquals("Informática", categorias.get(1).getNome());
    }

    @Test 
    @Order (3)
    public void deveSalvarUmaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Lapis");
        categoria.setDescricao("Lapis de cor");
        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));
        assertEquals("Lapis", categoriaRepositorio.findById(categoria.getId()).orElseThrow().getNome());
        categoriaRepositorio.deleteById(categoria.getId());
    }

    @Test 
    @Order (4)
    public void deveExcluirUmaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Camiseta");
        categoria.setDescricao("Camiseta de verão");
        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));
        categoriaRepositorio.deleteById(categoria.getId());
        assertFalse(categoriaRepositorio.existsById(categoria.getId()));    
    }

    @Test 
    @Order (5)
    public void deveAlterarUmaCategoria() {
        Categoria categoria = new Categoria();
        categoria.setNome("Calça");
        categoria.setDescricao("Calça moleton");
        categoriaRepositorio.save(categoria);

        categoria.setNome("Tintas");
        categoria.setDescricao("Tintas guache");

        categoriaRepositorio.save(categoria);

        Categoria categoriaAlterada = categoriaRepositorio.findById(categoria.getId()).orElseThrow();

        assertEquals("Tintas", categoriaAlterada.getNome());
        assertEquals("Tintas guache", categoriaAlterada.getDescricao());
        categoriaRepositorio.deleteById(categoria.getId());

    }
}
