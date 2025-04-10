-- liquibase formatted sql

-- changeset yehorhavryliuk:1743947251121-5
CREATE TABLE items
(
    id BIGSERIAL PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    type_id    BIGINT,
    user_id    BIGINT,
    type       VARCHAR(50)  NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE
);

-- changeset yehorhavryliuk:1743947251121-6
CREATE TABLE lots
(
    id BIGSERIAL PRIMARY KEY,
    item_id          BIGINT NOT NULL UNIQUE,
    seller_id        BIGINT NOT NULL,
    best_bid_user_id BIGINT,
    detail_id        BIGINT NOT NULL UNIQUE,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at       TIMESTAMP WITH TIME ZONE
);

-- changeset yehorhavryliuk:1743947251121-8
CREATE TABLE lot_detail
(
    id BIGSERIAL PRIMARY KEY,
    initial_price DOUBLE PRECISION NOT NULL,
    bid_price     DOUBLE PRECISION,
    status        VARCHAR(50)      NOT NULL,
    expires_at    TIMESTAMP WITH TIME ZONE,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at    TIMESTAMP WITH TIME ZONE
);

-- changeset yehorhavryliuk:1743947251121-12
ALTER TABLE items
    ADD CONSTRAINT FK_ITEM_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset yehorhavryliuk:1743947251121-13
ALTER TABLE lots
    ADD CONSTRAINT FK_LOT_ON_BEST_BID_USER FOREIGN KEY (best_bid_user_id) REFERENCES users (id);

-- changeset yehorhavryliuk:1743947251121-14
ALTER TABLE lots
    ADD CONSTRAINT FK_LOT_ON_DETAIL FOREIGN KEY (detail_id) REFERENCES lot_detail (id);

-- changeset yehorhavryliuk:1743947251121-15
ALTER TABLE lots
    ADD CONSTRAINT FK_LOT_ON_ITEM FOREIGN KEY (item_id) REFERENCES items (id);

-- changeset yehorhavryliuk:1743947251121-16
ALTER TABLE lots
    ADD CONSTRAINT FK_LOT_ON_SELLER FOREIGN KEY (seller_id) REFERENCES users (id);

