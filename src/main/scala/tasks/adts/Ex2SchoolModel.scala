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
    def school(teachers: Sequence[Teacher], course: Sequence[Course]): School
    def teacher(name: String): Teacher
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
    

    opaque type Course = String
    opaque type Teacher = (String, Sequence[Course])
    opaque type School = (Sequence[Teacher], Sequence[Course]) 
    
    def empty(): School = (Nil(), Nil())

    extension (school: School)
      def addTeacher(name: String): School = ???
      def addCourse(name: String): School = ???
      def teacherByName(name: String): Optional[Teacher] = ???
      def courseByName(name: String): Optional[Course] = ???
      def nameOfTeacher(teacher: Teacher): String = ???
      def nameOfCourse(teacher: Teacher): String = ???
      def setTeacherToCourse(teacher: Teacher, course: Course): School = ???
      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = ???


