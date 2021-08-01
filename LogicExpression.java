package application;
/**
 * 
 * l'expression logique est du type:
 * expression1 O expression2
 * tel que O €{>=,<=,>,<,==,!=}
 *
 */
public class LogicExpression implements Commande{
	private String logicExpression;
	private String expression1_S=new String();
	private String expression2_S=new String();
	private Expression e1,e2;
	private int indexInInterpreter=-1;
	LogicExpression(String logicExpression){
		this.logicExpression=logicExpression;
	}
	@Override
	public void checkSyntax() throws Exception {
		int numOfIndex=-1;
		
		for(int i=0;i<Interpreter.operationBoolean.length;i++)
		{
			if ((numOfIndex=this.logicExpression.indexOf(Interpreter.operationBoolean[indexInInterpreter=i]))!=-1)
				break;
		}
		if (numOfIndex==-1) throw new LogicalException("your logical expression is wrong for now");
		
		for (int i=0;i<numOfIndex;i++)
			this.expression1_S+=this.logicExpression.charAt(i);
		
		for(int i=numOfIndex+Interpreter.operationBoolean[indexInInterpreter].length();i<this.logicExpression.length();i++)
			this.expression2_S+=this.logicExpression.charAt(i);
		this.e1=new Expression(this.expression1_S);
		this.e2=new Expression(this.expression2_S);
		
		this.e1.checkSyntax();
		this.e2.checkSyntax();
		
	}
	@Override
	public double evaluate() throws Exception {
		switch(this.indexInInterpreter) {
		case 0:
			if(e1.evaluate()>=e2.evaluate())
				return 1;
			else 
				return -1;
			
		case 1:
			if(e1.evaluate()<=e2.evaluate())
				return 1;
			else 
				return -1;
			
		case 2:
			if(e1.evaluate()>e2.evaluate())
				return 1;
			else 
				return -1;
			
		case 3:
			if(e1.evaluate()<e2.evaluate())
				return 1;
			else 
				return -1;
			
		case 4:
			if(e1.evaluate()==e2.evaluate())
				return 1;
			else 
				return -1;
			
		case 5:
			if(e1.evaluate()!=e2.evaluate())
				return 1;
			else 
				return -1;
			
		default:
			throw new LogicalException("your logical expression is wrong for now");
		}
		
	}
	
	
}
