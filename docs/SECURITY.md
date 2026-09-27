# Security and privacy requirements

## Non-negotiable safeguards

- Explicit organization authorization and supported enterprise enrollment.
- Transparent app identity, persistent management disclosure, and user-visible session indicators.
- Least privilege, role-based access, tenant isolation, MFA for administrators, and short-lived scoped tokens.
- User consent for screen capture and interactive assistance; no permission-dialog automation or bypass.
- TLS for all network traffic; encryption at rest; Android Keystore for device-held secrets.
- Audit log for enrollment, policy changes, session requests, consent, session start/stop, and revocation.
- Data minimization, configurable retention, export/deletion procedures, and documented privacy notice.
- Rate limiting, replay protection, abuse reporting, and rapid credential revocation.

## Prohibited implementation patterns

Do not implement stealth mode, hidden capture, covert persistence, credential harvesting, keylogging, arbitrary command execution, privilege escalation, exploit-based enrollment, or unattended remote control. Do not claim Device Owner grants root or universal input control.

## Release gate

Before production: complete a threat model, security review, dependency audit, penetration test, privacy/legal review, Google Play policy review (if distributed through Play), and device/OEM compatibility testing.
