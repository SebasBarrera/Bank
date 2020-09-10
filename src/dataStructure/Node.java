package dataStructure;

public class Node<E> {
	
	private Node<E> next;
	private Node<E> prev;
	private E value;
	
	public Node(E value) {
		next = null;
		prev = null;
		this.value = value;
	}

	/**
	 * @return the next
	 */
	public Node<E> getNext() {
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public Node<E> getPrev() {
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}

	/**
	 * @return the value
	 */
	public E getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(E value) {
		this.value = value;
	}
	
	
}
