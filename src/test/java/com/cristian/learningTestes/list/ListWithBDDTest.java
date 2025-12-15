package com.cristian.learningTestes.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ListWithBDDTest
{

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturn10()
    {
        List<?> list = mock( List.class );

        given( list.size() ).willReturn( 10 );

        assertThat(  list.size(), is( 10 ) );
    }

    @Test
    void testMockingList_When_SizeIsCalled_ShouldReturnMultipleValues()
    {
        List<?> list = mock( List.class );

        given( list.size() ).willReturn( 10 )
                            .willReturn( 20 )
                            .willReturn( 30 )
                            .willReturn( 40 );

        assertThat( list.size(), is( 10 ) );
        assertThat( list.size(), is( 20 ) );
        assertThat( list.size(), is( 30 ) );
        assertThat( list.size(), is( 40 ) );
    }

    @Test
    void testMockingList_When_GetIsCalled_ShouldReturnCristian()
    {
        var list = mock( List.class );

        given( list.get(0) ).willReturn( "Cristian" );

        assertThat( list.get(0), is( "Cristian" ) );
        assertNull( list.get(1) );
    }

    @Test
    void testMockingList_When_GetIsCalledWithArgumentMatcher_ShouldReturnCristian()
    {
        var list = mock( List.class );

        given( list.get( anyInt() ) ).willReturn( "Cristian" );

        assertThat( list.get( anyInt() ), is("Cristian") );
        assertThat( list.get( anyInt() ), is("Cristian") );
    }

    @Test
    void testMockingList_When_ThrowException()
    {
        List< Integer > list = mock( List.class );

        given( list.get( anyInt() ) ).willThrow( new RuntimeException( "test" ) );

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> list.get( 0 ),
                "Should throw RuntimeException"
        );

        assertThat( exception.getMessage(), is( "test" ) );
    }

}
