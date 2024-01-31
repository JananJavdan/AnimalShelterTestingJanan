/*package be.intecbrussel.AnimalShelterTest;

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

class AnimalShelterTest1 {

    private AnimalShelter1 animalShelter;
    private List<Animal> animals;//list van dieren

    @BeforeEach
    public void setUp() {
        animals = new ArrayList<>();// initialiseert de lijst van dieren
        //animal subklassen
        Animal dog1 = new Dog(false, 3, "Noel", 123, true);
        Animal cat1 = new Cat(false, 3, "Mitzi", 345, true);
        Animal monkey1 = new Monkey(false, 11, "Kimmy", 213, true);
        animals.add(dog1);//call methode
        animals.add(cat1);
        animals.add(monkey1);
        animalShelter = new AnimalShelter1(animals, 346); //next animal added will have id 346(instantie van animal shelter gemaakt
    }


    @Test
    void printAnimals() {
        //Capture system out print and place in variable
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//This is an output stream where data is written into a byte array.این یک جریان خروجی است که در آن داده ها در یک آرایه بایت نوشته می شوند
        PrintStream ps = new PrintStream(baos);//This allows you to capture the output that would normally be printed to the console.
        PrintStream oldOut = System.out;// Saves the original standard output stream (console output) so that it can be restored later.
        System.setOut(ps);
        animalShelter.printAnimals(); //call method
        System.setOut(oldOut);// Restores the original standard output stream, so subsequent output goes to the console as usual.
        String result = baos.toString();//This allows the test to examine what would have been printed to the console during the execution of printAnimals.

        //Expected result
        String expectedResult =
                "Animal , isClean=false, age=3, name='Noel', animalNumber=123}\r\n" +
                        "Animal , isClean=false, age=3, name='Mitzi', animalNumber=345}\r\n" +
                        "Animal , isClean=false, age=11, name='Kimmy', animalNumber=213}\r\n";

        //Test (om te controleren of de verwachte waarde gelijk is aan de werkelijke waarde)
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

        //Test (om te controleren of de verwachte waarde gelijk is aan de werkelijke waarde)
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
    void findAnimal(int animalNumber, String expectedResult) { //by animalNumber (De testmethode accepteert twee parameters: animalNumber en expectedResult)
        Animal foundAnimal = animalShelter.findAnimal(animalNumber);   //call method
        String result = "";

        if(foundAnimal != null) {
            result = foundAnimal.toString();
        }
        //Als het gevonden dier niet null is, wordt de toString-methode van het dier
        // opgeroepen en de resulterende string wordt toegewezen aan de variabele result.

        //Test
        Assertions.assertEquals(expectedResult, result);

    }
    //De invoerparameters voor deze testmethode zouden worden geleverd door de findAnimalFactory-methode,
    // die een Stream<Arguments> van verschillende sets invoerparameters zou moeten retourneren.
    //پارامترهای ورودی برای این روش تست توسط روش findAnimalFactory ارائه می‌شود که یک Stream<Arguments> از چندین مجموعه را می‌گیرد.
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//Hier wordt een ByteArrayOutputStream aangemaakt, een stream waarin gegevens worden geschreven in een byte-array.
        PrintStream ps = new PrintStream(baos);//Hier wordt een PrintStream gemaakt die schrijft naar de eerder gecreëerde ByteArrayOutputStream.
        PrintStream oldOut = System.out;//Hier wordt de standaard uitvoer (output) van het systeem opgeslagen in een variabele genaamd oldOut.
        System.setOut(ps);// Hier wordt de standaard uitvoer omgeleid naar de PrintStream die is verbonden met ByteArrayOutputStream.
        animalShelter.printAnimals();       // call method print animals
        System.setOut(oldOut);//Hier wordt de standaard uitvoer teruggezet naar wat het was voordat deze werd omgeleid.
        String result = baos.toString();// Hier wordt de inhoud van de ByteArrayOutputStream omgezet naar een String.

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
//@Test
//void findAnimalByNumberTest() {
//    Animal monkey1 = new Monkey(false, 4, "Banana", 1, true);
//    Animal monkey2 = new Monkey(false, 2, "Bongo", 2, true);
//    Animal monkey3 = new Monkey(false, 5, "Cheeky", 3, true);
//    Animal monkey4 = new Monkey(false, 5, "Chimp", 4, true);
//
//    animalShelter.addAnimal(monkey1);
//    animalShelter.addAnimal(monkey2);
//    animalShelter.addAnimal(monkey3);
//    animalShelter.addAnimal(monkey4);
//
//    Optional<Animal> foundAnimal4 = animalShelter.findAnimal(4);
//    Optional<Animal> foundAnimal2 = animalShelter.findAnimal(2);
//    Optional<Animal> foundAnimal1 = animalShelter.findAnimal(1);
//
//    assertTrue(foundAnimal4.isPresent());
//    assertEquals(monkey4, foundAnimal4.get());
//
//    assertTrue(foundAnimal2.isPresent());
//    assertEquals(monkey2, foundAnimal2.get());
//
//    assertTrue(foundAnimal1.isPresent());
//    assertEquals(monkey1, foundAnimal1.get());
//}
//
//@Test
//void FindAnimalByNameTest() {
//    Animal dog = new Dog(false, 5, "Charlie", 1, true);
//    Animal cat = new Cat(false, 3, "Buddy", 2, false);
//
//    animalShelter.addAnimal(dog);
//    animalShelter.addAnimal(cat);
//
//    Optional<Animal> foundAnimal = animalShelter.findAnimal("Buddy");
//
//    assertTrue(foundAnimal.isPresent());
//    assertEquals(cat, foundAnimal.get());
//}/////////////////////////////////////////////////////////////////////////
//@ParameterizedTest
//@MethodSource("animalData")
//void findAnimalByNumberTest(int animalNumber, String expectedName) {
//    Animal monkey1 = new Monkey(false, 4, "Banana", 1, true);
//    Animal monkey2 = new Monkey(false, 2, "Bongo", 2, true);
//    Animal monkey3 = new Monkey(false, 5, "Cheeky", 3, true);
//    Animal monkey4 = new Monkey(false, 5, "Chimp", 4, true);
//
//    animalShelter.addAnimal(monkey1);
//    animalShelter.addAnimal(monkey2);
//    animalShelter.addAnimal(monkey3);
//    animalShelter.addAnimal(monkey4);
//
//    Optional<Animal> foundAnimal = animalShelter.findAnimal(animalNumber);
//
//    assertTrue(foundAnimal.isPresent());
//    assertEquals(expectedName, foundAnimal.get().getName());
//}
//
//private static Stream<Arguments> animalData() {
//    return Stream.of(
//            Arguments.of(4, "Chimp"),
//            Arguments.of(2, "Bongo"),
//            Arguments.of(1, "Banana"),
//            Arguments.of(40,"Lolo")
//    );
//}/////////////////////////////////////////////////////////////////////////
//package intecbrussel.be;
//
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//
//import java.util.*;
//import java.util.stream.Stream;
//
//public class AnimalShelterTest {
//    static HashMap<Disease, Boolean> isVaccinated = new HashMap<>();
//    private AnimalShelter animalShelter;
//
//    {
//        ArrayList<Animal> anilmalList = new ArrayList<>();
//
//
//        isVaccinated.put(Disease.CHICKENPOCKS, false);
//        isVaccinated.put(Disease.FLUE, false);
//        isVaccinated.put(Disease.POLIO, false);
//        isVaccinated.put(Disease.HEPATITISA, false);
//        anilmalList.add(new Dog(1, "Jacky_Dog1", 5, false, true, isVaccinated));
//        anilmalList.add(new Dog(2, "Sheru_Dog2", 2, false, true, isVaccinated));
//        anilmalList.add(new Dog(3, "Tomy_Dog3", 1, false, true, isVaccinated));
//
//        anilmalList.add(new Cat(4, "Lucy_Cat1", 4, false, true, isVaccinated));
//        anilmalList.add(new Cat(5, "Micy_Cat2", 1, false, true, isVaccinated));
//        anilmalList.add(new Cat(6, "Micky_Cat3", 6, false, true, isVaccinated));
//
//        anilmalList.add(new Monkey(7, "Bholu_Monkey1", 1, false, true, isVaccinated));
//        anilmalList.add(new Monkey(8, "Golu_Monkey2", 5, false, true, isVaccinated));
//        anilmalList.add(new Monkey(9, "Bhima_Monkey3", 7, false, true, isVaccinated));
//        anilmalList.add(new Monkey(10, "Molu_Monkey4", 3, false, true, isVaccinated));
//
//
//        this.animalShelter = new AnimalShelter(anilmalList, isVaccinated);
//
//    }
//
//    @Test //checks animals are sorted acc to name or not
//    //Animal Shelter method 2
//
//    public void testSortAnimalsByAnimalNo() {
//        List<Animal> sortedListByAnimalNoResult = animalShelter.sortAnimals();  //Get actual resul of sorted list by Name
//
//        //loop to check all the animals are sorted according to Animal No in ascending order
//
//        for (int i = 0; i < sortedListByAnimalNoResult.size() - 1; i++) {
//            Animal currentAnimal = sortedListByAnimalNoResult.get(i);
//            Animal nextAnimal = sortedListByAnimalNoResult.get(i + 1);
//            int comparisonResult = currentAnimal.getAnimalNumber().compareTo(nextAnimal.getAnimalNumber());
//
//            //Assertion based on compareTo
//            //if first animal's animalNo is less than the next animal's animalNo then compareTo returns -1 otherwise +1 and if both are equal then returns 0
//
//            Assertions.assertTrue(comparisonResult <= 0);
//        }
//    }
//
//    @Test //checks animals are sorted acc to name or not
//    //Animal Shelter method 3
//    public void testSortAnimalsByName() {
//        List<Animal> sortedListByNameResult = animalShelter.sortAnimalsByName();
//        for (int i = 0; i < sortedListByNameResult.size() - 1; i++) {
//            Animal currentAnimal = sortedListByNameResult.get(i);
//            Animal nextAnimal = sortedListByNameResult.get(i + 1);
//
//            int comparisonResult = currentAnimal.getName().compareTo(nextAnimal.getName());
//            Assertions.assertTrue(comparisonResult <= 0);
//        }
//    }
//
//
//    @Test //checks animals are sorted acc to age or not
//    //Animal Shelter method 4
//
//    public void testSortAnimalsByAge() {
//        List<Animal> sortedListByAgeResult = animalShelter.sortAnimalsByAge();  //Get actual resul of sorted list by age
//        for (int i = 0; i < sortedListByAgeResult.size() - 1; i++) //loop to check all the animals are sorted according to age in ascending order
//        {
//            Animal currentAnimal = sortedListByAgeResult.get(i);
//            Animal nextAnimal = sortedListByAgeResult.get(i + 1);
//
//            int comparisonResult = currentAnimal.getAge().compareTo(nextAnimal.getAge());
//            Assertions.assertTrue(comparisonResult <= 0);
//
//        }
//    }
//
//    @Test
//    //method 6 (ifAnimal is in the list)
//    public void testFindAnimal() {
//        int animalNoTOBeFound = 6;
//        //  List<Animal> animals = createInitialAnimalList(); // Implement this metho
//
//        Optional<Animal> foundAnimal = animalShelter.findAnimal(animalNoTOBeFound);
//
//        // Assert: Verify that the existing animal is found
//        Assertions.assertTrue(foundAnimal.isPresent(), "Existing animal should be found");
//        Assertions.assertEquals(animalNoTOBeFound, foundAnimal.get().getAnimalNumber(), "Found animal number should match");
//    }
//
//    @Test
//    //method 6 (ifAnimal is not in the list)
//    public void testAnimalNotFound() {
//        Integer nonExistingAnimalNumber = 999;
//        Optional<Animal> notFoundAnimal = animalShelter.findAnimal(nonExistingAnimalNumber);
//
//        // Assert: Verify that the non-existing animal is not found
//        Assertions.assertFalse(notFoundAnimal.isPresent(), "Non-existing animal should not be found");
//    }
//
//    @Test
//    //method 7 (ifAnimal name is in the list)
//    public void testFindAnimalByName() {
//        String animalNameTOBeFound = "Tomy_Dog3";
//        Optional<Animal> foundAnimal = animalShelter.findAnimal(animalNameTOBeFound);
//
//        // Assert: Verify that the existing animal is found
//        Assertions.assertTrue(foundAnimal.isPresent(), "Existing animal should be found");
//        Assertions.assertEquals(animalNameTOBeFound, foundAnimal.get().getName(), "Found animal number should match");
//    }
//
//    @Test
//    //method 7 (ifAnimal name is not in the list)
//    public void testAnimalNotFoundByName() {
//        String nonExistingAnimalName = "BBB";
//        Optional<Animal> notFoundAnimal = animalShelter.findAnimal(nonExistingAnimalName);
//
//        // Assert: Verify that the non-existing animal is not found
//        Assertions.assertFalse(notFoundAnimal.isPresent(), "Non-existing animal should not be found");
//    }
//
//    @Test
//    //method 8 (ifAnimal no. to be treated is available in the list )
//    public void testTreatAnimal() {
//
//        int animalNoToBeTreated = 3;
//        animalShelter.treatAnimal(animalNoToBeTreated);
//        List<Animal> updatedAnimalList = animalShelter.getAnimals();
//
//        // Assert: Verify that the existing animal by name is treated (hasFoulBreath set to false)
//        Optional<Animal> treatedAnimal = updatedAnimalList.stream()
//                .filter(animal -> animal.getAnimalNumber().equals(animalNoToBeTreated))
//                .findFirst();
//
//        Assertions.assertTrue(treatedAnimal.isPresent(), "Existing animal should be treated");
//        Assertions.assertFalse(((Dog) treatedAnimal.get()).isHasFoulBreath(), "hasFoulBreath should be false for treated animal");
//
//    }
//
//    @Test
//    //method 8 (ifAnimal no. to be treated is not available in the list)
//    public void testTreatAnimalNoNotAvailable() {
//
//        Integer nonExistingAnimalNumber = 999;
//        animalShelter.treatAnimal(nonExistingAnimalNumber);
//        List<Animal> updatedAnimalList = animalShelter.getAnimals();
//
//        // Assert: Verify that the existing animal by name is treated (hasFoulBreath set to false)
//        Optional<Animal> notFoundAnimal = updatedAnimalList.stream()
//                .filter(animal -> animal.getAnimalNumber().equals(nonExistingAnimalNumber))
//                .findFirst();
//
//        // Assert: Verify that the non-existing animal is not found
//        Assertions.assertFalse(notFoundAnimal.isPresent(), "Non-existing animal should not be found");
//    }
//
//
//    @Test
//    //method 9 (if Animal name to be treated is available in the list )
//    public void testTreatAnimalByName() {
//
//        String animalNameToBeTreated = "Micy_Cat2";
//        animalShelter.treatAnimal(animalNameToBeTreated);
//        List<Animal> updatedAnimalList = animalShelter.getAnimals();
//
//        // Assert: Verify that the existing animal by name is treated (hasFoulBreath set to false)
//        Optional<Animal> treatedAnimal = updatedAnimalList.stream()
//                .filter(animal -> animal.getName().equals(animalNameToBeTreated))
//                .findFirst();
//
//        Assertions.assertTrue(treatedAnimal.isPresent(), "Existing animal should be treated");
//        Assertions.assertFalse(((Cat) treatedAnimal.get()).isHasLongNails(), "hasLongNails should be false for treated animal");
//
//    }
//
//    @Test
//    //method 9 (ifAnimal name to be treated is not available in the list)
//    public void testTreatAnimalNameNotAvailable() {
//
//        String nonExistingAnimalName = "CCC";
//        animalShelter.treatAnimal(nonExistingAnimalName);
//        List<Animal> updatedAnimalList = animalShelter.getAnimals();
//
//        // Assert: Verify that the existing animal by name is treated (hasFoulBreath set to false)
//        Optional<Animal> notFoundAnimal = updatedAnimalList.stream()
//                .filter(animal -> animal.getName().equals(nonExistingAnimalName))
//                .findFirst();
//
//        // Assert: Verify that the non-existing animal is not found
//        Assertions.assertFalse(notFoundAnimal.isPresent(), "Non-existing animal should not be found");
//    }
//
//    @Test // checks animals are set to true after cleaning
//    //Method 10
//    public void isCleanStatusUpdateTest() {
//
//        animalShelter.treatAllAnimals();// Call the method that updates the isClean status
//        List<Animal> updatedAnimalList = animalShelter.getAnimals();// Capture the updated list of animals
//        for (Animal updatedAnimal : updatedAnimalList) {
//            Assertions.assertTrue(updatedAnimal.isClean());  // checks in updated Animal list isClean() is set to true.
//        }
//    }
//
//    @Test // to find oldest animal
//    //Method 11
//
//    public void testOldestAnimal() {
//        Animal result = animalShelter.findOldestAnimal();
//
//        Animal expected = new Monkey(9, "Bhima_Monkey3", 7, false, true, isVaccinated);
//        Assertions.assertEquals(expected.getAnimalNumber(), result.getAnimalNumber());
//    }
//
//    @Test  //to count no. of animals
//    //Method 12
//    public void countAnimalTest() {
//        int result = animalShelter.countAnimals();
//
//        int expected = 10;
//        Assertions.assertEquals(expected, result);
//    }
//
//
//    @Test // to add animal in the list
//    //Method 13
//
//    public void testAddAnimal() {
//        Animal animalToBeAdded = new Monkey(112, "Heera_Monkey3", 3, false, true, isVaccinated);
//        String nameOfAddedAnimal = animalToBeAdded.getName();
//
//        animalShelter.addAnimal(animalToBeAdded);
//
//        List<Animal> listAfterAddingAnimal = animalShelter.getAnimals();
//
//        Optional<Animal> addedAnimal = listAfterAddingAnimal.stream()
//                .filter(animal -> animal.getName().equals(nameOfAddedAnimal))
//                .findFirst();
//        Assertions.assertTrue(addedAnimal.isPresent(), "Existing animal should be present");
//    }
//}*/