package AnimalShelterClasses.service;

import AnimalShelterClasses.model.Animal;
import AnimalShelterClasses.model.Desease;
import AnimalShelterClasses.model.SortType;
import AnimalShelterClasses.repository.AnimalRepository;

import javax.naming.Name;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static AnimalShelterClasses.model.SortType.NAME;
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

////////////////////////////////////////////////////////////////////////////////////methode sortAnimal
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
    }////////////////////////////////////////////////////////////////////////////////methode treat Animal
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
    //////////////////////////////////////////////////////////////////////////////////////methode get all animal sorted by name
    public AnimalRepository getAllAnimalsSortedByName(SortType sortType){
        return animalRepository;
        }//////////////////////////////////////////////////////////////////////////////methode findAllAnimalByName
        public void findAllAnimalByName(){
        List<Animal> animalListAll = animalRepository.getAllAnimalSortedByName();
        }
////////////////////////////////////////////////////////////////////////////////methode findAnAnimal
    public void findAnAnimal(Animal animal){
        List<Animal> animalList = animalService.animalRepository.getAnimals();
        for (Animal animal1 : animalList){
            System.out.println();
        }
        }
    //////////////////////////////////////////////////////////////////////addAnimal
    public void addAnimal(List<Animal> animalList){
    //   animalList.add(1,);
    }


    }





