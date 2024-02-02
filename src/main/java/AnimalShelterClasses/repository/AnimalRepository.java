package AnimalShelterClasses.repository;

import AnimalShelterClasses.model.Animal;
import AnimalShelterClasses.model.SortType;
import AnimalShelterClasses.service.AnimalService;

import java.util.ArrayList;
import java.util.Comparator;
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

    private List<Animal> myAnimalList = new ArrayList<>();
    {
        myAnimalList.add(new Animal(5,"Bob", 32));
    }

    public List<Animal> getMyAnimalList() {
        return myAnimalList;
    }
    private List<Animal> animalList;
 public void addAnimal(List<Animal> animals){
        animalList.add(new Animal(5, "Kim", 33));
 }


  public List<Animal> getAnimalsByName(String name) {
     return null;
    }


    public List<Animal> getAnimalsByAge(int age) {
     return null;
    }
public List<Animal> getAllAnimalsSortedByName(SortType name){
    List<Animal> sortedAnimals = new ArrayList<>(animalList);
    sortedAnimals.sort(Comparator.comparing(Animal::getName));
    return sortedAnimals;
}
public List<Animal> findAllAnimalByName(SortType name){
     List<Animal> foundAnimals = new ArrayList<>(animalList);
     foundAnimals.sort(Comparator.comparing(Animal::getName));
     return foundAnimals;
}
public List<Animal> returnAllAnimals(){
     return returnAllAnimals();
}
}
