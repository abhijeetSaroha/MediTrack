# Java Environment Setup Instructions

This guide outlines how the Java Development Kit (JDK) was installed and configured for the MediTrack project.

## 1. Installation of JDK
1. Downloaded the latest stable release of the Java SE Development Kit (JDK) from the official Oracle website (or OpenJDK).
2. Followed the standard installation wizard for the respective Operating System.

## 2. Environment Variables Configuration
To ensure Java commands (`java` and `javac`) can be executed from any terminal window, the environment variables were configured:
* **JAVA_HOME:** Pointed to the root directory of the JDK installation (e.g., `C:\Program Files\Java\jdk-17`).
* **PATH:** Appended `%JAVA_HOME%\bin` to the system's PATH variable.

## 3. Verification
Opened a new terminal/command prompt and executed the following commands to verify the installation:

```bash
java -version
javac -version
```

The Java Runtime Environment (JRE) is inherently included within modern JDK packages, satisfying all requirements to compile and run the MediTrack application.
