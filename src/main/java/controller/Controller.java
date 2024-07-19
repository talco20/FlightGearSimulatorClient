package controller;

import java.util.Observable;
import java.util.Observer;
import Model.Model;
import view.WindowController;

public class Controller implements Observer {

    Model m;
    WindowController wc;

    public Controller(Model model, WindowController windowController) {
        this.m = model;
        this.wc = windowController;
        model.addObserver(this);
        windowController.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        m.setAileron(wc.getAileron());
        m.setElevators(wc.getElevator());
        m.setRudder(wc.getRudder());
        m.setThrottle(wc.getThrottle());
    }
}
