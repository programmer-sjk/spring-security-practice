# spring security practice
- 스프링 시큐리티의 기능, 다양한 인증, 내부 구조 학습을 위한 repo

## 인증 기능
- Basic, JWT Token 기반의 두 인증 방식을 제공
```text
- Basic(/, /health)
  - GET /health -> 전체 접근 가능
  - GET /       -> 메인 페이지로 Basic 인증 후 접근 가능
- JWT(/user**)
  - POST /users -> 회원가입 전체 접근 가능
  - GET  /users -> JWT 토큰 인증 후 접근 가능
```
