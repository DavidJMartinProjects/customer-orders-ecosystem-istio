DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  sku VARCHAR(250) DEFAULT NULL,
  qty INT DEFAULT NULL
);

INSERT INTO products (name, description, sku, qty) VALUES
  ('Garden Chair', 'Stylist green plastic chair', 'C001', 20),
  ('Kitchen Table', 'Stylist wooden kitchen table', 'T001', 10),
  ('Outdoor BBQ Set', 'An outdoor bbq set.', 'B001', 15);
