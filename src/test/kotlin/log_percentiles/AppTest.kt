/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package log_percentiles

import kotlin.test.Test
import kotlin.test.assertNotNull
import java.io.File

class AppTest {
    @Test fun testGetResponseTime() {

        val log = """10.2.3.4 [2018/13/10:14:02:39] "GET /api/my_articles?userId=3" 200 1230"""
        var respTimes = ArrayList<Int>()
        getResponseTime(log, respTimes)
        // println("respTimes: ${respTimes}")
        assert(respTimes[0] == 1230)

    }

    @Test fun testGetResponseTime2() {

        val f = File("src/test/resources/2018-13-10.log")
        val respTimes = ArrayList<Int>()
        for(log in f.readLines()) {
            getResponseTime(log, respTimes)
        }
        // println("respTimes: ${respTimes}")
        assert(respTimes[0] == 1230)
        assert(respTimes[1] == 4630)
    } 
}
