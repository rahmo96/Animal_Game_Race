package Animals;

/**
 * An interface defining the behavior of making a sound. Classes that implement this interface
 * must provide a method to produce a sound specific to the type of animal they represent.
 */
public interface Sound {
    /**
     * Returns the sound that the animal makes.
     *
     * @return A string representing the animal's sound.
     */
    String sound();
}

