package application;

import java.util.*;

public class Interpreter {
	
	public static List<Variable> ListOfVariable=new ArrayList<Variable>();//liste des variable entré par l'utilisateur
	public static String operationUsuelle[] ={"sin","cos","abs","sqrt"};//liste des fonction usuelle
	public static String operationBoolean[] ={">=","<=",">","<","==","!="};//listes des operation boolean
	public static char operator[]= {'+','*','-','/','^'};//list des operateur
	public static int nbBlockOuvert=0;//nombre de block ouvert pour le while
	public static int nbBlockOuvertIf=0;//nombre de block ouvert pour le If
	
	/**
	 * parcour du tableau des operateurs 
	 * si trouvé retourner true sinon false
	 */
	public static boolean isOperator(char c) {
		
		for(int i=0;i<Interpreter.operator.length;i++) {
			if (c==Interpreter.operator[i])
				return true;
		}
		return false;
		
	}
	
	
	
	/**
	 * il faut verifier que le caracter n'appartient pas a un intervalle des caracters alphanumérique
	 **/
	public static boolean isSpecialCharacter(char c) {
		return !((48<=c && c<=57) ||(65<=c && c<=90)||(97<=c && c<=122));
	}
	
	
	public static int isInVariableList(String varName) {
		for(int i=0 ;i<Interpreter.ListOfVariable.size();i++)
			if(Interpreter.ListOfVariable.get(i).getVariable_S().equals(varName))
				return i;
		
		return -1;
	}
	//si la fonction entrée existe dans la liste des fonction usuelle si dessu
	public static int isInFctList(String s) {
		for(int i=0;i<Interpreter.operationUsuelle.length;i++) {
			if (s.equals(Interpreter.operationUsuelle[i])) return i;
		}
		return -1;
	}
	//ecrire la list des variable 
	public static void printVariableList(){
		for(Variable p:Interpreter.ListOfVariable)
			System.out.println(p.getVariable_S()+"	"+p.getVariable_D());
	}
	
	
}
