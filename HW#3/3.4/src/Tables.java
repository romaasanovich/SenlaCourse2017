
public class Tables {
	Table tables[]= new Table[10];
	
	Tables() {
		for(int i=0;i<10;i++) {
			tables[i]=new Table(i);
		}
	}
}
