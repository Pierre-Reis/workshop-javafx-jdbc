package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {

	public List<Department> findAll(){
		
		List<Department> departmentList = new ArrayList<>();
		
		departmentList.add(new Department(1, "Books"));
		departmentList.add(new Department(2, "Computers"));
		departmentList.add(new Department(1, "Eletronics"));
		
		return departmentList;
	}
}
