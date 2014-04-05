package pl.htmljoytab;

import uk.digitalsquid.droidpad.ConnectionCallbacks;
import uk.digitalsquid.droidpad.Vec3;
import uk.digitalsquid.droidpad.buttons.Layout;

public class ConnectionCallbacksImpl implements ConnectionCallbacks {

    private final Layout layout;

    public ConnectionCallbacksImpl(Layout layout) {
        this.layout = layout;
    }

    @Override
    public void onConnectionFinished() {
    }

    @Override
    public Layout getScreenData() {
        return layout;
    }

    @Override
    public void broadcastState(int status, String connectedPc) {
    }

    @Override
    public void broadcastAlert(int type) {
    }

    @Override
    public Vec3 getAccelerometerValues() {
        return null;
    }

    @Override
    public Vec3 getGyroscopeValues() {
        return null;
    }

    @Override
    public float getWorldRotation() {
        return 0;
    }
}
