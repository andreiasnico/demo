package map.project.demo.Model.States;

import map.project.demo.Model.Employee;

/**
 * interface for the state design pattern
 */
public interface StateInterface {

     boolean handle(Employee employee);

}
