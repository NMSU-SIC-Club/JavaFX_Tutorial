# JavaFX_Tutorial
A tutorial project for proper organization of a JavaFX based project.  Covers everything from setup to advanced testing.

# Quick Start
Download the code:

```
git clone https://github.com/NMSU-SIC-Club/JavaFX_Tutorial.git
```

Build (requires Gradle):

```
gradle build
```

Run the application:

```
gradle run
```

Run all tests:

```
gradle test
```

run all tests without displaying UI:

```
gradle test -Dheadless=true
```

Note: Gradle will only run tests when changes occur in the code base


# Preparing your development environment
## Windows
We recommend using [Chocolatey][1] to manage your installations.
With Chocolately installed, you'll need to install the following packages in an admin CMD Prompt or PowerShell terminal:

```
choco install jdk8
choco install gradle
choco install eclipse
```

[1]: https://chocolatey.org/

## Linux
# Below are a list of commands that should get all the needed software and dependencies on
# your machine to be able to use the gradle build and test. The order of these commands should not matter to much, 
# however, you will need to allow you machine access to the webupd8team/java repository before installing java8
# This was tested on Ubuntu 16.04 and the test ran with no issues. 
# I would also recommend creating a fresh directory to pull the code into from github. 

...

sudo apt-get install git                     // this is to install git (version control)

sudo apt-get install gradle                  // this will get gradle, with all dependencies

sudo apt-get eclipse                         // this will get eclipse (text editor)

sudo add-apt-repository ppa:webupd8team/java // need access to the repo

sudo apt-get update                          //update your info according to the new repo

sudo apt-get install oracle-java8-installer  // install java
  # click ok
  # click yes to agree with the terms
  
git clone https://github.com/NMSU-SIC-Club/JavaFX_Tutorial.git

cd JavaFX_Tutorial                           // you will need to be in the correct directory

gradle build                                 // will test the app with the GUI

gradle test                                  // will test the app without the GUI

gradle run                                   // will launch the application

...

#  (9/15/2017)
