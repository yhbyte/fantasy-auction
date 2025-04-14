INSERT INTO lot_detail
(id, initial_price, bid_price, status, expires_at, created_at, updated_at)
VALUES(1, 103.56, null, 'DRAFT', now() + interval '2 day', now(), null);

INSERT INTO lots
(id, item_id, seller_id, best_bid_user_id, detail_id, created_at, updated_at)
VALUES(1, 1, 1, null, 1, now(), null);