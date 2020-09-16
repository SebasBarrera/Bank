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
	 * @return the top
	 */
	@Override
	public E top() {
		return top.getValue();
	}

	@Override
	public E pop() { //
		Node<E> first = top;
		if (first != null) {
			Node<E> second = first.getNext();
			if (second != null) {
				second.setPrev(null);
			}
			setTop(second);
		}
		return first.getValue();
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
