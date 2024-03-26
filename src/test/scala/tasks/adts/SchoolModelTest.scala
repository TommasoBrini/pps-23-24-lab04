package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import org.junit.Test
import u03.Sequences.Sequence.*

class SchoolModelTest{
    
    val bSchool: SchoolModule = basicSchool
    import bSchool.* 

    val pps: Course = course("PPS")
    val pcd: Course = course("PCD")
    val lcmc: Course = course("LCMC")
    val vir: Teacher = teacher("Viroli", Cons(pps, Nil()))
    val ric: Teacher = teacher("Ricci", Cons(pcd, Nil()))
    val brav: Teacher = teacher("Bravetti", Cons(lcmc, Nil()))
    
    @Test def testCreateCourse() =
        assertEquals("PPS", pps)


    
}