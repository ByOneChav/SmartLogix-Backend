package com.microservice.envio.TestEnvio;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.microservice.envio.controller.EnvioController;
import com.microservice.envio.model.Envio;
import com.microservice.envio.service.EnvioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(EnvioController.class)
public class PruebaTestEnvio {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnvioService envioService;

    @Test
    void findAllReturnsEnvios() throws Exception {
        Envio envio = new Envio(1L, "Compra PC", 2, 500000, 10L);
        when(envioService.findAll()).thenReturn(List.of(envio));

        mockMvc.perform(get("/api/envio/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].descripcion").value("Compra PC"));

        verify(envioService).findAll();
    }

    @Test
    void findByIdReturnsEnvio() throws Exception {
        Envio envio = new Envio(1L, "Compra PC", 2, 500000, 10L);
        when(envioService.findById(1L)).thenReturn(envio);

        mockMvc.perform(get("/api/envio/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descripcion").value("Compra PC"));

        verify(envioService).findById(1L);
    }

    @Test
    void saveEnvioReturnsCreated() throws Exception {
        Envio guardado = new Envio(1L, "Compra PC", 2, 500000, 10L);
        when(envioService.save(any(Envio.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/envio/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "descripcion": "Compra PC",
                                  "cantidad": 2,
                                  "precio": 500000,
                                  "inventarioId": 10
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.descripcion").value("Compra PC"));

        verify(envioService).save(any(Envio.class));
    }

    @Test
    void deleteEnvioReturnsOk() throws Exception {
        mockMvc.perform(delete("/api/envio/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Envio eliminado correctamente"));

        verify(envioService).delete(1L);
    }
}

