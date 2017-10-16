
public class Digit {
	public int digit;
	public int sum;
	
	public void processing() {
		digit = (new java.util.Random()).nextInt(999);
		String temp= Integer.toString(digit);
		int tempDigit=digit;
		for(int i =0,j=10;i<temp.length();i++)
		{
			
			sum+=tempDigit%(j);
			tempDigit/=10;
		}
	}
	
	
	public void output() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(digit);
		stringBuilder.append(" ");
		stringBuilder.append(sum);
		System.out.println(stringBuilder.toString());
	}
}