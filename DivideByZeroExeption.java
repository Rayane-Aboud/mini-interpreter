package application;
//une exeption qui est utilisé dans la class term (où il y a des division) pour le risque de diviser par 0 !
public class DivideByZeroExeption extends Exception {
	DivideByZeroExeption(){
		System.out.println("Devide by 0 not allowed");
	}
}
