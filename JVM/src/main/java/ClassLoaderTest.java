import java.util.Base64;

/**
 * @author simple
 */
public class ClassLoaderTest extends ClassLoader {
    public static void main(String[] args) {
        try {
            new ClassLoaderTest().findClass("Hello").newInstance(); // 加载并初始化Hello类
            new ClassLoaderTest().findClass("jvm.Hello").newInstance(); // 加载并初始化Hello类
            new ClassLoaderTest().findClass("jvm.Hello2").newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 可以对class做加解密

        byte[] bytes;
        try {
            bytes = FileUtils.binRead("JVM/src/main/java/Hello.class"); // 生成class文件： javac Hello.java
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    public static byte[] decode(String base64) {
//        Base64.getMimeDecoder() : 带 Mime 适用于那些base64格式带换行的
        return Base64.getDecoder().decode(base64);
    }
}
