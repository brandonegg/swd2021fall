import java.util.ArrayList;

public class SimulationRunner {

    public static void main(String[] args) {
        //ArrayList<Animal> animalList = createAnimalList();
        ArrayList<Animal> animalList = createSimpleAnimalList();

        int i = 0;
        int cycleCount = 50;
        while (i < cycleCount) {
            for (Animal animal : animalList) {
                animal.computeChange(1);
                animal.updatePopulation();
            }
            /*for (Animal animal : animalList) {
                animal.updatePopulation();
            }*/
            System.out.println("After cycle " + i + ":");
            for (Animal animal : animalList) {
                System.out.println(animal.toString());
            }
            i++;
        }
    }

    public static ArrayList<Animal> createAnimalList() {
        ArrayList<Animal> animals = new ArrayList<Animal>();

        Bear bear = new Bear(100,0.4f,0.6f);
        Deer deer = new Deer(100, 0.2f, 0.4f);
        Fox fox = new Fox(100, 0.3f, 0.6f);
        Rabbit rabbit = new Rabbit(100, 0.4f, 0.6f);

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

    public static ArrayList<Animal> createSimpleAnimalList() {
        ArrayList<Animal> animals = new ArrayList<Animal>();

        Fox fox = new Fox(0.5, 0.3, 0.2);
        Rabbit rabbit = new Rabbit(1.0, 0.7, 0.5);

        fox.addPrey(rabbit);
        rabbit.addPredator(fox);

        animals.add(fox);
        animals.add(rabbit);

        return animals;
    }
}

/**
 * Brainstorming:
 * an animal, prey, predator etc. have a set of prey and predators.
 */