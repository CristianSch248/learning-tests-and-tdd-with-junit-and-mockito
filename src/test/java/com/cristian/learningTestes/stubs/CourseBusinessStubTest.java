package com.cristian.learningTestes.stubs;

import com.cristian.learningTestes.service.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CourseBusinessStubTest
{
    @Test
    void testCourseRelatedToSpring_When_UsingAStub()
    {
        CourseService courseService = new CourseServiceStub();

        CourseBusiness courseBusiness = new CourseBusiness( courseService );

        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring( "cristian" );

        assertEquals( 4, filteredCourses.size() );

    }

    @Test
    void testCourseRelatedToSpring_When_UsingAFoobarStudent()
    {
        CourseService courseService = new CourseServiceStub();

        CourseBusiness courseBusiness = new CourseBusiness( courseService );

        var filteredCourses = courseBusiness.retrieveCoursesRelatedToSpring( "foo bar" );

        assertEquals( 0, filteredCourses.size() );

    }
}