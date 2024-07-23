# FlightGearSimulatorClient-MVC-Architecture
FlightGear 2020.3 Simulator Client Controllers Using MVC Architecture

# FlightGear JavaFX Client

This project is a JavaFX-based client application designed to interface with the FlightGear flight simulator using telnet. The application provides users with a comprehensive graphical user interface (GUI) to control various flight parameters. Users can manipulate the aileron and elevator using an on-screen joystick, adjust the throttle and rudder through intuitive sliders, and fine-tune these controls with keyboard inputs. By connecting to FlightGear's telnet server, the application sends real-time commands to the simulator, allowing for a seamless and interactive flight experience. This setup is ideal for flight simulation enthusiasts looking for an enhanced control interface and developers interested in extending or customizing their flight control systems.

![FilghtGearController](https://github.com/user-attachments/assets/6ebc17c6-57ef-41b2-8eae-0995d1d49f13)


## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features
- **Joystick Control**: Control the elevator and aileron using a joystick interface.
- **Throttle and Rudder Sliders**: Adjust throttle and rudder values using sliders, with fine control via keyboard inputs.
- **Real-time FlightGear Integration**: Connects to FlightGear simulator using telnet to send flight control commands.

## Installation

### Prerequisites
- **Java Development Kit (JDK) 11 or higher**: Ensure JDK is installed and JAVA_HOME environment variable is set.
- **FlightGear**: Download and install FlightGear from [FlightGear official website](https://www.flightgear.org/).

### Clone the Repository
```bash
git clone https://github.com/yourusername/flightgear-javafx-client.git
cd flightgear-javafx-client
```
### Setting Up FlightGear
To connect the application with FlightGear, start FlightGear with the following telnet configuration:
```bash
fgfs --telnet=socket,in,10,127.0.0.1,5402,tcp
```
This command enables the telnet server on FlightGear, allowing the JavaFX client to send flight control commands.

### Running the Application
1. **Open the project in your favorite IDE** (e.g., IntelliJ IDEA, Eclipse).
2. **Build and run the project** from your IDE or using the command line:
   ```bash
   ./gradlew run
   ```

## Usage
- **Joystick Control**: Use the on-screen joystick to control the elevator (up/down) and aileron (left/right).
- **Throttle and Rudder Sliders**: Adjust the throttle and rudder using the sliders at the bottom and left of the window, respectively.
  - **Keyboard Control**: Use the arrow keys to finely adjust throttle and rudder values:
    - `UP` key: Increase throttle
    - `DOWN` key: Decrease throttle
    - `LEFT` key: Decrease rudder
    - `RIGHT` key: Increase rudder

## Project Structure
The project follows the Model-View-Controller (MVC) architectural pattern, ensuring a clean separation of concerns.

### Model
- **Model.java**: Represents the data and business logic of the application. It manages the connection to FlightGear and sends control commands.

### View
- **WindowController.java**: Handles the graphical user interface and user interactions. It includes joystick and slider controls.
- **FXML Files**: Defines the layout of the UI using FXML.

### Controller
- **Controller.java**: Acts as a mediator between the Model and View. It listens to user inputs from the View, updates the Model, and refreshes the View as needed.

## Contributing
Contributions are welcome! Please follow these steps to contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Open a Pull Request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
