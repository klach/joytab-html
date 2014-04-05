package pl.joytabhtml.server;

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.resource.Directory;
import pl.joytabhtml.ConnectionCallbacksImpl;
import pl.joytabhtml.rest.JoyTabApplication;
import uk.digitalsquid.droidpad.Connection;
import uk.digitalsquid.droidpad.ConnectionInfo;
//import uk.digitalsquid.droidpad.MDNSBroadcaster;
import uk.digitalsquid.droidpad.buttons.Layout;
import uk.digitalsquid.droidpad.buttons.ModeSpec;
import uk.digitalsquid.droidpad.buttons.Orientation;
import uk.digitalsquid.droidpad.buttons.Slider;

public class Main {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 3141;
        int httpPort = 8080;
        int interval = 20;

//        // Start mDNS broadcaster
//        String deviceName = "localserver";
//        // not supported - deviceName = "secure:" + deviceName;
//        MDNSBroadcaster mdns = new MDNSBroadcaster(InetAddress.getByName(host),
//                deviceName.substring(0, Math.min(deviceName.length(), 40)),
//                port);
//        mdns.start();

        Layout layout = new Layout();
        final Slider slider1 = new Slider(0, 0, 0, 0, Orientation.Both);
        slider1.setPosition(0.5f, 0.5f);
        layout.add(slider1);
        final Slider slider2 = new Slider(0, 0, 0, 0, Orientation.Both);
        slider2.setPosition(0.5f, 0.5f);
        layout.add(slider2);
        final Slider slider3 = new Slider(0, 0, 0, 0, Orientation.Both);
        slider3.setPosition(0.5f, 0.5f);
        layout.add(slider3);

        ModeSpec spec = new ModeSpec();
        spec.setMode(ModeSpec.LAYOUTS_JS);
        spec.setLayout(layout);

        final ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.callbacks = new ConnectionCallbacksImpl(layout);
        connectionInfo.port = port;
        connectionInfo.securePort = port + 1;
        connectionInfo.spec = spec;
        connectionInfo.interval = (float)interval / 1000f;
        connectionInfo.reverseX = false;
        connectionInfo.reverseY = false;
//        connectionInfo.identity = pskAuthenticator;
        connectionInfo.onlyBindLocalInsecure = false;

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Connection connection = new Connection();
                        connection.doInBackground(connectionInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        // start Restlet
        Component component = new Component();
        component.getServers().add(Protocol.HTTP, httpPort);
        component.getClients().add(Protocol.FILE);

        component.getDefaultHost().attach("/rest",
                new JoyTabApplication(slider1, slider2, slider3));
        component.getDefaultHost().attach("/joy", new Application() {
            @Override
            public Restlet createRoot() {
                return new Directory(getContext(), "file:resources/static/");
            }
        });

        component.start();
    }
}
