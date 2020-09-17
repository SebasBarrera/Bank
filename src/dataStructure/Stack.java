package dataStructure;

public class Stack<E> implements IStack<E>{
	
	private Node<E> top;
	
	@Override
	public void push(E newE) {
		Node<E> newEl = new Node<E>(newE);
		if (top != null) {
			newEl.setNext(top);
			top.setPrev(newEl);
		}
		setTop(newEl);
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (top == null) {
			isEmpty = true;
		}
		return isEmpty;
	}

	/**
	 * @return the top value
	 */
	@Override
	public E top() {
		E value = null;
		if (top != null) {
			value = top.getValue();
		}
		return value;
	}

	@Override
	public E pop() {
		E value = null;
		if (top != null) {
			value = top.getValue();
			Node<E> second = top.getNext();
			if (second != null) {
				second.setPrev(null);
			}
			setTop(second);
		}
		return value;
	}

	@Override
	public int size() {
		int size = 0;
		Node<E> current = top;
		while (current != null) {
			size++;
			current = current.getNext();
		}
		return size;
	}

	/**
	 * @param top the top to set
	 */
	private void setTop(Node<E> top) {
		this.top = top;
	}

	@Override
	public void clear() {
		top = null;
		
	}

}
