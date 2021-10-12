import java.util.ArrayList;

public class SimulationRunner {

    public static void main(String[] args) {
        ArrayList<Animal> animalList = createAnimalList();

        int i = 0;
        int cycleCount = 50;
        while (i < cycleCount) {
            for (Animal animal : animalList) {
                animal.computeChange();
            }
            for (Animal animal : animalList) {
                animal.updatePopulation();
            }
            System.out.println("After cycle " + i + ":");
            for (Animal animal : animalList) {
                System.out.println(animal.toString());
            }
            i++;
        }
    }

    public static ArrayList<Animal> createAnimalList() {
        ArrayList<Animal> animals = new ArrayList<Animal>();

        Bear bear = new Bear(10,0.4f,0.6f);
        Deer deer = new Deer(10, 0.2f, 0.4f);
        Fox fox = new Fox(10, 0.3f, 0.6f);
        Rabbit rabbit = new Rabbit(10, 0.4f, 0.6f);

        bear.addPrey(deer);
        bear.addPrey(fox);
        bear.addPrey(rabbit);

        deer.addPredator(bear);
        deer.addPredator(fox);

        fox.addPrey(deer);
        fox.addPrey(rabbit);
        fox.addPredator(bear);

        rabbit.addPredator(bear);
        rabbit.addPredator(deer);
        rabbit.addPredator(fox);

        animals.add(bear);
        animals.add(deer);
        animals.add(fox);
        animals.add(rabbit);

        return animals;
    }
}

/**
 * Brainstorming:
 * an animal, prey, predator etc. have a set of prey and predators.
 */