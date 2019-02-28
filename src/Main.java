import java.awt.List;
import java.util.ArrayList;

public class Main {

	static Point A = new Point(1,1);
	static Point B = new Point(3,3);
	static Point C = new Point(6,6);
	static Point D = new Point(8,8);
	static ArrayList<Point> points = new ArrayList<Point>();
	static ArrayList<Point> ordered = new ArrayList<Point>();
	
	
public static void main(String[] args){
	points.add(A);
	points.add(B);
	points.add(C);
	points.add(D);
	startup();
	for(int i = 0; i < points.size(); i++){
		System.out.println(points.get(i).x);
		System.out.println(points.get(i).y);
		System.out.println(points.get(i).list.get(0).d);
		System.out.println(points.get(i).list.get(1).d);
		System.out.println(points.get(i).list.get(2).d);
	}
	
	findFirstTwo(); 
	Point temp;
	while(ordered.size() < points.size()){
		
		temp = selectNextPoint(); //Next point to be added
		addPointToOrdered(temp); //Add point to current graph and trim overwritten lines
		removePoint(temp); //remove temp from all distance tracker lists
			
	}
	
	outputResults();
	
	
}


//Get the list of points with their distances initialized
public static void startup(){
	//this loops should send a list of points to the fill distance function for every point [i]
	for (int i = 0; i < points.size(); i++){
		
		ArrayList<Point> temp = new ArrayList<Point>();
		
		for (int j = 0; j < points.size(); j++){
			if(points.get(i) == points.get(j)){
				continue;
			}
			else{
				temp.add(points.get(j));
			}	
		}
			points.get(i).fillDistance(temp);
	}
	
	
	
}



//find the shortest distance between two points and add them to ordered list and remove them from the distance trackers
public static void findFirstTwo(){
	//TODO
	//check for smallest number in distance trackers
	DistanceTracker temp = points.get(0).distances.get(0);
	Point p1 = points.get(0);
	Point p2 = findPoint(points.get(0).distances.get(0).x , points.get(0).distances.get(0).y);
	for (int i = 1; i < points.size(); i++){
		if(points.get)
		
		
		
	}
	
	
	
	
	
	
}

public static Point findPoint(int x, int y){
	for (int i = 0; i < points.size(); i++){
		if(points.get(i).x == x && points.get(i).y == y)
			return points.get(i);
	}
	//
	// SHOULD RETURN ERROR BUT FOR NOW JUST SOME POINT
	//
	return points.get(0);
	
	
}

//remove a point from all distance trackers
public static void removePoint(Point a){
	//TODO
}

//from the points in "ordered" return the closest point
public static Point selectNextPoint(){
	return points.get(0);
}

//add point to ordered and remove the overwritten line
public static void addPointToOrdered(Point a){
	//TODO
}

//output results in a nice and easy to read way
public static void outputResults(){
	//TODO
}
}


