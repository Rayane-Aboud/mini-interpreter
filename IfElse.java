package application;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * if contient:
 * une expression logique
 * un block pour le If
 * un block pour le else
 * doit finir par un endIf
 *
 */

public class IfElse {
	private LogicExpression logicalExpression;
	private List<LigneDeCommande> blockInstructionIf=new ArrayList<LigneDeCommande>();//liste d'instruction If
	private List<LigneDeCommande> blockInstructionElse=new ArrayList<LigneDeCommande>(); //liste d'instruction Else
	boolean endIf=false;
	boolean endElse=true;
	IfElse(String logicalExpression_S){
		logicalExpression=new LogicExpression(logicalExpression_S);
	}
	
	public void ifElseConstruction() throws Exception {
		blockInstructionIf=new ArrayList<LigneDeCommande>();
		blockInstructionElse=new ArrayList<LigneDeCommande>();
		int i=-1;
		while(endIf==false) {
			i++;
			blockInstructionIf.add(new LigneDeCommande());
			if(i==Integer.MAX_VALUE)
				throw new WhileException("nombre trop grand d'instruction dans un seul block");
			blockInstructionIf.get(i).read();
			if(blockInstructionIf.get(i).getLine().equals("else"))break;
			if (Interpreter.nbBlockOuvertIf==0)
				endIf=true;
		}
		
		 i=-1;
		while(endIf==false) {
			i++;
			blockInstructionElse.add(new LigneDeCommande());
			blockInstructionElse.get(i).read();
			if (Interpreter.nbBlockOuvertIf==0)
				endIf=true;
		}
		//lazem apres if wella else neketbou endif khatra wa7da 
			
	}
	
	
	public void execute() throws Exception {
		long cptLoop=0;
		logicalExpression.checkSyntax();
		/*
		if(this.blockInstructionIf.get(blockInstructionIf.size()-1).getLine()=="endIf")
			this.blockInstructionIf.remove(blockInstructionIf.size()-1);
		else
			this.blockInstructionElse.remove(blockInstructionElse.size()-1);
		*/
		if(logicalExpression.evaluate()>0) {
			for(LigneDeCommande l:blockInstructionIf) {
				
				l.execute();
			}
		}
		else {
			for(LigneDeCommande l:blockInstructionElse) {
				l.execute();
			}
		}
			
		
	}
	
	
}
