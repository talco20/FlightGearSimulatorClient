package view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;

import java.util.Observable;
import Model.Model;

public class WindowController extends Observable {
    @FXML
    Canvas joystick;

    @FXML
    Slider rudderslide;

    @FXML
    Slider throttleslide;

    private Model model;
    boolean mousePushed;
    double jx, jy;
    double mx, my;
    double aileron;
    double elevator;
    double throttle;
    double rudder;

    public WindowController() {
        mousePushed = false;
        jx = 0;
        jy = 0;
        aileron = 0;
        elevator = 0;
        throttle = 0;
        rudder = 0;
    }

    @FXML
    public void initialize() {
        sliderInit();
        throttle = throttleslide.getValue();
        rudder = rudderslide.getValue();
    }

    private void sliderInit() {
        rudderslide.setMin(-1);
        rudderslide.setMax(1);
        rudderslide.setShowTickMarks(true);
        rudderslide.setBlockIncrement(0.4);

        throttleslide.setMin(-1);
        throttleslide.setMax(1);
        throttleslide.setShowTickMarks(true);
        throttleslide.setBlockIncrement(0.2);

        rudderslide.setValue(0);
        throttleslide.setValue(1);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public double getThrottle() {
        return throttle;
    }

    public double getRudder() {
        return rudder;
    }

    public double getAileron() {
        return aileron;
    }

    public double getElevator() {
        return elevator;
    }

    public void paint() {
        GraphicsContext gc = joystick.getGraphicsContext2D();
        mx = joystick.getWidth() / 2;
        my = joystick.getHeight() / 2;
        gc.clearRect(0, 0, joystick.getWidth(), joystick.getHeight());
        gc.strokeOval(jx - 50, jy - 50, 100, 100);
        aileron = (jx - mx) / mx;
        elevator = (jy - my) / my;
        // Clamp values to [-1, 1]
        aileron = Math.max(-1, Math.min(1, aileron));
        elevator = Math.max(-1, Math.min(1, elevator));
        setChanged();
        notifyObservers();
        System.out.println("Aileron: " + aileron + ", Elevator: " + elevator);
    }

    @FXML
    public void mouseDown(MouseEvent me) {
        if (!mousePushed) {
            mousePushed = true;
            System.out.println("Mouse is down");
        }
    }

    @FXML
    public void mouseUp(MouseEvent me) {
        if (mousePushed) {
            mousePushed = false;
            System.out.println("Mouse is up");
            jx = mx;
            jy = my;
            paint();
        }
    }

    @FXML
    public void mouseMove(MouseEvent me) {
        if (mousePushed) {
            jx = me.getX();
            jy = me.getY();
            paint();
        }
    }

    @FXML
    public void keyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                throttle = Math.min(throttle + 0.2, 1);
                throttleslide.setValue(throttle);
                model.setThrottle(throttle);
                break;
            case DOWN:
                throttle = Math.max(throttle - 0.2, -1);
                throttleslide.setValue(throttle);
                model.setThrottle(throttle);
                break;
            case LEFT:
                rudder = Math.max(rudder - 0.1, -1);
                rudderslide.setValue(rudder);
                model.setRudder(rudder);
                break;
            case RIGHT:
                rudder = Math.min(rudder + 0.1, 1);
                rudderslide.setValue(rudder);
                model.setRudder(rudder);
                break;
            default:
                break;
        }
        System.out.println("Throttle: " + throttle + ", Rudder: " + rudder);
    }
}
