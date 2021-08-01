package application;

import java.util.*;

public class Factor implements Commande{
	//attribut principal
	private List<Element> listOfElements;
	private String factor_S;
	private String buffer;
	//Constructeur
	public Factor(String factor_S) {
		this.factor_S=factor_S;
		
	}
	//getter
	String getFactor_S() {
		return this.factor_S;
	}
	//afficher la liste des composant d'un facteur : elements
	public void afficherListElement() {
		for (Element e:listOfElements)
			System.out.println(e.getElement_S());
	}
	
	
	//verifier la syntaxe d'un facteur 
	@Override
	public void checkSyntax() throws Exception {
		listOfElements=new ArrayList<Element>();
		buffer=new String();
		
		int pile;
		for(int i=0;i<this.getFactor_S().length();i++) {
			
				//si on trouve le '(' on saute tous ce qui est entre parenthese
				if(this.getFactor_S().charAt(i)=='(') {
					pile=1;
					this.buffer+=this.getFactor_S().charAt(i);
					while(!(pile==0 && this.getFactor_S().charAt(i)==')')) {
						i++;
						this.buffer+=this.getFactor_S().charAt(i);
						if (this.getFactor_S().charAt(i)=='(') pile++;
						else if(this.getFactor_S().charAt(i)==')')pile--;	
					}
					i++;
				}
				if(i>=this.getFactor_S().length())
					break;
			
			if(this.getFactor_S().charAt(i)!='^') {
				this.buffer+=this.getFactor_S().charAt(i);
				
			}
			else {
				listOfElements.add(new Element(buffer));
				buffer=new String();
			}
				
		}
		listOfElements.add(new Element(buffer));
		
		
		
	}
	/**
	void afficher() {
		for(Element e:this.listOfElements)
			System.out.println(e.getElement_S());
	}*/
	
	
	//evaluer le faceur 
	@Override
	public double evaluate() throws Exception {
		double valeur=0;
		if (this.listOfElements.size()>0) {
			
			this.listOfElements.get(0).checkSyntax();
			valeur=this.listOfElements.get(0).evaluate();
			
		}
		else 
			throw new FactorException();
		for(int i=1;i<this.listOfElements.size();i++)
		{
			this.listOfElements.get(i).checkSyntax();
			valeur=Math.pow(valeur, this.listOfElements.get(i).evaluate());
		}
		
			return valeur;
		
	}

	//une autre maniere d'evaluer 
	/**
	 * @Override
	public double evaluate() throws Exception {
		double valeur=0;
		if (this.listOfElements.size()>0) {
			this.listOfElements.get(0).checkSyntax();
			valeur=this.listOfElements.get(0).evaluate();
			
		}
		else 
			return 0;
		
		for(int i=1;i<this.listOfElements.size();i++)
		{
			this.listOfElements.get(i).checkSyntax();
			this.listOfElements.remove(this.listOfElements.get(0));
			valeur=Math.pow(valeur, this.evaluate());
		}
			return valeur;
		
	}*/
	
	
}
