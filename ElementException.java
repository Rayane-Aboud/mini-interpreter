package application;

//il y a trois types dans cet expressions
public class ElementException extends Exception{
	ElementException(int i){//expression en ce qui concerne les parenthese
		if(i>0)
			System.out.println("there are more '(' than ')' ");
		else
			System.out.println("there are more  ')' than '(' ");
			
	}
	ElementException(){//expression en ce qui concerne un element inconnue
		System.out.println("Unkown element");
	}
	public ElementException(String message) {
		System.out.println(message);
	}
}
