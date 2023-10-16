CREATE TABLE highway (
                         no          number          primary key,    -- 고유 번호
                         routeNo     number          unique,         -- 노선 번호
                         routeCd     varchar2(50)    unique,         -- 노선 코드
                         routeNm     varchar2(50)    unique          -- 고속도로명
);

create sequence seq_highway_no;

create table highwayrest (
                             no          number          primary key,                        -- 고유 번호
                             svarCd      varchar2(30)    unique,                             -- 휴게소 번호
                             svarNm      varchar2(30)    unique,                             -- 휴게소 이름
                             gudClssCd   number          check(gudClssCd BETWEEN 0 AND 1),   -- 0: 상행 / 1: 하행
                             rprsTelNo   varchar2(30)    unique,                             -- 휴게소 번호
                             routeCd     varchar2(20)    references highway(routeCd),        -- 노선 코드
                             svarAddr    varchar2(80)    not null                            -- 휴게소 주소지
);
alter table highwayrest modify svarNm varchar2(50);
alter table highwayrest modify svarAddr varchar2(100);

create sequence seq_highwayrest_no;

create table restfood (
                          no          number          primary key,                    -- 고유 번호
                          name        varchar2(100)   not null,                       -- 음식 이름
                          accVol      number          default 0,                      -- 누적판매량
                          stdRestCd   varchar2(30)    references highwayrest(svarCd)  -- 휴게소 번호
);

alter table restfood add foodCost number default 0 not null;

create sequence seq_restfood_no;

create table restMaster (
                            masterNo        number          primary key,                        -- 고유번호
                            id              varchar2(50)    references highwayrest(svarCd),     -- 아이디(노선코드)
                            logout          date            default sysdate,                    -- 마지막 로그아웃 날짜
                            lastIn          date            default sysdate,                    -- 마지막 로그인 날짜
                            tel             varchar2(50)    not null,                           -- 휴게소 번호
                            status          number(1)       default 0                           -- 0: 휴업 / 1: 영업 / 2: 쉬는시간
);

create sequence seq_restMaster_no;

create table member (
                        memberNo         number          primary key,    -- 고유 번호
                        id               varchar(50)     unique,         -- 로그인 아이디
                        password         varchar(50)     not null,       -- 로그인 비밀번호
                        name             varchar(50)     not null,       -- 이름
                        nickname         varchar(50)     not null,       -- 닉네임
                        email            varchar(50)     unique,         -- 이메일
                        tel              varchar(50)     unique          -- 전화번호
);

create sequence seq_member;

create table orders (
                        orderNo         number          primary key,                                    -- 고유번호
                        foodNo          number          references restfood(no),                                   -- 메뉴 이름
                        restNo          number          not null,                                       -- 휴게소 코드
                        pay             number          default 0,                                      -- 결제 여부 0: 결제안됨 / 1: 결제됨
                        quantity        number          default 1,                                      -- 주문한 수량
                        memberNo        number          references member(memberNo)on delete cascade,   -- 회원번호
                        ordersNo        varchar2(21)    not null,                                       -- 결제 번호
                        status          number          check(status BETWEEN 0 AND 4),                  -- 0: 결제 완료  / 1: 수락 / 2: 거부 / 3: 취소 / 4: 완료
                        oDate           date            default sysdate                                 -- 주문 날짜
);

create sequence seq_order;

create sequence seq_orders;

create table search (
                        searchNo       number          primary key,                                 -- 고유번호
                        searchType     number          check(searchType BETWEEN 1 AND 3),           -- 1. 고속도로 / 2. 휴게소 / 3. 메뉴
                        memberNo       number          references member(memberNo) on delete cascade,     -- 회원 no
                        searchDate     date            not null,                                    -- 검색 일자
                        searchWord     varchar2(100)   not null                                     -- 사용자가 입력한 검색어
);

create sequence seq_search_no;

create table review(
                       reviewNo        number          primary key,                                    -- 고유 번호
                       memberNo        number          references member(memberNo) on delete cascade,  -- 작성 회원
                       wDate           date            default sysdate,                                -- 작성일
                       content         varchar(200)    not null,                                       -- 후기 내용
                       star            number(1)       check(star BETWEEN 0 AND 5),                    -- 별점(1~5점)
                       orderNo         number          references orders(orderNo) on delete cascade,   -- 주문번호
                       img1            varchar2(100),
                       img2            varchar2(100),
                       img3            varchar2(100)
);

create sequence seq_review;

insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000306', '0544331523');
insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000062', '0553840144');
insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000021', '0432601053');
insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000338', '07082407761');

insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000011', '0415887868');
insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000034', '0437312233');
insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000024', '0432608035');
insert into restMaster(masterNo, id, tel) values (seq_restMaster_no.nextval, '000015', '0415512480');

select re.id id, hi.svarnm name, re.status status from restmaster re, highwayrest hi where re.id = hi.svarcd