create table aircontrol
(
    id         varchar(255) not null
        primary key,
    topics     varchar(255) null,
    msg        varchar(255) null,
    hardwareid int          null,
    behaviour  varchar(255) null
);

create table airhardware
(
    id         varchar(255) not null
        primary key,
    name       varchar(255) not null,
    hardwareid int          not null,
    time       bigint       not null,
    status     tinyint(1)   not null,
    type       varchar(255) not null
);

create table airpowerdata
(
    id         varchar(255) not null
        primary key,
    hardwareid int          not null,
    time       bigint       not null,
    UA         varchar(255) not null,
    IA         varchar(255) not null,
    PA         varchar(255) not null,
    KWH        varchar(255) not null
);

create table airstabledata
(
    id                                 varchar(255) not null
        primary key,
    hardwareID                         int          null,
    time                               bigint       null,
    temperature                        varchar(255) null,
    humidity                           varchar(255) null,
    co2                                varchar(255) null,
    waterSpeed                         varchar(255) null,
    fanOutletWaterTemperature          varchar(255) not null,
    fanIntakeWaterTemperature          varchar(255) not null,
    coolingTowerOutletWaterTemperature varchar(255) not null,
    coolingTowerIntakeWaterTemperature varchar(255) not null
);

create table airuser
(
    id        varchar(255) not null
        primary key,
    username  varchar(255) null,
    passwd    varchar(255) null,
    authority varchar(255) null
);


