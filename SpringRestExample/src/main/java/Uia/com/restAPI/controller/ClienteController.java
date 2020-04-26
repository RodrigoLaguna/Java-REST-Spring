package Uia.com.restAPI.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import Uia.com.restAPI.model.Cliente;

/**
 * Handles requests for the Employee service.
 */
@Controller
public class ClienteController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	//Map to store employees, ideally we should use database
	Map<Integer, Cliente> empData = new HashMap<Integer, Cliente>();
	
	@RequestMapping(value = ClienteRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
	public @ResponseBody Cliente getDummyCliente() {
		logger.info("Start getDummyEmployee");
		Cliente emp = new Cliente();
		emp.setId(9999);
		emp.setName("Dummy");
		emp.setCreatedDate(new Date());
		empData.put(9999, emp);
		return emp;
	}
	
	@RequestMapping(value = ClienteRestURIConstants.GET_EMP, method = RequestMethod.GET)
	public @ResponseBody Cliente getCliente(@PathVariable("id") int empId) {
		logger.info("Start getEmployee. ID="+empId);
		
		return empData.get(empId);
	}
	
	@RequestMapping(value = ClienteRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
	public @ResponseBody List<Cliente> getAllCliente() {
		logger.info("Start getAllEmployees.");
		List<Cliente> emps = new ArrayList<Cliente>();
		Set<Integer> empIdKeys = empData.keySet();
		for(Integer i : empIdKeys){
			emps.add(empData.get(i));
		}
		return emps;
	}
	
	@RequestMapping(value = ClienteRestURIConstants.CREATE_EMP, method = RequestMethod.POST)
	public @ResponseBody Cliente createCliente(@RequestBody Cliente emp) {
		logger.info("Start createEmployee.");
		emp.setCreatedDate(new Date());
		empData.put(emp.getId(), emp);
		return emp;
	}
	
	@RequestMapping(value = ClienteRestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
	public @ResponseBody Cliente deleteCliente(@PathVariable("id") int empId) {
		logger.info("Start deleteEmployee.");
		Cliente emp = empData.get(empId);
		empData.remove(empId);
		return emp;
	}
	
}
