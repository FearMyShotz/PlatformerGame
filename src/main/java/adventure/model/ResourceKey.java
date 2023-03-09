package adventure.model;

import adventure.util.Identifiable;

@SuppressWarnings("hiding")
public class ResourceKey<AssetType extends GameAsset> implements Comparable<ResourceKey<AssetType>>, Identifiable {

    public static transient final String SEPARATOR = ":";
    public static transient final String DEFAULT_NAMESPACE = "game" + SEPARATOR;

    protected AssetType assetType;

    protected String namespace;
    protected String key;

    public ResourceKey(String key) {
        this(DEFAULT_NAMESPACE, key);
    }

    public ResourceKey(String namespace, String key) {
        this.namespace = namespace.equals(DEFAULT_NAMESPACE) ? "" : namespace;
        this.key = key;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getAccessor() {
        return namespace + SEPARATOR + key;
    }

    public AssetType getType() {
        return assetType;
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getName() {
        return getAccessor();
    }

    @Override
    public ResourceKey<?> getIdentifier() {
        return this;
    }

    @Override
    public String toString() {
        return "ResourceKey["
            + "namespace=" + namespace
            + ", key=" + key + "]";
    }

    @Override
    public int compareTo(ResourceKey<AssetType> resourceKey) {
        return getAccessor().compareTo(resourceKey.getAccessor());
    }

    public static class Builder<T extends GameAsset> {

        private ResourceKey<T> key;

        public Builder(String key) {
            this.key = new ResourceKey<T>(key);
        }

        public Builder(String namespace, String key) {
            this.key = new ResourceKey<T>(namespace, key);
        }

        public Builder<T> setNamespace(String namespace) {
            this.key.setNamespace(namespace);
            return this;
        }

        public Builder<T> setKey(String key) {
            this.key.setKey(key);
            return this;
        }

        public ResourceKey<T> build() {
            return key;
        }
    }
}