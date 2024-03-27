package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import org.junit.Test
import u03.Sequences.Sequence.*
import u03.Optionals.* 
import Optional.*

class SchoolModelTest{
    
    import SchoolModel.* 
    val sm: SchoolModule = BasicSchool
    import sm.*

    val pps: Course = course("PPS")
    val pcd: Course = course("PCD")
    val lcmc: Course = course("LCMC")
    val vir: Teacher = teacher("Viroli", Cons(pps, Nil()))
    val ric: Teacher = teacher("Ricci", Cons(pcd, Nil()))
    val brav: Teacher = teacher("Bravetti", Cons(lcmc, Nil()))
    val mySchool: School = school(Cons(vir, Cons(ric, Cons(brav, Nil()))), Cons(pps, Cons(pcd, Cons(lcmc, Nil()))))
    
    @Test def testCreateCourse() =
        assertEquals("PPS", pps)

    @Test def testTeacherByName() =
        assertEquals(Just(vir), mySchool.teacherByName("Viroli"))

    @Test def testAddTeacher() =
        val mal: Teacher = teacher("Maltoni", Nil())
        assertEquals(Just(mal), mySchool.addTeacher("Maltoni").teacherByName("Maltoni"))
        
    @Test def testAddCourse() =
        assertEquals(Just("ML"), mySchool.addCourse("ML").courseByName("ML"))

    @Test def testNameOfTeacher() =
        assertEquals("Viroli", mySchool.nameOfTeacher(vir))

    @Test def tes
}