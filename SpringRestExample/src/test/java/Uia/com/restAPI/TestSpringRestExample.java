package Uia.com.restAPI;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import Uia.com.restAPI.controller.ClienteRestURIConstants;
import Uia.com.restAPI.model.Cliente;

public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:8080/SpringRestExample";
	
	public static void main(String args[]){
		
		testGetDummyCliente();
		System.out.println("*****");
		testCreateCliente();
		System.out.println("*****");
		testGetCliente();
		System.out.println("*****");
		testGetAllCliente();
	}

	private static void testGetAllCliente() {
		RestTemplate restTemplate = new RestTemplate();
		//we can't get List<Employee> because JSON convertor doesn't know the type of
		//object in the list and hence convert it to default JSON object type LinkedHashMap
		List<LinkedHashMap> emps = restTemplate.getForObject(SERVER_URI+ClienteRestURIConstants.GET_ALL_EMP, List.class);
		System.out.println(emps.size());
		for(LinkedHashMap map : emps){
			System.out.println("ID="+map.get("id")+",Name="+map.get("name")+",CreatedDate="+map.get("createdDate"));;
		}
	}

	private static void testCreateCliente() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente emp = new Cliente();
		emp.setId(1);emp.setName("Pankaj Kumar");
		Cliente response = restTemplate.postForObject(SERVER_URI+ClienteRestURIConstants.CREATE_EMP, emp, Cliente.class);
		printEmpData(response);
	}

	private static void testGetCliente() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente emp = restTemplate.getForObject(SERVER_URI+"/rest/emp/1", Cliente.class);
		printEmpData(emp);
	}

	private static void testGetDummyCliente() {
		RestTemplate restTemplate = new RestTemplate();
		Cliente emp = restTemplate.getForObject(SERVER_URI+ClienteRestURIConstants.DUMMY_EMP, Cliente.class);
		printEmpData(emp);
	}
	
	public static void printEmpData(Cliente emp){
		System.out.println("ID="+emp.getId()+",Name="+emp.getName()+",CreatedDate="+emp.getCreatedDate());
	}
}
