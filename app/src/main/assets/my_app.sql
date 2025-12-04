-- ============================
-- TABLE 1: Users
-- ============================
CREATE TABLE ousers (
    oid TEXT PRIMARY KEY,
    ofull_name TEXT NOT NULL,
    oemail TEXT UNIQUE NOT NULL,
    ophone TEXT,
    owallet_balance REAL DEFAULT 0,
    ocreated_at TEXT DEFAULT CURRENT_TIMESTAMP
);

-- ============================
-- TABLE 2: Products
-- ============================
CREATE TABLE oproducts (
    oid TEXT PRIMARY KEY,
    oname TEXT NOT NULL,
    obrand TEXT,
    oprice REAL,
    oimage_url TEXT,
    orating REAL,
    osold_count INTEGER
);

-- ============================
-- TABLE 3: Cart Items
-- ============================
CREATE TABLE ocart_items (
    oid TEXT PRIMARY KEY,
    oproduct_id TEXT,
    oquantity INTEGER,
    ostatus TEXT, -- completed / active
    FOREIGN KEY (oproduct_id) REFERENCES oproducts(oid)
);

-- ============================
-- TABLE 4: Orders
-- ============================
CREATE TABLE oorders (
    oid TEXT PRIMARY KEY,
    oorder_number INTEGER,
    oproduct_id TEXT,
    ostatus TEXT,
    oshipping_method TEXT,
    oshipping_cost REAL,
    FOREIGN KEY (oproduct_id) REFERENCES oproducts(oid)
);

-- ============================
-- TABLE 5: Wishlist
-- ============================
CREATE TABLE owishlist (
    oid TEXT PRIMARY KEY,
    oproduct_id TEXT,
    FOREIGN KEY (oproduct_id) REFERENCES oproducts(oid)
);

-- ============================
-- TABLE 6: Transactions
-- (Empty — you can fill later)
-- ============================
CREATE TABLE otransactions (
    oid TEXT PRIMARY KEY,
    otype TEXT,
    oamount REAL,
    ocreated_at TEXT DEFAULT CURRENT_TIMESTAMP
);
