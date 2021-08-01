package application;

public class Variable implements Commande {
	private String variable_S;//variable sous forme String aka variable name
	private double variable_D;//variable sous forme de Double aka valeur affecté a la variable
	//getters et setters
	public String getVariable_S() {return this.variable_S;}				
	public double getVariable_D() {return this.variable_D;}			
	public void setVariable_D(double v) {this.variable_D=v;}		
	
	
	//Constructeur 1
	public Variable(String variableName,double value){
		this.variable_S=variableName;
		this.variable_D=value;
	}
	
	//Constructeur 2
	public Variable(String variableName){
		this.variable_S=variableName;
	}
	
	
	
	@Override
	public void checkSyntax() throws VariableException  {
		if(this.getVariable_S().length()>0) {
			//une variable ne doit pas commencer par un caractere numerique 
			if (48<=this.getVariable_S().charAt(0) && this.getVariable_S().charAt(0)<=57 )
				throw new VariableException("variable name starts with a number");
				
			else
			{
				//une variable ne peut pas contenir un caracter special
				for(int i=0;i<this.getVariable_S().length();i++) {
					if(Interpreter.isSpecialCharacter(this.getVariable_S().charAt(i)))
						throw new VariableException("variable name contains a special caracter index: "+ i +" character :"+this.getVariable_S().charAt(i));
				}
					
			}
		}
		else 
			throw new VariableException("no variable name included");
		
		
	}
	@Override
	public double evaluate() throws VariableNotExsitingExeption{
		
		int i;
		//il faut que la variable soit existante pour qu'on l'evalue
		if ((i=Interpreter.isInVariableList(this.variable_S))==-1)throw new VariableNotExsitingExeption("variable non existante");
			else
				this.setVariable_D(Interpreter.ListOfVariable.get(i).getVariable_D());
		
		
		return this.getVariable_D();
	}
}
