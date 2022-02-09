package edu.ucacue.facturacion2.infraestructura.servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import edu.ucacue.facturacion2.infraestructura.repositorio.ClienteRepositorio;
import edu.ucacue.facturacion2.modelo.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ClienteReporteService {
	
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	
	
	public String generarReporte(String formato) throws FileNotFoundException, JRException
	{
		List<Cliente> clientes = clienteRepositorio.findAll();
		
		//Hay que mapear las personas al reporte
		File file= ResourceUtils.getFile("classpath:reportes\\clientes_reporte.jrxml");

		JasperReport jasperReport=JasperCompileManager.compileReport(file.getAbsolutePath());
		
		
		JRBeanCollectionDataSource dataSource= new JRBeanCollectionDataSource(clientes);
		
		Map<String,Object> parametros= new HashedMap();
		parametros.put("Creado por","Sebastian Quevedo");
		
		JasperPrint jasperPrint=JasperFillManager.fillReport(jasperReport,parametros, dataSource);
		if(formato.equalsIgnoreCase("html"))
		{
			JasperExportManager.exportReportToHtmlFile(jasperPrint,"c:\\clientesprueba.html");
		}
		if(formato.equalsIgnoreCase("pdf"))
		{
			JasperExportManager.exportReportToPdfFile(jasperPrint,"c:\\clientes.pdf");
		}
		return "Reporte generado correctamente";
	}

}
