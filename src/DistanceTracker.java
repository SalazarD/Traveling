import java.util.Comparator;

public class DistanceTracker implements Comparator<DistanceTracker>{
	
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

	 @Override
		public int compare(DistanceTracker o1, DistanceTracker o2) {
			// TODO Auto-generated method stub
			if(o1.d > o2.d)
				return(1);
			return 0;
		}
		
	
	
}