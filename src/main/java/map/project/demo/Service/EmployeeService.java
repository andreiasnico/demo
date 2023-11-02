//package map.project.demo.Service;
//
//import map.project.demo.Model.Employee;
//import map.project.demo.Repository.EmployeeRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//
//public class EmployeeService {
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    public Employee save(Employee employee){
//        employeeRepository.save(employee);
//        return employee;
//    }
//
//    public void delete(Employee employee){
//        employeeRepository.delete(employee);
//    }
//
//    public Iterable<Employee> findAllEmployees(){
//        return employeeRepository.findAll();
//    }
//
//}
