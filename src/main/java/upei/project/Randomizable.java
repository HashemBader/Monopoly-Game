package upei.project;
/**
 * The {@code Randomizable} interface provides a mechanism for objects that support
 * setting a seed for generating random numbers.
 */
public interface Randomizable {
    /**
     * Sets the seed for generating random numbers.
     *
     * @param seed the seed value to be used for initializing the random number generator.
     */
    void setSeed(long seed);
}
