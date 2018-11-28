To display existed house, floor, room: [Display data](https://github.com/TranPhucVinh/Android/tree/master/PROJECTS/Display%20data)

To display existed devices in room: Use Recycler view (to send in many parameter)

```java
displayAdapter = new DisplayAdapter(getApplicationContext(),
                    deviceType, deviceName, currentDeviceStatus);
```

To control devices: use protocol Websocket. Use library OKHTTP: okhttp-3.11.0.jar

Control devices: [Control devices](https://github.com/TranPhucVinh/Android/tree/master/PROJECTS/Control%20devices)

### Add OKHTTP jar to project

In tab ``1.Project``, change from ``Android`` to ``Project``

Right click in the name of the project, go to ``Open module settings``. Go to ``Dependencies``, press plus symbol and find module okhttp
