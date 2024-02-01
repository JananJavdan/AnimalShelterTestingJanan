package AnimalShelterClasses.repository;

import AnimalShelterClasses.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {
    private List<Animal> shelterAnimals = new ArrayList<>();

    {
        shelterAnimals.add(new Animal(1, "Bobby", 52));
        shelterAnimals.add(new Animal(2, "Jean-Neymar", 10));
        shelterAnimals.add(new Animal(3, "PotatoSalad", 30));
        shelterAnimals.add(new Animal(4, "Anastasia", 9));
    }

    public List<Animal> getAnimals() {
        return shelterAnimals;
    }
    public List<Animal> getAllAnimalSortedByName(){
        return shelterAnimals;
    }

}
