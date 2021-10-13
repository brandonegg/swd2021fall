import java.util.ArrayList;

public class Animal {

    private ArrayList<Animal> predatorsList;
    private ArrayList<Animal> preyList;

    private double populationSize;
    private double upcomingPopChange;
    private double growthCoefficient;
    private double deathCoefficient;

    private String name;

    public Animal(double initialSize, double growthCoefficient, double deathCoefficient) {
        upcomingPopChange = 0.0;
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
    public void computeChange(double dt) {
        if (preyList.size() == 0 && predatorsList.size() == 0) {
            upcomingPopChange = 0;
        } else {
            double changeAmount = 0.0;
            if (preyList.size() == 0) {
                //Animal is only a prey, add natural growth
                System.out.println(name + " is only a prey");
                changeAmount += getPopulationSize() * getGrowthCoefficient();
            } else {
                for (Animal prey : preyList) {
                    changeAmount += getGrowthCoefficient() * prey.getPopulationSize() * getPopulationSize();
                }
            }

            if (predatorsList.size() == 0) {
                //Animal is only a predator, subtract natural death
                System.out.println(name + " is only a predator");
                changeAmount -= getDeathCoefficient()*getPopulationSize();
            } else {
                for (Animal predator : predatorsList) {
                    changeAmount -= getDeathCoefficient()*getPopulationSize()* predator.getPopulationSize();
                }
            }
            upcomingPopChange = changeAmount*dt; //Queue next change;
            System.out.println("upcoming pop change for " +name + ": " +upcomingPopChange);
        }
    }

    public void updatePopulation() {
        populationSize += upcomingPopChange;
        if (populationSize <= 0) {
            populationSize = 0;
        }
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

    public void setGrowthCoefficient(double growthC) {
        this.growthCoefficient = growthC;
    }

    public void setDeathCoefficient(double deathC) {
        this.deathCoefficient = deathC;
    }

    public String getName() {
        return name;
    }

    public double getGrowthCoefficient() {
        return growthCoefficient;
    }

    public double getDeathCoefficient() {
        return deathCoefficient;
    }

    public double getPopulationSize() {
        return populationSize;
    }

    @Override
    public String toString() {
        return (name + ": " + populationSize +" alive");
    }
}
