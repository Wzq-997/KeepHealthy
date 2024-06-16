create table food
(
    id       varchar(10) null comment '食物id',
    name     varchar(10) null comment '食物名称',
    calories double      null comment '食物所含卡路里'
)
    comment '食物';

create table sporttype
(
    sport_id       varchar(11) not null comment '运动编号'
        primary key,
    sport_name     varchar(10) not null comment '运动名称',
    single_calorie double      not null comment '标准卡路里消耗'
)
    comment '运动类型';

create table user
(
    id                   varchar(10) not null comment '用户账号'
        primary key,
    name                 varchar(10) not null comment '用户昵称',
    password             varchar(10) not null comment '密码',
    sex                  int         null comment '性别',
    birthday             date        not null comment '生日',
    height               double      not null comment '身高',
    weight               double      not null comment '体重',
    targetweight         double      null comment '目标体重',
    past_medical_history longtext    null comment '过往病史'
);

create table healthanalysis
(
    Date         date        null comment '日期',
    user_id      varchar(10) null comment '用户id',
    BMI          double      null,
    health_level int         null comment '健康等级',
    constraint HealthAnalysis_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '健康分析表';

create table sleep
(
    user_id       varchar(10) not null comment '用户名',
    time_quantume int         null comment '睡眠总时长',
    weak          datetime    null comment '起床时间',
    bed           datetime    null comment '入睡时间',
    date          date        null comment '日期',
    constraint Sleep_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '睡眠质量表';

create table sportrecord
(
    date             date        not null comment '运动日期',
    sport_id         varchar(11) null comment '运动类别',
    consume_calories double      null comment '消耗卡路里(分钟)',
    user_id          varchar(10) not null comment '用户id',
    last_time        double      null comment '持续时间',
    constraint userid
        foreign key (user_id) references user (id)
)
    comment '运动记录表';

create table userdiet
(
    date             date        null comment '日期',
    consume_calories double      null comment '所含卡路里',
    food_id          varchar(10) null comment '食物编号',
    user_id          varchar(10) null,
    constraint uerdiet_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '用户饮食表';


