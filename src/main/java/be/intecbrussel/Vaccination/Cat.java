package be.intecbrussel.Vaccination;

public class Cat extends Animal{
    private boolean hasLongNails;

    public Cat() {
    }

    public Cat(boolean isClean, int age, String name, int animalNumber, boolean hasLongNails) {
        super(isClean, age, name, animalNumber);
        this.hasLongNails = hasLongNails;
    }

    public boolean isHasLongNails() {
        return hasLongNails;
    }

    public void setHasLongNails(boolean hasLongNails) {
        this.hasLongNails = hasLongNails;
    }

    @Override
    public void treatAnimal() {
        super.setClean(true);
        this.setHasLongNails(false);

    }


}
