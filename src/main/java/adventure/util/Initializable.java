package adventure.util;

@FunctionalInterface
public interface Initializable<I> {

    void initialize(I i);
    
}