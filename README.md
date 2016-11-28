iOS-Automation
==============

This repo contains automation scripts for iOS sample apps developed by playback apps team

How to check out
- Go your local folder where you want to check out the files 
- Perform this command if you are checking out for the first time 
  git clone http://github.services.ooyala.net/Platform/iOS-Automation.git
- If you have already cloned the repo before, checkout the branch you need to work and do git pull to get the latest command. 


How to run

1. Run from IDE
*Info will be added soon*


2. Run from command line
- Start Appium. Show simulator log on console.
appium --show-sim-log

If encountered Error related to ideviceinstaller, run below command 
brew install --HEAD ideviceinstaller 


// Before running the iOS SDK Automation please follow these guidelines.

1. Folder Structure:---

a. Create one folder With the iOS name and under this folder take clone of iOS Appium from this repo:- "git clone https://git.corp.ooyala.com/scm/pbq/ios-automation.git"
b. Now in iOS folder create one more folder with the name of repo and under this repo take clone from - "git clone https://github.com/ooyala/ios-sample-apps.git"

//below step is obsolete
//2. Prerequisites
// a. Install follwing libraried before running the code:-
//    i. ios-deploy -->> https://github.com/phonegap/ios-deploy
//   ii. libimobiledevice -- >> http://confusatory.org/post/127183189821/ios-debugging-device-console-without-wires , https://github.com/libimobiledevice/libimobiledevice (This library has been added in maven dependency now no need to install manually.)

3. how to run iOS Automation
a. Move to iOS-automation folder and run this following command :-
        mvn clean test -DsuiteXmlFile="Enter XML file which you want "

*Info will be added soon*
