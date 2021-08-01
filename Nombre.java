package application;

public class Nombre implements Commande{
	private String nombre_S;//forme chaine 
	private double nombre_D;//valeur affecté a la variable
	
	public String getNombre_S() {
		return this.nombre_S;
	}
	
	public double getNombre_D() {
		return this.nombre_D;
	}
	
	public void setNombre(double nombre_D) {
		this.nombre_D=nombre_D;
	}
	public Nombre(String nombre_S){
		this.nombre_S=nombre_S;
	}
	
	@Override
	public void checkSyntax() throws NumberException  {
		try {
				setNombre(Double.parseDouble(nombre_S));
			}	
		catch(Exception e){
			throw new NumberException(this.getNombre_S()+" contains non-number caracter");
		}
			
		
	}
	@Override
	public double evaluate() {
		return this.getNombre_D();
	}
}
