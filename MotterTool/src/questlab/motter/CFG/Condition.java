package questlab.motter.CFG;

public class Condition {
   
	private String decstatement;
	public Condition() {
		// TODO Auto-generated constructor stub
		decstatement="";
	}
	public Condition(String decisionStatemnet) {
		// TODO Auto-generated constructor stub
		decstatement=decisionStatemnet;
	}
	 public String getDecstatement() {
			return decstatement;
		}
		public void setDecstatement(String decstatement) {
			this.decstatement = decstatement;
		}
}
