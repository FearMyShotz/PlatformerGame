package platformer.util;

import platformer.model.ResourceKey;
public interface Identifiable {
    
    int getId();

    String getName();

    ResourceKey<?> getIdentifier();
}