"Create categories tables "
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,        -- e.g. "Electronics"
    parent_category_id BIGINT,         -- Nullable for root categories
    description TEXT,
    FOREIGN KEY (parent_category_id) REFERENCES categories(id) -- Self-referencing for subcategories
);
"create product table"
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,        -- e.g. "Smartphone"
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,     -- e.g. 19999.99
    stock INT NOT NULL,                -- Quantity in stock
    sku VARCHAR(255) NOT NULL,         -- SKU for unique product identification
    category_id BIGINT,                -- Foreign Key referencing categories
    brand VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id)
);
"Create product_images to store images of products"
CREATE TABLE product_images (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     product_id BIGINT,                   -- Foreign Key to Products
     image_url VARCHAR(255),               -- URL/path to image
     image_type VARCHAR(50),               -- e.g., "Main", "Thumbnail"
     FOREIGN KEY (product_id) REFERENCES products(id)
 );

"Create product rating table for products reviews"
CREATE TABLE product_ratings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,                   -- Foreign Key to Products
    customer_id BIGINT,                  -- Foreign Key to Customers (if you have a Customer table)
    rating INT NOT NULL,                 -- Rating value (1-5)
    review TEXT,                         -- Review text
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

"Create product variants for varity of products"
CREATE TABLE product_variants (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,                   -- Foreign Key to Products
    variant_name VARCHAR(255),           -- e.g., "Size", "Color"
    variant_value VARCHAR(255),          -- e.g., "Medium", "Red"
    price DECIMAL(10, 2),                 -- Variant-specific price, if different
    stock INT NOT NULL,                   -- Stock for this variant
    FOREIGN KEY (product_id) REFERENCES products(id)
);



