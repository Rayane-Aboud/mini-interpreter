package application;

public class Element implements Commande{
	//static int i=0;
	private String element_S;//forme de chaine de caracter
	private String buffer;//buffer pour recuperer le nom de la fonction (avant les '(') ou le nom d'une variable ou une expression ou un nombre
	private String ctxOfFct;//recuperer le context d'une fonction si elle existe
	private int nature;//nombre, expression,fonction usuelle ou variable
	
	
	
	Element(String s){				
		this.element_S=s;
		
	}
	
	public String getElement_S() {	
		return this.element_S;
	}
	
	@Override
	public void checkSyntax() throws Exception {//redefinition de la methode checkSyntax() implémenté par l'interface Commande
		int pile=0;
		
		//systeme de verification par nombre de parenthèse
		
		for(int i=0;i<getElement_S().length();i++)
			if(getElement_S().charAt(i)=='(')
				pile++;
			else if(getElement_S().charAt(i)==')')
				pile--;
		if (pile!=0)
			throw new ElementException(pile);
		
		
		//------------------PART 2-----------------------
		
		this.nature=this.checkNature();//voir la nature de cet element :nombre,expression,fctUsuelle ou variable
		
		switch (this.nature) {
		case 1://si l'expression est un nombre
			Nombre n=new Nombre(this.buffer);
			n.checkSyntax();
			break;
		case 2://si l'expression commence par une parenthese
			Expression e=new Expression(this.buffer);
			e.checkSyntax();
			break;
		case 3://si l'expression est une fonction usuelle
			FctUsuelle f=new FctUsuelle(this.buffer,this.ctxOfFct);
			f.checkSyntax();
			break;
		case 4://si l'expression est une variable
			Variable v=new Variable(this.buffer);
			v.checkSyntax();
			break;
		}
		
	}
	
	
	public int checkNature() throws ElementException{
		this.buffer=new String();
		/**si l'expression commence par un chiffre alors il est considéré comme un nombre */
		if((48<=getElement_S().charAt(0) && this.getElement_S().charAt(0)<=57) ||(this.getElement_S().length()>1 && 48<=getElement_S().charAt(1) && this.getElement_S().charAt(1)<=57) &&((this.getElement_S().charAt(0)=='-'||this.getElement_S().charAt(0)=='+')&&(this.getElement_S().charAt(0)!='('))) {
			
			for(int i=0;i<getElement_S().length();i++)
				this.buffer+=getElement_S().charAt(i);
			
			
			return 1;//nombre
		}
		/**si l'expression commence par une parenthese alors le contenue est considéré comme une expresion*/
		else if(getElement_S().charAt(0)=='(' ||((this.getElement_S().charAt(0)=='-'||this.getElement_S().charAt(0)=='+')&&(this.getElement_S().charAt(0)=='(')))
		{
			if(getElement_S().charAt(0)=='(') {
				for(int i=1;i<getElement_S().length()-1;i++)
					this.buffer+=getElement_S().charAt(i);
			}
			else
				for(int i=0;i<getElement_S().length();i++)
					this.buffer+=getElement_S().charAt(i);
			
			return 2;//expression
		}
		/**si l'expression commence par un caracter alphabetique il est soit une variable soit une fonction*/
		else if (!Interpreter.isSpecialCharacter(this.getElement_S().charAt(0))||((this.getElement_S().charAt(0)=='-'||this.getElement_S().charAt(0)=='+')&& !Interpreter.isSpecialCharacter(this.getElement_S().charAt(1)))){
			this.ctxOfFct=new String();
			int pile;
			for(int i=0 ;i<getElement_S().length();i++) {
				if (this.getElement_S().charAt(i)=='(')//si l'expression contient une parenthese ouvrante c une fonction
				{
					
					pile=1;
					this.ctxOfFct+=this.getElement_S().charAt(i);
					while(!(pile==0 && this.getElement_S().charAt(i)==')')) {
						i++;
						this.ctxOfFct+=this.getElement_S().charAt(i);
						if (this.getElement_S().charAt(i)=='(') pile++;
						else if(this.getElement_S().charAt(i)==')')pile--;
						
					}
					
					i++;
				}
				if(i>=this.getElement_S().length())
					return 3;//expression
				
				this.buffer+=getElement_S().charAt(i);
				}
				
			}
				
			return 4;//sinon c une variable
			
				
		}
		
				
		


	@Override
	public double evaluate() throws Exception {
		
		if (this.getElement_S().length()==0)
			 throw new VariableNotExsitingExeption("your Element buffer is empty");

		
		
		switch (this.nature) {
		case 1:	
			Nombre n=new Nombre(this.buffer);
			n.checkSyntax();
	
			return n.evaluate();
			
		case 2:
			Expression e=new Expression(this.buffer);
			e.checkSyntax();
	
			return e.evaluate();
			
		case 3:
			FctUsuelle f=new FctUsuelle(this.buffer,this.ctxOfFct);
			f.checkSyntax();
			return f.evaluate();
			
		case 4:
			
			Variable v=new Variable(this.buffer);
			v.checkSyntax();
			return v.evaluate();
		default:
			throw new ElementException("Cannot evaluate the element");
		
	}
}
}
