import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

public class Point{
	
	int x;
	int y;
	ArrayList<DistanceTracker> list = new ArrayList<DistanceTracker>();
	
	Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//@param will be array of distances 
	//Must fill array "distance" with points listed by closest distance
	public void fillDistance(ArrayList<Point> points){
		for(int i = 0; i < points.size(); i++){
			list.add(new DistanceTracker(points.get(i).x, points.get(i).y, distance(this, points.get(i))));
		}
		Collections.sort(list);
	}
	//standard distance function
	public double distance(Point a, Point b){
		
	         
	    double ac = Math.abs(b.y - a.y);
	    double cb = Math.abs(b.x - a.x);
	         
	    return Math.hypot(ac, cb);
	}
	
	
	
	
}