# GlobalBooks Service Governance Policy

## Versioning
- URL versioning: /rest/{service}/v{major}/...
- SOAP namespace: http://globalbooks.com/{service}/v{major}
- Breaking changes bump major.

## SLAs
- Availability: 99.5% monthly per service.
- Latency: Catalog GetBook p50 ≤ 50ms, p95 ≤ 200ms.
- Error rate: <0.5% 5xx per month.

## Deprecation
- 6 months notice for major changes.
- Run v1 and v2 concurrently during migration window.

## Security
- SOAP: WS-Security tokens (UsernameToken, X.509 for signing).
- REST: OAuth2 (Authorization Code + PKCE for UI, Client Credentials for server-to-server).
