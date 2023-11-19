package com.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.entity.Docente;
import com.empresa.repository.DocenteRepository;

@Service
public class DocenteServiceImpl implements DocenteService {

	@Autowired
	private DocenteRepository repository;


	@Override
	public Docente insertaActualizaDocente(Docente docente) {
		return repository.save(docente);
	}

  
	@Override
	public List<Docente> listaDocentePorNombreLike(String nombre) {
		return repository.listaPorNombreLike(nombre);
	}

	@Override
	public void eliminaDocente(int idDocente) {
		repository.deleteById(idDocente);
	}


	@Override
	public List<Docente> listaPorNombreIgualRegistra(String nombre) {
		return repository.listaPorNombreIgualRegistra(nombre);
	}


	@Override
	public List<Docente> listaPorDNIIgualRegistra(String dni) {
		return repository.listaPorDNIIgualRegistra(dni);
	}


	@Override
	public List<Docente> listaPorNombreIgualActualiza(String nombre, int idDocente) {
		return repository.listaPorNombreIgualActualiza(nombre, idDocente);
	}


	@Override
	public List<Docente> listaPorDNIIgualActualiza(String dni, int idDocente) {
		return repository.listaPorDNIIgualActualiza(dni, idDocente);
	}


	@Override
	public List<Docente> listaConsulta(String nombre, String dni, int estado, int idUbigeo) {
		return repository.listaConsulta(nombre, dni, estado, idUbigeo);
	}


}
