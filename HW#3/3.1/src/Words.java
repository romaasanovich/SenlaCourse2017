
public class Words {
	
	String a[] = new String[] {
			"i","like","senla","course"
			};
	
	public void procWords() {
		char c[];
		StringBuilder stringBuilder = new StringBuilder();
		for(int i =0;i<a.length;i++)
		{
			c = a[i].toCharArray();
			c[0]=Character.toUpperCase(c[0]);
			stringBuilder.append(c);
			stringBuilder.append(" ");
		}
		System.out.println(stringBuilder.toString());
	}
}
