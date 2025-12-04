# ShoesPlace Android App (Kotlin)

This is a minimal Android app replicating the UI and core features of the "ShoesPlace" React app, adapted to Android with Kotlin and Room (SQLite) for local data persistence.

## Features

- Welcome screen with slideshow-like welcome message and Register / Login actions
- Home screen with product lists (Popular & Newest), brand filters
- Product detail screen with Add to Cart
- Checkout screen showing cart items and total
- Payment methods screen placeholder
- Wishlist and Orders screens
- Profile and Wallet screens
- Local persistence with Room (SQLite): Products, Wishlist, Cart, Orders, and User

## Project Structure

- `app/src/main/java/com/example/shoesplace/` - Kotlin sources
  - `data/` - Room entities, DAOs, AppDatabase, Repository
  - `ui/` - Activities and Fragments
  - `ui/adapters/` - RecyclerView adapters
- `app/src/main/res/layout/` - Layout XML files
- `app/src/main/AndroidManifest.xml` - Manifest

## How to open and run

1. Install Android Studio (Arctic Fox or later recommended).
2. From Android Studio: `File` -> `Open` and select the project root `android-mystores`.
3. Allow Gradle to sync and make sure `Kotlin` plugin is enabled.
4. Build and Run on an emulator or a device.

Note: This project uses Glide for image loading and Room for SQLite. An Internet connection is required to load placeholder images.

## 📸 App Screenshots
 
## Welcome Screen:
<img width="189" height="355" alt="image" src="https://github.com/user-attachments/assets/49468d6b-d49b-41dc-97a3-6abc18fb79f8" />

## Home Page After Login:
<img width="189" height="355" alt="image" src="https://github.com/user-attachments/assets/bcf69917-0ebf-45c8-9210-d12467de8994" />

## Product Detail Page:
<img width="176" height="331" alt="image" src="https://github.com/user-attachments/assets/5d3a4363-94ce-4193-86f2-b6cff4c649a4" />
## Cart Page - Multiple Items:
<img width="186" height="346" alt="image" src="https://github.com/user-attachments/assets/ee5d2a1f-e9c3-4d3d-8f5d-332c9d335067" />
## Checkout Page:
<img width="196" height="370" alt="image" src="https://github.com/user-attachments/assets/2543867f-c232-401e-858b-c0947d5c1808" />
## Payment Methods Page:
<img width="185" height="348" alt="image" src="https://github.com/user-attachments/assets/3979ea2e-11bc-4259-9ee3-2b8797e92b2f" />
## Orders/Tracking Page:
<img width="203" height="380" alt="image" src="https://github.com/user-attachments/assets/833023c7-460f-4fc6-a4dc-30553abf5d31" />
##  Wishlist Page:
<img width="196" height="365" alt="image" src="https://github.com/user-attachments/assets/895dc854-236e-459f-8c01-b768e1c5b87b" />
## Wallet Page:
<img width="224" height="421" alt="image" src="https://github.com/user-attachments/assets/21968a82-5c38-4788-852a-31aca4c1be56" />
## Profile Page:
<img width="221" height="415" alt="image" src="https://github.com/user-attachments/assets/41b61044-d535-4d2f-a6b9-cef5bb0c0541" />


Replace these with your real screenshots inside a folder called /images.

## Save & Publish to GitHub (Windows - cmd)

Quick steps to save & push this project to a GitHub repo on Windows using cmd:

1) Create a repository on GitHub (or note the repo URL). Example: https://github.com/yourname/ShoesPlace.git

2) In a Windows cmd shell, run the helper script we included:

```
cd path\to\android-mystores
publish_to_github.bat https://github.com/yourname/ShoesPlace.git main
```

This script sets up a local git repo, commits the current files, and attempts to push to the given repo URL's `main` branch.

If you prefer to use `gh` (GitHub CLI), you can also do the following:

```
cd path\to\android-mystores
git init
git add .
git commit -m "initial commit"
gh repo create yourname/ShoesPlace --public --source=. --push
```

Make sure you have `git` and `gh` installed and authenticated (use `gh auth login` to authenticate). If you don't have an SSH key, you can use HTTPS and provide a Personal Access Token (PAT) when prompted.

If you want me to automate creating the GitHub repo for you, please grant the `gh` CLI or provide a repository name and confirm you want public or private; otherwise I will not push anything without your authorization.

## Notes & To-dos

- Sample data is seeded in `WelcomeActivity` via `Repository.seedSampleProducts()` for demo purposes.
- Improvements to consider:
  - Implement proper login/auth and profile management
  - Replace static strings with `strings.xml`
  - Add better navigation using `Navigation Component`
  - Add unit tests and instrumentation tests
  - Implement wishlist, cart, and order UI/DB syncing

## License

This sample project is provided as-is for demo and learning purposes. Use and adapt as you like.
# My-Shoes-Place-E-Commerce-Application  
