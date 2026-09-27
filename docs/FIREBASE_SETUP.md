# Firebase backend setup

This repository is prepared for a Firebase-backed control plane, but it is **not connected to a Firebase project**. No project credentials or deployment have been supplied.

## Required setup

1. Create a Firebase project owned by the organization operating the managed devices.
2. Register the Android application with package name `com.droidcommand.ultimate`.
3. Add the generated `google-services.json` to `app/` (do not commit service-account private keys or administrator credentials).
4. Enable Firebase Authentication with an approved sign-in method and configure App Check.
5. Create Firestore and deploy the repository's deny-by-default rules using the Firebase CLI.
6. Implement and deploy trusted backend authorization before allowing any device or session documents. The backend must verify tenant membership, device enrollment, pairing expiry, revocation, and per-session consent.
7. Store secrets in GitHub Actions Secrets or a managed secret store; never embed server credentials in the APK.

## Security model

- Pairing is initiated by an authorized administrator and confirmed on the target device.
- Pairing tokens must be single-use, short-lived, and exchanged over TLS.
- Persisted trust authorizes device presence and policy synchronization only; it does not grant ongoing screen capture or unattended interaction.
- Each screen-sharing session requires Android's system-mediated MediaProjection consent and a visible, persistent session indicator with a stop action.
- Remote actions must be narrowly allow-listed, logged, and gated by Android-supported management APIs and the enrolled device's policy state.
- Firestore rules remain deny-by-default until a tested tenant/device authorization design is deployed. Never use permissive rules such as `allow read, write: if true`.

## Current implementation status

The Android app currently displays live local model, Android version, battery percentage, and network transport. Enrollment, Firebase authentication, remote pairing, policy delivery, screen sharing, and remote assistance are not yet implemented. A Firebase project configuration is required before those backend features can be built and tested end-to-end.
