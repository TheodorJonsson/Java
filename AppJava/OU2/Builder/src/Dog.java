import java.util.ArrayList;
import java.util.List;

public class Dog{
    private String name;
    private String type;
    private Integer age;
    private List<String> toys;

    private Dog(String name, String type, Integer age, List<String> toys){
        this.name = name;
        this.type = type;
        this.age = age;
        this.toys = toys;
    }

    @Override
    public String toString(){
        String toString = new String();
        if(name != null){
            toString = name;
        }
        if(type != null){
            toString = toString + "\n" + type;
        }
        if(age != null){
            toString = toString + "\n" + age;
        }
        if(toys != null){
            toString = toString + "\n" + toys;
        }
        return toString;
    }

    public static class DogBuilder implements Builder{
        private String name;
        private String type;
        private Integer age;
        private List<String> toys;

        @Override
        public DogBuilder name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public DogBuilder type(String type) {
            this.type = type;
            return this;
        }

        @Override
        public DogBuilder age(int age) {
            this.age = age;
            return this;
        }

        @Override
        public DogBuilder addtoy(String toy) {
            this.toys = new ArrayList<>();
            toys.add(toy);
            return this;
        }

        public Dog build() {
            return new Dog(this.name, this.type, this.age, this.toys);
        }
    }

}
