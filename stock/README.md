# Stock Analysis System

미국 NASDAQ 시총 상위 500 종목의 일별 시세를 수집·저장하고, 소외지수(NI) / RSI / 이동평균선 / 거래량 등의 지표로 분석하는 시스템.

## 모듈 구조

```
stock/
├── common-module/    공통 엔티티
├── daemon-module/    데몬 (현재 비활성)
├── main-module/      Spring Boot 백엔드 (REST API + 배치 + 분석)
├── frontend/         React + Vite 분석 화면
└── buildSrc/         Gradle convention plugin
```

## 기술 스택

- **백엔드**: Java 17, Spring Boot 3.1.x, Spring Data JPA, Spring Batch, Flyway, Hibernate 6, MySQL, Redis, jsoup
- **프론트**: React 18, TypeScript, Vite, Tailwind CSS, AG Grid
- **외부 API**: KIS(한국투자증권) 해외주식 시세, stockanalysis.com (universe 갱신)

## 사전 요구사항

- JDK 17 이상 (JDK 21 권장)
- Node.js 18 이상 (Vite 4 사용 중)
- MySQL 8 (`stock` database, `root/root1234` — `main-module/src/main/resources/application.yml` 참조)
- Redis (KIS API 토큰 캐시)
- 환경변수 `JASYPT_PASSWORD` (암호화된 KIS 키 복호화)

## 실행 방법

### 1. 백엔드 (Spring Boot)

IntelliJ에서 `StockApplication` Run, 또는:

```bash
cd /Users/imchaewon/git/study/stock
./gradlew :main-module:bootRun
```

- 포트: `8080`
- 첫 실행 시 Flyway가 V1~V4 마이그레이션 자동 실행

### 2. 프론트 (Vite dev server)

```bash
cd /Users/imchaewon/git/study/stock/frontend
npm install   # 최초 1회
npm run dev
```

- 포트: `5173`
- `/api` 요청은 `localhost:8080`으로 자동 프록시 (`vite.config.ts` 참조)
- 브라우저: <http://localhost:5173>

## 주요 기능

| 기능 | 설명 |
|---|---|
| Universe 자동 관리 | 매주 월요일 05:00 KST에 stockanalysis.com에서 NASDAQ 시총 상위 500 갱신 |
| 매일 시세 수집 | KIS API로 universe 전 종목 일별 시세 수집 → `overseas_stock_snapshot` |
| 과거 데이터 백필 | `POST /internal/batch/overseas-stock/backfill` 비동기 백필 |
| 분석 화면 | 종목별 NI / RSI / 정배열 / Bull Score / Triple Bull 시그널 |
| 시장 국면 분류 | 강세 추세 / 강세 고변동 / 약세 횡보 / 약세 고변동 |
| 가격·거래량 변동률 | N일/N주 프리셋 + 커스텀 윈도우 |

## 주요 API

```
GET  /api/analysis/overseas?baseDate=&daysWindows=&weeksWindows=
GET  /api/analysis/market-regime?baseDate=&historyDays=
POST /internal/universe/refresh
POST /internal/batch/overseas-stock/run?bizDate=
POST /internal/batch/overseas-stock/backfill?startDate=&endDate=
```

baseDate 미지정 시 DB 최신 영업일 자동 사용.

## 지표 정의

- **소외지수 (NI)**: `(현재가 − 52주저점) / (52주고점 − 52주저점) × 100`. 100에 가까울수록 52주 고점 근처
- **RSI(14)**: Wilder smoothing 기준 모멘텀 지표
- **정배열**: `SMA20 > SMA50 > SMA200`
- **Bull Score**: `(NI + RSI + 정배열점수) / 3`, 0~100
- **Triple Bull**: `NI ≥ 75 AND RSI > 70 AND 정배열` (강세 추세에서 가장 강한 매수 후보)
