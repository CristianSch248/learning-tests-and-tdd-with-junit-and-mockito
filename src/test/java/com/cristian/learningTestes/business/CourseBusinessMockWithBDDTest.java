package com.cristian.learningTestes.business;

import com.cristian.learningTestes.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

public class CourseBusinessMockWithBDDTest
{
    private CourseService  mockService;
    private CourseBusiness courseBusiness;

    List< String > courses;


    @BeforeEach
    void setup()
    {
        mockService    = mock( CourseService.class );
        courseBusiness = new CourseBusiness( mockService );

        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testCourseRelatedToSpring_When_UsingAMock()
    {
        given( mockService.retrieveCourses( "cristian" ) ).willReturn( courses );

        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring( "cristian" );

        assertThat( filteredCourses.size(), is( 4 ) );
    }

    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethodDeleteCourses()
    {
        given( mockService.retrieveCourses( "cristian" ) ).willReturn( courses );

        courseBusiness.deleteCoursesRelatedToSpring( "cristian" );

        verify( mockService, times( 1 ) ).deleteCourse( "Agile Desmistificado com Scrum, XP, Kanban e Trello" );

        verify( mockService ).deleteCourse( "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI" );

        verify( mockService, never() ).deleteCourse( "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Dockeo" );
    }


    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoVerify_Should_CallMethodDeleteCoursesV2()
    {
        given( mockService.retrieveCourses( "cristian" ) ).willReturn( courses );

        courseBusiness.deleteCoursesRelatedToSpring( "cristian" );

        then( mockService ).should().deleteCourse( "Agile Desmistificado com Scrum, XP, Kanban e Trello" );

        then( mockService ).should().deleteCourse( "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI" );

        then( mockService ).should( never() ).deleteCourse( "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker" );
    }

    @Test
    void testDeleteCoursesNotRelatedToSpring_UsingMockitoCaptureArguments_Should_CallMethodDeleteCoursesV3()
    {

//        courses = Arrays.asList(
//                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
//                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
//        );



        given( mockService.retrieveCourses( "cristian" ) ).willReturn( courses );

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass( String.class );

//        String course1 = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

        courseBusiness.deleteCoursesRelatedToSpring( "cristian" );

        then( mockService ).should( times( 7 ) ).deleteCourse( argumentCaptor.capture() );

        assertThat( argumentCaptor.getAllValues().size(), is( 7 ) );
    }
}
