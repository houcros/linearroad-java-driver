package de.twiechert.linroad.jdriver;

import com.sun.jna.Native;

/**
 * Created by tafyun on 30.05.16.
 */
public class DataDriver {

    // provide as arguement
    public static void main(String[] args) {
     DataDriver dataDriver = new DataDriver(args[0]);
        dataDriver.runSample();
    }

     private DataDriverLibrary instance = (DataDriverLibrary) Native.loadLibrary("datadriver", DataDriverLibrary.class);
     private String path;


    public DataDriver(String path) {
        //System.setProperty("jna.library.path", "datadriver");
        System.setProperty("jna.library.path", "/Library/Java/JavaVirtualMachines/jdk1.8.0_51.jdk/Contents/Home/jre/lib/libdatadriver.dylib");
        this.path = path;

    }

    public DataDriver() {
        System.setProperty("jna.library.path", "datadriver");

    }

    public void runSample() {
        DataDriverLibrary.TupleReceivedCallback tupleReceivedCallback = new DataDriverLibrary.TupleReceivedCallback() {
            public void invoke(String tuple) {
                String[] array = tuple.split(",");
                System.out.println("Received: "+tuple);
                System.out.println("Current speed is: "+array[3]);

            }
        };

        instance.startProgram(path,tupleReceivedCallback);
    }

    public DataDriverLibrary getLibrary() {return instance;}




}
