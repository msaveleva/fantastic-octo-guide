# Little Lemon

Android project for Android App Capstone (Coursera, Meta). The course is a part of [Meta Android Developer Professional Certificate](https://www.coursera.org/professional-certificates/meta-android-developer).

Kotlin, Jetpack Compose, Ktor, Room.

## Features

1. **Data Fetching and Storage**:
   - Fetches and stores a list of menu items in a Room database for persistent data management.

2. **Onboarding and Registration**:
   - Features an onboarding screen that guides users through the registration process. User data is stored in SharedPreferences.

3. **Home Screen**:
   - Displays comprehensive information about the service.
   - Provides a dynamic list of menu items, allowing users to filter the list by search phrases, categories, or both.

4. **Profile Management**:
   - Offers a profile screen where users can view own information provided during onboarding.
   - Includes a logout function.


## Building blocks

This project is built using a range of modern technologies and libraries:

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.
- [Ktor](https://ktor.io/) - An asynchronous framework for creating microservices, web applications, and more. Used for handling server-side logic and networking.
- [Room](https://developer.android.com/training/data-storage/room) - An abstraction layer over SQLite used for database access while utilizing the full power of SQLite.
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines. It simplifies fetching, decoding, and displaying images in the application.

## Wireframe

The wireframe could be found in `design/` folder of the project. Wireframe has been built in Figma.

## Further improvements

While the project currently meets the course's requirements, there are several enhancements that could be considered for future development. These enhancements, though not essential for the project's current scope, would be valuable if the project were to be expanded.

- [ ] Localization
- [ ] Themes support
- [ ] Network layer update
  - [ ] Path isolation
  - [ ] API versioning
  - [ ] Endpoints implementation
- [ ] Migration to clean architecture
  - [ ] ViewModels for each screen
  - [ ] Repositories
  - [ ] Use cases
- [ ] Unit and Integration Testing

## Special thanks

Working soundtrack for tasks from [week 1](https://open.spotify.com/album/0e1WaSNDZnoPixaxDNdWo4?si=vbZTVjDYTgahiJStV1zMTA) and [week 2 & 3](https://open.spotify.com/album/0DoVnWjNFYoUfq7qe36jxh?si=nH4dXTqzTvqFp6kquhX76g).