package application;

public class FactorException extends Exception {
	FactorException(){
		System.out.println("cannot evaluate factor");
	}
}
