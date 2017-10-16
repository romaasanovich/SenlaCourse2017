	
public class Disk {
	public int generalSize=0;
	public Type generalType;
	
	Composition compositions[]= new Composition[] {
			new Composition(),new Composition(),new Composition(),new Composition()
	};
	
	public void recordingOnDisk() {
		System.out.println("Start Recording!!!");
		generalSize = getSize();
		generalType = getType();
		System.out.println("Recording OK!!!");
	}
	
	public int getSize() {
		for(int i=0;i<compositions.length;i++) {
			generalSize+=compositions[i].size;
		}
		return generalSize;
	}
	
		
	public Type getType() {
		int temp[]=new int[Type.values().length];
		for(int i=0;i<Type.values().length;i++)
		{
			temp[i]=0;
		}
		
		for(int i=0;i<compositions.length;i++) {
			if(compositions[i].type==Type.ROCK) {
				temp[Type.ROCK.ordinal()]++;
			}
			else if(compositions[i].type==Type.RAGGY) {
				temp[Type.RAGGY.ordinal()]++;
			}
			else if(compositions[i].type==Type.CLUB) {
				temp[Type.CLUB.ordinal()]++;
			}else if(compositions[i].type==Type.POP) {
				temp[Type.POP.ordinal()]++;
			}
		}
		return Type.values()[maxIndex(temp)];
	}
	
	
	
	public static int maxIndex(int[] arr){
        int temp=0,index=0;
        
	    for(int i = 0; i < arr.length; i++){
	    	
	            if (arr[i] > temp) {
	                temp = arr[i];
	                index=i;
	            }
	   	    }
	    return index;
	}
	
	
		
	
}
