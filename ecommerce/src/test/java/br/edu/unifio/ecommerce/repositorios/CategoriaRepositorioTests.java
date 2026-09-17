package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;

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
    public void deveBuscarUmaCategoriaPorId () {
        Categoria categoria = categoriaRepositorio.findById (Short.parseShort("2")).orElseThrow();
        
        assertNotNull(categoria);
        assertEquals("Livros", categoria.getNome());
    }

     
    @Test
    public void deveListarTodasAsCategorias () {
        List<Categoria> categorias = categoriaRepositorio.findAll(Sort.by("nome"));

        assertEquals(5, categorias.size());
        assertEquals("Cadernos", categorias.get(0).getNome());
        assertEquals("Informática", categorias.get(1).getNome());
    }
}
