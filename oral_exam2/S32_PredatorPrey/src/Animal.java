import java.util.ArrayList;

public class Animal {

    private ArrayList<Animal> predatorsList;
    private ArrayList<Animal> preyList;

    private int populationSize;
    private int upcomingPopChange;
    private float growthCoefficient;
    private float deathCoefficient;

    private String name;

    public Animal(int initialSize, float growthCoefficient, float deathCoefficient) {
        upcomingPopChange = 0;
        populationSize = initialSize;
        this.growthCoefficient = growthCoefficient;
        this.deathCoefficient = deathCoefficient;

        predatorsList = new ArrayList<Animal>();
        preyList = new ArrayList<Animal>();
    }

    /*
    For every predator that feeds on your animal,
    you should have deducting term composed of the product of the two animal populations and the corresponding coefficient
    And for every prey that your animal feeds upon,
    you should have an additive term composed of the product of the two animal populations and the corresponding coefficient
     */
    public void computeChange() {
        if (preyList.size() == 0 && predatorsList.size() == 0) {
            upcomingPopChange = 0;
        } else {
            int changeAmount = 0;
            if (preyList.size() == 0) {
                //Animal is only a prey, add natural growth
                changeAmount += getPopulationSize() * getGrowthCoefficient();
            } else {
                for (Animal prey : preyList) {
                    changeAmount += getGrowthCoefficient() * prey.getPopulationSize() * getPopulationSize();
                }
            }

            if (predatorsList.size() == 0) {
                //Animal is only a predator, subtract natural death
                changeAmount -= getDeathCoefficient()*getPopulationSize();
            } else {
                for (Animal predator : predatorsList) {
                    changeAmount -= getDeathCoefficient()*getPopulationSize()* predator.getPopulationSize();
                }
            }
            upcomingPopChange = changeAmount; //Queue next change;
        }
    }

    public void updatePopulation() {
        populationSize += upcomingPopChange;
        upcomingPopChange = 0;

        if (populationSize <= 0) {
            kill();
        }
    }

    public void kill() {
        //TODO:
        //Make populationSize 0
        //Go through prey list, and remove this animal from their predator list.
        //Go through predator list and remove this animal from their prey list
    }

    public void addPredator(Animal predator) {
        if (!preyList.contains(predator)) {
            predatorsList.add(predator);
        } else {
            throw new IllegalArgumentException("Predator is already a prey!");
        }
    }

    public void addPrey(Animal prey) {
        if (!predatorsList.contains(prey)) {
            preyList.add(prey);
        } else {
            throw new IllegalArgumentException("Prey is already a predator!");
        }
    }

    public void removePredator(Animal predator) {
        predatorsList.remove(predator);
    }

    public void removePrey(Animal prey) {
        preyList.remove(prey);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changePopulationSize(int amount) {
        populationSize += amount;
    }

    public void setGrowthCoefficient(float growthC) {
        this.growthCoefficient = growthC;
    }

    public void setDeathCoefficient(float deathC) {
        this.deathCoefficient = deathC;
    }

    public String getName() {
        return name;
    }

    public float getGrowthCoefficient() {
        return growthCoefficient;
    }

    public float getDeathCoefficient() {
        return deathCoefficient;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    @Override
    public String toString() {
        return (name + ": " + populationSize +" alive");
    }
}
