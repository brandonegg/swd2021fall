public class Predator extends Animal {

    public Predator(int initialSize, float growthCoefficient, float deathCoefficent) {
        changePopulationSize(initialSize);
        setGrowthCoefficient(growthCoefficient);
        setDeathCoefficient(deathCoefficent);
    }

    public void runCycle(int preyCount) {
        changePopulationSize((int) ((getGrowthCoefficient()*getPopulationSize()*preyCount)-getDeathCoefficient()*getPopulationSize()));
    }

}
