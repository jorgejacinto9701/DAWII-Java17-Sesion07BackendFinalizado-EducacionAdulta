package com.empresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Docente;
import com.empresa.service.DocenteService;

@RestController
@RequestMapping("/url/consultaDocente")
@CrossOrigin(origins = "http://localhost:4200")
public class DocenteConsultaController {

	@Autowired
	private DocenteService docenteService;
	
	@ResponseBody
	@GetMapping("/consultaDocentePorParametros")
	public List<Docente> listaConsultaDocente(
								@RequestParam(name = "nombre" , required = false , defaultValue = "") String nombre, 
								@RequestParam(name = "dni" , required = false , defaultValue = "") String dni, 
								@RequestParam(name = "estado" , required = false , defaultValue = "1") int estado, 
								@RequestParam(name = "idUbigeo" , required = false , defaultValue = "-1") int idUbigeo){
		
		List<Docente> lstSalida = docenteService.listaConsulta("%"+nombre+"%", dni, estado, idUbigeo);
		return lstSalida;
	}
	
	
}
