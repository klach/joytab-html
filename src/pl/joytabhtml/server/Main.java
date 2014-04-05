package pl.htmljoytab.server;

import pl.htmljoytab.ConnectionCallbacksImpl;
import uk.digitalsquid.droidpad.Connection;
import uk.digitalsquid.droidpad.ConnectionInfo;
//import uk.digitalsquid.droidpad.MDNSBroadcaster;
import uk.digitalsquid.droidpad.buttons.Layout;
import uk.digitalsquid.droidpad.buttons.ModeSpec;
import uk.digitalsquid.droidpad.buttons.Orientation;
import uk.digitalsquid.droidpad.buttons.Slider;

import java.net.InetAddress;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 3141;
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

        new Thread() {
            public void run() {
                while (true) {
                    slider1.setPosition(new Random().nextFloat(), new Random().nextFloat());
                    try { sleep(1000); } catch (Exception e) {}
                }
            }
        }.start();

        ModeSpec spec = new ModeSpec();
        spec.setMode(ModeSpec.LAYOUTS_JS);
        spec.setLayout(layout);

        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.callbacks = new ConnectionCallbacksImpl(layout);
        connectionInfo.port = port;
        connectionInfo.securePort = port + 1;
        connectionInfo.spec = spec;
        connectionInfo.interval = (float)interval / 1000f;
        connectionInfo.reverseX = false;
        connectionInfo.reverseY = false;
//        connectionInfo.identity = pskAuthenticator;
        connectionInfo.onlyBindLocalInsecure = false;

        while (true) {
            Connection connection = new Connection();
            connection.doInBackground(connectionInfo);
        }
    }
}
