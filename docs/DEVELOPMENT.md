# Development

## Prerequisites

- Android Studio with a current stable Android SDK.
- JDK version supported by the selected Android Gradle Plugin.
- A test device or emulator. Enterprise provisioning behavior must be validated on supported physical devices.
- Firebase project credentials for backend integration; keep `google-services.json` out of version control.

## Implementation sequence

1. Establish Kotlin/Compose app shell and CI build.
2. Add enrollment-state detection and a documented, supported Android Enterprise provisioning path.
3. Implement authenticated device registration, one-time pairing, revocation, and policy sync.
4. Add capability discovery and policy application with explicit error reporting.
5. Add consent-based MediaProjection screen sharing and WebRTC signaling.
6. Add optional, separately consented accessibility assistance, visible controls, and audit events.
7. Add admin console, tests, security review, and release documentation.

## Validation

Test fresh install, enrollment, unenrollment, offline/online transitions, token expiry, revoked devices, denied screen-capture consent, disabled accessibility, Android version differences, and OEM-specific restrictions. Do not test on devices or accounts without authorization.
