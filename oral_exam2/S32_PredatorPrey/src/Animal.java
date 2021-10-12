public class Animal {

    private int populationSize;
    private float growthCoefficient;
    private float deathCoefficient;

    private String name;

    public Animal() {
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
}
