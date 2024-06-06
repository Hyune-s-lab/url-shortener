# url-shortener

- 입력받은 string 을 shorten string 으로 변환해주는 api 를 제공 합니다.
- 직관적인 예시를 위해 url 을 대상으로 합니다.
- hexagonal architecture 를 기반으로 하지만 일부 변형 하였습니다.
- 각 phase 는 업무, 기능 요건 변경에 따른 점진적인 발전을 구분 합니다.

### environment

- spring boot, kotlin
- mysql, redis
- testcontainers, datafaker

### testcode - phase 1

![image](https://github.com/Hyune-s-lab/url-shortener/assets/55722186/aa52a4a5-9c28-45e2-9c82-a1c567909fc2)

## feature

### ✅ phase1

![image](https://github.com/Hyune-s-lab/url-shortener/assets/55722186/a2bb4a68-5e4b-4d44-b441-403098eec4e9)

- [x] mysql 세팅 - docker
- [x] multi-module 구성
- [x] api 구현
    - [x] POST /shorten-url
    - [x] GET /shorten-url/{urlkey}
- [x] 기능 구현
    - [x] url 을 shorten-url (urlkey) 로 변환
    - [x] 생성된 shorten url 은 24 시간 동안 유효
