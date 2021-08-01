package application;
//exception qui s'execute si on entre une ligne qui ne commance pas par let ou print ou un while ou if
public class CommandeNotFoundException extends Exception {
	CommandeNotFoundException(String message){
		System.out.println(message);
	}
}
