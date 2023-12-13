import java.util.ArrayList;
import java.util.List;

public class Director {
    public void buildPapillon(Builder builder){
        builder.type("Papillon")
                .age(16)
                .addtoy("stick");
    }
    public void buildPoodle(Builder Builder){
        Builder.type("Poodle")
                .age(10)
                .addtoy("ball");
    }
}
