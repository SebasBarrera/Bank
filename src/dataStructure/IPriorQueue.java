package dataStructure;

public interface IPriorQueue<E> {
	
	public E extractMaxheap();
	public void increaseKey(int i, int k);
	public void priorityInsert(int key);
	
}
