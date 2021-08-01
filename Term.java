package application;

import java.util.*;

public class Term implements Commande{
	
	private String term_S;
	private List<Factor> listOfFactors;
	private List<Character> listOfOperators;
	
	private String buffer;
	Term(String term_S){
		this.term_S=term_S;
		
	}
	public String getTerm_S() {
		return this.term_S;
	}
	@Override
	public void checkSyntax() throws Exception {
			listOfFactors=new ArrayList<Factor>();
			listOfOperators=new ArrayList<Character>();
			int pile;
			buffer=new String();
			for(int i=0;i<this.getTerm_S().length();i++) {
				
				
				//si on trouve le '(' on saute tous ce qui est entre parenthese
				if(this.getTerm_S().charAt(i)=='(') {
					pile=1;
					this.buffer+=this.getTerm_S().charAt(i);
					while(!(pile==0 && this.getTerm_S().charAt(i)==')')) {
						i++;
						this.buffer+=this.getTerm_S().charAt(i);
						if (this.getTerm_S().charAt(i)=='(') pile++;
						else if(this.getTerm_S().charAt(i)==')')pile--;
						
					}
					
					i++;
				}
				if(i>=this.getTerm_S().length())
					break;
				
				if (this.getTerm_S().charAt(i)=='/' ||this.getTerm_S().charAt(i)=='*') {
					this.listOfOperators.add(this.getTerm_S().charAt(i));
					
					if (this.buffer.length()!=0) {
						this.listOfFactors.add(new Factor(this.buffer));
						this.buffer=new String();
					}
					
				}
				else
					this.buffer+=this.getTerm_S().charAt(i);
				
			}
			
			if(this.buffer.length()!=0) {
				this.listOfFactors.add(new Factor(this.buffer));
			}
		
		
	}
	
	
	public void afficher() {
		for(Factor f:this.listOfFactors) {
			System.out.println(f.getFactor_S());
		}
	}
	
	
	@Override
	public double evaluate() throws Exception {
		
		this.listOfFactors.get(0).checkSyntax();
		//System.out.println(this.listOfFactors.get(0).evaluate()+"\n\n\n");
		double valeur=this.listOfFactors.get(0).evaluate();
		
		for(int i=1;i<this.listOfFactors.size();i++) {
			
			this.listOfFactors.get(i).checkSyntax();
			
			if(this.listOfOperators.size()>0 && this.listOfOperators.get(i-1)=='*')
			{
				valeur=valeur*this.listOfFactors.get(i).evaluate();
			}
			else
			{
				if(this.listOfFactors.get(i).evaluate()==0)
					throw new DivideByZeroExeption();
				else
					valeur=valeur/this.listOfFactors.get(i).evaluate();
			}
		}
		
		
			return valeur;
		
	
	}

	
	
}
