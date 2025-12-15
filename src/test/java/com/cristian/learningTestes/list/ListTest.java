package com.cristian.learningTestes.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ListTest
{

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10()
    {
        List<?> list = mock( List.class );

        when( list.size() ).thenReturn( 10 );

        assertEquals( 10, list.size() );
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues()
    {
        List<?> list = mock( List.class );

        when( list.size() ).thenReturn( 10 ).thenReturn( 20 ).thenReturn( 30 ).thenReturn( 40 );

        assertEquals( 10, list.size() );
        assertEquals( 20, list.size() );
        assertEquals( 30, list.size() );
        assertEquals( 40, list.size() );
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnCristian()
    {
        var list = mock( List.class );

        when( list.get(0) ).thenReturn( "Cristian" );

        assertEquals( "Cristian", list.get(0) );
        assertNull(  list.get(1) );
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnCristian()
    {
        var list = mock( List.class );

        when( list.get( anyInt() ) ).thenReturn( "Cristian" );

        assertEquals( "Cristian", list.get( anyInt() ) );
        assertEquals( "Cristian", list.get( anyInt() ) );
    }

    @Test
    void testMockingList_When_ThrowException()
    {
        var list = mock( List.class );

        when( list.get( anyInt() ) ).thenThrow( new RuntimeException( "test" ) );

        assertThrows( RuntimeException.class, () -> list.get( anyInt() ), () -> "Should throw RuntimeException" );
    }

}
