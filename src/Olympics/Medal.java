package Olympics;

import java.util.Objects;

/**
 * Represents an Olympic medal, including its type (gold, silver, bronze), the tournament
 * it was awarded in, and the year it was won.
 */
public class Medal {

    public enum type { BRONZE, SILVER, GOLD }

    private type t;
    private String tournament;
    private int year;

    /**
     * Creates a default Medal object with no specific details.
     */
    public Medal() {}

    /**
     * Creates a Medal object with the specified type, tournament, and year.
     * @param t The type of medal.
     * @param tournament The name of the tournament.
     * @param year The year the medal was won.
     */
    public Medal(type t, String tournament, int year) {
        this.t = t;
        this.tournament = new String(tournament); // Create a new string to avoid reference issues
        this.year = year;
    }

    /**
     * Returns a string representation of the medal information.
     * @return A formatted string with medal type, tournament name, and year.
     */
    @Override
    public String toString() {
        return "Medal" +
                " Type =" + t +
                "\nTournament ='" + tournament + '\'' +
                "\nYear=" + year +
                "\n----\n";
    }

    /**
     * Determines if this Medal object is equal to another object.
     * Two Medal objects are considered equal if they have the same type, tournament name, and year.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same object reference
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Null or different class
        }
        Medal other = (Medal) obj;
        return t == other.t &&
                Objects.equals(tournament, other.tournament) &&
                year == other.year;

    }

}
