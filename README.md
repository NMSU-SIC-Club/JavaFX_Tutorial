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
choco install git
choco install jdk8
choco install gradle
choco install eclipse
```

[1]: https://chocolatey.org/

## Linux
### Ubuntu
Below are a list of commands that should get all the needed software and dependencies on
your machine to be able to use the gradle build and test. The order of these commands should not matter to much, 
however, you will need to allow you machine access to the webupd8team/java repository before installing java8
This was tested on Ubuntu 16.04 and the test ran with no issues. 
I would also recommend creating a fresh directory to pull the code into from github. 

Prerequisite to installing JDK8: 
```
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
```

Installing packages:
```
sudo apt-get install git
sudo apt-get install gradle
sudo apt-get install oracle-java8-installer
```

**Note**: The Java installer will prompt you with terms of use.  Go ahead and accept to continue install

You can also install Eclipse with `apt-get`, but this is discouraged as it installs an older version.  In any case, here is the command:

```
sudo apt-get install eclipse
```
