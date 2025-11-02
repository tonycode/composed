## Frequently used Gradle tasks

- `./gradlew clean` - delete build dirs (`./build/`, `./app/build/`, `./**/build/`)

### Dependencies
- `./gradlew dependencyUpdates --no-parallel` - check for dependency updates, result will be printed in console
    - open `build/dependencyUpdates/report.html`
- `./gradlew app:dependencies` - view dependencies hierarchy
    - `./gradlew app:dependencies --configuration debugCompileClasspath`

### Code-style
- `./gradlew ktlintFormat`

### Unit-tests
- `./gradlew test`
    - ~~with coverage: `./gradlew createDebugUnitTestCoverageReport`~~
- ~~`./gradlew connectedAndroidTest`~~

### App install/uninstall
- `./gradlew installDebug`
- `./gradlew installRelease`
- `./gradlew uninstallDebug`
- `./gradlew uninstallRelease`
- `./gradlew uninstallAll`


## Development notes

- [API levels](https://apilevels.com)
- [SDK Platform release notes](https://developer.android.com/tools/releases/platforms)
- targetSdkVersion - [doc](https://support.google.com/googleplay/android-developer/answer/9859152?rd=1#targetsdk)
    - [target API level req for Google Play](https://support.google.com/googleplay/android-developer/answer/11926878?sjid=12347168255939481243-EU)

