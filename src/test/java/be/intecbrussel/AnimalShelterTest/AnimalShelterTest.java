package be.intecbrussel.AnimalShelterTest;

import be.intecbrussel.Vaccination.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AnimalShelterTest {

    private final AnimalShelter animalShelter;

    {
        animalShelter = new AnimalShelter();
    }



    @Test
    void sortAnimalsTest() {
        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal dog2 = new Dog(false, 4, "Louie", 222, true);
        Animal dog3 = new Dog(false, 2, "Kobey", 33, true);

        animalShelter.addAnimal(dog1);
        animalShelter.addAnimal(dog2);
        animalShelter.addAnimal(dog3);

        animalShelter.sortAnimals();

        assertEquals(dog1, animalShelter.findAnimal(1).orElse(null));
        assertEquals(dog2, animalShelter.findAnimal(2).orElse(null));
        assertEquals(dog3, animalShelter.findAnimal(3).orElse(null));
    }

    @Test
    void sortAnimalsByNameTest() {
        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal cat1 = new Cat(false, 3, "Mitzi", 345, true);
        Animal cat2 = new Cat(false, 2, "KittY", 376, true);

        animalShelter.addAnimal(dog1);
        animalShelter.addAnimal(cat1);
        animalShelter.addAnimal(cat2);

        animalShelter.sortAnimalByName();

        assertEquals(cat1, animalShelter.findAnimal(2).orElse(null));
        assertEquals(dog1, animalShelter.findAnimal(1).orElse(null));
        assertEquals(cat2, animalShelter.findAnimal(3).orElse(null));
    }

    @Test
    void SrtAnimalsByAgeTest() {
        Animal cat1 = new Dog(false, 3, "Mitzi", 345, true);
        Animal cat2 = new Cat(false, 2, "KittY", 376, true);
        Animal cat3 = new Cat(false, 1, "Lola", 398, true);

        animalShelter.addAnimal(cat1);
        animalShelter.addAnimal(cat2);
        animalShelter.addAnimal(cat3);

        animalShelter.sortAnimalByAge();

        assertEquals(cat1, animalShelter.findAnimal(3).orElse(null));
        assertEquals(cat2, animalShelter.findAnimal(2).orElse(null));
        assertEquals(cat3, animalShelter.findAnimal(1).orElse(null));
    }

    @Test
    void printAnimalsNotVaccinatedTest() {
        Animal dog = new Dog(false, 3, "Noel", 123, true);
        Animal cat = new Cat(false, 2, "KittY", 376, true);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);


        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.printAnimalsNotVaccinated(Disease.POLIO);

        String expectedOutput = "***************** Animals not vaccinated for POLIO ********************\n" +
                "isClean=false, age=3, name='Noel', animalNumber=123\n" +
                "isClean=false, age=2, name='Variable...', animalNumber=376\n";

        assertEquals(expectedOutput.trim(), outPut.toString().trim());
    }

    @Test
    void printAnimalsVaccinatedTest() {

        // TODO result not obtained. animalShelter.printAnimalsVaccinated(Disease.FLUE) is null.
        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal dog2 = new Dog(false, 4, "Louie", 222, true);

        animalShelter.addAnimal(dog1);
        animalShelter.addAnimal(dog2);

        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.printAnimalsNotVaccinated(Disease.FLUE);

        String expectedOutput = "***************** Animals vaccinated for FLUE ********************\n" +
                "isClean=true, age=3, name='Noel', animalNumber=123\n" +
                "isClean=true, age=4, name='Louie', animalNumber=222\n";

        assertEquals(expectedOutput.trim(), outPut.toString().trim());
    }

    @Test
    void findAnimalByNumberTest() {
        Animal monkey1 = new Monkey(true, 28, "Jonathan", 28, true);
        Animal monkey2 = new Monkey(false, 29, "mary", 29, true);
        Animal monkey3 = new Monkey(true, 27, "leen", 27, false);
        Animal monkey4 = new Monkey(false, 26, "martin", 26, false);

        animalShelter.addAnimal(monkey1);
        animalShelter.addAnimal(monkey2);
        animalShelter.addAnimal(monkey3);
        animalShelter.addAnimal(monkey4);

        Optional<Animal> foundAnimal4 = animalShelter.findAnimal(4);
        Optional<Animal> foundAnimal2 = animalShelter.findAnimal(2);
        Optional<Animal> foundAnimal1 = animalShelter.findAnimal(1);

        assertTrue(foundAnimal4.isPresent());
        assertEquals(monkey4, foundAnimal4.get());

        assertTrue(foundAnimal2.isPresent());
        assertEquals(monkey2, foundAnimal2.get());

        assertTrue(foundAnimal1.isPresent());
        assertEquals(monkey1, foundAnimal1.get());
    }


    @Test
    void findAnimalByNameTest() {
        Animal monkey1 = new Monkey(true, 28, "Jonathan", 28, true);
        Animal monkey2 = new Monkey(false, 29, "mary", 29, true);
        Animal monkey3 = new Monkey(true, 27, "leen", 27, false);
        Animal monkey4 = new Monkey(false, 26, "martin", 26, false);

        animalShelter.addAnimal(monkey1);
        animalShelter.addAnimal(monkey2);
        animalShelter.addAnimal(monkey3);
        animalShelter.addAnimal(monkey4);

        Optional<Animal> foundAnimal = animalShelter.findAnimal(3);

        assertTrue(foundAnimal.isPresent());
        assertEquals("Cheeky", foundAnimal.get().getName());
    }



    @Test
    void FindAnimalByNameTest() {
        Animal dog = new Dog(false, 2, "Kobey", 33, true);
        Animal cat = new Cat(false, 7, "Nali", 798, true);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);

        Optional<Animal> foundAnimal = animalShelter.findAnimal("Kimmy");

        assertTrue(foundAnimal.isPresent());
        assertEquals(cat, foundAnimal.get());
    }

    @Test
    void treatAnimalByNumberTest() {
        Animal dog = new Dog(false, 5, "Charlie", 1, true);

        animalShelter.addAnimal(dog);

        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.treatAnimal(1);

        String expectedOutput = "Searching for animal with animal number : 1\n" +
                "Animal found: isClean=false, age=5, name='Charlie', animalNumber=1\n" +
                "Animal with number 1 is being treated.\n" +
                "isClean=true, age=5, name='Charlie', animalNumber=1\n";

        assertEquals(expectedOutput, outPut.toString());
    }

    @Test
    void treatAnimalByNameTest() {
        Animal dog = new Dog(false, 5, "Max", 1, true);
        Animal cat = new Cat(false, 3, "Max", 2, false);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);

        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.treatAnimal("Max");

        String expectedOutput = "Searching for animal with name : Max\n" +
                "Animal found: isClean=false, age=5, name='Max', animalNumber=1\n" +
                "Animal with name Max is being treated.\n" +
                "isClean=true, age=5, name='Max', animalNumber=1\n";

        assertEquals(expectedOutput, outPut.toString());
    }

    @Test
    void treatAllAnimalsTest() {
        Animal dog = new Dog(false, 5, "Charlie", 1, true);
        Animal cat = new Cat(false, 3, "Buddy", 2, false);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);

        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.treatAllAnimal();

        String expectedOutput = "isClean=true, age=5, name='Charlie', animalNumber=1\n" +
                "isClean=true, age=3, name='Buddy', animalNumber=2\n";
        assertEquals(expectedOutput, outPut.toString());
    }

    @Test
    void findOldestAnimaTest() {
        Animal dog = new Dog(false, 5, "Charlie", 1, true);
        Animal cat = new Cat(false, 3, "Buddy", 2, false);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);

        assertEquals(dog, animalShelter.findOldestAnimal());
    }

    @Test
    void countAnimalsTest() {
        Animal dog = new Dog(false, 5, "Charlie", 1, true);
        Animal cat = new Cat(false, 3, "Buddy", 2, false);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);

        assertEquals(2, animalShelter.countAnimals());
    }
}


