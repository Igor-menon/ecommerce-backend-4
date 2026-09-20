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

import br.edu.unifio.ecommerce.entidades.Cliente;

@SpringBootTest 

public class ClienteRepositorioTests {
    @Autowired 
    private ClienteRepositorio clienteRepositorio;

    @Test 
    @Order (2)
    public void deveBuscarUmClientePorId () {
       Cliente cliente = clienteRepositorio.findById (Integer.parseInt("1")).orElseThrow();

        assertNotNull(cliente);
        assertEquals("Jorge da Silva", cliente.getNome());
    }

    @Test 
    @Order (1)
    public void deveListarTodosOsClientes() {
        List<Cliente> clientes = clienteRepositorio.findAll(Sort.by("nome"));

        assertEquals(5, clientes.size());
        assertEquals("Ana Costa", clientes.get(0).getNome());
        assertEquals("Carlos Santos", clientes.get(1).getNome());
    }

    @Test 
    @Order (3)
    public void deveExcluirUmClientePorId() {
        Cliente cliente = new Cliente();
        cliente.setNome("Amanda Lopes");
        cliente.setEmail("amanda.lopes@gmail.com");
        cliente.setTelefone("1498568947");
        clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(cliente.getId()));
        clienteRepositorio.deleteById(cliente.getId());
        assertFalse(clienteRepositorio.existsById(cliente.getId()));
    }

    @Test 
    @Order (4)
    public void deveSalvarUmCliente() {
        Cliente cliente = new Cliente();
        cliente.setNome("João Pereira");
        cliente.setEmail("joao.pereira@gmail.com");
        cliente.setTelefone("14958744189");
        clienteRepositorio.save(cliente);

        assertTrue(clienteRepositorio.existsById(cliente.getId()));
        assertEquals("João Pereira", clienteRepositorio.findById(cliente.getId()).orElseThrow().getNome());
        clienteRepositorio.deleteById(cliente.getId());
    }

    @Test 
    @Order (5)
    public void deveAlterarUmCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("João Pereira");
        cliente.setEmail("joao.pereira@gmail.com");
        cliente.setTelefone("14958744189");
        clienteRepositorio.save(cliente);

        cliente.setNome("João Pereira Gomes");
        cliente.setEmail("joao.gomes@gmail.com");
        cliente.setTelefone("14958744180");
        clienteRepositorio.save(cliente);

        Cliente clienteAlterado = clienteRepositorio.findById(cliente.getId()).orElseThrow();

        assertEquals("João Pereira Gomes", clienteAlterado.getNome());
        assertEquals("joao.gomes@gmail.com", clienteAlterado.getEmail());
        assertEquals("14958744180", clienteAlterado.getTelefone());
        clienteRepositorio.deleteById(cliente.getId());

    }
}
