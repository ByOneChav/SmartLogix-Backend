package com.microservice.pedido.TestPedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.microservice.pedido.model.Pedido;
import com.microservice.pedido.repository.PedidoRepository;
import com.microservice.pedido.service.PedidoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Clase de pruebas unitarias para PedidoService
 * Usa Mockito para simular el repositorio (NO usa BD real)
 */
@ExtendWith(MockitoExtension.class)
public class PruebasTestPedido {

    /**
     * Simulación del repositorio (mock)
     * No accede a base de datos real
     */
    @Mock
    private PedidoRepository pedidoRepository;

    /**
     * Inyección del mock dentro del servicio
     * Mockito crea una instancia real del service pero con el repo simulado
     */
    @InjectMocks
    private PedidoService pedidoService;

    /**
     * TEST 1: Obtener todos los pedidos
     */
    @Test
    public void testFindAll() {

        // Simulamos lista de pedidos
        List<Pedido> lista = new ArrayList<>();
        Pedido p = new Pedido(1L, "Compra PC", 2, 500000, 10L);
        lista.add(p);

        // Definimos comportamiento del mock
        when(pedidoRepository.findAll()).thenReturn(lista);

        // Ejecutamos método del service
        List<Pedido> resultado = pedidoService.findAll();

        // Validaciones
        assertEquals(1, resultado.size());
        verify(pedidoRepository, times(1)).findAll();
    }

    /**
     * TEST 2: Buscar pedido por ID (existe)
     */
    @Test
    public void testFindById() {

        Pedido p = new Pedido(1L, "Compra PC", 2, 500000, 10L);

        // Simulación de respuesta del repositorio
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(p));

        Pedido resultado = pedidoService.findById(1L);

        // Validaciones
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(pedidoRepository).findById(1L);
    }

    /**
     * TEST 3: Buscar pedido por ID (NO existe)
     */
    @Test
    public void testFindByIdNotFound() {

        // Simulamos que no existe
        when(pedidoRepository.findById(99L)).thenReturn(Optional.empty());

        // Validamos que lanza excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pedidoService.findById(99L);
        });

        assertEquals("Pedido no encontrado", exception.getMessage());

        verify(pedidoRepository).findById(99L);
    }

    /**
     * TEST 4: Guardar pedido
     */
    @Test
    public void testSave() {

        Pedido p = new Pedido(1L, "Compra PC", 2, 500000, 10L);

        // Simulación de guardado
        when(pedidoRepository.save(p)).thenReturn(p);

        Pedido resultado = pedidoService.save(p);

        // Validaciones
        assertNotNull(resultado);
        assertEquals("Compra PC", resultado.getDescripcion());

        verify(pedidoRepository).save(p);
    }

    /**
     * TEST 5: Eliminar pedido
     */
    @Test
    public void testDelete() {

        Long id = 1L;

        // Simulación de eliminación (void)
        doNothing().when(pedidoRepository).deleteById(id);

        // Ejecutamos
        pedidoService.delete(id);

        // Verificamos que se llamó correctamente
        verify(pedidoRepository, times(1)).deleteById(id);
    }

    /**
     * TEST 6: Buscar pedidos por inventarioId
     */
    @Test
    public void testFindByInventarioId() {

        List<Pedido> lista = new ArrayList<>();
        Pedido p = new Pedido(1L, "Compra PC", 2, 500000, 10L);
        lista.add(p);

        // Simulación
        when(pedidoRepository.findAllByInventarioId(10L)).thenReturn(lista);

        List<Pedido> resultado = pedidoService.findByInventarioId(10L);

        // Validaciones
        assertEquals(1, resultado.size());
        verify(pedidoRepository).findAllByInventarioId(10L);
    }
}