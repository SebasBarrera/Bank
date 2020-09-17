package dataStructure;

public interface IQueue<E> {
	
	public void offer(E newE);
	public boolean isEmpty();
	public E peek();
	public E poll();
	public void clear();
	public int size();	
}
