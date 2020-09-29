package dataStructure;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customExceptions.BiggerKeyException;
import customExceptions.HeapUnderFlowException;
import customExceptions.SmallerKeyException;

class HeapTest {

	private Heap<Integer> heap;
	
	private void setupStage0() {
		heap = new Heap<>(0,0);
	}
	
	private void setupStage1() {
		heap = new Heap<>(3,3);
		Integer[] testValues = {1 ,2 ,3};
		int[] keysValues   =   {6, 0, 2};
 		heap.setElements(testValues);
 		heap.setKeys(keysValues);
	}

	private void setupStage2() {
		heap = new Heap<>(10,10);
		Integer[] testValues = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
		int[] keysValues   =   {4, 3, 0, 2,  0, 0,  1,  0, 1, 5};
		heap.setElements(testValues);
		heap.setKeys(keysValues);
	}
	
	@Test
	void testHeapSort0() {
		setupStage0();
		heap.heapSort();
		assertEquals(0, heap.getArraysize());
		assertEquals(0, heap.getHeapSize());
	}
	
	@Test
	void testHeapSort1( ) {
		setupStage1();
		int[] testValues = {1 ,2 ,3};
		heap.heapSort();
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	@Test
	void testHeapSort2() {
		setupStage2();
		heap.heapSort();
		for (int i = 0; i < heap.getArraysize() - 1; i++) {
			int j = i + 1;
			assertTrue(heap.getElements()[j].compareTo(heap.getElements()[i]) >= 0, "");
		}
	}
	
	@Test
	void testBuildMaxHeap0() {
		setupStage0();
		assertEquals(0, heap.getArraysize());
		assertEquals(0, heap.getHeapSize());
	}
	
	@Test
	void testBuildMaxHeap1() {
		setupStage1();
		int[] testValues = {3 ,2, 1};
		heap.buildMaxHeap();
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	@Test
	void testBuildMaxHeap2() {
		setupStage1();
		heap.buildMaxHeap();
		for (int i = 0; i < heap.getArraysize() - 1; i++) {
			int j = i + 1;
			assertTrue(heap.getElements()[i].compareTo(heap.getElements()[j]) >= 0, "");
		}
	}
	
	@Test
	void testMaxHeapify0() {
		setupStage0();
		heap.maxHeapify(3, 4);
		assertEquals(0, heap.getArraysize());
		assertEquals(0, heap.getHeapSize());
	}
	
	@Test
	void testMaxHeapify1() {
		setupStage0();
		heap.maxHeapify(1, 0);
		Integer[] testValues = {1 ,2 ,3};
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	@Test
	void testMaxHeapify2() {
		setupStage0();
		heap.maxHeapify(4, 0);
		Integer[] testValues = {4 ,1 ,3 ,14 ,16 , 9, 10, 2, 8, 7};
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	@Test
	void testSwap0() {
		setupStage0();
		assertThrows(IndexOutOfBoundsException.class, ()->{heap.swap(3,0);});
	}
	
	@Test
	void testSwap1() {
		setupStage1();
		heap.swap(0, 1);
		int[] testValues = {2 , 1, 3};
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	@Test
	void testSwap2() {
		setupStage2();
		heap.swap(7, 2);
		Integer[] testValues = {4 ,1 ,14 ,2 ,16 , 9, 10, 3, 8, 7};
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	@Test
	void testExist0() {
		setupStage0();
		assertFalse(heap.exist(1));
	}
	
	@Test
	void testExist1() {
		setupStage1();
		assertFalse(heap.exist(6));
		assertTrue(heap.exist(2));
	}
	
	@Test
	void testExist2() {
		setupStage2();
		assertFalse(heap.exist(65));
		assertTrue(heap.exist(8));
	}
	
	@Test
	void testExtractMaxHeap0() {
		setupStage0();
		assertThrows(HeapUnderFlowException.class, ()->{heap.extractMaxheap();});
	}
	
	@Test
	void testExtractMaxHeap1() throws HeapUnderFlowException {
		setupStage1();
		assertEquals(1, heap.extractMaxheap());
	}
	
	@Test
	void testExtractMaxHeap2() throws HeapUnderFlowException {
		setupStage2();
		assertEquals(4, heap.extractMaxheap());
		heap.setHeapSize(10);
		assertEquals(7, heap.extractMaxheap());
	}
	
	@Test
	void testDecreaseKey0() {
		setupStage0();
		assertThrows(IndexOutOfBoundsException.class, ()->{heap.decreaseKey(2, 5);});
	}
	
	@Test
	void testDecreaseKey1() throws BiggerKeyException {
		setupStage1();
		heap.decreaseKey(2, 1);
		assertEquals(1, heap.getKeys()[2]);
	}
	
	@Test
	void testDecreaseKey2() throws BiggerKeyException {
		setupStage2();
		assertThrows(BiggerKeyException.class, ()->{heap.decreaseKey(3, 7);});
		heap.decreaseKey(0, -1);
		assertEquals(-1, heap.getKeys()[9]);
	}
	
	@Test
	void testIncreaseKey0() throws SmallerKeyException {
		setupStage0();
		assertThrows(IndexOutOfBoundsException.class, ()->{heap.increaseKey(4, 9);});
	}
	
	@Test
	void testIncreaseKey1() throws SmallerKeyException {
		setupStage1();
		heap.increaseKey(0, 7);
		assertEquals(7, heap.getKeys()[0]);
	}
	
	@Test
	void testIncreaseKey2() throws SmallerKeyException {
		setupStage2();
		heap.increaseKey(9, 6);
		assertEquals(6, heap.getKeys()[0]);
	}
	

	
	@Test
	void testPriorityInsert0() throws SmallerKeyException {//dont work
		setupStage0();
		heap.priorityInsert(4, 2);
		Integer[] elements = {4};
		Integer[] keys = {2};
		assertEquals(1, heap.getArraysize());
		
		assertTrue(elements[0].compareTo((int)heap.getElements()[0].intValue())== 0);
		assertEquals(elements[0], heap.getElements()[0]);
		assertEquals(heap.getElements()[0], elements[0]);
		assertEquals(keys[0], heap.getKeys()[0]);
		heap.priorityInsert(9, 6);
		int[] elements1 = {9, 4};
		int[] keys1 = {6, 2};
		assertEquals(2, heap.getArraysize());
		assertEquals(elements1[0], heap.getElements()[0]);
		assertEquals(keys1[0], heap.getKeys()[0]);
	}
	
	
}