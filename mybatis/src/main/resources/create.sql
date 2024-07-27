DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item`
(
    `id`         BIGINT(0),
    `product_id` BIGINT(0),
    `amount`     INT(0),
    `order_id`   BIGINT(0),
    PRIMARY KEY (`id`)
) ENGINE = INNODB;

DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`
(
    `id`          BIGINT(0) NOT NULL auto_increment,
    `name`        VARCHAR(50),
    `price`       DECIMAL(20, 2),
    `description` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE = INNODB;

DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `id`          BIGINT(0) NOT NULL auto_increment,
    `customer_id` BIGINT(0),
    `address_id`  BIGINT(0),
    `create_time` BIGINT(0),
    PRIMARY KEY (`id`)
) ENGINE = INNODB;

DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`
(
    `id`    BIGINT(0) NOT NULL auto_increment,
    `name`  VARCHAR(100),
    `phone` VARCHAR(30),
    PRIMARY KEY (`id`)
) ENGINE = INNODB;

DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address`
(
    `id`          BIGINT(0) NOT NULL auto_increment,
    `street`      VARCHAR(50),
    `city`        VARCHAR(50),
    `country`     VARCHAR(50),
    `customer_id` BIGINT(0),
    PRIMARY KEY (`id`)
) ENGINE = INNODB;