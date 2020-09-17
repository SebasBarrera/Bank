package dataStructure;

public interface IHashTable<K, V> {

	public void insert(K key, V value);
	public void delete(K key);
	public V getValue(K key);
	public V search(K key);
}