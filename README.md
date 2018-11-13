# RSSKotlin + MVVM + Room + Retrofit + Livedata

Aplicación en kotlin que recoge feed del api https://newsapi.org

# Arquitectura

Esta aplicación utiliza una arquitectura clean compuesta por tres capas:
- domain
- data
- presentation


![alt Clean Arquitecture](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg "Clean Arquitecture")

La capa de presentación utliza MVVM como patrón de diseño utilizando los componentes de arquitectura de Android.

![alt MVVM](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png "MVVM")

# Librerías
Las librerías utilizadas en este proyecto:
- [Retrofit](https://github.com/square/retrofit): Se utiliza para consumir el API Rest.
- [Dagger](https://github.com/google/dagger): Con esta librería se hace la injección de dependencias haciendo más limpio y testeable el código.
- [Moshi](https://github.com/square/moshi): Hace que el parseo del JSON sea más sencillo
- [Glide](https://github.com/bumptech/glide): Carga las imágenes utilizadas en la aplicación
- [Room](https://developer.android.com/topic/libraries/architecture/room): Se ha utilizado una base de datos Room
- [Livedata](https://developer.android.com/topic/libraries/architecture/livlivedataedata): Con esta librería se usa el patrón de diseño __Observer__
- [coroutines](https://github.com/Kotlin/kotlinx.coroutines): Esta librería establece el hilo en el que se realizan las operaciones de los interactors
