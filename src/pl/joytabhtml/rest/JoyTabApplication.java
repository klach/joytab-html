package pl.joytabhtml.rest;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
import uk.digitalsquid.droidpad.buttons.Slider;

public class JoyTabApplication extends Application {

    private final Slider slider1;
    private final Slider slider2;
    private final Slider slider3;

    public JoyTabApplication(Slider slider1, Slider slider2, Slider slider3) {
        this.slider1 = slider1;
        this.slider2 = slider2;
        this.slider3 = slider3;
    }

    /**
     * Creates a root Restlet that will receive all incoming calls.
     */
    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/sliders", SlidersResource.class);
        return router;
    }

    public Slider getSlider1() {
        return slider1;
    }

    public Slider getSlider2() {
        return slider2;
    }

    public Slider getSlider3() {
        return slider3;
    }
}
