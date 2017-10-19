
public class ArrayWords {
		
	public String [] procWords(String [] a ) {
		char c[];
		
		for(int i =0;i<a.length;i++)
		{
			StringBuilder stringBuilder = new StringBuilder();
			c = a[i].toCharArray();
			c[0]=Character.toUpperCase(c[0]);
			stringBuilder.append(c);
			stringBuilder.append(" ");
			a[i]=stringBuilder.toString();
		}
		return a;
	}
}
