package application;

import java.util.ArrayList;
import java.util.List;

public class While {

	private LogicExpression logicalExpression;
	private List<LigneDeCommande> blockInstruction=new ArrayList<LigneDeCommande>();
	boolean endWhile=false;
	While(String logicalExpression_S){
		logicalExpression=new LogicExpression(logicalExpression_S);
	}
	
	public void whileConstruction() throws Exception {
		blockInstruction=new ArrayList<LigneDeCommande>();
		int i=-1;
		while(endWhile==false) {
			
			i++;
			blockInstruction.add(new LigneDeCommande());
			if(i==Integer.MAX_VALUE)
				throw new WhileException("nombre trop grand d'instruction");
			blockInstruction.get(i).read();
			if (Interpreter.nbBlockOuvert==0)
				endWhile=true;
		}
	}
	
	

	
	public void execute() throws Exception {
		long cptLoop=0;
		logicalExpression.checkSyntax();
		blockInstruction.remove(blockInstruction.size()-1);
		while(logicalExpression.evaluate()>0) {
			for(LigneDeCommande l:blockInstruction) {
				
				l.execute();
			}
				
			cptLoop++;
			
			if (cptLoop==Long.MAX_VALUE) throw new WhileException("boucle Infini !!");
			
		}
			
		
	}
}
