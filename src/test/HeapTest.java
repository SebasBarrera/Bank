package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dataStructure.Heap;

class HeapTest {

	private Heap<Integer> heap;
	
	private void setupStage0() {
		heap = new Heap<>(3,3);
		Integer[] testValues = {1 ,2 ,3};
		heap.setElements(testValues);
	}
	
	private void setupStage1() {
		heap = new Heap<>(10,10);
		Integer[] testValues = {9 ,8 ,7, 6, 5, 4, 3, 2, 1, 0};
		heap.setElements(testValues);
	}

	private void setupStage2() {
		heap = new Heap<>(10,3);
		Integer[] testValues = {4 ,1 ,3 ,2 ,16 , 9, 10, 14, 8, 7};
		heap.setElements(testValues);
	}
	
	@Test
	void testHeapSort0( ) {
		setupStage0();
		int[] testValues = {1 ,2 ,3};
		heap.heapSort();
		for (int i = 0; i < heap.getArraysize(); i++) {
			assertEquals(heap.getElements()[i], testValues[i]);
		}
	}
	
	
	@Test
	void testHeapSort1() {
		setupStage1();
		heap.heapSort();
		for (int i = 0; i < heap.getArraysize() - 1; i++) {
			int j = i + 1;
			assertTrue(heap.getElements()[j].compareTo(heap.getElements()[i]) >= 0, "");
			
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
	
	/*@Test
	void testInsert0() {
		setupStage0();
		int testValue = 75343;
		int testValue2 = 63436;
		hashTable.insert(testValue, testValue2);
		assertEquals(hashTable.search(testValue), testValue2, "The hash table is not inserting in the correct way");
	}
	
	@Test
	void testInsert1() {
		setupStage1();
		int testValue = 953;
		int testValue2 = 23536;
		hashTable.insert(testValue, testValue2);
		assertEquals(hashTable.search(testValue), testValue2, "The hash table is not inserting in the correct way");
	}
	
	@Test
	void testInsert2() {
		setupStage2();
		int testValue = 450;
		int testValue2 = 4235;
		hashTable.insert(testValue, testValue2);
		assertEquals(hashTable.search(testValue), testValue2, "The hash table is not inserting in the correct way");
	}
	
	@Test
	void testDelete0() {
		setupStage0();
		int testValue = 2957;
		hashTable.delete(testValue);
		assertEquals(hashTable.search(testValue), null, "The hashTable is not deleting, it should'nt have nodes");
	}
	
	@Test
	void testDelete1() {
		setupStage1();
		assertEquals(hashTable.search(82), -4134);
		hashTable.delete(82);
		assertEquals(hashTable.search(82), null, "The hashTable is not deleting as well, the -4134 still here");
	}
	
	@Test
	void testDelete2() {
		setupStage2();
		assertEquals(hashTable.search(-1820), 7);
		hashTable.delete(-1820);
		assertEquals(hashTable.search(-1820), null, "The hashTable is not deleting, the value 7 still here");
	}
	
	@Test
	void testSearch0() {
		setupStage0();
		assertEquals(hashTable.search(42), null, "The hashTable is not searching as well");
	}
	
	@Test
	void testSearch1() {
		setupStage1();
		assertEquals(hashTable.search(82), -4134, "The hashTable is not searching as well");
	}
	
	@Test
	void testSearch2() {
		setupStage2();
		assertEquals(hashTable.search(0), 724520, "The hashTable is not searching as well");
	}*/
	
}