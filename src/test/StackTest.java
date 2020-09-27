package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dataStructure.Node;
import dataStructure.StackG;

class StackTest {

	private StackG<Integer> stack;
	
	private void setupStage0() {
		stack = new StackG<>();
	}
	
	private void setupStage1() {
		setupStage0();
		Node<Integer> n1 = new Node<>(532);
		stack.push(n1.getValue());
	}
	private void setupStage2() {
		setupStage1();
		Node<Integer> n2 = new Node<>(99472);
		stack.push(n2.getValue());
		Node<Integer> n3 = new Node<>(4272942);
		stack.push(n3.getValue());
	}
	
	@Test
	void testPush0() {
		setupStage0();
		int testValue = 75343;
		stack.push(new Node<>(testValue).getValue());
		assertEquals(stack.top(), testValue, "The value pushed is not on the top");
	}
	
	@Test
	void testPush1() {
		setupStage1();
		int testValue = 582935;
		stack.push(new Node<>(testValue).getValue());
		assertEquals(stack.top(), testValue, "The value pushed is not on the top");
	}
	
	@Test
	void testPush2() {
		setupStage2();
		int testValue = 2359926;
		stack.push(new Node<>(testValue).getValue());
		assertEquals(stack.top(), testValue, "The value pushed is not on the top");
	}
	
	@Test
	void testPop0() {
		setupStage0();
		stack.pop();
		assertEquals(stack.top(), null, "The stack is not empty");
	}
	
	@Test
	void testPop1() {
		setupStage1();
		stack.pop();
		assertEquals(stack.top(), null, "The stack is not empty");
	}
	
	@Test
	void testPop2() {
		setupStage2();
		stack.pop();
		assertEquals(stack.top(), 99472, "The value retorned is not the expected");
	}
	
	@Test
	void testIsEmpty0() {
		setupStage0();
		assertTrue(stack.isEmpty(), "The stack is not empty");
	}
	
	@Test
	void testIsEmpty1() {
		setupStage1();
		assertFalse(stack.isEmpty(), "The stack is empty");
	}
	
	@Test
	void testIsEmpty2() {
		setupStage2();
		assertFalse(stack.isEmpty(), "The stack is empty");
	}
	
	@Test
	void testTop0() {
		setupStage0();
		assertEquals(stack.top(), null, "There is something in the stack");
	}
	
	@Test
	void testTop1() {
		setupStage1();
		assertEquals(stack.top(), 532, "The value retorned is not the expected");
	}
	
	@Test
	void testTop2() {
		setupStage2();
		assertEquals(stack.top(), 4272942, "The value retorned is not the expected");
	}
	
	@Test
	void testSize0() {
		setupStage0();
		assertEquals(stack.size(), 0, "There is something in the stack");
	}
	
	@Test
	void testSize1() {
		setupStage1();
		assertEquals(stack.size(), 1, "The size of the stack is not 1");
	}
	
	@Test
	void testSize2() {
		setupStage2();
		assertEquals(stack.size(), 3, "The size of the stack is not 1");
	}

}
