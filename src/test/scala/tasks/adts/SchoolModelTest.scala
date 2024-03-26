package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import org.junit.Test
import u03.Sequences.Sequence.*

class SchoolModelTest{
    
    val bSchool: SchoolModule = basicSchool
    import bSchool.* 

    @Test def testSchoolEmpty =
        val school = empty()
        assertEquals((Nil(), Nil()), school)

}