package dataStructure;

public interface IHeap<E> {
	
	public void buildMaxHeap();
	public void buildMinHeap(int i);
	public void maxHeapify(int i);
	public void minHeapify(int i);
	public void heapSort();
	public int right(int i);
	public int left(int i);	
	public int parent(int i);
}
