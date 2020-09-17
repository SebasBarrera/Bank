package dataStructure;

public class HashTable<K, V> implements IHashTable<K, V>{
	
	public final static int ARRAY_SIZE = 1000;
	 
	private NodeH<K, V>[] nodes;
	
	public HashTable() {
		nodes = (NodeH<K,V>[])new NodeH[ARRAY_SIZE];
	}
	
	public NodeH<K, V>[] getNodes() {
		return nodes;
	}
	
	public void setNodes(NodeH<K,V>[] nodes) {
		this.nodes = nodes;
	}
	
	public int index(K key) {
		return key.hashCode() % ARRAY_SIZE;
	}
	
	@Override
	public void insert(K key, V value) {
		NodeH<K, V> current = nodes[index(key)];
		if (current == null) {
			current = new NodeH<K, V>(key, value);
			nodes[index(key)] = current;
		} else {
			boolean found = false;
			while (current != null && !found) {
				if (current.getKey().equals(key)) {
					current.setValue(value);
					found = true;
				}
				current.getNext();
			}
			current = nodes[index(key)];
			NodeH<K, V> newCurrent = new NodeH<K, V>(key, value);
			current.setPrev(newCurrent);
			newCurrent.setNext(current);
			nodes[index(key)] = newCurrent;
		}
	}

	@Override
	public void delete(K key) {
		NodeH<K, V> current = nodes[index(key)];
		boolean found = false;
		if (current != null) {
			while (current != null && !found) {
				if (current.getKey().equals(key)) {
					found = true;
					if (current.getNext() != null) {
						current.getNext().setPrev(current.getPrev());
					}
					if (current.getPrev() != null) {
						current.getPrev().setNext(current.getNext());
					}
					nodes[index(key)] = current.getNext(); 
				}
				current = current.getNext();
			}
		}
	}

	@Override
	public V getValue(K key) {
		NodeH<K, V> newKey = nodes[index(key)];
		V value = null;
		boolean found = false;
		while (newKey != null && !found) {
			if (newKey.getKey().equals(key)) {
				found = true;
				value = newKey.getValue();
			}
			newKey = newKey.getNext();
		}
		return value;
	}

	@Override
	public V search(K key) {
		return getValue(key);
	}
	

}
