package com.empresa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Docente;
import com.empresa.service.DocenteService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.apachecommons.CommonsLog;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@RestController
@RequestMapping("/url/consultaDocente")
@CrossOrigin(origins = "http://localhost:4200")
@CommonsLog
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
	
	@PostMapping("/reporteDocentePdf")
	public void exportDailyOrders(
			@RequestParam(name = "nombre" , required = false , defaultValue = "") String nombre, 
			@RequestParam(name = "dni" , required = false , defaultValue = "") String dni, 
			@RequestParam(name = "estado" , required = false , defaultValue = "1") int estado, 
			@RequestParam(name = "idUbigeo" , required = false , defaultValue = "-1") int idUbigeo,
			HttpServletResponse response,
			HttpServletRequest request) throws IOException, JRException {
	   
		//PASO 1: Obtener el dataSource que va generar el reporteHo
		List<Docente> lstSalida = docenteService.listaConsulta("%"+nombre+"%", dni, estado, idUbigeo);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstSalida);
		
		//PASO 2: Obtener el archivo que contiene el diseño del reporte
		String fileDirectory = request.getServletContext().getRealPath("/WEB-INF/reportes/reporteDocente.jasper");
		log.info(">>> " + fileDirectory);
		FileInputStream stream   = new FileInputStream(new File(fileDirectory));
		
		//PASO 3: Parámetros adicionales
		String ImgDirectory = request.getServletContext().getRealPath("/WEB-INF/img/logo.jpg");
		log.info(">> LOGO >> " + ImgDirectory);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("RUTA_LOGO",  ImgDirectory);
		
		//PASO 4: Enviamos dataSource, diseño y parámetros para generar el PDF
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(stream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
		
		//PASO 5: Enviar el PDF generado
		response.setContentType("application/x-pdf");
	    response.addHeader("Content-disposition", "attachment; filename=ReporteAutor.pdf");

		OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
	}
	
}
