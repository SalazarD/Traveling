import java.awt.List;
import java.util.ArrayList;

public class Main {

	static ArrayList<Point> points = new ArrayList<Point>();
	static ArrayList<Point> ordered = new ArrayList<Point>();
	
public static void main(String[] args){
	startup();
	System.out.println(points);
	
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


