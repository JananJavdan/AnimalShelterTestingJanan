package be.intecbrussel.Vaccination;

import java.util.*;

import static java.util.Collections.min;

public class AnimalShelter1 {

    private List<Animal> animals=new ArrayList<>();
    private int animalId;

    public AnimalShelter1() {
        this.animalId=0;
    }

    public AnimalShelter1(int animalId) {
        this.animalId = animalId;
    }

    public AnimalShelter1(List<Animal> animals, int animalId) {
        this.animals = animals;
        this.animalId = animalId;
    }
    //getters en setters
    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public void printAnimals(){
        animals.forEach(System.out::println);
    }

    public void sortAnimals(){

        animals.sort(Comparator.comparing(Animal::getAnimalNumber).reversed());
    }

    public void sortAnimalsByName(){

        animals.sort(Comparator.comparing(Animal::getName));
    }
    public void sortAnimalsByAge(){

        animals.sort(Comparator.comparing(Animal::getAge));
    }

    public void printAnimalsNotVaccinated(Disease disease){
        animals.stream()
                .filter(animal -> !animal.getIsVaccinated().get(disease))
                .forEach(System.out::println);
    }

    public  Animal findAnimal(int animalNumber){

        return animals.stream()
                .filter(animal -> animal.getAnimalNumber()==animalNumber)
                .findFirst().
                orElse(null);


    }

    public  Animal findAnimal(String name){
        return animals.stream()
                .filter(animal -> animal.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

    }
    public void treatAnimal(int animalNumber){

            animals.stream()
                    .filter(animal -> animal.getAnimalNumber()==animalNumber)
                    .findFirst().ifPresent(Treatable::treatAnimal);
    }
    public void treatAnimal(String name){
        animals.stream().
                filter(animal -> animal.getName().equalsIgnoreCase(name))
                .findFirst().ifPresent(Treatable::treatAnimal);
    }
    public void treatAllAnimals(){
       animals.forEach(Animal::treatAnimal);
    }
    public Animal findOldestAnimal(){

        return animals.stream()
                .max(Comparator.comparing(Animal::getAge))
                .orElse(null);
    }

    public int countAnimals(){

        long count = animals.stream().count();
       return (int) count;

    }

    public void addAnimal(Animal animal){
        animals.add(animal);
        animal.setAnimalNumber(animalId);
        animalId+=1;

    }


}
