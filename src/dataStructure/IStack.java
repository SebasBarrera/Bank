package dataStructure;

public interface IStack<E> {
	
	public void push(Node<E> newE);
	public boolean isEmpty();
	public Node<E> top();
	public Node<E> pop();
	public int size();
	
}
