CREATE TABLE IF NOT EXISTS client (
      id SERIAL PRIMARY KEY,
      nui VARCHAR(100) UNIQUE NOT NULL,
      fullname VARCHAR(100) NOT NULL,
      address VARCHAR(100) NOT NULL,
      email VARCHAR(100) NOT NULL

);

CREATE TABLE IF NOT EXISTS product (
       id SERIAL PRIMARY KEY,
       description VARCHAR(255) NOT NULL ,
       brand VARCHAR(255) NOT NULL ,
       price DECIMAL(10, 2) DEFAULT 0.00,
       stock INT NOT NULL
);

CREATE TABLE IF NOT EXISTS invoice (
       id SERIAL PRIMARY KEY,
       code VARCHAR(17) UNIQUE NOT NULL ,
       create_at DATE NOT NULL ,
       total DECIMAL(10, 2),
       client_id INT,
       FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS detail (
      id SERIAL PRIMARY KEY,
      quantity INT NOT NULL ,
      price DECIMAL(10, 2),
      subtotal DECIMAL(10,2) GENERATED ALWAYS AS ( price*quantity ) STORED,
      invoice_id INT,
      product_id INT,
      FOREIGN KEY (invoice_id) REFERENCES invoice(id),
      FOREIGN KEY (product_id) REFERENCES product(id),
      UNIQUE (invoice_id,product_id)
);
