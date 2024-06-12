# url-shortener

- 입력받은 string 을 shorten string 으로 변환해주는 api 를 제공 합니다.
    - 직관적인 예시를 위해 url 을 대상으로 합니다.
- 실무와 유사하도록 업무, 기능 요건 변경을 phase 로 표현 했습니다.
    - hexagonal architecture 를 기반 합니다.

### environment

- spring boot, kotlin
- mysql, redis
- testcontainers, datafaker

### testcode - phase 3

![image](https://github.com/Hyune-s-lab/url-shortener/assets/55722186/8c22b72f-1588-4ddb-b46f-c1fd4c38e0c1)

## feature

### ✅ phase1

![image](https://github.com/Hyune-s-lab/url-shortener/assets/55722186/a2bb4a68-5e4b-4d44-b441-403098eec4e9)

- [x] mysql 세팅 - docker
- [x] multi-module 구성
- [x] api 구현
    - [x] POST /shorten-url
    - [x] GET /shorten-url/{urlkey}
- [x] 기능 구현
    - [x] url 을 urlkey (shorten-url) 로 변환
    - [x] 생성된 urlkey 은 24 시간 동안 유효

### ✅ phase2

![image](https://github.com/Hyune-s-lab/url-shortener/assets/55722186/9c3ee484-e39d-425c-88f3-76feca4a595d)

- [x] 유효기간 세분화
    - free: 1 day, basic: 31 day, premium: unlimited
- [x] testcontainers 적용
    - local, test
- [x] 표준 예외 처리

### ✅ phase3

![image](https://github.com/Hyune-s-lab/url-shortener/assets/55722186/fdb1ea56-dc35-4002-91ab-853d139d0245)

- [x] redis 적용
    - @RedisHash 활용, ttl 24 hour

### phase4

- [ ] redis 개선
    - @Cacheable 적용
