package com.empresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empresa.entity.Docente;

public interface DocenteRepository extends JpaRepository<Docente, Integer> {

	
	@Query("select x from Docente x where x.nombre like ?1")
	public abstract List<Docente> listaPorNombreLike(String nombre);
	
	//Validaciones CRUD Registrar
	@Query("select x from Docente x where x.nombre = ?1")
	public abstract List<Docente> listaPorNombreIgualRegistra(String nombre);
	
	@Query("select x from Docente x where x.dni = ?1")
	public abstract List<Docente> listaPorDNIIgualRegistra(String dni);
	
	//Validaciones CRUD Actualizar
	@Query("select x from Docente x where x.nombre = ?1 and x.idDocente != ?2")
	public abstract List<Docente> listaPorNombreIgualActualiza(String nombre, int idDocente);
	
	@Query("select x from Docente x where x.dni = ?1 and x.idDocente != ?2")
	public abstract List<Docente> listaPorDNIIgualActualiza(String dni, int idDocente);
	
	//CONSULTA
	@Query("select x from Docente x where "
							+ "(x.nombre like ?1) and "
							+ "(?2 = '' or x.dni = ?2) and "
							+ "(x.estado = ?3 ) and "
							+ "(?4 = -1 or x.ubigeo.idUbigeo = ?4)")
	public abstract List<Docente> listaConsulta(String nombre, String dni, int estado, int idUbigeo);
	
	
}


