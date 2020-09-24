package dataStructure;

public class HashTable<K, V> implements IHashTable<K, V>{
	
	public final static int ARRAY_SIZE = 991;
	 
	private NodeH<K, V>[] nodes;
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		nodes = (NodeH<K,V>[])new NodeH[ARRAY_SIZE];
	}
	
	public NodeH<K, V>[] getNodes() {
		return nodes;
	}
	
	public void setNodes(NodeH<K,V>[] nodes) {
		this.nodes = nodes;
	}
	
	public int hashFunction(K key) {
		return (key.hashCode() & 0x7fffffff) % ARRAY_SIZE;
	}//the & 0x7fffffff is a efiecient form for get the absolut value of the hashCode
	
	@Override
	public void insert(K key, V value) {
		int position = hashFunction(key);
		NodeH<K, V> current = nodes[position];
		if (current == null) {
			current = new NodeH<K, V>(key, value);
			nodes[position] = current;
		} else {
			boolean found = false;
			while (current != null && !found) {
				if (current.getKey().equals(key)) {
					current.setValue(value);
					found = true;
				}
				current.getNext();
			}
			if (!found) {
				current = nodes[position];
				NodeH<K, V> newCurrent = new NodeH<K, V>(key, value);
				current.setPrev(newCurrent);
				newCurrent.setNext(current);
				nodes[position] = newCurrent;
			}
		}
	}

	@Override
	public void delete(K key) {
		int position = hashFunction(key);
		NodeH<K, V> current = nodes[position];
		boolean found = false;
		while (current != null && !found) {
			if (current.getKey().equals(key)) {
				found = true;
				if (current.getNext() != null) {
					current.getNext().setPrev(current.getPrev());
				}
				if (current.getPrev() != null) {
					current.getPrev().setNext(current.getNext());
				}
				nodes[position] = current.getNext(); 
			}
			current = current.getNext();
		}
	}

	@Override
	public V search(K key) {
		NodeH<K, V> current = nodes[hashFunction(key)];
		V value = null;
		boolean found = false;
		while (current != null && !found) {
			if (current.getKey().equals(key)) {
				found = true;
				value = current.getValue();
			}
			current = current.getNext();
		}
		return value;
	}

}