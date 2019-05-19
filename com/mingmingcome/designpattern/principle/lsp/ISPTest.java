package com.mingmingcome.designpattern.principle.lsp;

public class ISPTest {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal mouse = new Mouse();
        iLoveAnimal(dog);
        iLoveAnimal(mouse);
//        iLoveDog(dog);
//        iLoveDog(mouse);
    }

    public static void iLoveAnimal(Animal animal) {
        System.out.println(animal.getName());
    }

    public static void iLoveDog(Dog dog) {
        System.out.println(dog.getName());
    }

}
