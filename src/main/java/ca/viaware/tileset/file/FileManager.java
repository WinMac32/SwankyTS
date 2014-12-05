package ca.viaware.tileset.file;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Set;

public class FileManager {

    private static ArrayList<FileInterface> interfaces;

    public static void registerInterfaces() {
        Reflections reflections = new Reflections("ca.viaware.tileset.file.interfaces");
        Set<Class<? extends FileInterface>> interfaces = reflections.getSubTypesOf(FileInterface.class);

        FileManager.interfaces = new ArrayList<FileInterface>();

        for (Class<? extends FileInterface> i : interfaces) {
            try {
                FileManager.interfaces.add(i.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<FileInterface> getInterfaces() {
        return interfaces;
    }

}
