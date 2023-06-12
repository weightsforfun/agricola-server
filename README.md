# 프로젝트 정보
<div align="center">
  
<img src="https://github.com/dslov89/Agricola/assets/71018440/5413b40a-e5ab-45f0-807e-f35cb068ef76"  width=300 >
  
 ### Software Engineering 팀 프로젝트 💻
  
 </div>



### 아그리콜라 웹 보드게임 서버 
- 아그리콜라 보드게임을 웹게임으로 구현
- 현재 서버는 게임방, 사용자 개인 소켓 관리, 메세지 전달 기능 수행
- 현재는 프로토타입이고 추후 버전 업데이트 예정

### 기술 스택
- Spring Boot
- Spring Data JPA
- STOMP
- Mockito
- Junit 5

# 요구사항

- 게임방 관리
  - [x] 게임방 CRUD
  - [x] 게임방 입장시 자원 분배
  - [x] 게임 자원 및 메세지 초기화
  - [x] converter 를 통한 데이터 필요 자료형으로 변환

- 유저 소켓 관리
  - [x] 소켓 연결시 ID 부여
  - [x] 소켓을 통한 개인 channel 통신
  - [x] 구독한 channel 통신
  - [x] 엔드포인트 처리
  - [ ] simple broker -> relay broker 로 변경

- Security 적용
  - [ ] Stomp Interceptor 에 security filter 적용
  - [ ] JWT 적용 -> Temporal Principal을 JWT로 대체

# 서버 STOMP 구조

![image](https://github.com/weightsforfun/agricola-server/assets/38395623/2e67cf9a-59ce-4fba-9d27-c69fa79e3684)

### 기능
- 게임방 CRUD
- 게임방마다 게임에 필요한 자원들을 User 에게 socket을 통해 분배하고 초기화 합니다.
- 자원들을 DB 저장하거나 메세지를 보낼때 Converter를 사용해서 자료형을 변경합니다. 
- Request Interceptor,Pre Handler를 통해 사용자에게 ID 부여 후 해당 ID를 기반으로 개인 Socket통신을 진행합니다. 
- Annotation Method 를 통해 각 Data들을 구분하여 처리합니다.
- User Destination 을 통해 다른 사용자에게 보이면 안되는 정보, 해당 유저한테만 전달하는 고유 정보를 전달합니다.
- Simple Broker을 통해 기본 웹소켓 통신을 합니다.
