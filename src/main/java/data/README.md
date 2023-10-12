# data 패키지의 dto, entity

- dto : 뷰(view) 및 컨트롤러(controller), DAO의 3계층을 사용자가 원하는 데이터 형식에 맞게 만든 VO
  - FoodOrderDTO: restFood 테이블과 orders 테이블의 조인 결과를 저장한 VO
- entity : db의 테이블과 1:1 매칭이 되게 만든 VO
  - Food: restFood 테이블과 1:1 매칭