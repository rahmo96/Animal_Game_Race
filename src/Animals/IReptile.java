package Animals;

import Mobility.Mobile;

/**
 * This interface defines behaviors specific to reptile animals, extending the Mobile class.
 * It includes a constant for the maximum speed of reptiles and a method to speed up.
 */
public interface IReptile {
    public static final int MAX_SPEED = 5;

    /**
     * Attempts to increase the reptile's speed to the given value,
     * up to the maximum allowed speed defined in MAX_SPEED.
     *
     * @param new_speed The new speed to set (must be non-negative and not exceed MAX_SPEED).
     * @return true if the speed was successfully updated, false otherwise.
     */
    boolean speedUp(double new_speed);
}
