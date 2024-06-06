create table food
(
    id       varchar(10) null comment '食物id',
    name     varchar(10) null comment '食物名称',
    calories float       null comment '食物所含卡路里'
)
    comment '食物';

create table user
(
    id                   varchar(10) not null comment '用户账号'
        primary key,
    name                 varchar(10) not null comment '用户昵称',
    password             varchar(10) not null comment '密码',
    sex                  int         null comment '性别',
    birthday             date        not null comment '生日',
    height               float       not null comment '身高',
    weight               float       not null comment '体重',
    targetweight         varchar(5)  null comment '目标体重',
    past_medical_history longtext    null comment '过往病史'
);

create table healthanalysis
(
    Date        date        null comment '日期',
    userid      varchar(10) null comment '用户id',
    BMI         float       null,
    healthLevel int         null comment '健康等级',
    constraint HealthAnalysis_user_id_fk
        foreign key (userid) references user (id)
)
    comment '健康分析表';

create table sleep
(
    userid       varchar(10) not null comment '用户名',
    timequantume int         null comment '睡眠总时长',
    weak         datetime    null comment '起床时间',
    bed          datetime    null comment '入睡时间',
    date         date        null comment '日期',
    constraint Sleep_user_id_fk
        foreign key (userid) references user (id)
)
    comment '睡眠质量表';

create table sportrecord
(
    date            date        not null comment '运动日期',
    type            text        null comment '运动类别',
    consumeCalories float       null comment '消耗卡路里',
    userid          varchar(10) not null comment '用户id',
    constraint userid
        foreign key (userid) references user (id)
)
    comment '运动记录表';

create table uerdiet
(
    date            date        null comment '日期',
    consumeCalories float       null comment '所含卡路里',
    foods           text        null comment '食物',
    userid          varchar(10) null,
    constraint uerdiet_user_id_fk
        foreign key (userid) references user (id)
)
    comment '用户饮食表';


