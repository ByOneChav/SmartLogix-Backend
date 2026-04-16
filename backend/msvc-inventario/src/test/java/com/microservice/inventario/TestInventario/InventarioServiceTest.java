package com.microservice.inventario.TestInventario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.microservice.inventario.model.Inventario;
import com.microservice.inventario.repository.InventarioRepository;
import com.microservice.inventario.service.InventarioService;
import com.microservice.inventario.client.PedidoClient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import java.util.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class InventarioServiceTest {

    @Mock
    private InventarioRepository repository;

    @Mock
    private PedidoClient pedidoClient;

    @InjectMocks
    private InventarioService service;

    private Inventario inventario;

    @BeforeEach
    void setup() {
        inventario = Inventario.builder()
                .id(1L)
                .nombreProducto("Laptop")
                .ubicacion("Bodega")
                .stock(10)
                .precio(500000)
                .build();
    }

    // 1. Listar
    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(inventario));

        List<Inventario> lista = service.findAll();

        assertEquals(1, lista.size());
        verify(repository).findAll();
    }

    // 2. Buscar por ID
    @Test
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(inventario));

        Inventario result = service.findById(1L);

        assertEquals("Laptop", result.getNombreProducto());
    }

    // 3. Guardar
    @Test
    void testSave() {
        when(repository.save(inventario)).thenReturn(inventario);

        Inventario result = service.save(inventario);

        assertEquals(1L, result.getId());
    }

    // 4. Actualizar
    @Test
    void testUpdate() {
        when(repository.findById(1L)).thenReturn(Optional.of(inventario));
        when(repository.save(any())).thenReturn(inventario);

        Inventario actualizado = service.update(1L, inventario);

        assertEquals("Laptop", actualizado.getNombreProducto());
    }

    // 5. Eliminar
    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(1L);

        service.delete(1L);

        verify(repository).deleteById(1L);
    }

    // 6. Error cuando no existe
    @Test
    void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.findById(1L));
    }

    // 7. Integración con pedido
    @Test
    void testFindPedidos() {
        when(repository.findById(1L)).thenReturn(Optional.of(inventario));
        when(pedidoClient.findAllProductoByInventario(1L)).thenReturn(new ArrayList<>());

        var response = service.findPedidosByInventarioId(1L);

        assertEquals("Laptop", response.getNombreProducto());
    }
}