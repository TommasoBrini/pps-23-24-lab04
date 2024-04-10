
// TASK 1 : svolto da solo

object Ex1ComplexNumbers:
    trait ComplexADT:
        type Complex
        def complex(re: Double, im: Double): Complex
        extension (complex: Complex)
            def re(): Double
            def im(): Double
            def sum(other: Complex): Complex
            def subtract(other: Complex): Complex
            def asString(): String

    object BasicComplexADT extends ComplexADT:

        private case class ComplexNumber(re: Double, im: Double)
        opaque type Complex = ComplexNumber
    
        def complex(re: Double, im: Double): Complex = ComplexNumber(re, im)

        extension (complex: Complex)
            def re(): Double = complex match 
                case ComplexNumber(re, _) => re
            def im(): Double = complex match
                case ComplexNumber(_, im) => im
      
            def sum(other: Complex): Complex = ComplexNumber(complex.re + other.re, complex.im + other.im)
            def subtract(other: Complex): Complex = ComplexNumber(complex.re - other.re, complex.im - other.im)
            def asString(): String = complex match
                case _  =>
                    if im == 0 then "" + re
                    else if re == 0 then "" + im + "i"
                    else if im < 0 then "" + re + " - " + Math.abs(im) + "i"
                    else "" + re + " + " + im + "i"
      

// TASK 2: svolto da solo

import u03.Sequences.Sequence
import u03.Optionals.Optional
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
        import Optional.*
    
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


// TASK 3 : svolto da solo

object Ex3Stacks:

    trait StackADT:
        type Stack[A]
        def empty[A]: Stack[A] // factory
        extension [A](stack: Stack[A])
            def push(a: A): Stack[A]
            def pop(a: A): Optional[(A, Stack[A])]
            def asSequence(): Sequence[A]
  
    object StackImpl extends StackADT:
        import Sequence.*
        opaque type Stack[A] = Sequence[A]
        def empty[A]: Stack[A] = Nil()
        extension [A](stack: Stack[A])
            def push(a: A): Stack[A] = stack match
                case Cons(h, t) => Cons(h, t.push(a))
                case Nil() => Cons(a, Nil()) 
      
            def pop(a: A): Optional[(A, Stack[A])] = stack match
                case Cons(h,t) => Optional.Just(a, t)
                case _ => Optional.Empty()
      
            def asSequence(): Sequence[A] = stack


// TASK 4 : svolto da solo

object Ex4Summables:
    import Sequence.* 
  
    trait Summable[A]:
        def sum(a1: A, a2: A): A
        def zero: A

    def sumAll[A: Summable](seq: Sequence[A]): A = 
        val summable = summon[Summable[A]] 
        seq match
            case Cons(h, t) => summable.sum(h, sumAll(t))
            case Nil() => summable.zero

    given Summable[Int] with
        def sum(a1: Int, a2: Int): Int = a1 + a2
        def zero: Int = 0
  
    given Summable[Double] with
        def sum(a1: Double, a2: Double): Double = a1 + a2
        def zero: Double = 0
  
    given Summable[String] with
        def sum(a1: String, a2: String): String = a1 + a2
        def zero: String = "" 

        
// TASK 5 : svolto da solo
object Ex5Traversable:
    import Optional.*
    import Sequence.*
    
    def log[A](a: A): Unit = println("The next element is: "+a)
  
    trait Traversable[T[_]]:
        def logAll[A](t: T[A]): Unit

    def logAll[B, A[_]: Traversable](t: A[B]): Unit = summon[Traversable[A]].logAll(t)

    given Traversable[Optional] with
        def logAll[A](o: Optional[A]): Unit = o match
            case Just(a) => log(a)
            case _ => 

    given Traversable[Sequence] with
        def logAll[A](t: Sequence[A]): Unit = t match
            case Cons(head, tail) => log(head); logAll(tail)
            case _ => 

// TASK 6: svolto da solo
object Ex6TryModel:
    import u04.monads.Monads.Monad

    private enum TryImpl[A]:
        case Success(value: A)
        case Failure(exception: Throwable)

    opaque type Try[A] = TryImpl[A]

    def success[A](value: A): Try[A] = TryImpl.Success(value)
    def failure[A](exception: Throwable): Try[A] = TryImpl.Failure(exception)
    def exec[A](expression: => A): Try[A] = try success(expression) catch failure(_)

    extension [A](m: Try[A]) 
        def getOrElse[B >: A](other: B): B = m match
            case TryImpl.Success(value) => value
            case TryImpl.Failure(_) => other

    given Monad[Try] with
        override def unit[A](value: A): Try[A] = exec(value)
    
        extension [A](m: Try[A]) 
            override def flatMap[B](f: A => Try[B]): Try[B] = m match
                case TryImpl.Success(value) => f(value)
                case TryImpl.Failure(exception) => failure(exception)