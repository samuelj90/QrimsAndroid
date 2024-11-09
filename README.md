
# QrimsAndroid

QrimsAndroid is an Android application designed for product catalog management with added cart functionality, wishlist management, and a comprehensive checkout experience.This app has been enhanced to meet enterprise standards, with a modular, scalable architecture and robust features like user authentication, real-time updates, and secure payment integration.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Authentication**: Secure login and signup using Firebase Authentication (OAuth2 support available).
- **Product Catalog**: Displays a detailed list of products with search, filter, and sorting options.
- **Cart Management**: Add, update, and remove items from the cart, with total cost calculation.
- **Wishlist**: Save favorite products for future viewing and easy access.
- **Order Management**: Track placed orders, view order history, and get real-time updates.
- **Checkout Process**: Secure payment integration with Stripe.
- **Push Notifications**: Receive order status and special offers.
- **Offline Mode**: Local caching with Room Database for offline access.

## Getting Started

To get the app up and running on your local development environment:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/samuelj90/QrimsAndroid.git
   cd QrimsAndroid
   ```

2. **Setup Firebase**:
   - Create a Firebase project at [Firebase Console](https://console.firebase.google.com/).
   - Download the `google-services.json` file and place it in the `app/` directory.
   - Enable Firebase Authentication and Firestore Database for storing cart, wishlist, and order data.

3. **Stripe Configuration**:
   - Register your app with [Stripe](https://stripe.com/docs/api) to obtain API keys.
   - Set up the Stripe SDK by following the instructions on [Stripe's Android documentation](https://stripe.com/docs/payments/accept-a-payment).

4. **Build and Run**:
   - Open the project in Android Studio, sync Gradle, and run the app on an emulator or physical device.

## Architecture

QrimsAndroid follows the **MVVM (Model-View-ViewModel)** architecture with a clean separation of concerns:

- **Data Layer**: Handles data storage and retrieval with Room and Firebase, along with repository patterns.
- **Domain Layer**: Contains use cases that orchestrate complex logic and interactions between data sources.
- **Presentation Layer**: Uses Jetpack Compose and Android ViewModel for responsive and interactive UI.

The architecture ensures scalability and maintainability, making it ideal for enterprise-level applications.

## Tech Stack

- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Network**: Retrofit, Firebase Firestore
- **Database**: Room Database for offline caching
- **Authentication**: Firebase Authentication
- **Payment**: Stripe SDK
- **Push Notifications**: Firebase Cloud Messaging (FCM)

## Setup and Installation

1. **Install Android Studio** (v4.0 or later) and Java JDK 8 or later.
2. **Sync Gradle** and ensure all dependencies are installed.

**Environment Configuration**: Place sensitive keys (like Stripe API keys) in environment-specific configuration files.

### Dependencies
Add these dependencies to `build.gradle`:

```gradle
// Firebase
implementation "com.google.firebase:firebase-auth"
implementation "com.google.firebase:firebase-firestore"
implementation "com.google.firebase:firebase-messaging"

// Stripe
implementation 'com.stripe:stripe-android:20.7.0'

// Jetpack Compose
implementation "androidx.compose.ui:ui"
implementation "androidx.compose.material:material"
```

## Usage

### Authentication

- Sign up and log in using Firebase Authentication.
- After authentication, access the product catalog, cart, and wishlist.

### Product Catalog

- Browse and search for products.
- Use filters and sorting for a personalized experience.

### Cart and Checkout

- Add items to the cart, adjust quantities, and proceed to checkout.
- Securely pay through Stripe integration.

### Wishlist

- Add favorite products to the wishlist for easy access and future purchases.

## Contributing

We welcome contributions to enhance QrimsAndroid. Here’s how you can contribute:

1. **Fork the Repository**.
2. **Create a Feature Branch** (`feature/your-feature-name`).
3. **Commit and Push** your changes.
4. **Create a Pull Request** with a detailed description of your changes.

Please review the `CONTRIBUTING.md` file for more guidelines.

## License

QrimsAndroid is licensed under the [MIT License](LICENSE). See `LICENSE` for more details.
