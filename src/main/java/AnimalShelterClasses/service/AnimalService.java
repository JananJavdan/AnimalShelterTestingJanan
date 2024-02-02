package AnimalShelterClasses.service;

import AnimalShelterClasses.model.Animal;
import AnimalShelterClasses.model.SortType;
import AnimalShelterClasses.repository.AnimalRepository;

import java.util.Comparator;
import java.util.List;

import static AnimalShelterClasses.model.SortType.valueOf;

public class AnimalService {
    private AnimalRepository animalRepository;
    private AnimalService animalService;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }
    public AnimalService(AnimalService animalService){
        this.animalService = animalService;
    }

///methode sortAnimal
    public void sortAnimalShelter(SortType sortType){
        List<Animal> animalList = animalRepository.getAnimals();

        switch (sortType) {
            case AGE -> sortAnimalsByAge(animalList);
            case NAME -> sortAnimalsByName(animalList);
        }

        System.out.println();
    }

    public void sortAnimalsByName(List<Animal> animalList){
        animalList.sort(Comparator.comparing(Animal::getName));
    }

    public void sortAnimalsByAge(List<Animal> animalList){
        animalList.sort(Comparator.comparing(Animal::getAge));
    }///methode treat Animal
    public void treatAnimal(SortType sortType){
        List<Animal> animalList = animalRepository.getAnimals();

        switch (sortType) {
            case AGE -> treatAnimalByAge(animalList);
            case NAME -> treatAnimalByName(animalList);
        }
        System.out.println();
    }
    public void treatAnimalByAge(List<Animal> animalList){
        animalList.sort(Comparator.comparing(Animal::getAge));
    }
    public void treatAnimalByName(List<Animal> animalList){
        animalList.sort(Comparator.comparing(Animal::getName));
    }
    ///methode get all animal sorted by name
    public List<Animal> getAllAnimalsSortedByName(SortType sortType){
        return animalRepository.getAllAnimalSortedByName();
        }

        //methode findAllAnimalByName
        public List<Animal> findAllAnimalByName(){
        List<Animal> animalListAll = animalRepository.getAllAnimalSortedByName();
            return animalListAll;
        }
//methode findAnAnimal
    public List<Animal> findAnAnimal(String nameOrAge){
        if (equals(nameOrAge)){
            return findAnimalsByAge(Integer.parseInt(nameOrAge));
            }else
                return findAnimalsByName(nameOrAge);
        }
        private List<Animal> findAnimalsByName(String name){
        return animalRepository.getAnimalsByName(name);
        }
        private List<Animal> findAnimalsByAge(int age){
        return animalRepository.getAnimalsByAge(age);
        }
    ///methode addAnimal
    public void addAnimal(List<Animal> animals){
        animalRepository.addAnimal(animals);
    }
    public List<Animal> getAnimals(){
        return animalRepository.getAnimals();
    }

    //methode returnAllAnimal
    public List<Animal> returnAllAnimals(SortType sortType){
        return animalRepository.returnAllAnimals();
    }
        }






