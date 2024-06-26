package tasks.adts
import u03.Sequences.* 
import Sequence.*
import u03.Optionals.*
import u03.Optionals.Optional.*
import u04.moduletypes.Sets

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
    def course(name: String): Course
    def school(teachers: Sequence[Teacher], courses: Sequence[Course]): School
    def teacher(name: String, courses: Sequence[Course]): Teacher
    extension (school: School)
      def addTeacher(name: String): School
      def addCourse(name: String): School
      def teacherByName(name: String): Optional[Teacher]
      def courseByName(name: String): Optional[Course]
      def nameOfTeacher(teacher: Teacher): String
      def nameOfCourse(course: Course): String
      def setTeacherToCourse(teacher: Teacher, course: Course): School
      def coursesOfATeacher(teacher: Teacher): Sequence[Course]
    
  object BasicSchool extends SchoolModule:
    import Sequence.* 
    
    private case class TeacherImpl(name: String, courses: Sequence[Course])
    private case class SchoolImpl(teachers: Sequence[Teacher], courses: Sequence[Course])

    opaque type Course = String
    opaque type Teacher = TeacherImpl
    opaque type School = SchoolImpl 
    
    def course(name: String): Course = name
    def school(teachers: Sequence[Teacher], courses: Sequence[Course]): School = SchoolImpl(teachers, courses)
    def teacher(name: String, courses: Sequence[Course]): Teacher = TeacherImpl(name, courses) 

    extension (s: School)  
      def addTeacher(name: String): School = 
        require(s.teacherByName(name) == Empty())
        school(Cons(teacher(name, Nil()), s.teachers), s.courses)
        
      def addCourse(name: String): School = 
        require(s.courseByName(name) == Empty())
        school(s.teachers, Cons(name, s.courses))
      
      def teacherByName(name: String): Optional[Teacher] = s.teachers match
        case Cons(h, _) if h.name == name => Just(h)
        case Cons(_, t) => SchoolImpl(t, Nil()).teacherByName(name)
        case _ => Empty()

      def courseByName(name: String): Optional[Course] = s.courses match
        case Cons(h, _) if h == name => Just(name)
        case Cons(_, t) => SchoolImpl(s.teachers, t).courseByName(name)
        case _ => Empty()
      
      def nameOfTeacher(teacher: Teacher): String = teacher.name

      def nameOfCourse(course: Course): String = course

      def setTeacherToCourse(t: Teacher, course: Course): School =
        if s.teacherByName(t.name) == Empty then s.addTeacher(t.name)  
        if s.courseByName(course) == Empty then s.addCourse(course) 
        val updateTeachers = s.update(t, course)
        school(updateTeachers, s.courses)

      def update(t: Teacher, course: Course): Sequence[Teacher] = s.teachers match
        case Cons(h, tail) if h == t => Cons(teacher(t.name, Cons(course, t.courses)), tail)
        case Cons(head, tail) => Cons(head, school(tail, s.courses).update(t, course))
        case _ => Nil()
        
      def coursesOfATeacher(teacher: Teacher): Sequence[Course] = teacher.courses