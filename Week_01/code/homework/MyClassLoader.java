package homework;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

/**
 * 自定义类加载器
 */
public class MyClassLoader extends ClassLoader{
    public static void main(String[] args) {
        try {
            Object o = new MyClassLoader().findClass("Hello").newInstance();
            Method m = o.getClass().getMethod("hello");
            m.invoke(o);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        URL url = this.getClass().getResource("/"+ name + ".xlass");
        byte[] bytes = null;
        try {
            bytes = readFileAsByteArr(url.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(bytes == null){
            throw new ClassNotFoundException();
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (255 - bytes[i]);
        }
        return defineClass(name,bytes,0,bytes.length);
    }

    /**
     * 读取文件到字节数组
     * @param path 文件路径
     * @return
     * @throws IOException
     */
    private byte[] readFileAsByteArr(String path) throws IOException {
        FileInputStream in = new FileInputStream(new File(path));
        int size = in.available();
        byte[] bytes = new byte[size];
        in.read(bytes);
        return bytes;
    }
}
