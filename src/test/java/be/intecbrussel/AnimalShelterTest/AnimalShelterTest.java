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
    private final AnimalShelter1 animalShelter1;
    {
        animalShelter1 = new AnimalShelter1();
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
        Animal dog2 = new Dog(false, 4, "Louie", 222, true);
        Animal dog3 = new Dog(false, 2, "Kobey", 33, true);

        animalShelter.addAnimal(dog1);
        animalShelter.addAnimal(dog2);
        animalShelter.addAnimal(dog3);

        animalShelter.sortAnimalByName();

        assertEquals(dog3, animalShelter.findAnimal("Kobey").orElse(null));
        assertEquals(dog2, animalShelter.findAnimal("Louie").orElse(null));
        assertEquals(dog1, animalShelter.findAnimal("Noel").orElse(null));
    }

    @Test
    void SrtAnimalsByAgeTest() {
        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal dog2 = new Dog(false, 4, "Louie", 222, true);
        Animal dog3 = new Dog(false, 2, "Kobey", 33, true);

        animalShelter.addAnimal(dog1);
        animalShelter.addAnimal(dog2);
        animalShelter.addAnimal(dog3);

        animalShelter.sortAnimalByAge();

        assertEquals(dog3, animalShelter.findAnimal(33).orElse(null));
        assertEquals(dog2, animalShelter.findAnimal(222).orElse(null));
        assertEquals(dog1, animalShelter.findAnimal(123).orElse(null));


    }



    @Test
    void printAnimalsNotVaccinatedTest() {
        Animal dog = new Dog(false, 5, "Charlie", 1, true);
        Animal cat = new Cat(false, 3, "Buddy", 2, false);

        animalShelter.addAnimal(dog);
        animalShelter.addAnimal(cat);


        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.printAnimalsNotVaccinated(Disease.POLIO);

        String expectedOutput = "***************** Animals not vaccinated for POLIO ********************\n" +
                "isClean=false, age=5, name='Charlie', animalNumber=1\n" +
                "isClean=false, age=3, name='Buddy', animalNumber=2\n";

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

        Monkey jon = new Monkey(true, 28, "Jonathan", 28, true);
        Monkey mary = new Monkey(false, 29, "mary", 29, true);
        Monkey leen = new Monkey(true, 27, "leen", 27, false);
        Monkey martin = new Monkey(false, 26, "martin", 26, false);

        animalShelter.addAnimal(jon);
        animalShelter.addAnimal(mary);
        animalShelter.addAnimal(leen);
        animalShelter.addAnimal(martin);

        animalShelter1.findAnimal(29);

        int existingAnimalNumber = 29;
        assertTrue(animalShelter.findAnimal(existingAnimalNumber).isPresent(),
                "Het dier met animalNumber " + existingAnimalNumber + " moet gevonden worden.");

        int notExistingAnimalNumber = 999;
        assertFalse(animalShelter.findAnimal(notExistingAnimalNumber).isPresent(),
                "Het dier met animalNumber" + notExistingAnimalNumber+ " mag niet gevonden worden");


    }



    @Test
    void findAnimalByNameTest() {
        Animal monkey1 = new Monkey(false, 11, "Kimmy", 213, true);
        Animal monkey2 = new Monkey(false, 15, "Jolly", 331, true);
        Animal monkey3 = new Monkey(false, 10, "Perry", 453, true);


        animalShelter.addAnimal(monkey1);
        animalShelter.addAnimal(monkey2);
        animalShelter.addAnimal(monkey3);

        Optional<Animal> foundAnimal = animalShelter.findAnimal("Perry");

        assertTrue(foundAnimal.isPresent());
        assertEquals("Perry", foundAnimal.get().getName());

        String existingAnimalName = "Perry";
        assertTrue(animalShelter.findAnimal(existingAnimalName).isPresent(),
                "Het dier met animalName " + existingAnimalName + " moet gevonden worden.");

        String notExistingAnimalName = "Max";
        assertFalse(animalShelter.findAnimal(notExistingAnimalName).isPresent(),
        "Het dier met animalName" + notExistingAnimalName+ " mag niet gevonden worden");

    }



    @Test
    void FindAnimalByNameTest() {
        AnimalShelter1 animalShelter1 = new AnimalShelter1();
        Monkey jon = new Monkey(true, 28, "Jonathan", 28, true);
        Monkey mary = new Monkey(false, 29, "mary", 29, true);
        Monkey leen = new Monkey(true, 27, "leen", 27, false);
        Monkey martin = new Monkey(false, 26, "martin", 26, false);

        animalShelter1.addAnimal(jon);
        animalShelter1.addAnimal(mary);
        animalShelter1.addAnimal(leen);
        animalShelter1.addAnimal(martin);

        animalShelter1.treatAnimal("martin");

        Animal treatedAnimal = animalShelter1.findAnimal("martin");
        boolean isCleanAfterTreatment = treatedAnimal != null && treatedAnimal.isClean();
        assertEquals(true, isCleanAfterTreatment, "Het dier met ANIMALNAME martin moet zijn behandeld en schoon zijn.");
    }


    @Test
    void treatAnimalByNumberTest() {
        AnimalShelter1 animalShelter1 = new AnimalShelter1();
        Monkey jon = new Monkey(true, 28, "Jonathan", 28, true);
        Monkey mary = new Monkey(false, 29, "mary", 29, true);
        Monkey leen = new Monkey(true, 27, "leen", 27, false);
        Monkey martin = new Monkey(false, 26, "martin", 26, false);


        animalShelter1.addAnimal(jon);
        animalShelter1.addAnimal(mary);
        animalShelter1.addAnimal(leen);
        animalShelter1.addAnimal(martin);


        animalShelter1.treatAnimal(3);

        // Controleer of het dier is behandeld
        Animal treatedAnimal = animalShelter1.findAnimal(3);
        boolean isCleanAfterTreatment = treatedAnimal != null && treatedAnimal.isClean();

        // Assert
        assertEquals(true, isCleanAfterTreatment, "Het dier met ANIMALNUMBER 3 moet zijn behandeld en schoon zijn.");
    }


    @Test
    void treatAnimalByNameTest() {
        Animal mary = new Monkey(true, 29, "mary", 3, false);


        animalShelter.addAnimal(mary);


        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.treatAnimal("mary");

        String expectedOutput =
                "Animal found: isClean=true, age=29, name='mary', animalNumber=3\n" +
                "Animal with name mary is being treated.\n";


        assertEquals(expectedOutput.trim(), outPut.toString().trim());
    }

    @Test
    void treatAllAnimalsTest() {
        Animal leen = new Monkey(true, 27, "leen", 27, false);


        animalShelter.addAnimal(leen);


        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outPut));

        animalShelter.treatAllAnimal();

        String expectedOutput = "isClean=true, age=27, name='leen', animalNumber=27\n";
        //String actualOutput = outPut.toString().trim();

        assertEquals(expectedOutput, outPut.toString());
       // assertTrue(actualOutput.equalsIgnoreCase(expectedOutput));
    }

    @Test
    void findOldestAnimaTest() {
        Animal monkeymary = new Monkey(false, 29, "mary", 29, true);
        Animal monkeyjon = new Monkey(true, 28, "Jonathan", 28, true);

        animalShelter.addAnimal(monkeymary);
        animalShelter.addAnimal(monkeyjon);

        assertEquals(monkeymary, animalShelter.findOldestAnimal());
    }

    @Test
    void countAnimalsTest() {
        Animal cat = new Cat(false, 7, "Nali", 798, true);
        Animal dog = new Dog(false, 2, "Kobey", 33, true);

        animalShelter.addAnimal(cat);
        animalShelter.addAnimal(dog);

        assertEquals(2, animalShelter.countAnimals());
    }
}


