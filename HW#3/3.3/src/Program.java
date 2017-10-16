
public class Program {

	public static void main(String[] args) {
		Disk disk = new Disk();
		disk.recordingOnDisk();
		System.out.println("Size: "+disk.generalSize+"\nType: "+ disk.generalType );
	}

}
