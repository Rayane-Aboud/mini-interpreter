package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LigneDeCommande{
	/*ligne qu'on va lire*/
	private String line;
	
	public String getLine() {
		return this.line;
	}
	
	/*buffer of the variable*/
	private String buffer;//this buffer can be expression, variable ..
	private String buffer2;
	
	
	/*Liste de commande*/ 
	private List<String> listeDeCommande;//bon est ce que ne9edrou ndirouha enum
	
	/*Constructeur de la structure*/
	LigneDeCommande(){
		/*initialiser le buffer*/
		this.buffer=new String();
		/*initialiser la liste des commandes*/
		this.listeDeCommande=new ArrayList<String>();
		this.listeDeCommande.add("let");
		this.listeDeCommande.add("print");
	}
	
	
	/*lecture de la ligne*/
	public void read() throws BlockException {
		Scanner in=new Scanner(System.in);
		line=in.nextLine();
		//traitement de quelque cas
		if (line.equals("endBlock"))
			Interpreter.nbBlockOuvert--;
		if (line.equals("endIf")) {
			Interpreter.nbBlockOuvertIf--;
		}
			
		if (line.equals("end"))
			System.exit(0);
		if(Interpreter.nbBlockOuvertIf<0 || Interpreter.nbBlockOuvert<0)
		{
			Interpreter.nbBlockOuvertIf=0;
			Interpreter.nbBlockOuvert=0;
			throw new BlockException("faute de block");
		}
		
	}//io exeptions
	
	

	
	
	public void execute() throws Exception {
		String logicalexpression_S;
		/*lecture du premier mot*/
		this.buffer=new String();
		int i=0;//parcourir la chaine de caracter
		while(i<line.length() && line.charAt(i)!=' ') {
			buffer+=line.charAt(i);
			i++;
			
		}
		
		switch (buffer){
		case "let":
			String variable_S=new String();
			String expression_S=new String();
			/*passer les espaces*/
			while(line.charAt(i)==' ')
				i++;
			/*passer le nom de la variable*/
			while(i<line.length() && line.charAt(i)!='=') {
				variable_S+=line.charAt(i);
				i++;
			}
			i++;
			/*passer l'expression*/
			while(i<line.length() ) {
				if (line.charAt(i)!=' ')
					expression_S+=line.charAt(i);
				i++;
			}
			
			Let let=new Let(variable_S,expression_S);
			//let.evaluate tsemma tkemmel ba9i star ce n'est pas comme les autres
			let.checkSyntax();
			break;
			
			
			
		case "print":
			 expression_S=new String();
			/*passer les espaces*/
			while(line.charAt(i)==' ')
				i++;
			
			/*passer l'expression*/
			while(i<line.length() ) {
				if (line.charAt(i)!=' ')
					expression_S+=line.charAt(i);
				i++;
			}
			
			Print print=new Print(expression_S);//<-----------------------------------------------fait attention a ca
			//let.evaluate tsemma tkemmel ba9i star ce n'est pas comme les autres
			print.checkSyntax();
			break;
		case "while":
			Interpreter.nbBlockOuvert++;
			 logicalexpression_S=new String();
				/*passer les espaces*/
				while(line.charAt(i)==' ')
					i++;
				
				/*passer l'expression*/
				while(i<line.length() ) {
					if (line.charAt(i)!=' ')
						logicalexpression_S+=line.charAt(i);
					i++;
				}
				
				While while1=new While(logicalexpression_S);//<-----------------------------------------------fait attention a ca
				//let.evaluate tsemma tkemmel ba9i star ce n'est pas comme les autres
				while1.whileConstruction();
				while1.execute();
			break;
		
		case "if":
			Interpreter.nbBlockOuvertIf++;
			  logicalexpression_S=new String();
				/*passer les espaces*/
				while(line.charAt(i)==' ')
					i++;
				
				/*passer l'expression*/
				while(i<line.length() ) {
					if (line.charAt(i)!=' ')
						logicalexpression_S+=line.charAt(i);
					i++;
				}
				
				IfElse ifElse=new IfElse(logicalexpression_S);//<-----------------------------------------------fait attention a ca
				//let.evaluate tsemma tkemmel ba9i star ce n'est pas comme les autres
				ifElse.ifElseConstruction();
				ifElse.execute();
			break;
			
		case "else":
			if(Interpreter.nbBlockOuvertIf==0)
				throw new BlockException("you cannot make an else without an if");
			break;
		
		case "endIf":
			if(Interpreter.nbBlockOuvertIf<0)
				throw new BlockException("you cannot make an EndIf without an if");
			break;
		
		case "endBlock":
			if(Interpreter.nbBlockOuvert<0) {
				Interpreter.nbBlockOuvert=0;
				throw new BlockException("you cannot make an else without an if");
			}
				
			break;
		
			default:
				throw new CommandeNotFoundException(this.buffer +" Commande Not Found");
				
		}
		
	}
}