`2021年2月6日21:17:09 begin`

仅仅使用Google就完成了人生第一次破解

#### 起因

在异乡的打工人，不善言谈，幸有一老同学，周末常邀吃饭，感恩之心铭记于心。她结婚时，为表心意欲做视频，视频需要制作字幕，搜索之，偶遇一字幕软件，但是有些功能不支持游客使用，遂有想法破解这个软件。

#### 过程

人生第一次破解Java软件过程，就是一个不断试错的过程，不断使用Google搜索需要了解的破解的知识和方法，不断尝试使用这些知识和方法看是否能达到破解的目的。就像我们人生一样，必定是想实现某个人生目的，我们不断尝试使用各种方法去实现它，希望大家都能实现自己人生理想。

##### 序章

我手上有一字幕软件，我点击导出到Premiere Pro时，提示“使用此功能前必须登录”。

![导出到Premiere Pro](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075706first-crack-software-export.png)

![使用此功能前必须登录](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075730first-crack-software-no-login.png)

好，我注册一下，登录成功之后，再点击导出到Premiere Pro，提示“未开通此特权，无法使用对应功能”。

![未开通此特权，无法使用对应功能](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075742first-crack-software-already-login.png)

很好，我只是用一次，试用的机会都不给我吗？只好自己给自己搞个试用机会了。

##### Round 1

我的第一个想法就是找到对应的Jar包，进行反编译得到源码，修改源码，重新编译即可。

一般我使用的反编译工具就是 JD-GUI，使用方法是用软件打开jar包之后，点击 File>Save All Sources 即可导入源码。

但是这次要批量处理多个jar包，使用的功能是IDEA默认的反编译工具，在IDEA打开class文件时，就是通过该工具反编译后的结果。

java-decompiler 是IDEA中的插件名称，实际上来源于 fernflower 工具，github仓库地址：[https://github.com/fesh0r/fernflower](https://github.com/fesh0r/fernflower)。具体使用方法可参考`README.md`。我在java-decompiler的路径下 `F:\IntelliJ IDEA 2020.1.1\plugins\java-decompiler\lib` ,执行 `java -cp java-decompiler.jar org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler H:\source\xxxx_2.4.1_WIN64\xxxx_lib\ H:\source\code\`，得到了 `H:\source\xxxx_2.4.1_WIN64\xxxx_lib\` 目录下的所有Jar包源码，放在 `H:\source\code\`。

批量反编译命令：

![批量反编译命令](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075805first-crack-software-decompiler-command.png)

批量反编译结果：

![批量反编译结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075754first-crack-software-decompiler-run-result.png)

第一步完成，接下来就是看源码了，但并没有这么简单。代码是经过混淆的，没有那么容易看出具体逻辑。现在我有源码在手，但要找到导出功能在哪里，简直是大海捞针，一切回到原点。

反编译代码样例：

![反编译代码样例](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328095700first-crash-decompile-source-code.jpg)

##### Round 2

在一轮思考之后，下一步的想法是在运行时，捕捉到代码逻辑，方法间的调用，从而定位到具体的类上，通过 `-javaagent` 方式动态修改代码来达到我的目的。 

首先，我需要动态捕捉到代码逻辑。Google搜索`detector java trace method invoke`，从项目[https://github.com/emacsist/java-trace-method-invoke-tree](https://github.com/emacsist/java-trace-method-invoke-tree) 到 [https://github.com/oldmanpushcart/greys-anatomy](https://github.com/emacsist/java-trace-method-invoke-tree) 到 [https://github.com/oldmanpushcart/greys-anatomy](https://github.com/oldmanpushcart/greys-anatomy)，由于greys-anatomy不支持Window，所以找到它的wiki上提到的[https://github.com/btraceio/btrace](https://github.com/btraceio/btrace)。

btrace使用比较简单，README.md写得很清楚，先下载release上的压缩包解压，然后执行命令就可以了。

README：

![README](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075817first-crack-software-btrace-readme.png)


我使用的命令是`<btrace>/bin/btrace <PID> <trace_script>`，启动的字幕软件的线程PID，我是通过jvisualvm.exe中获取到的，所在目录`${jdk根目录}\bin`。

获取java进程的PID：

![get-java-pid](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075848first-crack-software-btrace-get-java-pid.png)

追踪脚本（trace_script），可以在samples目录中找到样例，修改样例就可以实现运行时方法的追踪。

一开始，追踪所有的方法，因控制台打印过快导致无法定位，所以需要缩小范围。通过学习swing事件监听相关知识`http://c.biancheng.net/view/1235.html`，了解点击会被动作事件监听器ActionListener.actionPerformed捕捉到，执行对应的动作。于是其中一个样例代码修改为：

``` java
@BTrace
public class AllMethods1 {
    @OnMethod(
            clazz = "+java.awt.event.ActionListener",
            method = "actionPerformed"
    )
    public static void m(@ProbeClassName String probeClass, @ProbeMethodName String probeMethod, AnyType[] args) {
        print("entered " + probeClass);
        println("." + probeMethod);
        printArray(args);
        println();

    }
}
```

`@OnMethod`注解上，可以监听到java.awt.event.ActionListener子类中的actionPerformed方法的执行，将类全限定名、方法名、参数打印出来。btrace执行效果如下：

![btrace运行结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075913first-crack-software-btrace-run-result.png)

从图中可知，我已经找到了当我点击导出的时候，点击事件被监听到并由U.aB.actionPerformed方法处理，接下来我们一起来看看反编译后的代码中U.aB.actionPerformed的具体逻辑。

![U.aB.actionPerformed](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075921first-crack-software-source-code.png)

从上图可以很简单的看出来，就是只要 `m.a(true) && m.b(true)` 返回true即可。

定位完成之后，那么我们需要用 `JavaAgent` 方式破解，我们需要找到m类中a方法和b方法直接返回true即可。关于`javaagent`相关知识可以查看以下几篇文章，也可自行google（感谢各位大佬的付出）：

[如何指导编写一个javaagent](https://blogs.oracle.com/ouchina/javaagent)

[认识 JavaAgent --获取目标进程已加载的所有类](https://paper.seebug.org/1099/)

[javaagent使用指南](https://www.cnblogs.com/rickiyang/p/11368932.html)

[破解 Java 系软件入门](https://juejin.cn/post/6844903856279650317)

先贴图再开搞：

![java-agent-overview](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328075938first-crack-software-java-agent-overview.png)

分三步走：

1、一个代理类（Agent class）

2、打包（Packaging），添加MANIFEST.MF，告诉JVM为我们的代理类提供哪些功能

3、让JVM加载代理jar，并执行代理类的内容

第一步，创建maven工程，创建代理类，AgentMain代码（Agent class）如下：
``` java
package org.example.agent;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
public class AgentMain {
    private static int ASM5 = 327680;
    private static int tmp = 0;

    public static class MyMethodVisitor extends AdviceAdapter {
        protected MyMethodVisitor(MethodVisitor mv, int access, String name, String desc) {
            super(ASM5, mv, access, name, desc);
        }

        @Override
        protected void onMethodEnter() {
            // 在方法开始插入 return true;
            mv.visitInsn(ICONST_1);
            mv.visitInsn(IRETURN);
        }
    }

    public static class MyClassVisitor extends ClassVisitor {

        public MyClassVisitor(ClassVisitor classVisitor) {
            super(ASM5, classVisitor);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
            // 转换 a 和 b 方法，(Z)Z表示入参为boolean，返回值为boolean
            if ((name.endsWith("a") && descriptor.equals("(Z)Z")) || (name.endsWith("b") && descriptor.equals("(Z)Z"))) {
                return new MyMethodVisitor(mv, access, name, descriptor);
            }
            return mv;
        }
    }

    public static void premain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException, IOException {
        inst.addTransformer(new MyClassFileTransformer(), true);
        Class[] classes = inst.getAllLoadedClasses();

        for (int i = 0; i < classes.length; i++) {
            try {
                if (classes[i].getName().equalsIgnoreCase("c.m")) {
                    inst.retransformClasses(classes[i]);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static class MyClassFileTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

            if (!"c/m".equalsIgnoreCase(className)) return bytes;
            ClassReader cr = new ClassReader(bytes);
            ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
            ClassVisitor cv = new MyClassVisitor(cw);
            cr.accept(cv, ClassReader.SKIP_FRAMES | ClassReader.SKIP_DEBUG);
            return cw.toByteArray();
        }
    }

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException, IOException {
        inst.addTransformer(new MyClassFileTransformer(), true);
        Class[] classes = inst.getAllLoadedClasses();
        for (int i = 0; i < classes.length; i++) {
            System.out.println(classes[i]);
            try {
                if (classes[i].getName().equalsIgnoreCase("c.m")) {
                    try {
                        classes[i].getMethod("a", boolean.class);
                        classes[i].getMethod("b", boolean.class);
                    } catch (NoSuchMethodException e) {
                        continue;
                    }
                    inst.retransformClasses(classes[i]);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
```

第二，配置元数据信息MAINFEST.MF，pom文件中配置如下：
``` xml
<build>
        <finalName>my-attach-agent</finalName>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                        </manifest>
                        <manifestEntries>
                            <Premain-Class>org.example.agent.AgentMain</Premain-Class>
                            <Agent-Class>org.example.agent.AgentMain</Agent-Class>
                            <Can-Redefine-Classes>true</Can-Redefine-Classes>
                            <Can-Retransform-Classes>true</Can-Retransform-Classes>
                            <Permissions>all-permissions</Permissions>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                    <compilerArguments>
                        <extdirs>libs</extdirs>
                        <verbose />
                        <!-- Q:mvn打包报错：程序包jdk.internal.org.objectweb.asm不存在
                        A:原因是因为rt包没有打到项目中去。
                        bootclasspath配置rt.jar所在目录即可，Windows分隔符英文分号,linux分隔符英文冒号
                        -->
                        <bootclasspath>F:\jdk1.8.0_221\jre\lib\rt.jar;F:\jdk1.8.0_221\lib\tools.jar;</bootclasspath>
                    </compilerArguments>
                </configuration>
            </plugin>
        </plugins>

    </build>
```

第三步，加载javaagent，我使用的方法有二：

1、使用java -javaagent:/path/to/my-attach-agent.jar -jar xxx.jar

2、动态加载。

``` java
public class Main {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException, AgentLoadException, AgentInitializationException {
        //获取当前系统中所有 运行中的 虚拟机
        System.out.println("running JVM start ");
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            System.out.println("=====================VirtualMachineDescriptor===================>");
            //如果虚拟机的名称为 xxx 则 该虚拟机为目标虚拟机，获取该虚拟机的 pid
            //然后加载 agent.jar 发送给该虚拟机
            System.out.println(vmd.displayName());
            if (vmd.displayName().endsWith("xxx.jar") || vmd.displayName().endsWith("xxx.jar")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());

                virtualMachine.loadAgent("H:\\code\\agent\\target\\my-attach-agent.jar", "org.example.agent.AgentMain");
//                virtualMachine.detach();
            }
        }
    }
}

```

大功告成。

![破解结果图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210328104638first-crack-result.jpg)

##### Round 3

虽然大功告成了，但是我还是想探索一下其他方法，如：[字节码增强技术探索](https://tech.meituan.com/2019/09/05/java-bytecode-enhancement.html) <b>2. 2.2 Javassist</b> 提到的方法 2.2 Javassist。

`ASM是在指令层次上操作字节码的，阅读上文后，我们的直观感受是在指令层次上操作字节码的框架实现起来比较晦涩。故除此之外，我们再简单介绍另外一类框架：强调源代码层次操作字节码的框架Javassist。`

我只需要将前面代码的AgentMain类中MyClassFileTransformer修改一下即可：

``` java
 public static class MyClassFileTransformer implements ClassFileTransformer {
        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                                ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException{
            if (!"c/m".equalsIgnoreCase(className)) return bytes;
            System.out.println(className);
            CtClass cc = null;
            try {

                ClassPool cp = ClassPool.getDefault();
                cc = cp.get("c.m");
                CtMethod m = cc.getDeclaredMethod("a", new CtClass[]{CtClass.booleanType});
                m.insertBefore("{ return true; }");
                CtMethod m1 = cc.getDeclaredMethod("b", new CtClass[]{CtClass.booleanType});
                m1.insertBefore("{ return true; }");
            } catch (Exception e) {
                return bytes;
            }
            byte[] transformerBytes = null;
            try {
                transformerBytes = cc.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
                return bytes;
            }
            return transformerBytes;
        }
    }
```

maven打包的时候注意：<b>需要MANIFEST.MF添加Boot-Class-Path。</b>

pom文件修改：

``` xml
<manifestEntries>
    <Premain-Class>org.example.agent.AgentMain002</Premain-Class>
    <Agent-Class>org.example.agent.AgentMain002</Agent-Class>
    <Can-Redefine-Classes>true</Can-Redefine-Classes>
    <Can-Retransform-Classes>true</Can-Retransform-Classes>
    <Permissions>all-permissions</Permissions>
    <Boot-Class-Path>G:/Maven/mavenLib/org/javassist/javassist/3.27.0-GA/javassist-3.27.0-GA.jar</Boot-Class-Path>
</manifestEntries>
```

`G:/Maven/mavenLib/org/javassist/javassist/3.27.0-GA/javassist-3.27.0-GA.jar`是javassit包文件路径。

当然也可以使用Build Artifacts方法，将依赖包打到代理jar包里。

#### 结果

结果是字幕软件破解成功了，视频也做好了，然后写了一大段文字加视频连接发给她，她说很感动。【完】

`2021年3月29日18:40:25 end`