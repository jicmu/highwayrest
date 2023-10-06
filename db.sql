create table restMaster (
    masterNo        number          primary key,        -- 고유번호
    id              varchar2(50)    unique,             -- 아이디
    password        varchar2(50)    not null,           -- 비밀번호
    logout          date            default sysdate,    -- 마지막 로그아웃 날짜
    lastIn          date            default sysdate,    -- 마지막 로그인 날짜
    tel             varchar2(50)    not null,       -- 휴게소 번호
    status          number(1)       default 0           -- 0: 휴업 / 1: 영업 / 2: 쉬는시간
);

create table food (
    foodNo          number          primary key,        -- 고유번호
    menu            varchar2(40)    not null,           -- 음식 이름
    accVol          number          default 0,          -- 누적판매량(default 0)
    restNo          number          not null            -- 휴게소 코드
);

create table orders (
    orderNo         number          primary key,                                    -- 고유번호
    menu            varchar(40)     not null,                                       -- 메뉴 이름
    restNo          number          not null,                                       -- 휴게소 코드
    pay             number          default 0,                                      -- 결제 여부 0: 결제안됨 / 1: 결제됨
    memberNo        number          references member(memberNo)on delete cascade,   -- 회원번호
    ordersNo        number          not null,                                       -- 주문 묶음 번호
    status          number          check(status BETWEEN 0 AND 3),                  -- 0: 미주문  / 1: 수락 / 2: 거부 / 3: 취소
    oDate           date            default sysdate                                 -- 주문 날짜
);

create table search (
    searchNo       number          primary key,                                 -- 고유번호
    searchType     number          check(searchType BETWEEN 1 AND 3),           -- 1. 고속도로 / 2. 휴게소 / 3. 메뉴
    memberNo       number          references member(memberNo) on delete cascade,     -- 회원 no
    searchDate     date            not null,                                    -- 검색 일자
    searchWord     varchar2(100)   not null                                     -- 사용자가 입력한 검색어
);

create table review(
    reviewNo        number          primary key,                                    -- 고유 번호
    memberNo        number          references member(memberNo) on delete cascade,  -- 작성 회원
    wDate           date            default sysdate,                                -- 작성일
    content         varchar(200)    not null,                                       -- 후기 내용
    star            number(1)       check(star BETWEEN 0 AND 5),                    -- 별점(1~5점)
    orderNo         number          references orders(orderNo) on delete cascade   -- 주문번호
    --orderMenu       varchar2(40)    not null--references orders(menu)               -- 주문메뉴
);

create table member (
   memberNo         number          primary key,    -- 고유 번호
   id               varchar(50)     unique,         -- 로그인 아이디
   password         varchar(50)     not null,       -- 로그인 비밀번호
   name             varchar(50)     not null,       -- 이름
   nickname         varchar(50)     not null,       -- 닉네임
   email            varchar(50)     unique,         -- 이메일
   tel              varchar(50)     unique          -- 전화번호
);

