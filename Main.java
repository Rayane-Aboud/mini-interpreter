package application;

public class Main {

	public static void main(String[] args) {
		
		
		
		
			while(true) {
				try  {
					LigneDeCommande l=new LigneDeCommande();
					l.read();
					l.execute();
					
					
				}
				catch(Exception e) {
					
				}
			}
		
		
		
		
	
		
	}

}
