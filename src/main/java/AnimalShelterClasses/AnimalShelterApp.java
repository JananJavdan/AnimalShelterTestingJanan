package AnimalShelterClasses;

import AnimalShelterClasses.model.Animal;
import AnimalShelterClasses.model.SortType;
import AnimalShelterClasses.repository.AnimalRepository;
import AnimalShelterClasses.service.AnimalService;

import javax.lang.model.element.Name;
import java.util.ArrayList;
import java.util.List;

public class AnimalShelterApp {
    public static void main(String[] args) {
        AnimalService animalService = new AnimalService(new AnimalRepository());
        List<Animal> inputAnimals = new ArrayList<>();
        {
            inputAnimals.add(new Animal(1,"Leen", 22));
        }
                animalService.addAnimal(inputAnimals);

        String nameOrAge = null;
        List<Animal> foundAnimal = animalService.findAnAnimal(nameOrAge);
        System.out.println("Found Animals: "+foundAnimal);

        List<Animal> allAnimalFromRepository = animalService.getAllAnimalsSortedByName(SortType.NAME);
        System.out.println("All Animal sorted by name:"+allAnimalFromRepository);

        List<Animal> foundAllByName = animalService.findAllAnimalByName();
        System.out.println("All Animal find by name:"+foundAllByName);

        List<Animal> animalList = animalService.getAnimals();
        System.out.println("Return All Animal"+animalList);
    }


}
