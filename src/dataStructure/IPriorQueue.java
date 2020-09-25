package dataStructure;

public interface IPriorQueue<E> {
	
	public E extractMaxheap();
	public void increaseKey(int i, int key); //solo con la i
	public void priorityInsert(int key);
	
}
