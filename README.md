# SaaS Integration
An Android task management app, with SaaS features using Firebase for Analytics, Crash reporting, and Performance monitoring.

# Table of Contents
  * [Demo](#demo)
  * [Tech Stack](#tech-stack)
  * [Third-Party Libraries Used](#third-party-libraries-used)
  * [Setup Instructions](#setup-instructions)
  * [Design Decisions](#design-decisions)
  * [Firebase Analytics](#firebase-analytics)
  * [Firebase Crashlytics](#firebase-crashlytics)
      - [In app crash sample](#in-app-crash-sample)
      - [Room db related crash sample](#room-db-related-crash-sample)
  * [Firebase Performance](#firebase-performance)
  * [APK](#apk)
  
## Demo
<img src ="https://github.com/hsinha76/SaaSIntegration/blob/04fa04bede793cd0c9bf3b9af371f02266bc7f24/demo.gif" width="300"/>

## Tech Stack
🖥️ Compose<br />
🌊 Kotlin Flows<br />
🧩 Hilt<br />
🌐 Retrofit<br />
💾 Room<br />
📊 Firebase Analytics<br />
💥 Firebase Crashlytics<br />
🚀 Firebase Performance

## Third-Party Libraries Used
- **Hilt**
- **Retrofit**
- **Room Database**
- **Firebase** : Analytics, Crashlytics, Performance Monitoring

## Setup Instructions
Prerequisites
1. Android Studio 

Installation
1. Clone repository
2. Open in Android Studio
3. Replace `google-services.json` with the file from your own Firebase project
4. Sync Gradle
5. Enable services like Analytics, Crashlytics, and Performance Monitoring in your Firebase console
6. Build and run
   
## Design Decisions
- **MVVM Architecture**: Used it for separation of concerns, further also used Kotlin Flows and Jetpack Compose for reactive state management, ensuring modular and testable code.  
- **Dependency Injection**: Used Hilt For simplified dependency injection ensuring clean and maintainable code.  
- **Data Persistence**: Room Database to store data locally.
- **Analytics**: Firebase Analytics to log events like (e.g. "Task Added, "Task Edited, "Task Completed").  
- **Crash Reporting**: Firebase Crashlytics to keep track of app crashes.
- **Performance Tracking**: Firebase Performance Monitoring to track app & network performance.  

## Firebase Analytics

<table style="width: 100%; border: none; text-align: center;">
  <tr>
    <td style="width: 50%; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/a40c88e375998b59ec9a3becd4adde2c5c916c62/Analytics/DebugView/Screenshot%202025-01-25%20at%206.37.03%E2%80%AFPM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Firebase DebugView">
      </div>
    </td>
    <td style="width: 50%; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/87fb5ef7d789ff6d43827fadc5eb3e955ae39bbd/Analytics/Screenshot%202025-01-26%20at%209.54.26%E2%80%AFAM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Firebase Analytics">
      </div>
    </td>
   <td style="width: 50%; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/87fb5ef7d789ff6d43827fadc5eb3e955ae39bbd/Analytics/Screenshot%202025-01-26%20at%209.55.43%E2%80%AFAM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Events - Firebase Analytics">
      </div>
    </td>
  </tr>
  <tr>
    <td align="center"><em>Firebase DebugView</em></td>
    <td align="center"><em>Firebase Analytics</em></td>
    <td align="center"><em>Events - Firebase Analytics</em></td>
  </tr>
</table>

## Firebase Crashlytics

> #### In app crash sample
<table style="width: 100%; table-layout: fixed;">
  <tr>
    <td style="width: 50%; text-align: center; vertical-align: top; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/a40c88e375998b59ec9a3becd4adde2c5c916c62/Crash/In%20App/inappcrash.gif" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Mobile Screen Recording">
      </div>
    </td>
    <td style="width: 50%; text-align: center; vertical-align: top; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/6508c5b5816ff3ee010502373dea2408e71ab587/Crash/In%20App/Screenshot%202025-01-25%20at%206.58.03%E2%80%AFPM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Firebase Crashlytics">
      </div>
    </td>
  </tr>
  <tr>
    <td align="center"><em>Mobile screen recording</em></td>
    <td align="center"><em>Firebase Crashlytics</em></td>
  </tr>
</table>

</br>

> #### Room db related crash sample
<table style="width: 100%; table-layout: fixed;">
  <tr>
    <td style="width: 50%; text-align: center; vertical-align: top; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/a40c88e375998b59ec9a3becd4adde2c5c916c62/Crash/RoomDb/roomdbcrash.gif" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Mobile Screen Recording">
      </div>
    </td>
    <td style="width: 50%; text-align: center; vertical-align: top; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/a40c88e375998b59ec9a3becd4adde2c5c916c62/Crash/RoomDb/Screenshot%202025-01-25%20at%207.00.16%E2%80%AFPM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Firebase Crashlytics">
      </div>
    </td>
  </tr>
  <tr>
    <td align="center"><em>Mobile screen recording</em></td>
    <td align="center"><em>Firebase Crashlytics</em></td>
  </tr>
</table>

## Firebase Performance

<table style="width: 100%; border: none; text-align: center;">
  <tr>
    <td style="width: 50%; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/a40c88e375998b59ec9a3becd4adde2c5c916c62/Network%20Performance/Screenshot%202025-01-25%20at%206.10.38%E2%80%AFPM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Firebase Performance">
      </div>
    </td>
    <td style="width: 50%; padding: 10px;">
      <div style="max-width: 100%; overflow: hidden; display: flex; justify-content: center; align-items: center;">
        <img src="https://github.com/hsinha76/SaaSIntegration/blob/a40c88e375998b59ec9a3becd4adde2c5c916c62/Network%20Performance/Screenshot%202025-01-25%20at%206.10.49%E2%80%AFPM.png" 
             style="max-width: 100%; height: auto; object-fit: contain;" 
             alt="Firebase Network Performance">
      </div>
    </td>
  </tr>
   <tr>
    <td align="center"><em>Firebase Performance</em></td>
    <td align="center"><em>Network Performance</em></td>
  </tr>
</table>

## APK
[Download APK](https://github.com/hsinha76/SaaSIntegration/blob/04fa04bede793cd0c9bf3b9af371f02266bc7f24/app-debug.apk)


