# MediTrack - Java Management System

MediTrack is a comprehensive, console-based Java application built to manage library-style medical records. It demonstrates proficiency in Core OOP, Advanced OOP concepts, Design Patterns, and modern Java features.

## Features & Implementation

* **Core OOP:** Robust use of Encapsulation (private fields, central `Validator`), Inheritance (`Person` -> `Doctor`/`Patient`), Polymorphism (overloaded searches, overridden methods), and Abstraction (`MedicalEntity`, Interfaces).
* **Advanced Data Handling:** * **Deep Cloning:** Implemented `Cloneable` on complex nested objects.
    * **Immutability:** Thread-safe `BillSummary` generation.
    * **Custom Exceptions:** `InvalidDataException` and `AppointmentNotFoundException`.
* **Design Patterns:**
    * **Singleton:** `IdGenerator` ensures synchronized, unique ID tracking.
* **Bonus Implementations:**
    * **File I/O & Persistence:** Saves and loads entity data using CSV parsing and `try-with-resources`.
    * **Java Streams & Lambdas:** Used for searching, filtering by enums, and computing statistical analytics (e.g., average consultation fees).

## How to Run

1.  Clone the repository and navigate to the root directory.
2.  Compile the Java files:
    ```bash
    javac -d out $(find src -name "*.java")
    ```
3.  Run the application.
    * **Standard Mode:**
        ```bash
        java -cp out com.meditrack.Main
        ```
    * **Persistence Mode:** Use the `--loadData` argument to automatically load previous states from the `data/` directory.
        ```bash
        java -cp out com.meditrack.Main --loadData
        ```

## Project Structure highlights
* `src/com/meditrack/entity/`: Core models, Enums, and Immutable classes.
* `src/com/meditrack/service/`: Business logic, Streams, and analytics.
* `src/com/meditrack/util/`: Generic `DataStore<T>`, Singletons, File I/O, and Validators.
* `docs/`: Contains the theoretical JVM Architecture Report and Environment Setup Instructions.