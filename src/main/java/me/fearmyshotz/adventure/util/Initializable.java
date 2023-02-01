package me.fearmyshotz.adventure.util;

@FunctionalInterface
public interface Initializable<I> {

    void initialize(I i);
    
}