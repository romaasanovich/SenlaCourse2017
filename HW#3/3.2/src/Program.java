
public class Program {
	public static void main(String[] a) {
		Digit digit= new Digit();
		int number= digit.getNumber();
		System.out.println("Number is: "+ number);
		System.out.println("Sum is : "+ digit.getSum(number));
	}

}
