package volumeController;

import java.io.IOException;

public class Controller {
    private static final String nircmdFilePath="src\\volumeController\\nircmd.exe";
    public  static void setSystemVolume(int volume) {




        Runtime rt = Runtime.getRuntime();
        Process pr;
        try {
            pr = rt.exec(nircmdFilePath + " setsysvolume " + volume);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
