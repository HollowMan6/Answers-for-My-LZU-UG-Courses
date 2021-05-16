package gomoku;

public class Rankings {
	
	private int round;
	private int step;
	private String result;
	public int getRound() {
		return round;
	}
	public void setRound(int r) {
		if(r<1){
			this.round = 1;
		}
		this.round = r;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int s) {
		this.step = s;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String r) {
		this.result = r;
	}
}
