# DroidCommand Ultimate

Android Enterprise device management and consent-based remote support.

> **Status:** Early Android MVP. The app displays live local device telemetry. Firebase, enterprise enrollment, remote pairing, screen sharing, and remote assistance are not yet connected or implemented.

## Goals

- Android Enterprise Device Owner provisioning for organization-owned, appropriately enrolled devices.
- One-time administrator pairing followed by persistent, revocable device trust.
- Device inventory, policy status, and auditable administrative actions.
- User-visible, consent-based screen sharing using Android MediaProjection.
- Optional remote assistance using Android's supported Accessibility APIs, only after explicit user enablement and with a persistent indicator.
- Capability detection and graceful fallback across Android versions and OEM builds.

## Security and platform boundaries

Device Owner is a managed-device role, not unrestricted root access. It does not bypass Android permission prompts or grant silent screen capture. Enrollment must use a supported Android Enterprise provisioning flow; some flows require a factory reset. MediaProjection and Accessibility are separately governed by Android and require user-facing authorization. The app must not conceal monitoring, bypass permissions, collect passwords, keylog, exploit vulnerabilities, or control devices without authorization.

Persistent trust means the device can reconnect to the service after network interruption; it does **not** mean perpetual screen capture or unattended interactive control. Each screen-sharing or assistance session must be visible, scoped, and stoppable by the device user.

## Repository layout

- `app/` — Android application source (to be expanded).
- `docs/ARCHITECTURE.md` — component boundaries and data flow.
- `docs/SECURITY.md` — threat model and safeguards.
- `docs/DEVELOPMENT.md` — local build setup and implementation sequence.

## Planned stack

Kotlin, Jetpack Compose, Android Management APIs / DevicePolicyManager where applicable, Firebase Authentication and Firestore, and WebRTC for real-time media. Production deployments should evaluate Android Management API eligibility and Google Play policies before selecting a management model.

## Build status

The initial commit establishes project documentation. Android build configuration and functional modules are intentionally staged next; no claim of working remote control or enterprise enrollment is made yet.

## License

No license has been selected. All rights reserved by default until a license is added.
