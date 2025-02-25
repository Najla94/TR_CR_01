import groovy.lang.GString;
import lombok.Getter;
import lombok.Setter;

@Setter    @Getter

public class Car{

    private String name;
    private int years;


    public Car(String name, int years){
        this.name = name;
                this.years= years;
    }

    @Override
    public String toString(){
        return "Car{name=" + name+ ", years="+ years + "}" ;
    }

}

