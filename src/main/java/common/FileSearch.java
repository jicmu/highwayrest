package common;

import org.apache.ibatis.session.SqlSessionFactory;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class FileSearch {
    public static void fileSearchAndConstructor(String packageName, Map<String, Handler> pathList) {
        String packageNameSlash = "./" + packageName.replace(".", "/");

        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);

        String directoryString = directoryURL.getFile();

        File file = new File(directoryString);
        if(file.exists()) {
            list(file.listFiles(), packageName, pathList);
        }
    }

    public static void list(File[] fileList, String packageName, Map<String, Handler> pathList) {
        for(File f : fileList) {
            if(f.getName().endsWith(".class")) {
                String fileName = f.getName();
                fileName = fileName.substring(0, fileName.length() - 6);
                try{
                    Class c = Class.forName(packageName + "." + fileName);
                    Constructor<?> constructor = c.getConstructor(null);
                    try {
                        Handler obj = (Handler)constructor.newInstance();
                        pathList.put(obj.getPath(), obj);
                        System.out.println("obj: " + obj);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e){
                    System.err.println(packageName + "." + fileName + " does not appear to be a valid class");
                    System.out.println(e.getMessage());
                }
            }
            else {
                String oPackageName = packageName;
                oPackageName += "." +f.getName();
                list(f.listFiles(), oPackageName, pathList);
            }
        }
    }

    public static void fileSearchAndMapperReturn(String packageName, ArrayList<Class<?>> list) {
        System.out.println("통과!");
        String packageNameSlash = "./" + packageName.replace(".", "/");
        System.out.println("packageNameSlash: " + packageNameSlash);

        URL directoryURL = Thread.currentThread().getContextClassLoader().getResource(packageNameSlash);
        System.out.println("url: " + directoryURL);

        String directoryString = directoryURL.getFile();
        System.out.println("directoryString: " + directoryString);

        File file = new File(directoryString);
        if(file.exists()) {
            mapperReturn(file.listFiles(), packageName, list);
        }
        System.out.println("list.size(): " + list.size());
        System.out.println("끝");
    }

    public static void mapperReturn(File[] fileList, String packageName, ArrayList<Class<?>> list) {
        for(File f : fileList) {
//            System.out.println("fileNm: " + f.getName());
            if(f.getName().endsWith(".class")) {
                String fileName = f.getName();
                fileName = fileName.substring(0, fileName.length() - 6);
                System.out.println("className: " + packageName + "." + f.getName());
                try{
                    Class<?> cl = Class.forName(packageName + "." + fileName);
                    System.out.println("cl: " + cl);
                    list.add(cl);
                } catch (ClassNotFoundException e){
                    System.err.println(packageName + "." + fileName + " does not appear to be a valid class");
                    System.out.println(e.getMessage());
                }
            }
            else {
                String oPackageName = packageName;
                oPackageName += "." +f.getName();
                mapperReturn(f.listFiles(), oPackageName, list);
            }
        }
    }
}
