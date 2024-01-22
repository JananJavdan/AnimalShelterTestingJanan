package be.intecbrussel.AnimalShelterTest;

import be.intecbrussel.Vaccination.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AnimalShelter1Test {

    private AnimalShelter1 animalShelter;
    private List<Animal> animals;

    @BeforeEach
    public void setUp() {
        animals = new ArrayList<>();
        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal cat1 = new Cat(false, 3, "Mitzi", 345, true);
        Animal monkey1 = new Monkey(false, 11, "Kimmy", 213, true);
        animals.add(dog1);
        animals.add(cat1);
        animals.add(monkey1);
        animalShelter = new AnimalShelter1(animals, 346); //next animal added will have id 346
    }


    @Test
    void printAnimals() {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.printAnimals(); //call method
        System.setOut(oldOut);
        String result = baos.toString();

        //Expected result
        String expectedResult =
                "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n" +
                "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +
                "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n";

        //Test
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void sortAnimals() { // sort by animal number
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.sortAnimals();  //call method sort
        animalShelter.printAnimals(); //call method print
        System.setOut(oldOut);
        String result = baos.toString();

        //Expected result
        String expectedResult =
                "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +
                "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"+
                "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n";

        //Test
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void sortAnimalsByName() {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.sortAnimalsByName();  //call method sort
        animalShelter.printAnimals();       //call method print
        System.setOut(oldOut);
        String result = baos.toString();

        //Expected result
        String expectedResult =
                "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"+
                "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +
                "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n";

        //Test
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void sortAnimalsByAge() {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.sortAnimalsByAge();   //call method sort
        animalShelter.printAnimals();       //call method print
        System.setOut(oldOut);
        String result = baos.toString();

        //Expected result
        String expectedResult =
                "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+
                "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +
                "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n";

        //Test
        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("printAnimalsNotVaccinatedFactory")
    void printAnimalsNotVaccinated(Disease disease) {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.printAnimalsNotVaccinated(disease);   //call method
        System.setOut(oldOut);
        String result = baos.toString();

        //Expected result
        String expectedResult =
                "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+
                "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +
                "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n";

        //Test
        Assertions.assertEquals(expectedResult, result);
    }
    static Stream<Disease> printAnimalsNotVaccinatedFactory(){
        return Stream.of(
                Disease.POLIO,
                Disease.HEPATITISA,
                Disease.FLUE,
                Disease.CHICKENPOCKS
        );
    }

    @ParameterizedTest
    @MethodSource("findAnimalFactory")
    void findAnimal(int animalNumber, String expectedResult) { //by animalNumber
        Animal foundAnimal = animalShelter.findAnimal(animalNumber);   //call method
        String result = "";

        if(foundAnimal != null) {
            result = foundAnimal.toString();
        }

        //Test
        Assertions.assertEquals(expectedResult, result);

    }
    static Stream<Arguments> findAnimalFactory(){
        return Stream.of(
                Arguments.of(123, "Animal , isClean=false, age=3, name='Noel', animalNumber=123}"),
                Arguments.of(345, "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}"),
                Arguments.of(0, "")
        );
    }

    @ParameterizedTest
    @MethodSource("findAnimalByNameFactory")
    void findAnimalByName(String animalName, String expectedResult) {
        Animal foundAnimal = animalShelter.findAnimal(animalName);   //call method
        String result = "";

        if(foundAnimal != null) {
            result = foundAnimal.toString();
        }

        //Test
        Assertions.assertEquals(expectedResult, result);

    }
    static Stream<Arguments> findAnimalByNameFactory(){
        return Stream.of(
                Arguments.of("Noel", "Animal , isClean=false, age=3, name='Noel', animalNumber=123}"),
                Arguments.of("Mitzi", "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}"),
                Arguments.of("rhth", ""),
                Arguments.of("", "")
        );
    }

    @ParameterizedTest
    @MethodSource("treatAnimalFactory")
    void treatAnimal(int animalNumber, String expectedResult) {  //by animalNumber
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.treatAnimal(animalNumber);    //call method treat animal by number
        animalShelter.printAnimals();               //call method print animals
        System.setOut(oldOut);
        String result = baos.toString();

        //Test
        Assertions.assertEquals(expectedResult, result);

    }
    static Stream<Arguments> treatAnimalFactory(){
        return Stream.of(
                Arguments.of(123, "Animal , isClean=true, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"),
                Arguments.of(345, "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=true, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"),
                Arguments.of(0,   "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n")
        );
    }

    @ParameterizedTest
    @MethodSource("treatAnimalByNameFactory")
    void treatAnimalByName(String animalName, String expectedResult) {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.treatAnimal(animalName);   //call method treat animal by name
        animalShelter.printAnimals();            //call method print animals
        System.setOut(oldOut);
        String result = baos.toString();

        //Test
        Assertions.assertEquals(expectedResult, result);

    }
    static Stream<Arguments> treatAnimalByNameFactory(){
        return Stream.of(
                Arguments.of("Noel",  "Animal , isClean=true, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"),
                Arguments.of("Mitzi", "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=true, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"),
                Arguments.of("",      "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n")
        );
    }

    @Test
    void treatAllAnimals() {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.treatAllAnimals();    //call method treat all animals
        animalShelter.printAnimals();       // call method print animals
        System.setOut(oldOut);
        String result = baos.toString();

        //Expected result
        String expectedResult = "Animal , isClean=true, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=true, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=true, age=11, name='Kimmy', animalNumber=213}\r\n";

        //Test
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void findOldestAnimal() {
        Animal foundAnimal = animalShelter.findOldestAnimal(); //call method

        //Expected result
        String expectedResult = "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}";

        //Test
        Assertions.assertEquals(expectedResult, foundAnimal.toString());
    }

    @Test
    void countAnimals() {
        int result = animalShelter.countAnimals(); //call method

        //Expected result
        int expectedResult = 3;

        //Test
        Assertions.assertEquals(expectedResult, result);
    }

    @ParameterizedTest
    @MethodSource("addAnimalFactory")
    void addAnimal(Animal animal, int expectedId, String expectedResult) {

        if(animal != null) {
            animalShelter.addAnimal(animal); //call method to add animal
        }

        //Test if id correct
        Assertions.assertEquals(expectedId, animalShelter.getAnimalId());

        //Test if animal added correctly to the list
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        animalShelter.printAnimals();       // call method print animals
        System.setOut(oldOut);
        String result = baos.toString();

        Assertions.assertEquals(expectedResult, result);

    }
    static Stream<Arguments> addAnimalFactory(){
        return Stream.of(
                Arguments.of(new Dog(false, 55, "Tom", 999, true), 347, "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n" +"Animal , isClean=false, age=55, name='Tom', animalNumber=346}\r\n"), // next animal id is 347
                Arguments.of(null, 346, "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n"),   //test with null value
                Arguments.of(new Dog(), 347, "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n"+"Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +"Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n" +"Animal , isClean=false, age=0, name='DEFAULT_ANIMAL_NAME', animalNumber=346}\r\n") //test with empty constructor
        );
    }
}