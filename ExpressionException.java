package application;
//erreur au niveau d'expression
public class ExpressionException extends Exception {
	ExpressionException(String message){
		System.out.println(message);
	}
}
