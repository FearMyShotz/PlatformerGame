package me.fearmyshotz.adventure.model;

public class ResourceKey<T extends GameAsset> {

    public static transient final String SEPARATOR = ":";
    public static transient final String DEFAULT_NAMESPACE = "game" + SEPARATOR;

    protected T assetType;

    protected String namespace;
    protected String key;

    public ResourceKey(String key) {
        this(DEFAULT_NAMESPACE, key);
    }

    public ResourceKey(String namespace, String key) {
        this.namespace = namespace;
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

    public T getType() {
        return assetType;
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