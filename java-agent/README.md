# Java Agent 实现

---

## Step

- 创建两个项目
    - agent是java-agent的实现
    - project 是需要注入java-agent的项目

- 构建好两个jar后运行以下命令
    - `java -javaagent:"agent-jar-abosulute-path=Args" -jar "project-jar-abosulute-path"`

- java agent

> https://docs.oracle.com/javase/9/docs/api/java/lang/instrument/package-summary.html

- premain(String, Instrumentation) : 在程序启动前会执行该方法，如果出现异常则不会继续执行程序
- agentmain(String, Instrumentation)： 在程序启动后执行，但是只能使用VM的 attach-> loadAgent -> detach方式加载