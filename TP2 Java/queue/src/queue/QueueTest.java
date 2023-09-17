package queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class QueueTest {

@Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( new Queue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithSomethingString().isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
    assertEquals( "Something", queueWithSomethingString().head() );
  }

  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithSomethingString();
    queue.take();
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( "Something", queueWithSomethingString().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithFirstAndSecondString();
    assertEquals( queue.take(), "First" );
    assertEquals( queue.take(), "Second" );
    assertTrue( queue.isEmpty() );
  }

  @Test public void test07HeadReturnsFirstAddedObject() {
    assertEquals( queueWithFirstAndSecondString().head(), "First" );
  }

  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithSomethingString();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }

  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithFirstAndSecondString().size() );
  }

  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike( () -> new Queue().take(), BlankObject.ErrorMessage );
  }

  @Test public void test11CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomethingString();
    queue.take();
    assertThrowsLike( () -> queue.take(), BlankObject.ErrorMessage );
  }

  @Test public void test12CanNotHeadWhenThereAreNoObjectsInTheQueue() {
    assertThrowsLike( () -> new Queue().head(), BlankObject.ErrorMessage );
  }
  
  private Queue queueWithSomethingString() {
		return new Queue().add( "Something" );
  }
  
  private Queue queueWithFirstAndSecondString() {
		return new Queue().add( "First" ).add( "Second" );
  }
  
  private void assertThrowsLike( Executable executable, String message ) {
	    assertEquals( message, assertThrows( Exception.class, executable ).getMessage() );
  }
}