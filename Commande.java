package application;

/**
 * 
 * une interface qui contient 2 methodes:
 * checkSyntax:qui verifie la maniere dont la ligne est ecrite
 * evaluate : evaluer l'expression apres la verification de la syntaxe correcte elle retourne un double 
 *
 */

public interface Commande {
	public void checkSyntax() throws  Exception;//verifier la syntaxe d'une ligne entré
	public double evaluate() throws Exception;//evaluer la ligne en retournant un double
}
