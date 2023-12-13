public class main {
    public static void main(String[] args) {
        Builder building = new Dog.DogBuilder();
        building.name("Nova");
        building.type("Chihuahua");
        Dog hund = (Dog) building.build();
        System.out.println(hund.toString());
    }
}
