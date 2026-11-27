# User Directory

This Kotlin Android demo loads the [JSONPlaceholder users](https://jsonplaceholder.typicode.com/users) with Retrofit and displays their names, usernames, and emails in Jetpack Compose.

## Run in Android Studio

Open this folder, use JDK 17 or later for Gradle, and install Android SDK Platform 37. Sync the project and run the `app` configuration on an API 24+ emulator or device with internet access. To build from PowerShell, run `.\gradlew.bat :app:assembleDebug`.

## Package structure

All packages are under `com.example.cleanarchitecturedemo` in the `app` module.

| Package | Responsibility |
| --- | --- |
| `data` | Defines the Retrofit endpoint and JSON DTO, maps the DTO to the domain model, and implements the repository. |
| `domain` | Defines the user model, repository interface, and verified-account eligibility rule. |
| `presentation` | Renders the Compose screen and exposes loading, loaded, and error states from the ViewModel. |
| `ui.theme` | Defines the Compose theme used by `MainActivity`. |

The ViewModel calls `GetDirectoryUsersUseCase`, which fetches users through `UserRepository` and keeps only verified accounts. JSONPlaceholder has no verification field, so the data layer simulates verification for user IDs 1, 3, and 5. `MainActivity` wires the repository and use case into the ViewModel. A failed request shows a Retry button.

`UserApiInterface.kt` keeps the Retrofit base URL, endpoint, and response DTO together. `RetrofitUserRepository.kt` contains the private mapper. The domain uses `model`, `repository`, and `usecase` subpackages.
