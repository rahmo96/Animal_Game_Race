package Mobility;

/**
 * Represents an object that has a location in a 2D coordinate system.
 */
public interface ILocatable {

    /**
     * Gets the current location of the object.
     *
     * @return The current location as a Point object.
     */
    Point getLocation();

    /**
     * Sets the location of the object to a new point.
     *
     * @param p The new location to set.
     * @return true if the location was successfully set, false otherwise.
     */
     boolean setLocation(Point p);
}
