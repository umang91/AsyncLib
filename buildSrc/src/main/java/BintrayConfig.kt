/**
 * @author Umang Chamaria
 * Date: 25/04/20
 */
object BintrayConfig {

  const val developerId = "umangchamaria"
  const val developerName = "Umang Chamaria"
  const val developerEmail = "umangchamaria@gmail.com"
  const val repoName = "umang"
  const val publisherId = "com.umang"

  const val siteUrl = "https://github.com/UmangChamaria/AysncLibKotlin"
  const val gitUrl = "https://github.com/UmangChamaria/AysncLibKotlin.git"

  const val licenseName = "The Apache Software License, Version 2.0"
  const val licenseUrl = "http://www.apache.org/licenses/LICENSE-2.0.txt"
  val allLicenses: Array<String> = arrayOf(licenseName)

  const val artifactId = "asynclib"
  const val artifactName = "aysnclibrary"
  const val description = "Light weight Kotlin library for performing tasks on background threads a.k.a WorkerThreads."
  const val versionName = "1.2.0"
  const val versionCode = 3
}