package dataStructure;

public class Queue<E> implements IQueue<E>{
	
	private Node<E> top;
	private Node<E> bottom;
	
	public Queue() {
		setTop(null);
		bottom = null;
	}

	@Override
	public void offer(Node<E> newE) {
		if (bottom != null) {
			bottom.setNext(newE);
			newE.setPrev(bottom);
		} else {
			setTop(newE);
		}
		bottom = newE;
	}

	@Override
	public boolean isEmpty() {
		boolean isEmpty = false;
		if (top == null) {
			isEmpty = true;
		}
		return isEmpty;
	}

	@Override
	public Node<E> peek() {
		return top;
	}

	@Override
	public Node<E> poll() {
		Node<E> first = top;
		if (first != null) {
			Node<E> second = top.getNext();
			if (second != null) {
				second.setPrev(null);
			}
			top = second;
		}
		return first;
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
	
	@Override
	public void clear() {
		setTop(null);
		setBottom(null);
	}
	
	/**
	 * @return the top
	 */
	public Node<E> getTop() {
		return top;
	}

	/**
	 * @param top the top to set
	 */
	public void setTop(Node<E> top) {
		this.top = top;
	}

	/**
	 * @return the bottom
	 */
	public Node<E> getBottom() {
		return bottom;
	}

	/**
	 * @param bottom the bottom to set
	 */
	public void setBottom(Node<E> bottom) {
		this.bottom = bottom;
	}	
	
}
