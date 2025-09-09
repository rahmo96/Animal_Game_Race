package Mobility;

/**
 * Represents a point in a 2D coordinate system with double precision x and y coordinates.
 */
public class Point {
    private int x; // The x-coordinate of the point
    private int y; // The y-coordinate of the point

    /**
     * Creates a new Point object initialized to (0, 0).
     */
    public Point() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Creates a new Point object with the specified x and y coordinates.
     * @param x The x-coordinate of the point.
     * @param y The y-coordinate of the point.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Point object as a copy of an existing Point object.
     * @param p The Point object to copy.
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Gets the x-coordinate of the point.
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of the point.
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }


    /**
     * Calculates the Euclidean distance between this Point and another Point.
     * @param other The other Point to calculate the distance to.
     * @return The Euclidean distance between the two points.
     */
    public double distance(Point other) {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    /**
     * Determines if this Point object is equal to another object.
     * Two Point objects are considered equal if they have the same x and y coordinates.
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Point) {
            return x == ((Point) obj).x && y == ((Point) obj).y;
        }
        return false;
    }

    /**
     * Returns a string representation of the point in the format "(x, y)".
     * @return The string representation of the point.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
