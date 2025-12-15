package com.cristian.learningTestes.service;

import java.util.ArrayList;
import java.util.List;

public class CourseBusiness
{
    private CourseService courseService;

    public CourseBusiness( CourseService courseService )
    {
        this.courseService = courseService;
    }

    public List< String > retrieveCoursesRelatedToSpring( String student )
    {
        List< String > filteredCourses = new ArrayList<>();

        if ( student.contains( "foo bar" ) )
        {
            return filteredCourses;
        }

        List< String > courses         = courseService.retrieveCourses( student );

        for ( String course : courses )
        {
            if ( course.contains( "Spring" ) )
            {
                filteredCourses.add( course );
            }
        }

        return filteredCourses;
    }

    public void deleteCoursesRelatedToSpring( String student )
    {
        List< String > courses = courseService.retrieveCourses( student );

        for ( String course : courses )
        {
            if ( ! course.contains( "Spring" ) )
            {
                courseService.deleteCourse( course );
            }
        }
    }
}
