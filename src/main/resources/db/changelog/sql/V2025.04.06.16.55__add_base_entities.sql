-- liquibase formatted sql

-- changeset yehorhavryliuk:1743947251121-5
CREATE TABLE item
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    type_id    BIGINT,
    user_id    BIGINT,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- changeset yehorhavryliuk:1743947251121-6
CREATE TABLE item_type
(
    id          BIGSERIAL PRIMARY KEY,
    type        VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE
);

-- changeset yehorhavryliuk:1743947251121-7
CREATE TABLE lot
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITH TIME ZONE,
    item_id          BIGINT NOT NULL UNIQUE,
    seller_id        BIGINT NOT NULL,
    best_bid_user_id BIGINT,
    detail_id        BIGINT NOT NULL UNIQUE
);

-- changeset yehorhavryliuk:1743947251121-8
CREATE TABLE lot_detail
(
    id               BIGSERIAL PRIMARY KEY,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITH TIME ZONE,
    initial_price    DOUBLE PRECISION,
    bid_price        DOUBLE PRECISION,
    expire_date_time TIMESTAMP WITH TIME ZONE,
    status           VARCHAR(255) NOT NULL
);

-- changeset yehorhavryliuk:1743947251121-11
ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_TYPE FOREIGN KEY (type_id) REFERENCES item_type (id);

-- changeset yehorhavryliuk:1743947251121-12
ALTER TABLE item
    ADD CONSTRAINT FK_ITEM_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset yehorhavryliuk:1743947251121-13
ALTER TABLE lot
    ADD CONSTRAINT FK_LOT_ON_BEST_BID_USER FOREIGN KEY (best_bid_user_id) REFERENCES users (id);

-- changeset yehorhavryliuk:1743947251121-14
ALTER TABLE lot
    ADD CONSTRAINT FK_LOT_ON_DETAIL FOREIGN KEY (detail_id) REFERENCES lot_detail (id);

-- changeset yehorhavryliuk:1743947251121-15
ALTER TABLE lot
    ADD CONSTRAINT FK_LOT_ON_ITEM FOREIGN KEY (item_id) REFERENCES item (id);

-- changeset yehorhavryliuk:1743947251121-16
ALTER TABLE lot
    ADD CONSTRAINT FK_LOT_ON_SELLER FOREIGN KEY (seller_id) REFERENCES users (id);

