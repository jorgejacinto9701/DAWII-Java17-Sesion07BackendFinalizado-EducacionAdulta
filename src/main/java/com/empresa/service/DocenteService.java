package com.empresa.service;

import java.util.List;

import com.empresa.entity.Docente;

public interface DocenteService {

	//Para el Crud
	public abstract Docente insertaActualizaDocente(Docente docente);
	public abstract List<Docente> listaDocentePorNombreLike(String nombre);
	public abstract void eliminaDocente(int idDocente);

	// Validaciones CRUD Registrar
	public abstract List<Docente> listaPorNombreIgualRegistra(String nombre);
	public abstract List<Docente> listaPorDNIIgualRegistra(String dni);

	// Validaciones CRUD Actualizar
	public abstract List<Docente> listaPorNombreIgualActualiza(String nombre, int idDocente);
	public abstract List<Docente> listaPorDNIIgualActualiza(String dni, int idDocente);
	
	//Consulta
	public abstract List<Docente> listaConsulta(String nombre, String dni, int estado, int idUbigeo);
	
}
