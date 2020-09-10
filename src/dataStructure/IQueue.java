package dataStructure;

public interface IQueue<E> {
	
	public void offer(Node<E> newE);
	public boolean isEmpty();
	public Node<E> peek();
	public Node<E> poll();
	public void clear();
	public int size();
	
}
