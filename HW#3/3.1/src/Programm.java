
public class Programm {
	public static void main(String[] s) {
		String a[] = new String[] {
				"i","like","senla","course"
				};

		ArrayWords words = new ArrayWords();
		String result[]=words.procWords(a);
		outputWords(result);
		
	}
	
	static void outputWords(String[] a) {
		for(int i =0;i<a.length;i++){
		 System.out.print(a[i]+" ");	
		}
	}
}
