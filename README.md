joytab-html
===========

Virtual analog joystick for PC controlled via browser on tablet or phone, e.g. on iPad where it's hard to find a native analog joystick working with 64bit PC.

For Android devices there is [DroidPad](http://www.digitalsquid.co.uk/droidpad/) that works well.

Bases on
========

* Source code for Android's part of [DroidPad](http://www.digitalsquid.co.uk/droidpad/)
* [virtualjoystick.js](https://github.com/jeromeetienne/virtualjoystick.js)
* [Restlet Framework](http://restlet.org/)

How to use it?
==============

* Run joytab-html (Java required): run.bat or run.sh depending on operating system
* Run PC client of [DroidPad](http://www.digitalsquid.co.uk/droidpad/)
* From DroidPad connect to joytab-html: double click on "Custom device", enter 127.0.0.1 as IP, port 3141, do not check "secure connection"
* Point a browser on tablet or phone to http://{your computer's IP}:8080/joy/j.html

How to build it?
================

TBD

Axes mapping
============

On my machine the first axis for some reason doesn't work linearly (don't know why?) so I don't use it directly.

The place where touches are mapped to axes is one of last lines in ```resources/static/j.html```:
```{x1: 0, y1: 0, x2: newY2, y2: newX2, x3: newX1, y3: newY1}```.

That should be later remapped in DroidPad PC client (Edit -> Reorder axes) according to needs. Here's the mapping I use:

```
Axis 1 -> None
Axis 2 -> None
Axis 3 -> Axis 3
Axis 4 -> Axis 4
Axis 5 -> Axis 1
Axis 6 -> Axis 2
```

Such a configuration should allow playing [FlightGear](http://www.flightgear.org/) but axis for rudder is not detected automatically.

If you occasionally play [Falcon BMS](http://www.benchmarksims.org/), here's the mapping to put in ```Falcon BMS 4.32\User\Config\DeviceDefaults.txt```:

```
# DroidPad Joystick
GUID = {D6ADD801-0000-0000-0000-504944564944}
AXIS_PITCH = Y
AXIS_ROLL = X
AXIS_YAW = RX
AXIS_THROTTLE = Z
```

Tested with
===========

* iPad
* Windows 7 64bit
