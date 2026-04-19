package com.microservice.envio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.envio.model.Envio;
import com.microservice.envio.service.EnvioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador REST de envios
 */
@RestController
@RequestMapping("/api/envio")
@Tag(name = "Envio", description = "Operaciones relacionadas a envios")
public class EnvioController {

	private final EnvioService envioService;

	public EnvioController(EnvioService envioService) {
		this.envioService = envioService;
	}

	/**
	 * LISTAR TODOS
	 */
	@GetMapping("/all")
	@Operation(summary = "Listar todos los envios")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista obtenida correctamente", content = @Content(schema = @Schema(implementation = Envio.class))),
			@ApiResponse(responseCode = "404", description = "No hay envios")
	})
	public ResponseEntity<?> findAll() {
		List<Envio> envios = envioService.findAll();

		if (envios.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay envios");
		}
		return ResponseEntity.ok(envios);
	}

	/**
	 * BUSCAR POR ID
	 */
	@GetMapping("/search/{id}")
	@Operation(summary = "Buscar envio por ID")
	public ResponseEntity<?> findById(
			@Parameter(description = "ID del envio", in = ParameterIn.PATH)
			@PathVariable Long id) {
		try {
			return ResponseEntity.ok(envioService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envio no encontrado");
		}
	}

	/**
	 * CREAR ENVIO
	 */
	@PostMapping("/create")
	@Operation(summary = "Crear un nuevo envio")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Envio creado"),
			@ApiResponse(responseCode = "400", description = "Error en datos")
	})
	public ResponseEntity<?> saveEnvio(@RequestBody Envio envio) {
		try {
			Envio nuevo = envioService.save(envio);
			return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear envio");
		}
	}

	/**
	 * ACTUALIZAR ENVIO
	 */
	@PutMapping("/update/{id}")
	@Operation(summary = "Actualizar envio")
	public ResponseEntity<?> updateEnvio(@PathVariable Long id, @RequestBody Envio envio) {
		try {
			return ResponseEntity.ok(envioService.update(id, envio));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Envio no encontrado");
		}
	}

	/**
	 * ELIMINAR ENVIO
	 */
	@DeleteMapping("/delete/{id}")
	@Operation(summary = "Eliminar envio")
	public ResponseEntity<?> deleteEnvio(@PathVariable Long id) {
		try {
			envioService.delete(id);
			return ResponseEntity.ok("Envio eliminado correctamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se pudo eliminar");
		}
	}

	/**
	 * BUSCAR POR INVENTARIO
	 */
	@GetMapping("/search-by-inventario/{inventarioId}")
	@Operation(summary = "Buscar envios por inventarioId")
	public ResponseEntity<?> findByInventario(@PathVariable Long inventarioId) {
		return ResponseEntity.ok(envioService.findByInventarioId(inventarioId));
	}

}
