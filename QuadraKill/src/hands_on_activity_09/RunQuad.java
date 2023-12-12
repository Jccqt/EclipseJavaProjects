package hands_on_activity_09;

import java.util.Scanner;

public class RunQuad {
	
	public static void main(String[]args) {
		Square square = new Square();
		Rectangle rectangle = new Rectangle();
		Scanner input = new Scanner(System.in);
		
		System.out.print("Press R for Rectangle or S for Square: ");
		String shape = input.next();
		
		while(!shapeChecker(shape)) {
			System.out.print("Invalid input! Please Press R for Rectangle or S for Square: ");
			shape = input.next();
		}
		
		if(shape.equalsIgnoreCase("R")) {
			System.out.println("A Rectangle:");
			rectangle.showDescription();
		}else if(shape.equalsIgnoreCase("S")) {
			System.out.println("A Square:");
			square.showDescription();
		}
		
	}// end of main method
	
	public static boolean shapeChecker(String shape) {
		return shape.matches("r|R|s|S");
	}// end of shapeChecker method
}// end of RunQuad class

class Quadrilateral{
	public void showDescription() {
		System.out.println("- is quadrilateral");
	}
}// end of Quadrilateral class

class Rectangle extends Quadrilateral{
	public void showDescription() {
		System.out.println("- has 4 right angles");
		super.showDescription(); // will call showDescription method of Quadrilateral
	}
}// end of Rectangle class

class Square extends Rectangle{
	public void showDescription() {
		System.out.println("- has 4 equal sides");
		super.showDescription(); // will call showDescription method of Rectangle
	}
}// end of Square class