package activity;

import java.util.Scanner;

public class RunAnimal {
	
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		Bird tweetie = new Bird();
		Cat meowie = new Cat();
		Dog arfie = new Dog();
		
		while(true) {
			System.out.print("Choose an animal. Press 'B' for Bird, 'C' for Cat, 'D' for Dog, or 'X' to exit: ");
			String animalChoice = input.nextLine();
		
		
			while(!answerChecker(animalChoice) || animalChoice.isEmpty()) {
				System.out.print("Invalid input! Please enter a valid input [B, C, D, X]: ");
				animalChoice = input.nextLine();
			}
			if(animalChoice.equalsIgnoreCase("B")) {
				//will print details about bird
				tweetie.eat();
				tweetie.sleep();
				tweetie.makeSound();
			}else if(animalChoice.equalsIgnoreCase("C")) {
				//will print details about cat
				meowie.eat();
				meowie.sleep();
				meowie.makeSound();
			}else if(animalChoice.equalsIgnoreCase("D")) {
				//will print details about dog
				arfie.eat();
				arfie.sleep();
				arfie.makeSound();
			}else if(animalChoice.equalsIgnoreCase("X")) {
				System.out.println("Thankyou for viewing animal details. ꒰ঌ( •ө• )໒꒱ ฅ^•ﻌ•^ฅ  ૮⍝• ᴥ •⍝ა");
				System.exit(0); // will terminate the program
			} 
		}
	}// end of main method
	
	public static boolean answerChecker(String animalChoice) {
		return animalChoice.matches("B|b|C|c|D|d|x|X");
	}// end of answerChecker method
}// end of RunAnimal class


abstract class Animal{
	public abstract void eat();
	public abstract void sleep();
	public abstract void makeSound();
	
}// end of Animal class
class Bird extends Animal{
	public void eat() {
		System.out.println("Birds love to eat seeds and ");
	}
	public void sleep() {
		System.out.println("sleep for 10-12 hours a day.");
	}
	public void makeSound() {
		System.out.println("Tweet tweet ꒰ঌ( •ө• )໒꒱");
	}
}// end of Bird class
class Cat extends Animal{
	public void eat() {
		System.out.println("Cats love to eat seafoods and ");
	}
	public void sleep() {
		System.out.println("sleep for 15 hours a day.");
	}
	public void makeSound() {
		System.out.println("Meoooooooow ฅ^•ﻌ•^ฅ");
	}
}// end of Cat class
class Dog extends Animal{
	public void eat() {
		System.out.println("Dogs love to eat meat and ");
	}
	public void sleep() {
		System.out.println("sleep for 12-14 hours a day.");
	}
	public void makeSound() {
		System.out.println("Arf Arf ૮⍝• ᴥ •⍝ა");
	}
}// end of Dog class
