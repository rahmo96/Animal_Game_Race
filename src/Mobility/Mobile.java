package Mobility;

import java.util.Objects;

/**
 * Represents a mobile object with a location and the ability to move.
 * Implements the ILocatable interface.
 */
public abstract class Mobile implements ILocatable {
    private Point location;
    private double totalDistance;

    /**
     * Creates a new Mobile object at the origin (0, 0).
     */
    public Mobile() {
        this.location = new Point(0, 0);
        this.totalDistance = 0.0;
    }

    /**
     * Creates a new Mobile object at the specified location.
     * @param location The initial location of the mobile object.
     */
    public Mobile(Point location) {
        this.setLocation(location); // Ensure proper initialization
        this.totalDistance = 0.0;
    }

    /**
     * Adds a distance to the total distance traveled by the mobile object.
     * @param distance The distance to add.
     */
    public void addTotalDistance(double distance) {
        if (distance >= 0) {
            this.totalDistance += distance;
        } else {
            System.out.println("Error: Distance cannot be negative.");
        }
    }

    /**
     * Calculates the distance between the current location of the mobile object and a given point.
     * @param point The point to calculate the distance to.
     * @return The distance between the current location and the given point.
     */
    public double calcDistance(Point point) {
        return this.location.distance(point);
    }


    /**
     * Gets the current location of the mobile object.
     * @return A copy of the current location of the mobile object.
     */
    public Point getLocation() {
        return new Point(location); // Return a copy to avoid external modification
    }

    /**
     * Sets the location of the mobile object to a new location.
     * @param location The new location to set.
     */

    public boolean setLocation(Point location) {
        if (location != null) {
            this.location = new Point(location); // Create a new Point to avoid reference issues
            return true; // Always indicate success
        } else {
            System.out.println("Error: Location cannot be null.");
            return false;
        }
    }


    /**
     * Determines if this Mobile object is equal to another object.
     * Two Mobile objects are considered equal if they have the same location and total distance.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    /**
     * Determines if this Mobile object is equal to another object.
     * Two Mobile objects are considered equal if they have the same location and total distance.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same object reference
        }
        if (!(obj instanceof Mobile)) { // Type check using instanceof
            return false; // Different type or null
        }
        Mobile other = (Mobile) obj; // Safe cast after type check
        return Objects.equals(location, other.location)
                && Double.compare(totalDistance, other.totalDistance) == 0;
    }
    public int getX(){
        return location.getX();
    }
    public int getY(){
        return location.getY();
    }
    public double getTotalDistance(){
        return totalDistance;
    }

    public double move(Point p1){
        double db1=0;
        db1=this.calcDistance(p1);
        this.addTotalDistance(db1);
        this.location=new Point(p1);
        return  db1;
    }




}
