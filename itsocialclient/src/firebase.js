
import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
import { getStorage } from "firebase/storage";
import { getAnalytics } from "firebase/analytics";
const firebaseConfig = {
    apiKey: "AIzaSyC4m5_osjePUg6uePDSspJXsWprbx7hiQw",
    authDomain: "it-firebase-d450c.firebaseapp.com",
    projectId: "it-firebase-d450c",
    storageBucket: "it-firebase-d450c.appspot.com",
    messagingSenderId: "17199862955",
    appId: "1:17199862955:web:b1559d5273fced1ec013ee",
    measurementId: "G-EDTV073EC8"
};
// Initialize Firebase
export const app = initializeApp(firebaseConfig);
export const auth = getAuth();
export const db = getFirestore();
export const storage = getStorage()