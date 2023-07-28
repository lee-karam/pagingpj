# paging_ex1
# DB table 생성
```
Hibernate:

    drop table if exists paging_tb_1
Hibernate:

    create table paging_tb_1 (
       id bigint not null auto_increment,
        board_content varchar(255) not null,
        board_title varchar(255) not null,
        board_writer varchar(255) not null,
        create_time datetime(6),
        update_time datetime(6),
        primary key (id)
    ) engine=InnoDB
```
![img.png](img.png)# pagingpj
# pagingpj
