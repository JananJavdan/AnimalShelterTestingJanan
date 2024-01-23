package be.intecbrussel.Vaccination;

import be.intecbrussel.Vaccination.AnimalShelter1;
import be.intecbrussel.Vaccination.Cat;
import be.intecbrussel.Vaccination.Dog;
import be.intecbrussel.Vaccination.Monkey;

import java.util.stream.Stream;

public class MainApp1 {
    public static void main(String[] args) {
        //creating objects
        AnimalShelter1 animalShelter1 = new AnimalShelter1();
        Monkey jon = new Monkey(true, 28, "Jonathan", 28, true);
        Monkey mary = new Monkey(false, 29, "mary", 29, true);
        Monkey leen = new Monkey(true, 27, "leen", 27, false);
        Monkey martin = new Monkey(false, 26, "martin", 26, false);
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Dog dog3 = new Dog();

        //adding animals to animalShelter
        System.out.println("1-adding animals to animalShelter ********************************************");
        animalShelter1.addAnimal(jon);
        animalShelter1.addAnimal(mary);
        animalShelter1.addAnimal(leen);
        animalShelter1.addAnimal(martin);
        animalShelter1.addAnimal(cat1);
        animalShelter1.addAnimal(cat2);
        animalShelter1.addAnimal(cat3);
        animalShelter1.addAnimal(dog1);
        animalShelter1.addAnimal(dog2);
        animalShelter1.addAnimal(dog3);
        Stream.of(animalShelter1).forEach(p -> System.out.println(animalShelter1));

        //printing all animals
        System.out.println("2-printing all animals *******************************************************");
        animalShelter1.printAnimals();

        //sortanimals by ANIMALNUMBER
        System.out.println("3-sort animals by ANIMALNUMBER ***********************************************");
        animalShelter1.sortAnimals();
        animalShelter1.printAnimals();

        //sortanimals by NAME
        System.out.println("4-sort animals by NAME *******************************************************");
        animalShelter1.sortAnimalsByName();
        animalShelter1.printAnimals();

        //sortanimals by AGE
        System.out.println("5- sort animals by AGE *******************************************************");
        animalShelter1.sortAnimalsByAge();
        animalShelter1.printAnimals();

        //vaccinateAnimal
        System.out.println("6- vaccinatie Animal  *******************************************************");
        jon.vaccinateAnimal(Disease.POLIO);
        jon.vaccinateAnimal(Disease.FLUE);
        jon.vaccinateAnimal(Disease.CHICKENPOCKS);
        jon.vaccinateAnimal(Disease.HEPATITISA);
        jon.vaccinateAnimal(Disease.HEPATITISA);

        //print animals NOT VACCINATED
        System.out.println("7-print animals NOT VACCINATED ***********************************************");
        animalShelter1.printAnimalsNotVaccinated(Disease.HEPATITISA);

        //find animal by ANIMALNUMBER
        System.out.println("8-find animal by ANIMALNUMBER ************************************************");
        System.out.println(animalShelter1.findAnimal(3));

        //find animal by NAME
        System.out.println("9-findanimal by NAME *********************************************************");
        System.out.println(animalShelter1.findAnimal("mary"));

        //treat animal by ANIMALNUMBER
        System.out.println("10-treat animal by ANIMALNUMBER ***********************************************");
        animalShelter1.treatAnimal(3);
        System.out.println(animalShelter1.findAnimal(3));

        //treat animal by NAME
        System.out.println("11-treat animal by NAME ******************************************************");
        animalShelter1.treatAnimal("MARy");
        System.out.println(animalShelter1.findAnimal("MARy"));

        //treat animal by ANIMALNUMBER
        System.out.println("12-treat animal by ANIMALNUMBER **********************************************");
        animalShelter1.treatAllAnimals();
        animalShelter1.printAnimals();

        //find OLDEST animal
        System.out.println("13- find OLDEST animal *******************************************************");
        System.out.println(animalShelter1.findOldestAnimal());

        //counting animals
        System.out.println("14-counting animals **********************************************************");
        System.out.println(animalShelter1.countAnimals());

    }
}
