package map.project.demo.Model.Adapters;

import map.project.demo.Model.*;

import java.lang.reflect.Type;

/**
 * facade class in order to make changing every object
 * we will take into account the type of the typeClass of each member
 */
public  class AdapterFacade {
    /**
     * method where depending on the type of object we give off the correct adapter will handle it
     * @param concreteObject Object we want to transform
     * @param classType Class type
     * @return dto version of the object
     */
    public static Object adaptToDto(Object concreteObject , Type classType){
        if(classType == Bill.class){
            return new BillAdapter().transformToDto((Bill) concreteObject);
        } else {
            if (classType == Building.class){
                return new BuildingAdapter().transformToDto((Building) concreteObject);
            }
            else{
                if(classType == Counter.class){
                    return new CounterAdapter().transformToDto((Counter) concreteObject);
                }
                else{
                    if(classType == Employee.class){
                        return new EmployeeAdapter().transformToDto((Employee) concreteObject);
                    }
                    else{
                        if(classType == Renter.class){
                            return new RenterAdapter().transformToDto((Renter) concreteObject);
                        }
                        else{
                            return new PaymentAdapter().transformToDto((Payment) concreteObject);
                        }
                    }
                }
            }
        }
    }
}
