create table food
(
    id       varchar(10) null comment '食物id',
    name     varchar(10) null comment '食物名称',
    calories double      null comment '食物所含卡路里'
)
    comment '食物';
/*数据插入：*/
INSERT INTO keephealthy.food (id, name, calories) VALUES ('F0001', '苹果', 52);
INSERT INTO keephealthy.food (id, name, calories) VALUES ('F0002', '香蕉', 89);
INSERT INTO keephealthy.food (id, name, calories) VALUES ('F0003', '葡萄', 43);
INSERT INTO keephealthy.food (id, name, calories) VALUES ('F0004', '橙子', 47);
INSERT INTO keephealthy.food (id, name, calories) VALUES ('F0005', '菠萝', 50);

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
INSERT INTO keephealthy.user (id, name, password, sex, birthday, height, weight, targetweight, past_medical_history) VALUES ('U0001', '张三', '123456', 1, '1990-01-01', 170, 65, 60, 'null');
INSERT INTO keephealthy.user (id, name, password, sex, birthday, height, weight, targetweight, past_medical_history) VALUES ('U0002', '李四', '654321', 0, '1992-02-14', 165, 55, 50, 'null');
INSERT INTO keephealthy.user (id, name, password, sex, birthday, height, weight, targetweight, past_medical_history) VALUES ('U0003', '王五', 'abcdef', 1, '1988-08-08', 180, 80, 75, 'null');
INSERT INTO keephealthy.user (id, name, password, sex, birthday, height, weight, targetweight, past_medical_history) VALUES ('U0004', '赵六', 'fedcba', 0, '1995-05-20', 175, 70, 65, 'null');
INSERT INTO keephealthy.user (id, name, password, sex, birthday, height, weight, targetweight, past_medical_history) VALUES ('U0005', '孙七', '765432', 1, '1991-11-11', 160, 50, 55, 'null');


create table healthanalysis
(
    Date        date        null comment '日期',
    user_id      varchar(10) null comment '用户id',
    BMI         double      null,
    health_level int         null comment '健康等级',
    constraint HealthAnalysis_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '健康分析表';
INSERT INTO keephealthy.healthyanalysis (Date, user_id, BMI, health_level) VALUES ('2023-01-01', 'U0001', 22.5, 1);
INSERT INTO keephealthy.healthyanalysis (Date, user_id, BMI, health_level) VALUES ('2023-01-15', 'U0002', 20, 2);
INSERT INTO keephealthy.healthyanalysis (Date, user_id, BMI, health_level) VALUES ('2023-02-01', 'U0003', 23, 3);
INSERT INTO keephealthy.healthyanalysis (Date, user_id, BMI, health_level) VALUES ('2023-02-15', 'U0004', 21.5, 2);
INSERT INTO keephealthy.healthyanalysis (Date, user_id, BMI, health_level) VALUES ('2023-03-01', 'U0005', 19.5, 1);

create table userdiet
(
    date             date        null comment '日期',
    consume_calories double      null comment '所含卡路里',
    food_id          varchar(10) null comment '食物编号',
    user_id          varchar(10) null,
    constraint userdiet_user_id_fk
        foreign key (user_id) references user (id)
)
    comment '用户饮食表';

create table sporttype
(
    sport_id       varchar(11) not null comment '运动编号'
        primary key,
    sport_name     varchar(10) not null comment '运动名称',
    single_calorie double      not null comment '标准卡路里消耗'
)
    comment '运动类型';
INSERT INTO keephealthy.sporttype (sport_id, sport_name, single_calorie) VALUES ('S001', '跑步', 1.3);
INSERT INTO keephealthy.sporttype (sport_id, sport_name, single_calorie) VALUES ('S002', '瑜伽', 2.3);
INSERT INTO keephealthy.sporttype (sport_id, sport_name, single_calorie) VALUES ('S003', '游泳', 3.5);
INSERT INTO keephealthy.sporttype (sport_id, sport_name, single_calorie) VALUES ('S004', '骑行', 2.6);
INSERT INTO keephealthy.sporttype (sport_id, sport_name, single_calorie) VALUES ('S005', '散步', 0.3);
INSERT INTO keephealthy.sporttype (sport_id, sport_name, single_calorie) VALUES ('S006', '攀登', 5);

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
create table menu
(
    id      int auto_increment comment 'id'
        primary key,
    name    varchar(20) null comment '名称',
    menu_id varchar(10) null comment '菜谱编号',
    make    longtext    null comment '菜谱制作方法',
    image   longtext    null comment '菜谱图片',
    tips    longtext    null comment '小建议',
    calorie double      null comment '摄入卡路里'
);
# sleep表
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
INSERT INTO keephealthy.sleep (user_id, time_quantume, weak, bed, date) VALUES ('U0001', 7, '2023-01-02 07:00:00', '2023-01-01 23:00:00', '2023-01-01');
INSERT INTO keephealthy.sleep (user_id, time_quantume, weak, bed, date) VALUES ('U0002', 8, '2023-01-16 06:30:00', '2023-01-15 22:30:00', '2023-01-15');
INSERT INTO keephealthy.sleep (user_id, time_quantume, weak, bed, date) VALUES ('U0003', 6, '2023-02-02 08:00:00', '2023-02-01 23:30:00', '2023-02-01');
INSERT INTO keephealthy.sleep (user_id, time_quantume, weak, bed, date) VALUES ('U0004', 8, '2023-02-16 07:15:00', '2023-02-15 23:00:00', '2023-02-15');
INSERT INTO keephealthy.sleep (user_id, time_quantume, weak, bed, date) VALUES ('U0005', 9, '2023-03-02 06:45:00', '2023-03-01 22:15:00', '2023-03-01');