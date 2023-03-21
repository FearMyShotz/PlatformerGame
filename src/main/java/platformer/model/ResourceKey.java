package platformer.model;

import platformer.util.Identifiable;

/**
 * Ein ResourceKey ist ein Schlüssel, der ein Asset identifiziert. Er kann bei einem {@link platformer.util.Registry} als Schlüssel verwendet werden.
 * 
 * @param AssetType der Typ des Assets, das durch diesen Schlüssel identifiziert wird
 * @author Jamil B.
 */
@SuppressWarnings("hiding")
public class ResourceKey<AssetType extends GameAsset> implements Comparable<ResourceKey<AssetType>>, Identifiable {

    /**
     * Der Trenner zwischen Namespace und Schlüssel.
     */
    public static transient final String SEPARATOR = ":";

    /**
     * Der Standard-Namespace.
     */
    public static transient final String DEFAULT_NAMESPACE = "game" + SEPARATOR;

    /**
     * Der generische Typ des Assets, das durch diesen Schlüssel identifiziert wird.
     */
    protected AssetType assetType;

    /**
     * Der Namespace, unter dem das Asset registriert ist.
     */
    protected String namespace;

    /**
     * Der Schlüssel, unter dem das Asset registriert ist.
     */
    protected String key;

    /**
     * Erstellt einen neuen ResourceKey mit dem Standard-Namespace {@link #DEFAULT_NAMESPACE}.
     * 
     * @param key der Schlüssel
     */
    public ResourceKey(String key) {
        this(DEFAULT_NAMESPACE, key);
    }

    /**
     * Erstellt einen neuen ResourceKey mit namespace.
     * 
     * @param namespace der Namespace
     * @param key der Schlüssel
     */
    public ResourceKey(String namespace, String key) {
        this.namespace = namespace.equals(DEFAULT_NAMESPACE) ? "" : namespace;
        this.key = key;
    }

    /**
     * Setzt den Namespace.
     * 
     * @param namespace der Namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Gibt den Namespace zurück.
     * 
     * @return der Namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Setzt den Schlüssel.
     * 
     * @param key der Schlüssel
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gibt den Schlüssel zurück.
     * 
     * @return der Schlüssel
     */
    public String getKey() {
        return key;
    }

    /**
     * Gibt den vollständigen Schlüssel zurück.
     * 
     * @return der vollständige Schlüssel
     */
    public String getAccessor() {
        return namespace + SEPARATOR + key;
    }

    /**
     * Gibt den generischen Typ des Assets zurück.
     * 
     * @return der generische Typ des Assets
     */
    public AssetType getType() {
        return assetType;
    }

    /**
     * Gibt die ID des Schlüssels zurück.
     * 
     * @return die ID des Schlüssels (-1)
     */
    @Override
    public int getId() {
        return -1;
    }

    /**
     * Gibt den Namen des Schlüssels zurück.
     * 
     * @return der Name des Schlüssels (der vollständige Schlüssel)
     */
    @Override
    public String getName() {
        return getAccessor();
    }

    /**
     * Gibt den Schlüssel zurück.
     * 
     * @return dieser Schlüssel
     */
    @Override
    public ResourceKey<?> getIdentifier() {
        return this;
    }

    /**
     * Gibt diesen Schlüssel als String-Repräsentation zurück.
     */
    @Override
    public String toString() {
        return "ResourceKey["
            + "namespace=" + namespace
            + ", key=" + key + "]";
    }

    /**
     * Vergleicht diesen Schlüssel mit einem anderen Schlüssel.
     * 
     * @param resourceKey der andere Schlüssel
     * @return -1, wenn dieser Schlüssel kleiner ist, 0, wenn beide Schlüssel gleich sind, 1, wenn dieser Schlüssel größer ist
     */
    @Override
    public int compareTo(ResourceKey<AssetType> resourceKey) {
        return getAccessor().compareTo(resourceKey.getAccessor());
    }

    /**
     * Der Builder für ResourceKeys.
     * 
     * @author Jamil B.
     * @param <AssetType> der Typ des Assets, das durch diesen Schlüssel identifiziert wird
     */
    public static class Builder<AssetType extends GameAsset> {

        /**
         * Der Schlüssel, der durch diesen Builder erstellt wird.
         */
        private ResourceKey<AssetType> key;

        /**
         * Erstellt einen neuen Builder mit dem Standard-Namespace {@link #DEFAULT_NAMESPACE}.
         * 
         * @param key der Schlüssel
         */
        public Builder(String key) {
            this.key = new ResourceKey<AssetType>(key);
        }

        /**
         * Erstellt einen neuen Builder mit namespace.
         * 
         * @param namespace der Namespace
         * @param key der Schlüssel
         */
        public Builder(String namespace, String key) {
            this.key = new ResourceKey<AssetType>(namespace, key);
        }

        /**
         * Setzt den Namespace.
         * 
         * @param namespace der Namespace
         * @return dieser Builder
         */
        public Builder<AssetType> setNamespace(String namespace) {
            this.key.setNamespace(namespace);
            return this;
        }

        /**
         * Setzt den Schlüssel.
         * 
         * @param key der Schlüssel
         * @return dieser Builder
         */
        public Builder<AssetType> setKey(String key) {
            this.key.setKey(key);
            return this;
        }

        /**
         * Gibt den Schlüssel zurück.
         * 
         * @return der Schlüssel
         */
        public ResourceKey<AssetType> build() {
            return key;
        }
    }
}