package map.project.demo.Model.Adapters;

/**
 * we are going to build an adapter design pattern in order to make specific transformations:
 * Concrete object to dto
 * dto to concrete object
 *
 * S - dto
 * T - concrete object
 */
public interface Adapter<T,S> {

    S transformToDto(T concreteObject);

}
