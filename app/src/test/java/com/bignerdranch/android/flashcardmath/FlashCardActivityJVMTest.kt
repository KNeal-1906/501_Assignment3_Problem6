import org.junit.Assert.assertTrue
import org.junit.Test

class FlashcardLogicTest {

    @Test
    fun outOfBounds_arraySizeDoesNotExceedLimit() {
        val array = intArrayOf(1, 2, 3)
        val size = 3

        assertTrue("Array size is out of bounds", array.size <= size)

    }
}
