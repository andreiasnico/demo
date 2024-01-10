package map.project.demo.Model.dto.Builder;

/**
 * interface for the builder design pattern in our project the builder
 * has the purpose to create parts of the dto objects we want
 * for example for the bill the builder will go and change sequential and create:
 * BillInformationDto , BillUnitInformationDto , BillRenterInformationDto
 *
 * @param <T> Hard type class (ex Bill)
 * @param <S> Dto we want to change into (ex BillInformationDto)
 */
public interface Builder<T, S> {
    /**
     * method where we will do the changes based on the layer of dto we are at
     *
     * @param object hard type object from our database
     * @return dto type we want to build into
     */
    S buildObject(T object);
}
