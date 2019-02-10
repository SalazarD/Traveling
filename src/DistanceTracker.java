import java.util.Comparator;

public class DistanceTracker implements Comparable<DistanceTracker>{
	
	int x;
	int y;
	double d;
	
	
	//data used for storing point relationships
	DistanceTracker(int x, int y, double d){
		this.x = x;
		this.y = y;
		this.d = d;
	}
	
	 public int compareTo(DistanceTracker a){
		 if(this.d == a.d)
			 return 0;
		 if(this.d > a.d)
			 return 1;
		 return -1;
		 
		
	 }

	
	
}