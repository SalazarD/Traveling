import java.awt.List;
import java.util.ArrayList;
import java.awt.geom.Line2D;




public class Main {

	static Point A = new Point(1,1);
	static Point B = new Point(3,3);
	static Point C = new Point(6,6);
	static Point D = new Point(8,8);
	static ArrayList<Point> points = new ArrayList<Point>();
	static ArrayList<Point> ordered = new ArrayList<Point>();
	
	
public static void main(String[] args){
	/*
	 * Need to make a clean way to mass enter points (perhsp taking a textfile?
	 */
	
	points.add(A);
	points.add(B);
	points.add(C);
	points.add(D);
	startup();
	
	////////////Testing
	for(int i = 0; i < points.size(); i++){
		System.out.println(points.get(i).x);
		System.out.println(points.get(i).y);
		System.out.println(points.get(i).distances.get(0).d);
		System.out.println(points.get(i).distances.get(1).d);
		System.out.println(points.get(i).distances.get(2).d);
	}
	/////////
	
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
	double temp = points.get(0).distances.get(0).d;
	Point p1 = points.get(0);
	Point p2 = findPoint(points.get(0).distances.get(0).x , points.get(0).distances.get(0).y);
	for (int i = 1; i < points.size(); i++){
		if(points.get(i).distances.get(0).d < temp){
			p1 = points.get(i);
			p2 = findPoint(p1.distances.get(0).x , points.get(0).distances.get(0).y);
		}	
	}
	
	ordered.add(p1);
	ordered.add(p2);
	removePoint(p1);
	removePoint(p2);
}

//return the point with corresponding x and y
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
	
	//loop through both ordered and points because we aren't sure 
	//if changing a point in "points" changes the same point in "ordered"
	for (int i = 0; i < points.size(); i++){
		for(int j = 0; j < points.get(i).distances.size(); j++){
			if(points.get(i).distances.get(j).x == a.x && points.get(i).distances.get(j).y == a.y)
				points.get(i).distances.remove(j);
		}
	}
	
	for (int i = 0; i < ordered.size(); i++){
		for(int j = 0; j < ordered.get(i).distances.size(); j++){
			if(ordered.get(i).distances.get(j).x == a.x && ordered.get(i).distances.get(j).y == a.y)
				ordered.get(i).distances.remove(j);
		}
	}
		
}

//from the points in "ordered" return the closest point
public static Point selectNextPoint(){
	double temp = ordered.get(0).distances.get(0).d;
	Point p1 = findPoint(ordered.get(0).distances.get(0).x , ordered.get(0).distances.get(0).y);
	
	for (int i = 0; i < ordered.size(); i++){
		if(ordered.get(i).distances.get(0).d < temp){
			p1 = ordered.get(i);
			temp = ordered.get(i).distances.get(0).d;
		}	
	}
	return p1;
}

//add point to ordered and remove the overwritten line
public static void addPointToOrdered(Point a){
	//TODO
	// a is the point from which we are going out
	// add is the point that we are adding to ordered
	//From the two points adjacent to a decide which is closer to add without crossing
	
	Point add = findPoint(a.distances.get(0).x , a.distances.get(0).y);
	
	
	// find index of a in ordered
	int aI = 0;
	
	for (int i = 0; i < ordered.size(); i++){
		if(ordered.get(i).x == a.x && ordered.get(i).y == a.y)
			aI = i;
	}
	
	
	// use that to find index of points adjacent to a
	
	// find the closer one
	Point closer;
	Point other;
	if(ordered.get(0).distance(ordered.get(aI-1), add) > ordered.get(0).distance(ordered.get(aI+1), add)){
		closer = ordered.get(aI+1);
		other = ordered.get(aI-1);
	}
	closer = ordered.get(aI-1);
	other = ordered.get(aI+1);
	
	
	// check if add -> closer crosses 
	
	boolean cross;
	
	
	
	// if cross check other and use
	
	
	int index;
	
	// add point accordingly if index is < or > a
	
	//if (index of a > closer)  
	//		ordered.add(indexof(a), add) 
	//else(order.add(indexof(a) + 1, add)     // You may need to check for the case that we are adding to the end of the list
	//
	
	
	
	
	
}

//output results in a nice and easy to read way
public static void outputResults(){
	//TODO
}
}


