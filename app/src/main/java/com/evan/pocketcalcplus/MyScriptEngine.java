package com.evan.pocketcalcplus;

import android.app.Application;

import com.myscript.certificate.MyCertificate;
import com.myscript.iink.Configuration;
import com.myscript.iink.Engine;

import java.io.File;

public class MyScriptEngine extends Application {
    private static Engine engine;

    public static synchronized Engine getEngine() {
        if (engine == null) {
            engine = Engine.create(MyCertificate.getBytes());
        }

        return engine;
    }

    public static void configureEngine(Engine engine, String packageCodePath,
                                       String filesDirPath) {
        // Setup configuration
        engine.getConfiguration().setStringArray("configuration-manager.search-path",
                new String[]{"zip://" + packageCodePath + "!/assets/conf"});

        // set the temporary directory
        engine.getConfiguration().setString("content-package.temp-folder",
                filesDirPath + File.separator + "tmp");

        // Defaults
        setFloatingPointPrecision(3);
    }

    public static void setFloatingPointPrecision(int input) {
        engine.getConfiguration().setNumber("math.solver.fractional-part-digits", input);
    }
}