package be.intecbrussel.Vaccination;


import be.intecbrussel.Vaccination.AnimalShelter;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        AnimalShelter animalShelter = new AnimalShelter();

        List<Animal> animals = new ArrayList<>();

        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal dog2 = new Dog(false, 4, "Louie", 222, true);
        Animal dog3 = new Dog(false, 2, "Kobey", 33, true);
        Animal cat1 = new Cat(false, 3, "Mitzi", 345, true);
        Animal cat2 = new Cat(false, 2, "KittY", 376, true);
        Animal cat3 = new Cat(false, 1, "Lola", 398, true);
        Animal cat4 = new Cat(false, 7, "Nali", 798, true);
        Animal monkey1 = new intec.be.Monkey(false, 11, "Kimmy", 213, true);
        Animal monkey2 = new intec.be.Monkey(false, 15, "Jolly", 331, true);
        Animal monkey3 = new intec.be.Monkey(false, 10, "Perry", 453, true);

        animals.add(dog1);
        animals.add(dog2);
        animals.add(dog3);
        animals.add(cat1);
        animals.add(cat2);
        animals.add(cat3);
        animals.add(cat4);
        animals.add(monkey1);
        animals.add(monkey2);
        animals.add(monkey3);

        for (Animal animal : animals) {
            animalShelter.addAnimal(animal);
        }


        animalShelter.printAnimals();
        animalShelter.sortAnimals();
        animalShelter.sortAnimalByAge();
        animalShelter.sortAnimalByName();

        dog1.vaccinateAnimal(Disease.POLIO);
        cat1.vaccinateAnimal(Disease.POLIO);
        monkey1.vaccinateAnimal(Disease.POLIO);
        animalShelter.printAnimalsNotVaccinated(Disease.POLIO);

        animalShelter.treatAnimal(0);
        animalShelter.treatAnimal(5);
        animalShelter.treatAnimal("Kim");
        animalShelter.treatAnimal("Mimi");

        animalShelter.treatAllAnimal();


        animalShelter.findAnimal(7);
        animalShelter.findAnimal("Lola");

        System.out.println(animalShelter.findOldestAnimal().getName());
        System.out.println(animalShelter.countAnimals());

    }
}

