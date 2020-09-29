package dataStructure;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class HashTableTest {

	private HashTable<Integer, Integer> hashTable;
	
	private void setupStage0() {
		hashTable = new HashTable<>();
	}
	
	private void setupStage1() {
		setupStage0();
		hashTable.insert(82, -4134);
	}

	private void setupStage2() {
		setupStage1();
		hashTable.insert(-91354, 137);
		hashTable.insert(0, 724520);
		hashTable.insert(-1820, 7);
	}
	
	@Test
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
		hashTable.delete(82);
		assertEquals(hashTable.search(82), null, "The hashTable is not deleting as well, the -4134 still here");
	}
	
	@Test
	void testDelete2() {
		setupStage2();
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
	}
	
}
