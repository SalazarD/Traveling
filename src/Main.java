import java.awt.List;
import java.util.ArrayList;
import java.awt.geom.Line2D;




public class Main {

	static Point A = new Point(1,1);
	static Point B = new Point(2,2);
	static Point C = new Point(2,1);

	static ArrayList<Point> points = new ArrayList<Point>();
	static ArrayList<Point> ordered = new ArrayList<Point>();


public static void main(String[] args){
	/*
	 * Need to make a clean way to mass enter points (perhaps taking a textfile?)
	 */

	points.add(A);
	points.add(B);
	points.add(C);
	startup();


// startup is correct and tested
// find first two is correct and tested
// Select Next Point is correct and tested

	findFirstTwo();

	System.out.println("FIRST TWO");
	////////////Testing
	for(int i = 0; i < ordered.size(); i++){
		System.out.println(ordered.get(i).x);
		System.out.println(ordered.get(i).y);

	}
	

	System.out.println("- distances -");
	System.out.println(ordered.get(0).distances.get(0).x + " , " + ordered.get(0).distances.get(0).y);
	System.out.println(ordered.get(1).distances.get(0).x + " , " + ordered.get(1).distances.get(0).y);

	Point temp;
	while(ordered.size() < points.size()){

		temp = selectNextPoint(); //Next point to be added

		System.out.println("NEXT POINT");
		System.out.println(temp.x);
		System.out.println(temp.y);

		addPointToOrdered(temp); //Add point to current graph and trim overwritten lines
		removePoint(temp); //remove temp from all distance tracker lists

	}
	/*
	for(int i = 0; i < ordered.size(); i++){
		System.out.println("i = " + i);
		System.out.println(ordered.get(i).x);
		System.out.println(ordered.get(i).y);

	}
	*/
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

			temp = points.get(i).distances.get(0).d;
			p1 = points.get(i);
			p2 = findPoint(p1.distances.get(0).x , p1.distances.get(0).y);
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


//IT APPEARS REMOVE POINT NEEDS TO BE TESTED FURTHER
//remove a point from all distance trackers
public static void removePoint(Point a){

	//loop through both ordered and points because we aren't sure
	//if changing a point in "points" changes the same point in "ordered"
	for (int i = 0; i < points.size() - 1; i++){
		for(int j = 0; j < points.get(i).distances.size(); j++){
			if(points.get(i).distances.get(j).x == a.x && points.get(i).distances.get(j).y == a.y)
				points.get(i).distances.remove(j);
		}
	}

	for (int i = 0; i < ordered.size() - 1; i++){
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
			p1 = findPoint(ordered.get(i).distances.get(0).x, ordered.get(i).distances.get(0).y);
			temp = ordered.get(i).distances.get(0).d;
		}
	}
	return p1;
}

//add point to ordered and remove the overwritten line
public static void addPointToOrdered(Point add){
	//TODO
	// a is the point from which we are going out
	// add is the point that we are adding to ordered
	//From the two points adjacent to a decide which is closer to add without crossing

	Point a = findPoint(add.distances.get(0).x , add.distances.get(0).y); 


	// find index of a in ordered
	int aI = 0;

	for (int i = 0; i < ordered.size(); i++){
		if(ordered.get(i).x == a.x && ordered.get(i).y == a.y)
			aI = i;
	}
	
	
	
	
	
	// use that to find index of points adjacent to a

	// find the closer one
	//index of closer and other
	//NEED CASE FOR WHEN aI = 0 or aI = last element in list
	int cI, oI = 0;
	Point closer;
	Point other;
	
	if(aI == ordered.size() - 1){
		if(ordered.get(0).distance(ordered.get(aI-1), add) >= ordered.get(0).distance(ordered.get(0), add)){
			closer = ordered.get(0);
			other = ordered.get(aI-1);
			cI = 0;
			oI = aI-1;
		}
		else{
			other = ordered.get(0);
			closer = ordered.get(aI-1);
			oI = 0;
			cI = aI-1;
		}
		
	}
	else{
		if(aI == 0){
			if(ordered.get(0).distance(ordered.get(ordered.size()-1), add) >= ordered.get(0).distance(ordered.get(aI+1), add)){
				closer = ordered.get(aI + 1);
				other = ordered.get(ordered.size()-1);
				cI = aI+1;
				oI = ordered.size()-1;
			}
			else{
				other = ordered.get(aI + 1);
				closer = ordered.get(ordered.size()-1);
				oI = aI+1;
				cI = ordered.size()-1;
			}
	
		}
		else { //not an edge case
		
	
			if(ordered.get(0).distance(ordered.get(aI-1), add) >= ordered.get(0).distance(ordered.get(aI+1), add)){
				closer = ordered.get(aI+1);
				other = ordered.get(aI-1);
				cI = aI+1;
				oI = aI-1;
			}
			else{
				closer = ordered.get(aI-1);
				other = ordered.get(aI+1);
				cI = aI-1;
				oI = aI+1;
			}
		}
	}

	System.out.print("HERE");//TEST 

	
	
	
	
	// check if add -> closer crosses
	// add accordingly in correct direction
	System.out.println("Adding: " + add.x + " ," + add.y);
	if(doTheyCross(add, closer, a, other)){
		
		if(oI < aI){ // other -> add -> a 
			ordered.add(oI, add);
		}
		else{ //a -> add -> other
			ordered.add(aI, add);	
		}
		
		
	}
	
	else{
		if(cI < aI){ //closer -> add -> a
			ordered.add(cI, add);
		}
		else{ // a -> add -> closer
			ordered.add(aI, add);
		}
	}

	
	//For future reference: you may have to check if it crosses all lines not just the adjacent lines
}





// Given three colinear points p, q, r, the function checks if
// point q lies on line segment 'pr'
public static boolean onSegment(Point p, Point q, Point r)
{
    if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
        q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
       return true;

    return false;
}

// To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are colinear
// 1 --> Clockwise
// 2 --> Counterclockwise
public static int orientation(Point p, Point q, Point r)
{
    int val = (q.y - p.y) * (r.x - q.x) -
              (q.x - p.x) * (r.y - q.y);

    if (val == 0) return 0;  // colinear

    return (val > 0)? 1: 2; // clock or counterclock wise
}

// referenc: https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/
public static boolean doTheyCross(Point p1, Point q1, Point p2, Point q2)
{ //p1q1 and p2q2 are line segments
    // Find the four orientations needed for general and
    // special cases
    int o1 = orientation(p1, q1, p2);
    int o2 = orientation(p1, q1, q2);
    int o3 = orientation(p2, q2, p1);
    int o4 = orientation(p2, q2, q1);

    // General case
    if (o1 != o2 && o3 != o4)
        return true;

    // Special Cases
    // p1, q1 and p2 are colinear and p2 lies on segment p1q1
    if (o1 == 0 && onSegment(p1, p2, q1)) return true;

    // p1, q1 and q2 are colinear and q2 lies on segment p1q1
    if (o2 == 0 && onSegment(p1, q2, q1)) return true;

    // p2, q2 and p1 are colinear and p1 lies on segment p2q2
    if (o3 == 0 && onSegment(p2, p1, q2)) return true;

     // p2, q2 and q1 are colinear and q1 lies on segment p2q2
    if (o4 == 0 && onSegment(p2, q1, q2)) return true;

    return false; // Doesn't fall in any of the above cases
}


// // Given three colinear points p, q, r, the function checks if
// // point q lies on line segment 'pr'
// public static boolean onSegment(Point p, Point q, Point r)
// {
//   System.out.println("TEST");
//     if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
//         q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
//        return true;
//
//     return false;
// }


//output results in a nice and easy to read way
public static void outputResults(){
	//TODO
}
}
