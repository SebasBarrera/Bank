package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dataStructure.Node;
import dataStructure.Queue;

class QueueTest {

	private Queue<Integer> queue;
	
	private void setupStage0() {
		queue = new Queue<>();
	}
	
	private void setupStage1() {
		setupStage0();
		Node<Integer> n1 = new Node<>(67);
		queue.offer(n1.getValue());
	}
	private void setupStage2() {
		setupStage1();
		Node<Integer> n2 = new Node<>(1287643);
		queue.offer(n2.getValue());
		Node<Integer> n3 = new Node<>(836763);
		queue.offer(n3.getValue());
	}
	
	@Test
	void testOffer0() {
		setupStage0();
		int testValue = 9643;
		queue.offer(new Node<>(testValue).getValue());
		assertEquals(queue.peek(), testValue, "The value offer is not in the front");
		assertEquals(queue.getBack().getValue(), testValue, "The value offer is not in the back");
	}
	
	@Test
	void testOffer1() {
		setupStage1();
		int testValue = 4707543;
		queue.offer(new Node<>(testValue).getValue());
		assertEquals(queue.getBack().getValue(), testValue, "The value offer is not in the back");
	}
	
	@Test
	void testOffer2() {
		setupStage2();
		int testValue = 2359926;
		queue.offer(new Node<>(testValue).getValue());
		assertEquals(queue.getBack().getValue(), testValue, "The value offer is not in the back");
	}
	
	@Test
	void testPoll0() {
		setupStage0();
		queue.poll();
		assertEquals(queue.peek(), null, "The queue is not empty");
	}
	
	@Test
	void testPoll1() {
		setupStage1();
		queue.poll();
		assertEquals(queue.peek(), null, "The queue is not empty");
	}
	
	@Test
	void testPoll2() {
		setupStage2();
		queue.poll();
		assertEquals(queue.peek(), 1287643, "The value retorned is not the expected");
	}
	
	@Test
	void testIsEmpty0() {
		setupStage0();
		assertTrue(queue.isEmpty(), "The queue is not empty");
	}
	
	@Test
	void testIsEmpty1() {
		setupStage1();
		assertFalse(queue.isEmpty(), "The queue is empty");
	}
	
	@Test
	void testIsEmpty2() {
		setupStage2();
		assertFalse(queue.isEmpty(), "The queue is empty");
	}
	
	@Test
	void testPeek0() {
		setupStage0();
		assertEquals(queue.peek(), null, "There is something in the queue");
	}
	
	@Test
	void testPeek1() {
		setupStage1();
		assertEquals(queue.peek(), 67, "The value retorned is not the expected");
	}
	
	@Test
	void testPeek2() {
		setupStage2();
		assertEquals(queue.peek(), 67, "The value retorned is not the expected");
	}
	
	@Test
	void testSize0() {
		setupStage0();
		assertEquals(queue.size(), 0, "There is something in the queue");
	}
	
	@Test
	void testSize1() {
		setupStage1();
		assertEquals(queue.size(), 1, "The size of the queue is not 1");
	}
	
	@Test
	void testSize2() {
		setupStage2();
		assertEquals(queue.size(), 3, "The size of the queue is not 1");
	}
	
	@Test
	void testClear0() {
		setupStage0();
		queue.clear();
		assertTrue(queue.isEmpty(), "The queue is not empty");
	}
	
	@Test
	void testClear1() {
		setupStage1();
		queue.clear();
		assertTrue(queue.isEmpty(), "The queue is not empty");
	}
	
	@Test
	void testClear2() {
		setupStage2();
		queue.clear();
		assertTrue(queue.isEmpty(), "The queue is not empty");
	}
}
