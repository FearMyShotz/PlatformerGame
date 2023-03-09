package adventure.util;

import adventure.model.ResourceKey;
public interface Identifiable {
    
    int getId();

    String getName();

    ResourceKey<?> getIdentifier();
}