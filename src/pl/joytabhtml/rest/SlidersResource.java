package pl.joytabhtml.rest;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

public class SlidersResource extends ServerResource {

    @Post
    public String set(Representation entity) {
        Form form = new Form(entity);
        float x1 = Float.parseFloat(form.getFirstValue("x1"));
        float y1 = Float.parseFloat(form.getFirstValue("y1"));
        float x2 = Float.parseFloat(form.getFirstValue("x2"));
        float y2 = Float.parseFloat(form.getFirstValue("y2"));
        float x3 = Float.parseFloat(form.getFirstValue("x3"));
        float y3 = Float.parseFloat(form.getFirstValue("y3"));
        JoyTabApplication app = (JoyTabApplication)getApplication();
        app.getSlider1().setPosition(x1, y1);
        app.getSlider2().setPosition(x2, y2);
        app.getSlider3().setPosition(x3, y3);
        return "OK";
    }
}
