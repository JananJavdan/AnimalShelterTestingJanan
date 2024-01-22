package be.intecbrussel.Vaccination;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AnimalShelter {
    private List<Animal> animals = new ArrayList<>();
    private int animalId = 0;

    public AnimalShelter() {
    }

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

    public void printAnimals() {
        System.out.println("---------- Print Animals ----------");
        animals.forEach(System.out::println);

    }

    public void sortAnimals() {
        System.out.println("\n---------- Sort Animals By Animal Number ----------");
        animals.stream().sorted(Comparator.comparing(Animal::getAnimalNumber))
                .forEach(System.out::println);
    }

    public void sortAnimalByName() {
        System.out.println("\n---------- Sort Animals By Name ----------");
        animals.stream().sorted(Comparator.comparing(Animal::getName))
                .forEach(System.out::println);

    }

    public void sortAnimalByAge() {
        System.out.println("\n---------- Sort Animals By Age ----------");
        animals.stream().sorted(Comparator.comparing(Animal::getAge))
                .forEach(System.out::println);
    }

    public void printAnimalsNotVaccinated(Disease disease) {
        System.out.println("\n---------- Animals Not Vaccinated ----------");
        System.out.println("Animals not vaccinated for " + disease + ":");
        animals.stream()
                .filter(animal -> !animal.getIsVaccinated().getOrDefault(disease, false))
                .forEach(System.out::println);
    }

    public Optional<Animal> findAnimal(int animalNumber) {
        System.out.println("\n---------- Find Animal By Animal Number ----------");
        System.out.println("Searching for animal by animal number : " + animalNumber);

        Optional<Animal> foundByAnimalNumber = animals.stream()
                .filter(animal -> animal.getAnimalNumber() == animalNumber)
                .findFirst();

        if (foundByAnimalNumber.isPresent()) {
            Animal animal = foundByAnimalNumber.get();
            System.out.println("Animal found  : " + animal);
        } else {
            System.out.println("Animal with animal number " + animalNumber + " was not found.");
        }
        return foundByAnimalNumber;
    }

    public Optional<Animal> findAnimal(String name) {
        System.out.println("\n---------- Find Animal By Animal Name ----------");
        System.out.println("Searching for animal by animal name : " + name);
        Optional<Animal> foundByAnimalName = animals.stream()
                .filter(animal -> animal.getName().equals(name))
                .findFirst();

        if (foundByAnimalName.isPresent()) {
            Animal animal = foundByAnimalName.get();
            System.out.println("Animal found  : " + animal);
        } else {
            System.out.println("Animal with animal number " + name + " was not found.");
        }
        return foundByAnimalName;
    }

    public void treatAnimal(int animalNumber) {
        animals.stream()
                .filter(animal -> animal.getAnimalNumber() == animalNumber)
                .forEach(Animal::treatAnimal);
    }

    public void treatAnimal(String name) {
        animals.stream().filter(animal -> animal.getName().equals(name))
                .forEach(Animal::treatAnimal);
    }

    public void treatAllAnimal() {
        animals.stream().forEach(Animal::treatAnimal);
    }


    public Animal findOldestAnimal() {
        System.out.println("\n---------- Find Oldest Animal ----------");
        return animals.stream().max(Comparator.comparing(Animal::getAge)).orElse(null);
    }

    public int countAnimals() {
        System.out.println("\n---------- Count Animals ----------");
        return animals.size();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animal.setAnimalNumber(animalId);
        animalId += 1;
    }

    @Override
    public String toString() {
        return "AnimalShelter{" +
                "animals=" + animals +
                ", animalId=" + animalId +
                '}';
    }
}
