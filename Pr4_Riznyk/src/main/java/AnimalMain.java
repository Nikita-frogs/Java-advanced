public class AnimalMain {
    public static void main(String[] args) {
        Animal[] animals = {new Dog(), new Cat(), new Animal()};

        for (Animal animal : animals) {
            animal.speak();
        }

        for (Animal animal : animals) {
            if (animal instanceof Dog dog) {    // Instanceof is essential to avoid ClassCastException
                dog.fetchstick();
            }
        }
    }
}
