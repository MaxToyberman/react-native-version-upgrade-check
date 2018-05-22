
# react-native-version-upgrade-check

## Getting started

`$ npm install react-native-version-upgrade-check --save`

### Mostly automatic installation

`$ react-native link react-native-version-upgrade-check`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-version-upgrade-check` and add `RNVersionUpgradeCheck.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNVersionUpgradeCheck.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNVersionUpgradeCheckPackage;` to the imports at the top of the file
  - Add `new RNVersionUpgradeCheckPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-version-upgrade-check'
  	project(':react-native-version-upgrade-check').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-version-upgrade-check/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-version-upgrade-check')
  	```

## Usage
```javascript
import RNVersionUpgradeCheck from 'react-native-version-upgrade-check';

const versionData = RNVersionUpgradeCheck.getVersionData()

if(versionData["isFirstLaunch"]) {
	// this is the first launch of your app
}
else if (versionData["isUpdated"]){
	// version was updated
}
else if (versionData["isSameVersion"]){
	// same version as before
}
```
  