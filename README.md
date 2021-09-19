# libgdx-inGameConsole-utils

The libgdx-inGameConsole-utils is a utility library for StrongJoshua's [libgdx-inGameConsole](https://github.com/StrongJoshua/libgdx-inGameConsole) library.

## Including in Project

To use this library in your gradle project, add the version number and jitpack repository information to your root build.gradle file:

```groovy
allprojects {
    ext {
    	...
        inGameConsoleUtils = 'master-SNAPSHOT'
    }
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```
And add the dependency in your core project:
```groovy
dependencies {
    implementation "com.github.Dgzt:libgdx-inGameConsole-utils:$inGameConsoleUtils"
}
```

## Usage

TODO
