## 이 프로젝트는 EJMCompany 과제기록물 입니다.
패키지 구조는 기능별로 나눠 놨습니다.
크지 프로젝트 이기 때문에 무분별한 패키지생성을 하지 않앗습니다.

**요구사항**

공통 코드는 한글 명과 코드 값으로 이루어져 있습니다.<br>
각 공통 코드는 그룹을 가지고 있어 그룹으로 조회 할 수 있고, 개별 조회도 가능합니다.

**개발환경**
- JDK 11 (corretto)

- gradle version7

- H2 / MySql

- JPA

## 🙋‍♀️ Api Spe

### 🙋‍♀️ createdCode API
Post /api/code)
### Description
코드의 벨류값과 한국어이름을 넣어 공용코드를 생성해주는 API입니다.
그룹은 코드를 생성 할때 지정할 수도 있고,해당 그룹이 존재하지 않는다면 그룹을 생성해서 지정 해줍니다.

### Parameters
- codeValue (path parameter): 코드의 벨류값을 지정합니다
  - Type: String
  - Required: true
- koreaName (path parameter): 코드의 한국어이름을 지정합니다
  - Type: String
  - Required: true
- groupName (path parameter): 지정할 그룹을 선택합니다.
  - Type: String
  - Required: false

### Response
- Status: 200 OK
---
### 🙋‍♀️ createdGroup API
Post /api/group)
### Description
그룹이름을 넣으면 해당 그룹을 생성해주는 API입니다.

### Parameters
- groupName (path parameter): 지정할 그룹을 선택합니다.
  - Type: String
  - Required: true

### Response
- Status: 200 OK
---
### 🙋‍♀️ updateGroupByCodeName API
PUT /api/group)
### Description
 이미 생성된 코드의 그룹을 지정 / 변경 합니다.

 매개변수는 둘중 선택 가능하며 두개 다 사용도 가능한 동적 API 입니다.

### Parameters
- codeValue (path parameter): 코드의 벨류값을 검색합니다
  - Type: String
  - Required: false
- koreaName (path parameter): 코드의 한국어이름을 검색합니다
  - Type: String
  - Required: false
- groupName (path parameter): 지정할 그룹을 선택합니다.
  - Type: String
  - Required: true

### Response
- Status: 200 OK
---
### 🙋‍♀️ searchByKoreanNameOrCodeValue API
GET /api/code)
### Description
코드 검색 API입니다.

코드의 벨류값 또는 한국어 이름을 넣어 검색을 수행합니다.

### Parameters
- codeValue (path parameter): 코드의 벨류값을 검색합니다
  - Type: String
  - Required: false
- koreaName (path parameter): 코드의 한국어이름을 검색합니다
  - Type: String
  - Required: false

### Response
- Status: 200 OK
- Content-Type: application/json

```json
{
    "codeValue": "345",
    "koreaName": "킴",
    "groupName": "300"
}
```
---

### 🙋‍♀️ searchCodeListByGroupName API
GET /api/group)
### Description
이 엔드포인트는 코드 정보를 가져옵니다.

### Parameters
- groupName (path parameter): 검색할 그룹 명
  - Type: String
  - Required: true

### Response
- Status: 200 OK
- Content-Type: application/json

```json
    {
        "codeValue": "123",
        "koreaName": "잭",
        "groupName": "100"
    },
    {
        "codeValue": "567",
        "koreaName": "힐",
        "groupName": "100"
    }
```
