`begin 2020年12月6日21:08:15`

## 命令模式(Command Pattern)

#### 定义

>Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.——《Design Patterns: Elements of Reusable Object-Oriented Software》

命令模式(Command)，將一个请求封装为一个对象，从而使你可用不同的请求对客户进行参数化，对请求排队或者记录请求日志，以及支持可撤销的操作。——《设计模式：可复用面向对象软件的基础》

#### 图示

命令模式结构图：

![命令模式结构图](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210516023435command-pattern-structure.jpg)


#### 角色

<b>抽象命令角色(Command)：</b>

- 声明一个接口包含了执行的操作，如图中excute方法

<b>具体命令角色(ConcreteCommand)：</b>

- 定义了接收者对象和命令的绑定
- 实现【抽象命令角色】定义的执行操作，具体实现是：在接收者调用对应的操作

<b>客户端角色(Client)：</b>

- 创建一个【具体命令角色】，并设置它的接收者

<b>调用者角色(Invoker)：</b>

- 要求命令执行请求，即调用Command.execute方法

<b>接收者角色(receiver)：</b>

- 知道如何执行一个请求相关的操作，真正执行操作的人

#### 代码示例

【记事本(notepad)】：通过命令模式实现记事本的复制(copy)、粘贴(paste)的功能

1、记事本打开TEXT文件之后，右键菜单(Menu)就是调用者，具体的菜单项目有复制、粘贴等

2、点击复制、粘贴命令，通过具体命令让接收者执行该命令，从TEXT复制到剪贴板，或从剪贴板粘贴到TEXT文件。

![记事本](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210516031527command-pattern-code-example.jpg)

具体代码如下：

抽象命令角色(Command.java)：
``` java
public interface Command {
    void execute();
}
```

具体命令角色(CopyCommand.java、PasteCommand.java)：
``` java
public class CopyCommand implements Command {
    private Text receiver;

    public CopyCommand(Text receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.copy();
    }
}

public class PasteCommand implements Command {
    private Text receiver;

    public PasteCommand(Text receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.paste();
    }
}
```

调用者角色(Menu.java)：
``` java
public class Menu {
    private static Map<String, Command> commandMap = new HashMap<>();

    public void addMenuItem(String menuItemName, Command command) {
        commandMap.put(menuItemName, command);
    }

    public void click(String menuItemName) {
        commandMap.get(menuItemName).execute();
    }

}
```

接收者(Text.java):
``` java
public class Text {
    public void copy() {
        System.out.println("复制成功");
    }
    public void paste() {
        System.out.println("粘贴成功");
    }
}
```


客户端角色(Menu.java)：
``` java
public class Notepad {
    public static void main(String[] args) {
        // 打开文件
        Text receiver = new Text();
        // 初始化右键菜单
        Menu menu = new Menu();
        Command copyCommand = new CopyCommand(receiver);
        Command pasteCommand = new PasteCommand(receiver);
        menu.addMenuItem("copy", copyCommand);
        menu.addMenuItem("paste", pasteCommand);
        // 复制命令
        menu.click("copy");
        // 粘贴命令
        menu.click("paste");
    }
}
```

执行命令结果：
![执行命令结果](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210516031935command-pattern-result.jpg)

#### 模式扩展

- 宏命令：使用组合模式将多个命令组合在一起

实现一个复制并粘贴命令，类似在Intellij IDEA里面的Windows系统快捷键是CTRL + D。

![复制粘贴命令](https://images.cnblogs.com/cnblogs_com/mingmingcome/1618392/o_210516033707command-pattern-duplicate.jpg)

代码如下：
``` java
public class DuplicateCommand implements Command {
    private List<Command> commandList;
    
    public DuplicateCommand(Text receiver) {
        commandList = new ArrayList<>();
        commandList.add(new CopyCommand(receiver));
        commandList.add(new PasteCommand(receiver));
    }
    
    @Override
    public void execute() {
        for(int i = 0; i < commandList.size(); i ++) {
            commandList.get(i).execute();
        }
    }
}

```

- 撤销操作：使用备忘录模式保存命令历史，用来撤销

我们要记录TEXT当时的状态，如TEXT当前有什么内容

修改后的接收者角色，也是备忘录模式中的备忘录角色：

``` java
public class TextWithUndo {
    private StringBuilder content;

    public TextWithUndo(String text) {
        this.content = new StringBuilder(text);
    }

    public void paste(String text) {
        content.append(text);
        System.out.println("粘贴成功");
    }

    public TextWithUndo createText() {
        return new TextWithUndo(this.content.toString());
    }

    public String toString() {
        return content.toString();
    }
}
```

修改后的具体命令角色，备忘录的发起人角色：

``` java
public class PasteCommandWithUndo implements Command {
    private TextWithUndo receiver;
    private UndoCommandCaretaker commandCaretaker;

    public PasteCommandWithUndo(TextWithUndo receiver, UndoCommandCaretaker commandCaretaker) {
        this.receiver = receiver;
        this.commandCaretaker = commandCaretaker;
    }

    @Override
    public void execute() {
        commandCaretaker.addText(this.createUndoReceiver());
        String fromClipboard = " xxx ";
        receiver.paste(fromClipboard);
    }

    public TextWithUndo createUndoReceiver() {
        return new TextWithUndo(this.receiver.toString());
    }

}
```

备忘录管理者角色：
``` java
public class UndoCommandCaretaker {
    private List<TextWithUndo> receiverList = new ArrayList<>();

    public TextWithUndo getText(int i) {
        return receiverList.get(i);
    }

    public void addText(TextWithUndo text) {
        receiverList.add(text);
    }
}
```

修改后的客户端：

``` java
public class NotepadWithUndo {
    public static void main(String[] args) {
        // 打开文件
        TextWithUndo receiver = new TextWithUndo("");
        // 初始化右键菜单
        Menu menu = new Menu();
        // 备忘录
        UndoCommandCaretaker commandCaretaker = new UndoCommandCaretaker();
        Command pasteCommand = new PasteCommandWithUndo(receiver, commandCaretaker);
        menu.addMenuItem("paste", pasteCommand);
        // 粘贴命令
        menu.click("paste");

        menu.click("paste");

        System.out.println(receiver.toString()); // 输出： xxx xxx 

        receiver = commandCaretaker.getText(1);

        System.out.println(receiver.toString()); // 输出： xxx 
    }
}
```

这样子结合备忘录模式就可以实现撤销操作


#### 使用场景

- 请求调用者和请求接收者解耦，使得调用者和接收者不直接交互
- 在不同的时间执行请求，将请求排队和执行请求（本文并未给出例子，可自行探究）
- 支持撤销(undo)和恢复(redo)操作（本文在模式扩展时有此代码样例）
- 支持将一组操作组合在一起，即宏命令（上文有样例）

#### 优点

- 降低耦合
- 符合开闭原则，很容易就可以添加新的命令

#### 缺点

- 可能会产生过多的具体命令类

#### 总结

命令模式的动机是为了消除请求调用者和请求接收者直接的耦合，使得请求调用者无需知道请求调用者。命令模式支持对请求的记录和排队、撤销等操作，也支持一个宏命令，一次执行多个命令。

`2021年5月16日14:25:27`