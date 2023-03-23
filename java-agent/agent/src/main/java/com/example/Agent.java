package com.example;

import com.sun.tools.attach.VirtualMachine;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * @author simple
 */
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println(agentArgs);
        System.out.println("This is premain....");
        inst.addTransformer(new CustomClassFileTransformer("com.example.Main", "premain"), true);
    }

    public static void agentmain(String agentArgs) { // 没有Instrumentation则执行这个方法
        System.out.println("agentmain: only args[" + agentArgs + "]");
    }

    // https://docs.oracle.com/javase/9/docs/api/java/lang/instrument/package-summary.html
    public static void agentmain(String agentArgs, Instrumentation inst) throws Exception {
        System.out.println("agentmain: both have [" + agentArgs + ", inst]");
        for (var clz : inst.getAllLoadedClasses()) {
            if (clz.getName().equals("com.example.Main")) {
                System.out.println("transfer the class -- " + clz.getName());
                inst.addTransformer(new CustomClassFileTransformer("com.example.Main", "agentmain"), true);
                inst.retransformClasses(clz);
                break;
            }
        }
    }

    public static class CustomClassFileTransformer implements ClassFileTransformer {
        String clzName;
        String operator;

        public CustomClassFileTransformer(String clzName, String operator) {
            this.clzName = clzName;
            this.operator = operator;
        }

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            if (!clzName.equals(className.replaceAll("/", "."))) return null;
            System.out.println("start to transfer class -- " + className);
            try {
                ClassPool pool = ClassPool.getDefault();

                if (classBeingRedefined != null) {
                    ClassClassPath ccp = new ClassClassPath(classBeingRedefined);
                    pool.insertClassPath(ccp); // 将需要修改的class的路径添加到pool中
                }

                CtClass ctClass = pool.get(clzName);
//                ctClass.setName("com/example/Main2"); // 重置名称后会导致无法启动
//                for (int i = 0; i < ctClass.getDeclaredMethods().length; i++) {
//                    System.out.println(ctClass.getDeclaredMethods()[i].getName());
//                }
                CtMethod method = ctClass.getDeclaredMethods()[1];
                method.setBody(String.format("""
                     {
                       System.out.println("main: (%s updated) Hello world!" + java.time.ZonedDateTime.now());
                     }
                    """, operator));
                byte[] bytes = ctClass.toBytecode();
                ctClass.detach(); // 由于后续不会用到这个类， 所以可以卸载class
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    // 手动向VM注入agent jar，注入后会执行agentmain方法
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\simple\\IdeaProjects\\JavaBase\\java-agent\\agent\\target\\agent-1.0.0-jar-with-dependencies.jar";
        VirtualMachine vm = VirtualMachine.attach("14976");
        vm.loadAgent(path);
        vm.detach();
    }
}
