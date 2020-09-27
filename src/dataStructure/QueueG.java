package dataStructure;

public class QueueG<E> implements IQueue<E>{
	
	private Node<E> front;
	private Node<E> back;
	
	public QueueG() {
		front = null;
		back = null;
	}

	@Override
	public void offer(E newEl) { //agrega de ultimo
		Node<E> newE = new Node<E>(newEl);
		if (back != null) {
			back.setNext(newE);
			newE.setPrev(back);
		} else {
			setFront(newE);
		}
		back = newE;
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (front == null) {
			isEmpty = true;
		}
		return isEmpty;
	}

	@Override
	public E peek() { // coge el primero
		E value = null;
		if (front != null) {
			value = front.getValue();
		}
		return value;
	}

	@Override
	public E poll() { // Elimina y toma el primero
		E value = null;
		if (front != null) {
			value = front.getValue();
			Node<E> second = front.getNext();
			if (second != null) {
				second.setPrev(null);
			}
			front = second;
		}
		return value;
	}

	@Override
	public int size() {
		int size = 0;
		Node<E> current = front;
		while (current != null) {
			size++;
			current = current.getNext();
		}
		return size;
	}
	
	@Override
	public void clear() {
		front = null;
		back = null;
	}
	
	

	/**
	 * @param front the front to set
	 */
	private void setFront(Node<E> front) {
		this.front = front;
	}

	/**
	 * @return the back
	 */
	public Node<E> getBack() {
		return back;
	}

	/**
	 * @param back the back to set
	 */
	@SuppressWarnings("unused")
	private void setback(Node<E> back) {
		this.back = back;
	}	
	
}
