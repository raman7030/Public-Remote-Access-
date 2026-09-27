# Architecture

## Components

1. **Android agent** — enrollment state, managed configuration, device capability reporting, local consent UI, and policy application through supported Android APIs.
2. **Admin console** — authenticated operator interface for device inventory, policy assignment, and session requests.
3. **Backend** — identity, device registry, short-lived session signaling, authorization checks, audit events, and revocation.
4. **Realtime transport** — WebRTC for user-approved screen sharing; signaling is authenticated and session-scoped.

## Trust and pairing

- Pairing uses a short-lived, single-use code or QR challenge.
- Bind the enrolled device to a tenant and device identity after server-side validation.
- Store refresh credentials using Android Keystore-backed storage; never embed service secrets in the APK.
- Support device unlink, administrator revocation, credential rotation, and lost-device procedures.
- Reconnection restores device presence and policy sync only. It must not silently resume screen capture or remote input.

## Session flow

1. An authenticated operator requests a support session for a device they are authorized to manage.
2. The agent displays the requester, purpose, requested capabilities, and clear accept/decline controls.
3. Screen sharing starts only after Android MediaProjection consent.
4. Remote input, if implemented, is a distinct capability requiring the user to enable the approved Accessibility service and accept the session.
5. Both sides display an active-session indicator; the device user can stop immediately.
6. End the session, revoke ephemeral credentials, and write an audit event.

## Management

Use Android Enterprise-supported enrollment and DevicePolicyManager APIs for policies that are available to the app's actual management role. Detect API level, device-owner status, OEM restrictions, and policy support before exposing controls. Never present unsupported controls as successful.
