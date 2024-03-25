package tasks.adts

import org.junit.*
import org.junit.Assert.*
import tasks.adts.SchoolModel.*
import org.junit.Test
import u03.Sequences.Sequence.*

class SchoolModelTest:
    val schoolModel: SchoolModule = BasicSchool
    import schoolModel.* 
    val school = schoolModel.setSchool(Nil(), Nil())

    @Test def testCreate() =
        assertEquals(school, schoolModel.setSchool(Nil(), Nil()))

