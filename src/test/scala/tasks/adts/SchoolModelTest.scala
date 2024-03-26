package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import org.junit.Test
import u03.Sequences.Sequence.*

class SchoolModelTest{
    
    import BasicSchool.*
    val pps: Course = course("PPS")
    val pcd: Course = course("PCD")
    val lcmc: Course = course("LCMC")
    val vir: Teacher = teacher("Viroli", Cons(pps, Nil()))
    val ric: Teacher = teacher("Ricci", Cons(pcd, Nil()))
    val brav: Teacher = teacher("Bravetti", Cons(lcmc, Nil()))

    val mySchool: School = school(Cons(vir, Cons(ric, Cons(brav, Nil()))), Cons(pps, Cons(pcd, Cons(lcmc, Nil()))))
    
    @Test def testCreateCourse() =
        assertEquals("PPS", pps)

    @Test def testAddTeacher() =
        val newSchool = mySchool.addTeacher("Maltoni")
        val mal = teacher("Maltoni", Nil())
        assertEquals(Cons(mal, Cons(vir, Cons(ric, Cons(brav, Nil())))), newSchool.getTeachers)
    

        
}