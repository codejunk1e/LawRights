# LawRights

Android app for interview test. App features:
 * Kotlin
 * MVVM
 * Unit tests
 * Dependency Injection (Hilt)
 * Offline caching
 * Corutines
 
## Thought process and architectural decisions
For the app, I decided to use a single module application with hilt, mostly bcause of the simplicity of the application. I used MVVM for architecture because of it's ease of use. Offline caching is done by fetching from the remote endpoint and saving into the local database. 
 
Custom UI design was done without the modal bottomsheet rather than the standard modal bottomsheet to allow use of custom back button and also show bottom naviagtions 
 
<p align="center">
  <img src="./media/20210913_132901.gif" width="200">
  <img src="./media/Screenshot_2021-09-13-13-07-55-043_io.github.codejunk1e.lawrights.jpg" width="200">
  <img src="./media/Screenshot_2021-09-13-13-08-03-190_io.github.codejunk1e.lawrights.jpg" width="200">
</p>

