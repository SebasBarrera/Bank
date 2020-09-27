package dataStructure;

import customExceptions.HeapUnderFlowException;
import customExceptions.SmallerKeyException;
import customExceptions.BiggerKeyException;

public interface IPriorQueue<E> {
	
	public E extractMaxheap() throws HeapUnderFlowException;
	public void increaseKey(int i, int key) throws SmallerKeyException; 
	public void priorityInsert(int key, E element) throws SmallerKeyException;
	void decreaseKey(int i, int key) throws BiggerKeyException;
	void reSize();
	
}
