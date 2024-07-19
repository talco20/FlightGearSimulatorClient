package Model;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Observable;

public class Model extends Observable implements AutoCloseable {

    HashMap<String, String> properties;
    Socket fg;
    PrintWriter out2fg;

    public Model(String propertiesFileName) throws IOException {
        properties = new HashMap<>();
        try (InputStream input = getClass().getResourceAsStream("/" + propertiesFileName)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + propertiesFileName);
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                String sp[] = line.split(",");
                properties.put(sp[0], sp[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int port = Integer.parseInt(properties.get("port"));
        try {
            fg = new Socket(properties.get("ip"), port);
            out2fg = new PrintWriter(fg.getOutputStream(), true);
            if (fg != null && out2fg != null) {
                System.out.println("Connected to FlightGear!");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("Unknown host: " + properties.get("ip"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IOException while connecting to: " + properties.get("ip") + " on port: " + port);
        }
    }

    public void setAileron(double x) {
        if (out2fg != null) {
            String command = properties.get("aileron");
            out2fg.println(command + " " + x);
            System.out.println("Aileron command sent: " + command + " " + x);
        } else {
            System.out.println("Error: Not connected to FlightGear");
        }
    }

    public void setElevators(double x) {
        if (out2fg != null) {
            String command = properties.get("elevators");
            out2fg.println(command + " " + x);
            System.out.println("Elevators command sent: " + command + " " + x);
        } else {
            System.out.println("Error: Not connected to FlightGear");
        }
    }

    public void setRudder(double x) {
        if (out2fg != null) {
            String command = properties.get("rudder");
            out2fg.println(command + " " + x);
            System.out.println("Rudder command sent: " + command + " " + x);
        } else {
            System.out.println("Error: Not connected to FlightGear");
        }
    }

    public void setThrottle(double x) {
        if (out2fg != null) {
            String command = properties.get("throttle");
            out2fg.println(command + " " + x);
            System.out.println("Throttle command sent: " + command + " " + x);
        } else {
            System.out.println("Error: Not connected to FlightGear");
        }
    }

    @Override
    public void close() {
        try {
            if (fg != null && !fg.isClosed()) {
                out2fg.close();
                fg.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
