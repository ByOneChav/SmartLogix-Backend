package com.microservice.envio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.microservice.envio.model.Envio;
import com.microservice.envio.repository.EnvioRepository;

import jakarta.transaction.Transactional;

/**
 * Servicio que contiene la logica de negocio de envios
 */
@Service
@Transactional
public class EnvioService {

	private final EnvioRepository envioRepository;

	public EnvioService(EnvioRepository envioRepository) {
		this.envioRepository = envioRepository;
	}

	/**
	 * Obtener todos los envios
	 */
	public List<Envio> findAll() {
		return envioRepository.findAll();
	}

	/**
	 * Buscar envio por ID
	 */
	public Envio findById(Long id) {
		return envioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Envio no encontrado"));
	}

	/**
	 * Guardar envio
	 */
	public Envio save(Envio envio) {
		return envioRepository.save(envio);
	}

	/**
	 * Actualizar envio
	 */
	public Envio update(Long id, Envio envio) {
		Envio existente = findById(id);

		existente.setDescripcion(envio.getDescripcion());
		existente.setCantidad(envio.getCantidad());
		existente.setPrecio(envio.getPrecio());
		existente.setInventarioId(envio.getInventarioId());

		return envioRepository.save(existente);
	}

	/**
	 * Eliminar envio
	 */
	public void delete(Long id) {
		envioRepository.deleteById(id);
	}

	/**
	 * Buscar envios por inventarioId
	 */
	public List<Envio> findByInventarioId(Long inventarioId) {
		return envioRepository.findAllByInventarioId(inventarioId);
	}

}
