package dataStructure;

public class NodeH<K, V> {
	
	private NodeH<K, V> next;
	private NodeH<K, V> prev;
	private V value;
	private K key;
	
	public NodeH(K key, V value) {
		this.key = key;
		this.value = value;
		this.next = null;
		this.prev = null;
	}

	/**
	 * @return the next
	 */
	public NodeH<K, V> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(NodeH<K, V> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public NodeH<K, V> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(NodeH<K, V> prev) {
		this.prev = prev;
	}

	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	
}
