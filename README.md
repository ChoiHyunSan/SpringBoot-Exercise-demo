# Spring Boot Q&A Board

## 프로젝트 소개
Spring Boot를 활용한 Q&A 게시판 프로젝트입니다. 사용자들이 질문을 작성하고 답변을 달 수 있는 커뮤니티 플랫폼을 구현했습니다.

## 기술 스택
- **Backend**
  - Java 21
  - Spring Boot 3.2.2
  - Spring Data JPA
  - MySQL

- **Frontend**
  - Thymeleaf
  - Bootstrap 5
  - JavaScript

## 주요 기능
- 질문 CRUD 기능
  - 질문 목록 조회
  - 질문 상세 보기
  - 질문 등록
  - 질문 수정/삭제
- 답변 CRUD 기능
  - 답변 등록
  - 답변 수정/삭제
- RESTful API 구현

## 프로젝트 구조
```
src/
├── main/
│   ├── java/
│   │   └── SpringBoot_Exercise/
│   │       └── demo/
│   │           ├── controller/
│   │           ├── domain/
│   │           └── repository/
│   └── resources/
│       └── templates/
└── test/
    └── java/
        └── SpringBoot_Exercise/
            └── demo/
```

## ERD
```
Question
- id (PK)
- subject
- content
- createDate
- answerList

Answer
- id (PK)
- content
- createDate
- question (FK)
```

## 시작하기

### 필수 조건
- Java 21
- MySQL 8.0 이상
- Gradle

### 데이터베이스 설정
1. MySQL에서 데이터베이스 생성:
```sql
CREATE DATABASE qna_board;
```

2. `application.properties` 설정:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/qna_board
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 빌드 및 실행
```bash
./gradlew build
./gradlew bootRun
```

## API 문서
- GET /api/questions - 질문 목록 조회
- POST /api/questions - 새 질문 등록
- GET /api/questions/{id} - 질문 상세 조회
- POST /api/questions/{id}/answers - 답변 등록
