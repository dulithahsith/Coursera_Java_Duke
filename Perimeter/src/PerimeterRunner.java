import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start with prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    public int largestX(Shape s){
    	int largest=-100;
    	for (Point currPt : s.getPoints()) {
    		if (largest<currPt.getX()){
    			largest=currPt.getX();
    		}
    	}
    	return largest;
    }
    public double longest(Shape s){
    	double longest= 0.0;
    	Point prevPt = s.getLastPoint();
    	for (Point currPt : s.getPoints()) {
    		double currDist = prevPt.distance(currPt);
    		if(currDist>longest){
    			longest=currDist;
    		}
    		prevPt = currPt;
    	}
    	return longest;
    	
    }
    public int getNumPoints(Shape s){
    	int count=0;
    	for (Point p:s.getPoints()){
    		count++;
    	}
    	return count;
    }
    public double getAverageLength(Shape s){
    	return getPerimeter(s)/getNumPoints(s);
    }
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("Number of points = "+getNumPoints(s));
        System.out.println("perimeter = " + length);
        System.out.println("Average length = "+getAverageLength(s));
        System.out.println("Longest = "+longest(s));
        System.out.println("Largest X = "+largestX(s));
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
}
