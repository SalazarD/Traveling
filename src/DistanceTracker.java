public class DistanceTracker{
	
	int x;
	int y;
	double d;
	
	
	//data used for storing point relationships
	DistanceTracker(int x, int y, double d){
		this.x = x;
		this.y = y;
		this.d = d;
	}
	
	 public double compareTo(DistanceTracker a){
		 return a.d;
		 
		
	 }
	
	
}