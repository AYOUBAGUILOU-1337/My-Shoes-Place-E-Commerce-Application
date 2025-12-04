Database Schema (prepackaged SQL)

The project includes an SQL file located at `app/src/main/assets/my_app.sql` with the following tables:

- `ousers`: users table
- `oproducts`: products catalog
- `ocart_items`: cart items
- `oorders`: orders
- `owishlist`: wishlist items
- `otransactions`: transactions log

On the first application run, Room executes this SQL to create the tables if they are missing.

If you prefer to pre-pack an SQLite DB instead of executing SQL at runtime, copy a pre-populated `.db` file to `app/src/main/assets/` and use Room's `createFromAsset("<dbfile>")` instead of the runtime callback.
