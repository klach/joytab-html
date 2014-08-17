package pl.joytabhtml.rest;

import org.restlet.data.Form;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SlidersResource extends ServerResource {

    private static final int VJOY_AXIS_MAX = 32768;
    private final InetAddress address = InetAddress.getLoopbackAddress();

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

        DatagramSocket socket = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream dataStream = new DataOutputStream(baos);
            dataStream.writeInt(vJoyAxisValue(x3));
            dataStream.writeInt(vJoyAxisValue(y3));
            dataStream.writeInt(vJoyAxisValue(x2));
            dataStream.writeInt(vJoyAxisValue(y2));
            dataStream.writeInt(vJoyAxisValue(x1));
            dataStream.writeInt(vJoyAxisValue(y1));
            dataStream.writeInt(0); // slider
            dataStream.writeInt(0); // dial
            dataStream.writeInt(0); // buttons
            dataStream.writeInt(0); // continuous POV
            dataStream.writeInt(0); // discrete POVs
            DatagramPacket packet = new DatagramPacket(baos.toByteArray(), baos.size(),
                    address, 1608);

            socket = new DatagramSocket();
            socket.send(packet);
        } catch (Exception e) {
            // ignore
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

        return "OK";
    }

    private int vJoyAxisValue(float value) {
        if (value <= 0f) return 0;
        if (value >= 1f) return VJOY_AXIS_MAX;
        return (int)(value * VJOY_AXIS_MAX);
    }
}
