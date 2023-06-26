SELECT o.product_name
FROM yandex_market.CUSTOMERS c
JOIN yandex_market.ORDERS o ON c.id = o.customer_id
WHERE LOWER(c.name) = LOWER(:name)