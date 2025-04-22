INSERT INTO lots
(id, item_id, seller_id, best_bid_user_id, initial_price, bid_price, status, expires_at, created_at,
 updated_at)
VALUES (1, 1, 1, null, 103.56, null, 'DRAFT', now() + interval '2 day', now(), null);