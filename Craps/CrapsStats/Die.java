public class Die {
	private int Die1;
	private int Die2;
	private int numDots;
	public static void main(String[] args){
		Die die = new Die();
		die.roll();
		System.out.println(die.getNumDots());
		die.roll();
		System.out.println(die.getNumDots());
		}
	public void roll(){
		Die1 = (int)(1 + (Math.random() * 6));
		//Die2 = (int)(1 + (Math.random() * 6));
		numDots = Die1;
	}
	public int getNumDots() {
		return numDots;
	}
}