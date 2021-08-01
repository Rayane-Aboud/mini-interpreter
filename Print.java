package application;

public class Print {
	//print est suivi par une expression
	private Expression expression; 
	
	public Print(String expression_S) {
		expression=new Expression(expression_S);
	}
	void checkSyntax() throws Exception {
		expression.checkSyntax();					//verifier la syntaxe
		System.out.println(expression.evaluate());	//evaluer l'expression et l'afficher
	}
}
