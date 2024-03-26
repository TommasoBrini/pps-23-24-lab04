package tasks.adts
import u03.Sequences.*
import u03.Optionals.*

/*  Exercise 2: 
 *  Implement the below trait, and write a meaningful test.
 *  Suggestion: 
 *  - reuse Sequences and Optionals as imported above
 *  - Course is a simple case classes with just the name
 *  - Teacher is a case class with name and sequence of courses
 *  - School is a case class with (sequences of) teachers and courses
 *  - add/set methods below create the new school 
 */

object SchoolModel:

  trait SchoolModule:
    type School
    type Teacher
    type Course
    def school(teachers: Sequence[Teacher], courses: Sequence[Course]): School
    def teacher(name: String, courses: Sequence[Course]): Teacher
    def course(name: String): Course
    extension (school: School)
      def addTeacher(name: String): School
      def addCourse(name: String): School
      def teacherByName(name: String): Optional[Teacher]
      def courseByName(name: String): Optional[Course]
      def nameOfTeacher(teacher: Teacher): String
      def nameOfCourse(teacher: Teacher): String
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]
    
  object basicSchool extends SchoolModule:
    import Sequence.* 
    
    private case class TeacherImpl(name: String, courses: Sequence[Course])
    private case class SchoolImpl(teachers: Sequence[Teacher], courses: Sequence[Course])

    opaque type Course = String
    opaque type Teacher = TeacherImpl
    opaque type School = SchoolImpl 
    
    def course(name: String): Course = name

    def school(teachers: Sequence[Teacher], courses: Sequence[Course]): School = SchoolImpl(teachers, courses)

    def teacher(name: String, courses: Sequence[Course]): Teacher = TeacherImpl(name, courses) 

    extension (school: School)
      def addTeacher(name: String): School = ???
      def addCourse(name: String): School = ???
      def teacherByName(name: String): Optional[Teacher] = ???
      def courseByName(name: String): Optional[Course] = ???
      def nameOfTeacher(teacher: Teacher): String = ???
      def nameOfCourse(teacher: Teacher): String = ???
      def setTeacherToCourse(teacher: Teacher, course: Course): School = ???
      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = ???


