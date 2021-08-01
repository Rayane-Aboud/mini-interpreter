package application;
//dans Let on divise la ligne de commande en 2 partie:


public class Let {
	private Variable variable;							//partie variable
	private Expression expression; 						//partie expression
	public Let(String variable_S,String expression_S) {
		variable=new Variable(variable_S);				
		expression=new Expression(expression_S);
	}
	void checkSyntax() throws Exception {
		
		
		variable.checkSyntax();			//verifier la syntaxe de la variable
		
		expression.checkSyntax();		//verifier la syntaxe de l'expression
		double d; 
		variable.setVariable_D( d=expression.evaluate());//evaluer l'expression et l'affecter a la variable
		//voir si la variable a un nom d'une fonction usuelle ou une commande
		if(Interpreter.isInFctList(variable.getVariable_S())!=-1) throw new VariableException("cannot name a variable after a function");
		//verifier l'existance de cette variable dans la liste principale des variables 
		if(Interpreter.isInVariableList(variable.getVariable_S())==-1 ) {
			//creation d'une nouvelle variable si existante
			Interpreter.ListOfVariable.add(variable);
		}
		else {
			//affectation avec la variable existante
			Interpreter.ListOfVariable.get(Interpreter.isInVariableList(variable.getVariable_S())).setVariable_D(d);
		}

		System.out.println("OK");
	}
}
