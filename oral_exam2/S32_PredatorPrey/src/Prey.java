public class Prey extends Animal {

    public Prey(int initialSize, float growthCoefficient, float deathCoefficient) {
        changePopulationSize(initialSize);
        setGrowthCoefficient(growthCoefficient);
        setDeathCoefficient(deathCoefficient);
    }

    public void runCycle(int predatorCount) {
        changePopulationSize((int) ((getGrowthCoefficient()*getPopulationSize())-(getDeathCoefficient()*getPopulationSize()*predatorCount)));
    }

}
