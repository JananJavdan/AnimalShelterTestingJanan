package be.intecbrussel.Vaccination;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class Animal implements Treatable,Vaccinateable {

    private Map<Disease,Boolean> isVaccinated=new HashMap<>();
    private boolean isClean;
    private int age;
    private String name;
    private int animalNumber;

    //nullpointexception voorkomen
    public Animal() {
        this(false, 0, "DEFAULT_ANIMAL_NAME", -1);
    }

    public Animal(boolean isClean, int age, String name, int animalNumber) {
       this.isClean = isClean;
        this.age = age;
        this.name = name;
        this.animalNumber = animalNumber;
        Stream.of(Disease.values()).forEach((key-> isVaccinated.put(key,false)));
    }
   //getters en setters
    public Map<Disease, Boolean> getIsVaccinated() {
        return isVaccinated;
    }

    public void setIsVaccinated(Map<Disease, Boolean> isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public boolean isClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        isClean = clean;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAnimalNumber() {
        return animalNumber;
    }

    public void setAnimalNumber(int animalNumber) {
        this.animalNumber = animalNumber;
    }
    @Override
    public void vaccinateAnimal(Disease disease) {

        isVaccinated.replace(disease,true);
    }

    @Override
    public String toString() {
        return "Animal " +

                ", isClean=" + isClean +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", animalNumber=" + animalNumber +
                '}';
    }
}
