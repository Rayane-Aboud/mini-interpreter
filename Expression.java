package application;

import java.util.ArrayList;
import java.util.List;

public class Expression implements Commande{
	
	private String expression_S;							//expression sous forme chaine de caracter
	private List<Term> listOfTerms;	//liste des termes 
	private String buffer;								//pour parcourir la chaine de caracter
	private List<Character> listOfOperator=new ArrayList<Character>();	//liste des operateur entre les termes
	//getter
	String getExpression_S() {return this.expression_S;}
	//Constructeur
	Expression(String expression_S){
		this.expression_S=expression_S;
	}
	
	
	
	@Override
	public void checkSyntax() throws Exception {//but est de decomposer l'expresssion en liste de termes et signaler des erreurs de syntax qui peuvent etre commise
		listOfTerms=new ArrayList<Term>();
		this.buffer=new String();
		if (this.getExpression_S().length()<=0)
			throw new ExpressionException("empty expression"); 
		//calculer le nombre des '(' et des ')'
		int pile =0;
		for(int i=0;i<this.getExpression_S().length();i++) {
			if (this.getExpression_S().charAt(i)=='(') pile++;
			else if(this.getExpression_S().charAt(i)==')')pile--;
		}
		
		if (pile!=0) throw new ExpressionException("le nombre de '(' et de ')' n'est pas egal");
			
		//-------------------------------------------
		
		//-------------------------------------------
		for(int i=1;i<this.getExpression_S().length();i++) {
			
			if (Interpreter.isOperator(getExpression_S().charAt(i-1))&&Interpreter.isOperator(getExpression_S().charAt(i))){
				if(getExpression_S().charAt(i)!='-')throw new ExpressionException("multiple operator");
			}
			
		}
		//parcour de l'expression
		for(int i=0;i<this.getExpression_S().length();i++) {
			//si on trouve le '(' on saute tous ce qui est entre parenthese
			if(this.getExpression_S().charAt(i)=='(') {
				pile=1;
				this.buffer+=this.getExpression_S().charAt(i);
				while(!(pile==0 && this.getExpression_S().charAt(i)==')')) {
					i++;
					this.buffer+=this.getExpression_S().charAt(i);
					if (this.getExpression_S().charAt(i)=='(') pile++;
					else if(this.getExpression_S().charAt(i)==')')pile--;
					
				}
				
				i++;
			}
			if(i>=this.getExpression_S().length())
				break;
			//on separe les terme en utilisant des + et des -
			if ((this.getExpression_S().charAt(i)=='-' ||this.getExpression_S().charAt(i)=='+')&&!(i-1>=0 && Interpreter.isOperator(this.getExpression_S().charAt(i-1))) ) {
				
				this.listOfOperator.add(this.getExpression_S().charAt(i));
				if (this.buffer.length()!=0) {
					this.listOfTerms.add(new Term(this.buffer));
					this.buffer=new String();
				}
				
			}
			else
				this.buffer+=this.getExpression_S().charAt(i);
		}
		if (this.buffer.length()!=0)
			this.listOfTerms.add(new Term(this.buffer));
	}
	
	
	
	
	//affihcer les listes des termes pour but de programmation
	public void afficher() {
		for (Term t:this.listOfTerms)
			System.out.println(t.getTerm_S());
	}
	
	
	
	
	
	@Override
	public double evaluate() throws Exception {
		double valeur =0;
		
		if (this.listOfOperator.size()>this.listOfTerms.size()) {
			throw new ExpressionException("expression exeption");
		}
		
		for(int i=0,j=(this.listOfOperator.size()==this.listOfTerms.size())?0:-1;i<this.listOfTerms.size()&&j<this.listOfOperator.size();i++,j++)
		{
			this.listOfTerms.get(i).checkSyntax();
			if (j>-1 && j==0) 
			{
				valeur=(this.listOfOperator.get(j)=='-')?(valeur-this.listOfTerms.get(i).evaluate()):valeur+this.listOfTerms.get(i).evaluate();
			}
			else
			{
				valeur=valeur+this.listOfTerms.get(i).evaluate();
			}
		}
			
		return valeur;
	}

	
	
}
