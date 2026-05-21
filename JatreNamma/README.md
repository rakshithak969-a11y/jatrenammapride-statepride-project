# 🏛️ Jatre-Namma Pride — Android App
### MindMatrix VTU Internship Program | Project #35

A digital guide for the Village Fair (Jatre), built with Android (Java) + Firebase.

---

## 📱 Features
- **Live Schedule** — Real-time event listing with LIVE/Upcoming/Completed status
- **Lost & Found** — Digital board with Firebase backend, "Mark as Resolved" feature
- **Fair Map** — Custom interactive map with location markers
- **Safety** — Emergency contacts (108, 100, 101) with one-tap dialing
- **Cultural Stories** — Legends behind Rathotsava, Kushti, and more

---

## 🚀 Setup Instructions

### Step 1 — Open in Android Studio
1. Unzip the project folder
2. Open Android Studio → **File → Open** → select `JatreNamma/` folder
3. Wait for Gradle sync to complete

### Step 2 — Firebase Setup (for Live Features)
1. Go to [Firebase Console](https://console.firebase.google.com)
2. Create a new project named **jatre-namma-pride**
3. Add an Android app with package name: `com.jatre.namma`
4. Download `google-services.json` and replace the existing one in `app/` folder
5. Enable **Realtime Database** and **Storage** in Firebase Console
6. Set database rules to allow read/write (for development):
```json
{
  "rules": {
    ".read": true,
    ".write": true
  }
}
```

### Step 3 — Build & Run
1. Connect your Android device (USB debugging ON) or start an emulator
2. Click **Run ▶** in Android Studio

> **Note:** The app works fully offline with demo data even without Firebase setup.

---

## 📂 Project Structure
```
app/src/main/java/com/jatre/namma/
├── SplashActivity.java         — Animated splash screen
├── MainActivity.java           — Bottom nav host
├── ui/
│   ├── HomeFragment.java       — Dashboard with live event card
│   ├── ScheduleFragment.java   — Event timeline (Firebase + demo)
│   ├── LostFoundFragment.java  — Lost & Found digital board
│   ├── MapFragment.java        — Interactive fair map
│   ├── SafetyFragment.java     — Emergency contacts
│   ├── CulturalStoryActivity   — Cultural legends
│   ├── AddLostFoundActivity    — Post lost/found item
│   └── LostFoundDetailActivity — View & resolve item
├── model/
│   ├── ScheduleEvent.java
│   ├── LostFoundItem.java
│   └── CulturalStory.java
└── adapter/
    ├── ScheduleAdapter.java
    ├── LostFoundAdapter.java
    └── StoryAdapter.java
```

---

## 🎨 Design
- **Theme:** Festive saffron (#E65100) with traditional Karnataka motifs
- **Language support:** Kannada title on splash screen
- **UI:** Material Design 3 components

---

## 🔧 Tech Stack
| Layer | Technology |
|-------|-----------|
| Language | Java |
| UI | XML Layouts + Material Components |
| Real-time | Firebase Realtime Database |
| Storage | Firebase Storage |
| Images | Glide |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 34 (Android 14) |

---

*Built for MindMatrix VTU Internship Program — Project #35*
