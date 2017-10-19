
public class Digit {
	
		public int getNumber() {
		int number = (new java.util.Random()).nextInt(999);
		return number;
		}
		
		public int getSum(int number) {
		int tempDigit=number;
		int a=0;
		for(int i =0,j=10;i<3;i++)
		{
			
			a+=tempDigit%(j);
			tempDigit/=10;
		}
		return a;
	}
	
	
	
}