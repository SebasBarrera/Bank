package dataStructure;

public class Stack<E> implements IStack<E>{
	
	private Node<E> top;
	
	@Override
	public void push(Node<E> newE) {
		if (top != null) {
			newE.setNext(top);
			top.setPrev(newE);
		}
		setTop(newE);
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
	public Node<E> top() {
		return top;
	}

	@Override
	public Node<E> pop() {
		Node<E> first = top;
		if (first != null) {
			Node<E> second = first.getNext();
			if (second != null) {
				second.setPrev(null);
			}
			setTop(second);
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

	/**
	 * @param top the top to set
	 */
	public void setTop(Node<E> top) {
		this.top = top;
	}

}
