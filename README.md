# COSC2658_Data Structures and Algorithms - Group Project

For problem description, see [Assessment Details](AssessmentDetails.md).

---


## Contribution Score

|          Name          |    Sid   | Contribution Score |
|:-----------------------|:---------|:------------------:|
| Pham Phuoc Sang        | s3975979 |         5          |
| Nguyen Ngoc Thanh Mai  | s3978486 |         5          |
| Cao Nguyen Hai Linh    | s3978387 |         5          |
| Tran Pham Khanh Doan   | s3978798 |         5          |


## Project File Structure

```
.
├── src/
│   ├── benchMark/
│   │   ├── benchMark.ipynb
│   │   ├── benchmark_results.csv
│   │   ├── Benchmark.java
│   ├── enums/
│   │   ├── ServiceType.java
│   ├── gui/
│   │   ├── GUI.java
│   ├── maps/
│   │   ├── Map2D.java
│   ├── models/
│   │   ├── Place.java
│   ├── utils/
│   │   ├── ArrayList.java
│   │   ├── List.java
│   │   ├── Rectangle.java
│   ├── Main.java
├── AssessmentDetails.md
├── README.md
├── requirements.txt
```

The project is organized into several directories and files within the `src/` folder:

- `benchMark/`: Contains files related to performance benchmarking.
  - `benchMark.ipynb`: Jupyter notebook for running and visualizing benchmark tests.
  - `benchmark_results.csv`: CSV file containing the results of benchmarks.
  - `Benchmark.java`: Java class for executing benchmarks.

- `enums/`: Contains Java enums used throughout the project.
  - `ServiceType.java`: Enum defining different types of services.

- `gui/`: Contains Java GUI components.
  - `GUI.java`: Main GUI class for the application interface.

- `maps/`: Contains classes for map functionalities.
  - `Map2D.java`: Class representing a two-dimensional map.

- `models/`: Contains models used in the project.
  - `Place.java`: Class representing a place on the map.

- `utils/`: Contains utility classes.
  - `ArrayList.java`: Custom implementation of an array list.
  - `List.java`: Interface for list implementations.
  - `Rectangle.java`: Class representing a rectangle shape.

- `AssessmentDetails.md`: Markdown file with detailed assessment information for the project.
- `Main.java`: Main entry point for the Java application.
- `README.md`: This file, containing project documentation.
- `requirements.txt`: Specifies the Python packages required for the project.

|:-----------------------------------|:-------:|
| [Git](https://git-scm.com)         | latest  |
| [Maven](https://maven.apache.org/) |  4.0.0  |
| [OpenJDK](https://openjdk.org/)    | 20.0.1  |
| [Python](https://www.python.org/)  |  3.11   |

**Note:** For this project, we are using Python [virtual environment](https://docs.python.org/3/library/venv.html) to keep it light. However, feel free to use [Anaconda](https://www.anaconda.com/) if it is more convenient.

### VSCode Requirements

Extension requirements for building and running Java classes:

| Requirement                                                                                             | Version |
|:--------------------------------------------------------------------------------------------------------|:-------:|
| [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) | latest  |
| [Test Runner for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-test)    | latest  |

Additional extensions for viewing data visualization in `notebooks`:

| Requirement                                                                       | Version |
|:----------------------------------------------------------------------------------|:-------:|
| [Python](https://marketplace.visualstudio.com/items?itemName=ms-python.python)    | latest  |
| [Jupyter](https://marketplace.visualstudio.com/items?itemName=ms-toolsai.jupyter) | latest  |

To properly setup Java and Python development environment for our project, refer to the following documentation:

- [Java in VSCode](https://code.visualstudio.com/docs/languages/java).
- [Java Testing in VSCode](https://code.visualstudio.com/docs/java/java-testing).
- [Data Science in VSCode](https://code.visualstudio.com/docs/datascience/overview).

To run tests, you need to install JUnit Maven dependency:

```bash
$ mvn install
```

If you are viewing and running Python scripts in `notebooks`, install the required Python packages:

```bash
$ pip install -r ./requirements.txt
```

### IntelliJ IDEA

| Requirement                                                                        | Version |
|:-----------------------------------------------------------------------------------|:-------:|
| [Python Plugin for IntelliJ IDEA](https://plugins.jetbrains.com/plugin/631-python) | latest  |

**Note:** The above requirement is needed only for viewing data visualization in `notebooks`. All Java functionalities and toolchain integrations are IDE's built-ins, so no other plugins are required.

To properly setup OpenJDK 17 to work with IntelliJ IDEA toolchain, refer to this documentation: [IntelliJ IDEA - SDKs](https://www.jetbrains.com/help/idea/sdk.html#change-module-sdk).

To run tests, you need to install JUnit:

```bash
$ mvn install
```

To enable and run Python functionality within IntelliJ IDEA, refer to the following documentation: [Python in IntelliJ IDEA](https://www.jetbrains.com/help/idea/python.html).

If you are viewing and running Python scripts in `notebooks`, install the required Python packages:

```bash
$ pip install -r ./requirements.txt
```

Refer to [IntelliJ IDEA - Getting Started](https://www.jetbrains.com/help/idea/getting-started.html) to learn about building, executing, debugging, and testing with IntelliJ IDEA and its Maven toolchain integration.


## Build and Execution

- Clone this repo and use IntelliJ IDEA to run the project at the project dir. 
- To run the application, please run the Main.java located in src/main/Main.java.
- To run the testing, please run the Map2DTest.java in the `test` folder.
- To run the benchmarking, please run the Benchmark.java in the `benchMark` folder, but you dont need to run this file because we already provided the benchmarking results in the benchmark_results.csv.
- To plot the heat map, please run the benchMark.ipynb in the `benchMark` folder, but before running, you are required to install all the dependencies in the requirements.txt file, and already config the python in IntelliJ IDEA. Or you can use vscode to just run the .ipynb file.


## Video Demonstration

Video available on YouTube: [Link]().
