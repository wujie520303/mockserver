package org.mockserver.stop;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class StopEventQueueTest {

    @Before
    public void emptyStoppables() {
        new StopEventQueue().clear();
    }

    @Test
    public void shouldCallStopOnAllRegisteredStoppables() {
        // given
        Stoppable stoppableOne = mock(Stoppable.class);
        Stoppable stoppableTwo = mock(Stoppable.class);
        StopEventQueue stopEventQueue = new StopEventQueue();
        stopEventQueue.register(stoppableOne);
        stopEventQueue.register(stoppableTwo);

        // when
        stopEventQueue.stop();

        // then
        verify(stoppableOne).stop();
        verify(stoppableTwo).stop();
    }

    @Test
    public void shouldRegisterStoppable() {
        // given
        Stoppable stoppableOne = mock(Stoppable.class);
        Stoppable stoppableTwo = mock(Stoppable.class);
        StopEventQueue stopEventQueue = new StopEventQueue();

        // when
        stopEventQueue.register(stoppableOne);
        stopEventQueue.register(stoppableTwo);

        // then
        assertThat(stopEventQueue.stoppables, contains(
                stoppableOne,
                stoppableTwo
        ));
    }

    @Test
    public void shouldUnregisterStoppable() {
        // given
        Stoppable stoppableOne = mock(Stoppable.class);
        Stoppable stoppableTwo = mock(Stoppable.class);
        StopEventQueue stopEventQueue = new StopEventQueue();

        // when
        stopEventQueue.register(stoppableOne);
        stopEventQueue.register(stoppableTwo);

        // then
        assertThat(stopEventQueue.stoppables, contains(
                stoppableOne,
                stoppableTwo
        ));

        // when
        stopEventQueue.unregister(stoppableOne);

        // then
        assertThat(stopEventQueue.stoppables, contains(
                stoppableTwo
        ));
    }

}