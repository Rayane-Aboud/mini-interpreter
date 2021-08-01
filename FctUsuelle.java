package application;

public class FctUsuelle implements Commande{
	
	private String fctName;
	private String fctCtx;
	
	public FctUsuelle(String fctName, String fctCtx) {
		this.fctName=fctName;
		this.fctCtx=fctCtx;
	}

	public String getFctName() {
		return fctName;
	}
	
	@Override
	public void checkSyntax() throws Exception {
		Expression e=new Expression(this.fctCtx);
		int index;
		
		if((index=Interpreter.isInFctList(fctName))==-1)
			throw new FctNameException("La fonction entré n'existe pas !");
		//checkSyntax de l'expression dedans !
		e.checkSyntax();
		
	}
	
	@Override
	public double evaluate() throws Exception {
		// TODO Auto-generated method stub
		String fctName=this.getFctName();
		Expression e=new Expression(this.fctCtx);
		
		e.checkSyntax();
		switch (fctName) {
		case "cos":
			return Math.cos(Math.PI*e.evaluate()/180);
			
		case "sin":
			return Math.sin(Math.PI*e.evaluate()/180);
			
		case "abs":
			return Math.abs(e.evaluate());
			
		case "sqrt":
			return Math.sqrt(e.evaluate());
		}
		
		
		return 0;
	}

	

}
