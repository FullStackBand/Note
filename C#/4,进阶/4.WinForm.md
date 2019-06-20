# WinForm 界面设计

WinForm 是 Windows Form 的简称，是基于 .NET Framework 平台的客户端（PC软件）开发技术，一般使用 [C#](http://c.biancheng.net/csharp/) 编程。C# WinForm 编程需要创建「Windows窗体应用程序」项目。

NET 提供了大量 Windows 风格的控件和事件，我们可以直接拿来使用，上手简单，开发快速。

Windows 窗体应用程序是 C# 语言中的一个重要应用，也是 C# 语言最常见的应用。这套 C# WinForm 教程将教会大家如何使用 WinForm 进行界面设计，并绑定对应的事件，开发出一个实用的客户端。

对于每一个使用过 Windows 操作系统的读者来说，Windows 应用程序是不会陌生的。使用 C# 语言编写的 Windows 应用程序与 Windows 操作系统的界面类似，每个界面都是由窗体构成的，并且能通过鼠标单击、键盘输入等操作完成相应的功能。

## 1.创建Windows窗体应用程序

创建 Windows 窗体应用程序的步骤与创建控制台应用程序的步骤类似，在 Visual Studio 2019 软件中，依次选择“文件”→“新建”→“项目”命令，弹出如下图所示的对话框。

![1560908369105](assets/1560908369105.png)

在该对话框中选择“Windows 窗体应用程序”，并更改项目名称、项目位置、解决方案名称等信息，单击“确定”按钮，即可完成 Windows 窗体应用程序的创建，如下图所示。

![1560908398467](assets/1560908398467.png)

```c#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsForms
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles(); // 1#
            Application.SetCompatibleTextRenderingDefault(false); // 2#
            Application.Run(new Form1()); // 3#
        }
    }
}
```

在上述的代码中

* `1#` 用于启动应用程序中可视的样式, 如果控件和操作系统支持, 那么控件回执就能根据不同的风格进行实现.
* `2#` 控件支持`UseCompatibleTextRenderingproperty`属性, 该方法将此属性设置为默认值.
* `3#` 用于设置在当前项目中要启动的窗体, 这里 `new Form1()`即为要启动的窗体.

在Windows窗体应用程序中界面是由不同类型的控件构成的.

系统中默认的控件全部存放到工具箱中，选择“视图”→“工具箱”，如下图所示。

![1560908878723](assets/1560908878723.png)

在工具箱中将控件划分为公共控件、容器、菜单和工具栏、数据、组件、打印、对话框等组。

如果工具箱中的控件不能满足开发项目的需求，也可以向工具箱中添加新的控件, 或者对工具箱中的控件重置或进行分组等操作，这都可以通过右击工具箱，在弹出的右键菜单中选择相应的命令实现。

![1560908977534](assets/1560908977534.png)

在右键菜单中选择“选择项”命令，弹出如下图所示的对话框。

![1560909508526](assets/1560909508526.png)

  在该对话框中列出了不同组件中所带的控件，如果需要在工具箱中添加，直接选中相应组件名称前的复选框即可。

如果需要添加外部的控件，则单击“浏览”按钮，找到相应控件的 .dll 或 .exe 程序添加即可。

Windows 窗体应用程序也称为事件驱动程序，也就是通过鼠标单击界面上的控件、通过键盘输入操作控件等操作来触发控件的不同事件完成相应的操作。

例如单击按钮、右击界面、向文本框中输入内容等操作。  

## 2.窗体属性

每一个 Windows 窗体应用程序都是由若干个窗体构成的，窗体中的属性主要用于设置窗体的外观。

在 Windows 窗体应用程序中右击窗体，在弹出的右键菜单中 选择“属性”命令，弹出如下图所示的属性面板。

![1560909668846](assets/1560909668846.png)

  在该图中列出的属性分为布局、窗口样式等方面，合理地设置好窗体的属性对窗体的 展现效果会起到事半功倍的作用。

窗体的常用属性如下表所示。  

| 属性                  | 作用                                                         |
| --------------------- | ------------------------------------------------------------ |
| Name                  | 用来获取或设置窗体的名称                                     |
| WindowState           | 获取或设置窗体的窗口状态，取值有3种，即Normal（正常）、Minimized（最小化）、Maximized（最大化），默认为 Normal，即正常显示 |
| StartPosition         | 获取或设置窗体运行时的起始位置，取值有 5 种，即 Manual（窗体位置由 Location 属性决定）、CenterScreen（屏幕居中）、WindowsDefaultLocation（ Windows 默认位置）、WindowsDefaultBounds（Windows 默认位置，边界由 Windows 决定）、CenterParent（在父窗体中居中），默认为 WindowsDefaultLocation |
| Text                  | 获取或设置窗口标题栏中的文字                                 |
| MaximizeBox           | 获取或设置窗体标题栏右上角是否有最大化按钮，默认为 True      |
| MinimizeBox           | 获取或设置窗体标题栏右上角是否有最小化按钮，默认为 True      |
| BackColor             | 获取或设置窗体的背景色                                       |
| BackgroundImage       | 获取或设置窗体的背景图像                                     |
| BackgroundImageLayout | 获取或设置图像布局，取值有 5 种，即 None（图片居左显示）、Tile（图像重复，默认值）、Stretch（拉伸）、Center（居中）、Zoom（按比例放大到合适大小） |
| Enabled               | 获取或设置窗体是否可用                                       |
| Font                  | 获取或设置窗体上文字的字体                                   |
| ForeColor             | 获取或设置窗体上文字的颜色                                   |
| Icon                  | 获取或设置窗体上显示的图标                                   |

下面通过实例来演示窗体属性的应用。

【实例】创建一个名为 TestForm 的窗体，并完成如下设置。

* 窗体的标题栏中显示“第一个窗体”。
* 窗体中起始位置居中。
* 窗体中设置一个背景图片。
* 窗体中不显示最大化和最小化按钮。

实现题目中要求的窗体，具体步骤如下。

#### 1) 创建名为 TestForm 的窗体

创建一个 Windows 应用程序 Windows-2，然后右击该项目，在弹出的右键菜单中选择“添加新项”命令，弹出如下图所示。

![添加windows 窗体](assets/4-1Z32Q60613163.gif)

#### 2) 设置 TestForm 窗体的属性

TestForm 窗体的属性设置如下表所示。

| 属性                  | 属性值       |
| --------------------- | ------------ |
| Name                  | TestForm     |
| StartPosition         | CenterScreen |
| Text                  | 第一个窗体   |
| MaximizeBox           | False        |
| MinimizeBox           | False        |
| Backgroundimage       | window_2.jpg |
| BackgroundlmageLayout | Stretch      |

在上述属性中除了背景图片 (Backgroundimage) 属性以外，其他属性直接添加上表中对应的属性值即可。

设置背景图片属性 (Backgroimdlmage) 的方法是单击 Backgroundimage 属性后的按钮，在弹出的对话框中单击“导入”按钮。

如下图所示， 选择图片 window_2.jpg 所在的路径，单击“确定”按钮即可完成背景图片属性的设置。

![“选择资源”对话框](assets/4-1Z32Q60RB38.gif)

#### 3) 设置 TestForm 窗体为启动窗体

每一个 Windows 窗体应用程序在运行时仅能指定一个启动窗体，设置启动窗体的方式是在项目的 Program.cs 文件中指定。具体的代码如下。

```C#
static class Program
{
    /// <summary>
    /// 应用程序的主入口点。
    /// </summary>
    [STAThread]
    static void Main()
    {
        Application.EnableVisualStyles();
        Application.SetCompatibleTextRenderingDefault(false);
        Application.Run(new TestForm());    //设置启动窗体
    }
}
```

完成以上 3 步后按 F5 键运行程序，效果如下图所示。

![TestForm 窗体](assets/4-1Z32Q6093Q59.gif)

## 3.窗体事件

在窗体中除了可以通过设置属性改变外观外，还提供了事件来方便窗体的操作。

在打开操作系统后，单击鼠标或者敲击键盘都可以在操作系统中完成不同的任务，例如双击鼠标打开“我的电脑”、在桌面上右击会出现右键菜单、单击一个文件夹后按 F2 键可以更改文件夹的名称等。

实际上这些操作都是 Windows 操作系统中的事件。

在 Windows 窗体应用程序中系统已经自定义了一些事件，在窗体属性面板中单击闪电图标即可查看到窗体中的事件，如下图所示。

![窗体中的事件](assets/4-1Z32QA630Y2.gif)

窗体中常用的事件如下表所示。

| 事件             | 作用                                     |
| ---------------- | ---------------------------------------- |
| Load             | 窗体加载事件，在运行窗体时即可执行该事件 |
| MouseClick       | 鼠标单击事件                             |
| MouseDoubleClick | 鼠标双击事件                             |
| MouseMove        | 鼠标移动事件                             |
| KeyDown          | 键盘按下事件                             |
| KeyUp            | 键盘释放事件                             |
| FormClosing      | 窗体关闭事件，关闭窗体时发生             |
| FormClosed       | 窗体关闭事件，关闭窗体后发生             |

下面通过实例来演示窗体中事件的应用。

【实例】通过窗体的不同事件改变窗体的背景颜色。

在本例中采用的事件分别是窗体加载事件 (Load)、鼠标单击事件 (MouseClick)、鼠标双击事件 (MouseDoubleClick)。

实现该操作的步骤如下。

### 1) 新建窗体

在上一节《C#窗体属性》中使用的 Windows-2 项目中添加一个名为 ColorForm 的窗体。

### 2) 添加事件

右击该窗体，在弹出的右键菜单中选择“属性”命令，然后在弹出的面板中单击闪电图标进入窗体事件设置界面。

在该界面中依次选中需要创建的事件，并双击该事件右侧的单元格，系统会自动为其生成对应事件的处理方法，设置后的属性面板如下图所示。

![ColorForm 窗口事件设置](assets/4-1Z32QA95DR.gif)

设置好事件后会在 ColorForm 窗体对应的代码文件中自动生成与事件对应的 4 个方法, 代码如下。

```c#
public partial class ColorForm : Form
{
    public ColorForm()
    {
        InitializeComponent();
    }
    private void ColorForm_MouseClick(object sender, MouseEventArgs e)
    {
    }
    private void ColorForm_MouseDoubleClick(object sender, MouseEventArgs e)
    {
    }
    private void ColorForm_Load(object sender, EventArgs e)
    {
    }
}
```

在执行不同事件时，系统会自动执行事件所对应方法中的内容。

### 3) 添加事件处理代码

在本例中每个事件完成的操作都是更改窗体的背景颜色，窗体的背景颜色所对应的属性是 BackColor。

除了可以在属性面板中设置外，使用代码设置的方式是使用 this 关键字代表当前窗体的实例，BackColor 属性类型是 Color 枚举类型的，代码如下。

```
this.BackColor = Color.Red;
```

上面的代码是将窗体的背景颜色设置为红色。

下面分别将类似代码添加到每一个事件中，代码如下。

```c#
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsForms
{
    public partial class Name : Form
    {
        public Name()
        {
            InitializeComponent();
        }

        private void onClick(object sender, MouseEventArgs e)
        {
            this.BackColor = Color.Blue;
        }

        private void onDoubleClick(object sender, MouseEventArgs e)
        {
            this.BackColor = Color.AliceBlue;
        }

        private void Form_Load(object sender, EventArgs e)
        {
            this.BackColor = Color.Chocolate;
        }
    }
}

```

### 4) 设置启动窗体

在 Windows-2 项目的 Program.cs 类中将 ColorForm 窗体设置为启动窗体，代码如下。

```c#
static class Program
{
    /// <summary>
    /// 应用程序的主入口点。
    /// </summary>
    [STAThread]
    static void Main()
    {
        Application.EnableVisualStyles();
        Application.SetCompatibleTextRenderingDefault(false);
        Application.Run(new ColorForm());    //设置启动窗体
    }
}
```

执行上面的代码，效果如下图所示。

* 加载完成

![1560911166474](assets/1560911166474.png)

* 单击窗口

![1560911218892](assets/1560911218892.png)

* 双击窗口

![1560911226869](assets/1560911226869.png)



## 4.窗体方法

自定义的窗体都继承自 System.Windows.Form 类，能使用 Form 类中已有的成员，包括属性、方法、事件等。

在前面《C#窗体属性》和《C#窗体事件》中已经分别介绍了窗体中常用的属性和事件。

实际上窗体中也有一些从 System.Windows.Form 类继承的方法，如下表所示。

| 方法                      | 作用                     |
| ------------------------- | ------------------------ |
| void Show()               | 显示窗体                 |
| void Hide()               | 隐藏窗体                 |
| DialogResult ShowDialog() | 以对话框模式显示窗体     |
| void CenterToParent()     | 使窗体在父窗体边界内居中 |
| void CenterToScreen()     | 使窗体在当前屏幕上居中   |
| void Activate()           | 激活窗体并给予它焦点     |
| void Close()              | 关闭窗体                 |

下面通过实例来演示窗体中方法的应用。

【实例】在 MainForm 窗体中单击，弹出一个新窗体 NewForm；在新窗体中单击，将 NewForm 窗体居中，双击，关闭 NewForm 窗体。

实现题目要求的效果需要经过以下步骤：

### 1) 在项目中添加所需的窗体

在 Windows-2 项目中添加所需的 MainForm 窗体和 NewForm 窗体。

### 2) 设置 MainForm 窗体中事件

在 MainForm 窗体中添加鼠标单击窗体事件，并在该事件对应的方法中写入打开 NewForm 窗体的代码，具体代码如下。

```c#
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsForms
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void onClick(object sender, MouseEventArgs e)
        {
            this.BackColor = Color.Blue;
        }

        private void onDoubleClick(object sender, MouseEventArgs e)
        {
            this.BackColor = Color.AliceBlue;
        }

        private void Form_Load(object sender, EventArgs e)
        {
            this.BackColor = Color.Chocolate;
        }

        private void Button1_Click(object sender, EventArgs e)
        {
            // 创建窗体
            Form cform = new ClickForm();
            // 显示窗体
            cform.Show();
        }
    }
}
```

### 3) 设置 NewForm 窗体的事件

在 NewForm 窗体中添加鼠标单击事件将窗体的显示位置居中，添加鼠标双击事件关闭 NewForm 窗体，并在相应的事件中添加代码，具体代码如下。

```c#
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsForms
{
    public partial class ClickForm : Form
    {
        public ClickForm()
        {
            InitializeComponent();
        }
		// 双击事件
        private void Close(object sender, MouseEventArgs e)
        {
            // 关闭窗口
            this.Close();
        }
		// 单击事件
        private void CenterWin(object sender, MouseEventArgs e)
        {
            // 窗口居中 在 屏幕
            this.CenterToScreen();
        }
    }
}
```

### 4) 将 MainForm 窗体设置为启动窗体

在 Windows-2 项目的 Program.cs 文件中设置 MainForm 窗体为启动窗体，代码如下。

```c#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsForms
{
    static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new MainForm());
        }
    }
}

```

完成以上步骤后运行该项目，并在 MainForm 窗体上单击鼠标，效果如下图所示。

![打开NewForm窗体](assets/4-1Z32QK1014X.gif)

单击 NewForm 窗体后，NewForm 窗体显示在屏幕中央，双击 NewForm 窗体即可将该窗体关闭。

在使用窗体中的方法时需要注意，如果是当前窗体需要调用方法直接使用 `this` 关键字代表当前窗体，通过`this.方法名` (参数列表)的方式调用即可。

如果要操作其他窗体，则需要用窗体的实例来调用方法。

## 5.McssageBox 消息框

消息框在 Windows 操作系统经常用到，例如在将某个文件或文件夹移动到回收站中时系统会自动弹出如下图所示的消息框。

![删除文件是弹出的消息框](assets/4-1Z3291A953L8.gif)

在 Windows 窗体应用程序中向用户提示操作时也是采用消息框弹出的形式。

消息框是通过 McssageBox 类来实现的，在 MessageBox 类中仅定义了 Show 的多个重载方法，该方法的作用就是弹出一个消息框。

由于 Show 方法是一个静态的方法，因此调用该方法只需要使用

```c#
MessageBox.Show( 参数 )
```

的形式即可弹出消息框。

消息框在显示时有不同的样式， 例如标题、图标、按钮等。

常用的 Show 方法参数如下表所示。

| 方法                                                         | 说明                                                         |
| ------------------------------------------------------------ | ------------------------------------------------------------ |
| DialogResult Show(string text)                               | 指定消息框中显示的文本（text）                               |
| DialogResult Show(string text, string caption)               | 指定消息框中显示的文本（text）以及消息框的标题（caption）    |
| DialogResult Show(string text, string caption,  MessageBoxButtons buttons) | 指定消息框中显示的文本（text）、消息框的 标题（caption）以及消息框中显示的按钮 （buttons） |
| DialogResult Show(string text, string caption, MessageBoxButtons buttons, MessageBoxIcon icon) | 指定消息框中显示的文本（text）、消息框的 标题（caption ）、消息框中显示的按钮 （buttons）以及消息框中显示的图标（icon） |

在上面所列出方法的参数中还涉及两个枚举类型，一个是 MessageBoxButtons，一个是 MessageBoxIcon。下面分别介绍这两个枚举类型中的具体值。

MessageBoxButtons 枚举类型主要用于设置消息框中显示的按钮，具体的枚举值如下。

* OK：在消息框中显示“确定”按钮。
* OKCancel：在消息框中显示“确定”和“取消”按钮。
* AbortRetryIgnore：在消息框中显示“中止” “重试”和“忽略”按钮。
* YesNoCancel：在消息框中显示“是” “否”和“取消”按钮。
* YesNo：在消息框中显示“是”和“否”按钮。
* RetryCancel：在消息框中显示“重试”和“取消”按钮。

MessageBoxIcon 枚举类型主要用于设置消息框中显示的图标，具体的枚举值如下。

* None：在消息框中不显示任何图标。
* Hand、Stop、Error：在消息框中显示由一个红色背景的圆圈及其中的白色X组成 的图标。
* Question：在消息框中显示由圆圈和其中的一个问号组成的图标。
* Exclamation、Warning：在消息框中显示由一个黄色背景的三角形及其中的一个感叹号组成的图标。
* Asterisk、Information：在消息框中显示由一个圆圈及其中的小写字母 i 组成的图标。

调用 MessageBox 类中的 Show 方法将返回一个 DialogResult 类型的值。

DialogResult 也是一个枚举类型，是消息框的返回值，通过单击消息框中不同的按钮得到不同的消息框返回值。

DialogResult 枚举类型的具体值如下。

* None：消息框没有返回值，表明有消息框继续运行。
* OK：消息框的返回值是 0K （通常从标签为“确定”的按钮发送）。
* Cancel：消息框的返回值是 Cancel （通常从标签为“取消”的按钮发送）。
* Abort：消息框的返回值是 Abort （通常从标签为“中止”的按钮发送）。
* Retry：消息框的返回值是 Retry （通常从标签为“重试”的按钮发送）。
* Ignore：消息框的返回值是 Ignore （通常从标签为“忽略“的按钮发送）。
* Yes：消息框的返回值是 Yes （通常从标签为“是“的按钮发送）。
* No：消息框的返回值是 No （通常从标签为“否“的按钮发送）。

下面通过实例来演示消息框的应用。

【实例】创建一个窗体，单击该窗体弹出一个消息框提示“是否打开新窗口”，如果单击“是”按钮，则打开新窗口，如果单击“否”按钮，则关闭当前窗体。

根据题目要求，完成该实例需要如下步骤。

### 1) 创建所需的窗体

创建一个名为 Windows_3 的项目，并在该项目中添加两个窗体，分别命名为 MainForm、 MessageForm。

### 2) 在 MainForm 窗体中添加事件

在 MainForm 窗体中添加鼠标单击事件，并在相应的事件中添加如下代码。

```c#
public partial class MainForm : Form
{
    public MainForm()
    {
        InitializeComponent();
    }
    private void MainForm_MouseClick(object sender, MouseEventArgs e)
    {
        //弹出消息框，并获取消息框的返回值
        DialogResult dr = MessageBox.Show("是否打开新窗体？", "提示", MessageBoxButtons.YesNo, MessageBoxIcon.Warning);
        //如果消息框返回值是Yes，显示新窗体
        if (dr == DialogResult.Yes)
        {
            MessageForm messageForm = new MessageForm();
            messageForm.Show();
        }
        //如果消息框返回值是No，关闭当前窗体
        else if (dr == DialogResult.No)
        {
            //关闭当前窗体
            this.Close();
        }
    }
}
```

### 3) 设置项目的启动窗体

在 Program.cs 文件中将 MainForm 设置为启动窗体，代码如下。

```c#
static class Program
{
    /// <summary>
    /// 应用程序的主入口点。
    /// </summary>
    [STAThread]
    static void Main()
    {
        Application.EnableVisualStyles();
        Application.SetCompatibleTextRenderingDefault(false);
        Application.Run(new MainForm());
    }
}
```

完成上面 3 个步骤后运行窗体，并在窗体上单击鼠标，弹出如下图所示的消息框。

![消息框](assets/4-1Z3291F434219.gif)

> 提示：消息框中的提示文字、图标、按钮等外观设置也可以通过设置消息框中的相应参数来改变。

## 6.控件简介

在 Windows 窗体应用程序中每个窗体都是由若干个控件构成的。

所谓控件就是人们常说的能输入文本的位置、能选择的位置、能单击的位置、图片显示的位置等。其中：

* 能输入文本的位置对应于 Windows 窗体应用程序中的文本框、多行文本框等。
* 能选择的位置对应于 Windows 窗体应用程序中的复选框、单选按钮、下拉列表框。
* 能单击的位置对应于 Windows 窗体应用程序中的按钮、超链接标签、菜单栏、工具栏等。
* 图片显示的位置对应于 Windows 窗体应用程序中的图片控件。

常用的 QQ 软件的登录界面如下图所示。

![QQ软件登录界面](assets/4-1Z329100146418.gif)

在该界面中可以看到主要包括用于输入“QQ 号码”和“密码”的文本框，用于选择“记住密码”和“自动登录”的复选框，用于单击“注册账号”和“找回密码”的超链接标签、用于登录 QQ 的“安全登录”按钮，以及显示“QQ 图标”的图片控件。

Windows 窗体应用程序的实现主要依靠控件，并通过控件的事件和属性来实现窗体的效果。

Windows 窗体应用程序的设计与 Windows 操作系统的界面有些相似，所提供的控件也相似，包括菜单栏、工具栏、对话框等，灵活地使用 Windows 窗体应用程序中所提供的控件能设计出符合客户要求、美观合理的界面。

## 7.Label和LinkLabel 标签控件

在 Windows 窗体应用程序中，每个窗体都必不可少地会用到文本框和标签控件。

由于在窗体中无法直接编写文本，通常使用标签控件来显示文本。

关于文本框的应用我们将在下一节《C# TextBox》中为大家讲解。

在 Windows 窗体应用程序中，标签控件王要分为普通的标签 (Label) 和超链接形式的标签 (LinkLabel) 。

普通标签 (Label) 控件的常用属性如下表所示。

| 属性名    | 作用                                                         |
| --------- | ------------------------------------------------------------ |
| Name      | 标签对象的名称，区别不同标签唯一标志                         |
| Text      | 标签对象上显示的文本                                         |
| Font      | 标签中显示文本的样式                                         |
| ForeColor | 标签中显示文本的颜色                                         |
| BackColor | 标签的背景颜色                                               |
| Image     | 标签中显示的图片                                             |
| AutoSize  | 标签的大小是否根据内容自动调整，True 为自动调整，False 为用户自定义大小 |
| Size      | 指定标签控件的大小                                           |
| Visible   | 标签是否可见，True 为可见，False 为不可见                    |

普通标签控件 (Label) 中的事件与窗体的事件类似，常用的事件主要有鼠标单击事件、 鼠标双击事件、标签上文本改变的事件等。

与普通标签控件类似，超链接标签控件 (LinkLabel) 也具有相同的属性和事件。

超链接标签主要应用的事件是鼠标单击事件，通过单击标签完成不同的操作，例如在 QQ 窗体中注册账号和找回密码的操作。

下面通过实例来演示标签控件的应用。

【实例】创建一个窗体，在窗体上放置两个普通标签控件 (Label)，分别显示“早上好！”和“GoodMorning！”。

在窗体上通过单击超链接标签 (LinkLabel) 交换这两个标签上显示的信息。

根据题目要求，首先创建一个名为 ChangeTextForm 的窗体，并设置所需控件的属性和事件，实现的代码如下。

```
public partial class ChangeTextForm : Form{    public ChangeTextForm()    {        InitializeComponent();    }    //超链接标签控件的单击事件    private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)    {        //交换标签上的信息。        string temp = label1.Text;        label1.Text = label2.Text;        label2.Text = temp;    }}
```

> 提示：所需控件可以在工具箱中创建，在控件上右键即可设置相关属性和事件

执行上面的代码，效果如下图所示。

![标签信息交换前和交换后的效果](assets/4-1Z329111623K1.gif)

## 8.TextBox：文本框控件

本框 (TextBox) 是在窗体中输入信息时最常用的控件，通过设置文本框属性可以实现多行文本框、密码框等。

在窗体上输入信息时使用最多的就是文本框。

除了前面《[C# Label、LinkLabel](http://c.biancheng.net/view/2953.html)》一节介绍的控件属性以外，文本框还有一些不同的属性， 如下表所示。  

| 属性名       | 作用                                                         |
| ------------ | ------------------------------------------------------------ |
| Text         | 文本框对象中显示的文本                                       |
| MaxLength    | 在文本框中最多输入的文本的字符个数                           |
| WordWrap     | 文本框中的文本是否自动换行，如果是 True，则自动换行，如果是 False，则不能自动换行 |
| PasswordChar | 将文本框中出现的字符使用指定的字符替换，通常会使用“*”字符    |
| Multiline    | 指定文本框是否为多行文本框，如果为 True，则为多行文本框，如果 为 False，则为单行文本框 |
| ReadOnly     | 指定文本框中的文本是否可以更改，如果为 True，则不能更改，即只读文本框，如果为 False，则允许更改文本框中的文本 |
| Lines        | 指定文本框中文本的行数                                       |
| ScrollBars   | 指定文本框中是否有滚动条，如果为 True，则有滚动条,如果为 False， 则没有滚动条 |

文本框控件最常使用的事件是文本改变事件 (TextChange)，即在文本框控件中的内容改变时触发该事件。

下面通过实例来演示文本框的应用。

【实例 1】创建一个窗体，在文本框中输入一个值，通过文本改变事件将该文本框中的值写到一个标签中。

根据题目要求，首先创建一个名为 TextBoxTest 的窗体，然后在窗体上添加文本框和标签，并在文本框的文本改变事件中编写代码。

具体的代码如下。

```c#
public partial class TextBoxTest : Form
{
    public TextBoxTest()
    {
        InitializeComponent();
    }
    //文本框文本改变事件
    private void textBox1_TextChanged(object sender, EventArgs e)
    {
        //将文本框中的文本值显示在标签中
        label2.Text = textBox1.Text;
    }
}
```

运行窗体，效果如下图所示。

![文本框的文本改变事件的应用](assets/4-1Z329125945U1.gif)

从上面的运行结果可以看出，使用控件的属性和事件通过一行代码即可完成所需的功能。

【实例 2】实现简单的登录窗体。

本例中的登录窗体仅包括用户名和密码，将登录窗体命名为 LoginForm。

单击“登录”超链接标签，对文本框中输入的用户名和密码进行判断，如果用户名和密码的输入值分别为 xiaoming 和 123456，则弹出消息框提示“登录成功！”，否则提示“登录失败！”。

具体代码如下。

```c#
public partial class LoginForm : Form
{
    public LoginForm()
    {
        InitializeComponent();
    }
    //判断是否登录成功
    private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
    {
        //获取用户名
        string username = textBox1.Text;
        //获取密码
        string password = textBox2.Text;
        //判断用户名密码是否正确
        if ("xiaoming".Equals(username) && "123456".Equals(password))
        {
            MessageBox.Show("登录成功！");
        }
        else
        {
            MessageBox.Show("登录失败！");
        }
    }
}
```

运行窗体后输入用户名和密码，单击“登录”超链接标签，效果如下图所示。

![登录成功是的效果](assets/4-1Z32913011TV.gif)

从上面的运行效果可以看出，输入密码的文本框中由于在 PasswordChar 属性中设置了 *，因此在文本框中输入的文本全部使用了 * 来替换。

## 9.Button：按钮控件

按钮主要用于提交页面的内容，或者是确认某种操作等。

按钮包括普通的按钮 (Button)、单选按钮 (RadioButton)，本节主要讲解按钮的应用，单选按钮将在下一节《

C# RadioButton

》为大家讲解。

按钮常用的属性包括在按钮中显示的文字 (Text) 以及按钮外观设置的属性，最常用的事件是单击事件。

【实例】实现一个简单的用户注册功能，并将提交的注册信息显示在新窗体的文本框中。

本例的用户注册界面中仅包括用户名和密码，通过单击“注册”按钮跳转到新窗体中并显示注册的用户名和密码，实现该功能分别使用 RegForm 窗体和 MainForm 窗体。

RegForm 窗体的界面如下图所示。

![用户注册窗体界面](assets/4-1Z329142935N5.gif)

在注册时判断用户名和密码不能为空，并且要求两次输入的密码一致，实现的代码如下。

```c#
public partial class RegForm : Form
{
    public RegForm()
    {
        InitializeComponent();
    }
    //“确定”按钮的单击事件，用于判断注册信息并跳转到新窗口显示注册信息
    private void button1_Click(object sender, EventArgs e)
    {
        string name = textBox1.Text;
        string pwd = textBox2.Text;
        string repwd = textBox3.Text;
        if (string.IsNullOrEmpty(name))
        {
            MessageBox.Show("用户名不能为空！");
            return;
        }
        else if (string.IsNullOrEmpty(textBox2.Text))
        {
            MessageBox.Show("密码不能为空！");
            return;
        }
        else if (!textBox2.Text.Equals(textBox3.Text))
        {
            MessageBox.Show("两次输入的密码不一致！");
            return;
        }
        //将用户名和密码传递到主窗体中
        MainForm mainForm = new MainForm(name, pwd);
        mainForm.Show();
    }
    //“取消”按钮的事件，用于关闭窗体
    private void button2_Click(object sender, EventArgs e)
    {
        //关闭窗体
        this.Close();
    }
}
```

MainForm 窗体的界面如下图所示。

![Mainform窗体界面的设计](assets/4-1Z329142956226.gif)

在 MainForm 界面中使用标签 label2 和 label3 分别显示用户名和密码，代码如下。

```c#
public partial class MainForm : Form
{
    public MainForm(string name,string pwd)
    {
        InitializeComponent();
        label2.Text = "用户名："+ name;
        label3.Text = "密  码："+pwd;
    }
}
```

运行 RegForm 窗体，效果如下图所示。

![注册信息界面](assets/4-1Z3291430214C.gif)

单击“确定”按钮，效果如下图所示。

![显示注册信息界面](assets/4-1Z329143102228.gif)

从上面的实例可以看出，如果需要在两个窗体中传递参数，则可以使用按钮和文本框。

## 10.RadioButton：单选按钮控件

在 

C#

 语言中 RadioButton 是单选按钮控件，多个 RadioButton 控件可以为一组，这一组内的 RadioButton 控件只能有一个被选中。

下面通过实例来演示单选按钮控件 RadioButton 的应用。

【实例】完成选择用户权限的操作，并在消息框中显示所选的权限名。

根据题目要求，用户权限包括“普通用户”“年卡用户”“VIP 用户”，因此需要 3 个单选按钮。

实现该功能的窗体名称为 RadioButtonForm，界面设计如下图所示。

![选择用户权限界面](assets/4-1Z32914563G11.gif)

单击“确认”按钮选择相应的用户权限，实现的代码如下。

```c#
public partial class RadioButtonForm : Form
{
    public RadioButtonForm()
    {
        InitializeComponent();
    }
    //单击“确定”按钮的事件
    private void button1_Click(object sender, EventArgs e)
    {
        string msg = "";
        if (radioButton1.Checked)
        {
            msg = radioButton1.Text;
        }
        else if (radioButton2.Checked)
        {
            msg = radioButton2.Text;
        }
        else if (radioButton3.Checked)
        {
            msg = radioButton3.Text;
        }
        MessageBox.Show("您选择的权限是：" + msg, "提示");
    }
}
```

RadioButtonForm 窗体的运行效果如下图所示。

![选择用户权限窗体运行效果](assets/4-1Z329145K9310.gif)

> 提示：Checked 属性可用于判断单选按钮是否被选中。如果该属性的返回值为 True，则代表选中；如果返回值为 False，则表示未选中。

## 11.CheckBox：复选框控件

在 C# 语言中复选框 (CheckBox) 是与上一节《C# RadioButton》中介绍的单选按钮相对应的，用于选择多个选项的操作。

复选框主要的属性是：Name、Text、Checked。

其中：

* Name：表示这个组件的名称；
* Text：表示这个组件的标题；
* Checked：表示这个组件是否已经选中。

主要的事件就是 CheckedChanged 事件。

下面通过实例来演示复选框 (CheckBox) 的应用。

【实例】完成选择用户爱好的操作，并在消息框中显示所选的爱好。

根据题目要求，用户爱好包括篮球、排球、羽毛球、乒乓球、游泳、阅读、写作，因此需要 7 个复选框。

实现该功能的窗体名称为 CheckBoxForm，窗体设计如下图所示。

![选择爱好的窗体设计界面](assets/4-1Z329154303D1.gif)

单击“确认”按钮显示选择的爱好，实现的代码如下。

```c#
public partial class CheckBoxForm : Form
{
    public CheckBoxForm()
    {
        InitializeComponent();
    }
    //单击“确认”按钮，显示选择的爱好
    private void button1_Click(object sender, EventArgs e)
    {
        string msg = "";
        if (checkBox1.Checked)
        {
            msg = msg + " " + checkBox1.Text;
        }
        if (checkBox2.Checked)
        {
            msg = msg + " " + checkBox2.Text;
        }
        if (checkBox3.Checked)
        {
            msg = msg + " " + checkBox3.Text;
        }
        if (checkBox4.Checked)
        {
            msg = msg + " " + checkBox4.Text;
        }
        if (checkBox5.Checked)
        {
            msg = msg + " " + checkBox5.Text;
        }
        if (checkBox6.Checked)
        {
            msg = msg + " " + checkBox6.Text;
        }
        if (checkBox7.Checked)
        {
            msg = msg + " " + checkBox7.Text;
        }
        if(msg != "")
        {
            MessageBox.Show("您选择的爱好是：" + msg, "提示");
        }
        else
        {
            MessageBox.Show("您没有选择爱好", "提示");
        }
    }
}
```

运行该窗体，效果如下图所示。

![选择爱好窗体运行效果](assets/4-1Z32915440E14.gif)

与判断单选按钮是否被选中一样，判断复选框是否被选中也使用 Checked 属性。

试想如果界面上的复选框有几十个或更多，每个复选框都需要判断，则会出现很多的冗余代码。

由于都要获取复选框是否被选中了，界面上的每一个控件都继承自 Control 类，直接判断界面上的控件是否为复选框即可，实现上述功能的代码可以简化为如下。

```c#
private void button1_Click(object sender, EventArgs e)
{
    string msg = "";
    foreach(Control c in Controls)
    {
        //判断控件是否为复选框控件
        if(c is CheckBox)
        {
            if (((CheckBox)c).Checked)
            {
                msg = msg + " " + ((CheckBox)c).Text;
            }
        }
    }
    if(msg != "")
    {
        MessageBox.Show("您选择的爱好是：" + msg, "提示");
    }
    else
    {
        MessageBox.Show("您没有选择爱好", "提示");
    }
}
```

执行以上代码的效果与上图一致，但从代码量上来说已经减少了很多的冗余代码， 减轻了程序员的工作量。

## 12.CheckedListBox：复选列表框控件

在 

C#

 语言中提供了与《

C# CheckBox

》一节中介绍的复选框功能类似的复选列表框 (CheckedListBox)，方便用户设置和获取复选列表框中的选项。

复选列表框显示的效果与复选框类似，但在选择多个选项时操作比一般的复选框更方便。

下面通过实例来演示复选列表框的应用。

【实例】使用复选列表框完成选购水果的操作。

根据题目要求，创建一个名为 CheckedListBox 窗体，在复选列表框中添加 6 种水果， 单击“购买”按钮，弹出消息框显示购买的水果种类。

该窗体的设计界面如下图所示。

![选购水果界面设计](assets/4-1Z329161330914.gif)

实现单击“购买”按钮的代码如下。

```c#
public partial class CheckedListBox : Form
{
    public CheckedListBox()
    {
        InitializeComponent();
    }
    //“购买”按钮的点击事件，用于在消息框中显示购买的水果种类
    private void button1_Click(object sender, EventArgs e)
    {
        string msg = "";
        for(int i = 0; i < checkedListBox1.CheckedItems.Count; i++)
        {
            msg = msg + " " + checkedListBox1.CheckedItems[i].ToString();
        }
        if (msg != "")
        {
            MessageBox.Show("您购买的水果有：" + msg, "提示");
        }
        else
        {
            MessageBox.Show("您没有选购水果！", "提示");
        }
    }
}
```

运行该窗体，效果如下图所示。

![选购水果窗体运行效果](assets/4-1Z32916142J40.gif)

在使用复选列表框控件时需要注意获取列表中的项使用的是 Checkedltems 属性，获取当前选中的文本（上图中蓝色的区域）使用的是 Selectedltem 属性。

## 13.ListBox：列表框控件

列表框 (ListBox) 将所提供的内容以列表的形式显示出来，并可以选择其中的一项或多项内容，从形式上比使用复选框更好一些。

例如，在 Word 中设置字体时界面如下图所示。

![设置字体界面](assets/4-1Z3291P045325.gif)

在列表框控件中有一些属性与前面介绍的控件不同，如下表所示。

| 属性名        | 作用                                                         |
| ------------- | ------------------------------------------------------------ |
| MultiColumn   | 获取或设置列表框是否支持多列，如果设置为 True，则表示支持多列； 如果设置为 False，则表示不支持多列，默认为 False |
| Items         | 获取或设置列表框控件中的值                                   |
| SelectedItems | 获取列表框中所有选中项的集合                                 |
| SelectedItem  | 获取列表框中当前选中的项                                     |
| SelectedIndex | 获取列表框中当前选中项的索引，索引从 0 开始                  |
| SelectionMode | 获取或设置列表框中选择的模式，当值为 One 时，代表只能选中一项， 当值为 MultiSimple 时，代表能选择多项，当值为 None 时，代表不能选 择，当值为 MultiExtended 时，代表能选择多项，但要在按下 Shift 键后 再选择列表框中的项 |

列表框还提供了一些方法来操作列表框中的选项，由于列表框中的选项是一个集合形式的，列表项的操作都是用 Items 属性进行的。

例如 `Items.Add` 方法用于向列表框中添加项，`Items.Insert` 方法用于向列表框中的指定位置添加项，`Items.Remove` 方法用于移除列表框中的项。

【实例 1】使用列表框的形式完成《C# CheckBox》一节中爱好的选择。

根据题目要求，使用列表框列出所需的爱好，将窗体命名为 ListBoxForm，界面设计如下图所示。

![使用列表框选择爱好的界面](assets/4-1Z3291P3352b.gif)

> 提示：ListBox实现多选需要设置窗体的 SelectionMode 属性为 MultiSimple。

单击“确定”按钮以消息框弹出所选的爱好，实现的代码如下。

```c#
public partial class ListBoxForm : Form
{
    public ListBoxForm()
    {
        InitializeComponent();
    }
    //单击“确定”按钮事件
    private void button1_Click(object sender, EventArgs e)
    {
        string msg = "";
        for(int i = 0; i < listBox1.SelectedItems.Count; i++)
        {
            msg = msg + " " + listBox1.SelectedItems[i].ToString();
        }
        if (msg != "")
        {
            MessageBox.Show("您选择的爱好是：" + msg, "提示");
        }
        else
        {
            MessageBox.Show("您没有选择爱好", "提示");
        }
    }
}
```

运行 ListBoxForm 窗体，效果如下图所示。

![ListBoxForm窗体运行效果](assets/4-1Z3291P432145.gif)

单击“确定”按钮后弹出消息框显示被选中的项目，效果如下图所示。

![显示列表框中选中的项](assets/4-1Z3291P506147.gif)

【实例 2】在实例 1 的基础上添加两个按钮，一个负责向列表框中添加爱好，一个负责删除选中的列表项。

根据题目要求，ListBoxForm 窗体的设计效果如下图所示。

![具有添加删除操作的窗体](assets/4-1Z3291P54D49.gif)

实现的代码如下。

```c#
//将列表框中的选中项删除
private void button2_Click(object sender, EventArgs e)
{
    //由于列表框控件中允许多选所以需要循环删除所有已选项
    int count = listBox1.SelectedItems.Count;
    List<string> itemValues = new List<string>();
    if (count != 0)
    {
        for(int i = 0; i < count; i++)
        {
            itemValues.Add(listBox1.SelectedItems[i].ToString());
        }
        foreach(string item in itemValues)
        {
            listBox1.Items.Remove(item);
        }
    }
    else
    {
        MessageBox.Show("请选择需要删除的爱好！");
    }
}
//将文本框中的值添加到列表框中
private void button3_Click(object sender, EventArgs e)
{
    //当文本框中的值不为空时将其添加到列表框中
    if (textBox1.Text != "")
    {
        listBox1.Items.Add(textBox1.Text);
    }
    else
    {
        MessageBox.Show("请添加爱好！");
    }
}
```

在编写删除操作的功能时需要注意，首先要将列表框中的选中项存放到一个集合中, 然后再对该集合中的元素依次使用 Remove 方法移除。

向列表框中添加选项的效果如下图所示。

![向列表框中添加选项](assets/4-1Z3291P63c58.gif)

当选中列表框中的值并单击“删除”按钮后，列表中的相应选项即可被删除。

## 14.ComboBox：组合框控件

在 C#  WinForm开发中组合框（ComboBox）控件也称下拉列表框，用于选择所需的选项，例如在注册学生信息时选择学历、专业等。

使用组合框可以有效地避免非法值的输入。

在组合框中也有一些经常使用的属性，如下表所示。

| 属性名           | 作用                                                         |
| ---------------- | ------------------------------------------------------------ |
| DropDownStyle    | 获取或设置组合框的外观，如果值为 Simple，同时显示文本框和列表框，并且文本框可以编辑；如果值为 DropDown，则只显示文本框，通过鼠标或键盘的单击事件展开文本框，并且文本框可以编辑；如果值为 DropDownList，显示效果与 DropDown 值一样，但文本框不可编辑。默认情况下为 DropDown |
| Items            | 获取或设置组合框中的值                                       |
| Text             | 获取或设置组合框中显示的文本                                 |
| MaxDropDownltems | 获取或设置组合框中最多显示的项数                             |
| Sorted           | 指定是否对组合框列表中的项进行排序，如果值为 True，则排序， 如果值为 False，则不排序。默认情况下为 False |

在组合框中常用的事件是改变组合框中的值时发生的，即组合框中的选项改变事件 SelectedlndexChanged。

此外，在组合框中常用的方法与列表框类似，也是向组合框中添加项、从组合框中删除项。

【实例】实现一个选择专业的实例。

根据题目要求，创建一个名为 ComboBoxForm 的窗体，界面设计如下图所示。

![选择专业窗体的界面](assets/4-1Z401094945243.gif)

在窗体的设计界面中为组合框填入 5 个专业，或者使用代码添加值，在本实例中使用代码向组合框中添加值；通过“添加”或“删除”按钮将文本框中输入的值添加到组合框中或从组合框中删除。

实现的代码如下。

```c#
public partial class ComboBoxForm : Form
{
    public ComboBoxForm()
    {
        InitializeComponent();
    }
    //窗体加载事件，为组合框添加值
    private void ComboBoxForm_Load(object sender, EventArgs e)
    {
        comboBox1.Items.Add("计算机应用");
        comboBox1.Items.Add("英语");
        comboBox1.Items.Add("会计");
        comboBox1.Items.Add("软件工程");
        comboBox1.Items.Add("网络工程");
    }
    //组合框中选项改变的事件
    private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
    {
        //当组合框中选择的值发生变化时弹出消息框显示当前组合框中选择的值
        MessageBox.Show("您选择的专业是：" + comboBox1.Text, "提示");
    }
    //“添加”按钮的单击事件，用于向组合框中添加文本框中的值
    private void button1_Click(object sender, EventArgs e)
    {
        //判断文本框中是否为空，不为空则将其添加到组合框中
        if (textBox1.Text != "")
        {
            //判断文本框中的值是否与组合框中的的值重复
            if (comboBox1.Items.Contains(textBox1.Text))
            {
                MessageBox.Show("该专业已存在！");
            }
            else
            {
                comboBox1.Items.Add(textBox1.Text);
            }
        }
        else
        {
            MessageBox.Show("请输入专业！", "提示");
        }
    }
    //“删除按钮的单击事件，用于删除文本框中输入的值”
    private void button2_Click(object sender, EventArgs e)
    {
        //判断文本框是否为空
        if (textBox1.Text != "")
        {
            //判断组合框中是否存在文本框中输入的值
            if (comboBox1.Items.Contains(textBox1.Text))
            {
                comboBox1.Items.Remove(textBox1.Text);
            }
            else
            {
                MessageBox.Show("您输入的专业不存在", "提示");
            }
        }
        else
        {
            MessageBox.Show("请输入要删除的专业","提示");
        }
    }
}
```

运行该窗体，效果如下图所示。

![选择会计专业时的运行效果](assets/4-1Z4010950425P.gif)

## 15.PictureBox：图片控件

在 Windows 窗体应用程序中显示图片时要使用图片控件 ( PictureBox )，图片的设置方式与背景图片的设置方式相似。

图片控件中常用的属性如下表所示。

| 属性名        | 作用                                                         |
| ------------- | ------------------------------------------------------------ |
| Image         | 获取或设置图片控件中显示的图片                               |
| ImageLocation | 获取或设置图片控件中显示图片的路径                           |
| SizeMode      | 获取或设置图片控件中图片显示的大小和位置，如果值为 Normal，则图片显不在控件的左上角；如果值为 Stretchimage，则图片在图片控件中被拉伸或收缩，适合图片的大小；如果值为AutoSize，则控件的大小适合图片的大小；如果值为 Centerimage，图片在图片控件中居中；如果值为 Zoom，则图片会自动缩放至符合图片控件的大小 |

图片控件中图片的设置除了可以直接使用 ImageLocation 属性指定图片路径以外，还可以通过 Image.FromFile 方法来设置。

实现的代码如下。

图片控件的名称 .Image = Image. FromFile( 图像的路径 );

【实例】实现图片交换。

根据题目要求，定义一个名为 PictureBoxForm 的窗体，并在该窗体上放置两个图片控件和 一个按钮，界面设计如下图所示。

![交换图片界面设计](assets/4-1Z401103G2594.gif)

单击“交换”按钮完成图片的交换，并在窗体加载时为图片控件设置图片，实现的代码如下。

```c#
public partial class PictureBoxForm : Form
{
    public PictureBoxForm()
    {
        InitializeComponent();
    }
    //窗体加载事件，设置图片空间中显示的图片
    private void PictureBoxForm_Load(object sender, EventArgs e)
    {
        pictureBox1.Image = Image.FromFile(@"D:\\C#_test\\111.jpg");
        pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
        pictureBox2.Image = Image.FromFile(@"D:\\C#_test\\222.jpg");
        pictureBox2.SizeMode = PictureBoxSizeMode.StretchImage;
    }
    //“交换”按钮的单击事件，用于交换图片
    private void button1_Click(object sender, EventArgs e)
    {
        //定义中间变量存放图片地址，用于交换图片地址
        PictureBox pictureBox = new PictureBox();
        pictureBox.Image = pictureBox1.Image;
        pictureBox1.Image = pictureBox2.Image;
        pictureBox2.Image = pictureBox.Image;
 
```

运行该窗体，效果如下图所示。

![交换图片窗体运行效果](assets/4-1Z401103K4322.gif)

单击“交换”按钮，效果如图9-27所示。

![交换图片后的效果](assets/4-1Z401103S0G2.gif)

在 Windows 窗体应用程序中，图片也可以用二进制的形式存放到数据库中，并使用文件流的方式读取数据库中的图片。

通过图片控件的 FromStream 方法来设置使用流读取的图片文件。

## 16.Timer：定时器控件

在 Windows 窗体应用程序中，定时器控件（Timer）与其他的控件略有不同，它并不直接显示在窗体上，而是与其他控件连用，表示每隔一段时间执行一次 Tick 事件。

定时器控件中常用的属性是 Interval，用于设置时间间隔，以毫秒为单位。

此外，在使用定时器控件时还会用到启动定时器的方法（Start）、停止定时器的方法（Stop）。

下面通过实例来演示定时器的使用。

【实例】实现图片每秒切换一次的功能

根据题目要求，使用定时器和图片控件完成每秒切换一次图片的功能，这里仅使用两张图片做切换。

将实现该功能的窗体命名为 TimerForm，界面设计如下图所示。

![图片切换窗体界面设计](assets/4-1Z4011141513S.gif)

实现该功能的代码如下。

```c#
public partial class TimerForm : Form
{
    //设置当前图片空间中显示的图片
    //如果是 Timer1.jpg   flag的值为FALSE
    //如果是 Timer2.jpg   flag的值为TRUE
    bool flag = false;
    public TimerForm()
    {
        InitializeComponent();
    }
    //窗体加载事件，在图片空间中设置图片
    private void TimerForm_Load(object sender, EventArgs e)
    {
        pictureBox1.Image = Image.FromFile(@"D:\C#_test\Timer1.jpg");
        pictureBox1.SizeMode = PictureBoxSizeMode.StretchImage;
        //设置每隔1秒调用一次定时器Tick事件
        timer1.Interval = 1000;
        //启动定时器
        timer1.Start();
    }
    //触发定时器的事件，在该事件中切换图片
    private void timer1_Tick(object sender, EventArgs e)
    {
        //当flag的值为TRUE时将图片控件的Image属性切换到Timer1.jpg
        //否则将图片的Image属性切换到Timer2.jpg
        if (flag)
        {
            pictureBox1.Image = Image.FromFile(@"D:\C#_test\Timer1.jpg");
            flag = false;
        }
        else
        {
            pictureBox1.Image = Image.FromFile(@"D:\C#_test\Timer2.jpg");
            flag = true;
        }
    }
    //“启动定时器”按钮的单击事件
    private void button1_Click(object sender, EventArgs e)
    {
        timer1.Start();
    }
    //“停止定时器”按钮的单击事件
    private void button2_Click(object sender, EventArgs e)
    {
        timer1.Stop();
    }
}
```

运行该窗体，效果如下图所示。

![图片切换窗体运行效果](assets/4-1Z401114114O4.gif)

由于切换是动态的，从运行效果看不出来，读者可以直接通过演示程序查看效果。

另外，在本实例中将时间间隔属性 Interval 设置为 1000 毫秒，即 1 秒，读者可以根据实际情况更改该值。

## 17.DateTimePicker：日期时间控件

在 C# 语言中日期时间控件（DateTimePicker）在时间控件中的应用最多，主要用于在界面上显示当前的时间。

日期时间控件中常用的属性是设置其日期显示格式的 Format 属性。

Format 属性提供了 4 个属性值，如下所示。

* Short：短日期格式，例如2017/3/1；
* Long：长日期格式，例如2017年3月1日；
* Time：仅显示时间，例如，22:00:01；
* Custom：用户自定义的显示格式。

如果将 Format 属性设置为 Custom 值，则需要通过设置 CustomFormat 属性值来自定义显示日期时间的格式。

【实例】在窗体上设置动态的日期时间（使用定时器）。

根据题目要求，界面设计如下图所示。

![显示当前时间的界面设计](assets/4-1Z401134613152.gif)

实现该功能的代码如下。

```c#
public partial class DateTimePickerForm : Form
{
    public DateTimePickerForm()
    {
        InitializeComponent();
    }
    //DateTimePickerForm窗体加载事件
    private void DateTimePickerForm_Load(object sender, EventArgs e)
    {
        //设置日期时间控件中仅显示时间
        dateTimePicker1.Format = DateTimePickerFormat.Time;
        //设置每隔一秒调用一次定时器Tick事件
        timer1.Interval = 1000;
        //启动定时器
        timer1.Start();
    }
    private void timer1_Tick(object sender, EventArgs e)
    {
        //重新设置日期时间控件的文本
        dateTimePicker1.ResetText();
    }
}
```

## 18.日历控件（MonthCalendar）

在 

C#

 中日历控件（MonthCalendar）用于显示日期，通常是与文本框联用，将日期控件中选择的日期添加到文本框中。

下面通过实例来学习日历控件的应用。

【实例】使用日历控件实现入职日期的选择。

根据题目要求，通过单击“选择”按钮显示日历控件，并将选择的日期显示在文本框中，界面设计如下图所示。

![选择入职日期的窗体界面](assets/4-1Z401143H94V.gif)

实现该功能的代码如下。

```c#
public partial class MonthCalendarForm : Form
{
    public MonthCalendarForm()
    {
        InitializeComponent();
    }
    //窗体加载事件
    private void MonthCalendarForm_Load(object sender, EventArgs e)
    {
        //隐藏日历控件
        monthCalendar1.Hide();
    }
    //“选择”按钮的单击事件
    private void button1_Click(object sender, EventArgs e)
    {
        //显示日历控件
        monthCalendar1.Show();
    }
    //日历控件的日期改变事件
    private void monthCalendar1_DateSelected(object sender, DateRangeEventArgs e)
    {
        //将选择的日期显示在文本框中
        textBox1.Text = monthCalendar1.SelectionStart.ToShortDateString();
        //隐藏日历控件
        monthCalendar1.Hide();
    }
}
```

运行该窗体，效果如下图所示。

![选择入职日期窗体的运行效果](assets/4-1Z401143RS20.gif)

## 19.ContextMenuStrip：右键菜单控件（上下文菜单）

在 

C#

 WinForm开发中的右键菜单又叫上下文菜单，即右击某个控件或窗体时出现的菜单，它也是一种常用的菜单控件。

在 Windows 窗体应用程序中，上下文菜单在设置时直接与控件的 ContextMenuStrip 属性绑定即可。

下面通过实例来演示上下文菜单的应用。

【实例】创建 Windows 窗体应用程序，并为该窗体创建上下文菜单，菜单项包括打开窗体、关闭窗体。

根据题目要求创建 Windows 窗体，并在该窗体中添加上下文菜单。

在 Windows 窗体的 ContextMenuStrip 属性中设置所添加上下文菜单的名称。

设置属性的界面如下图所示。

![窗体中 ContextMenuStrip 属性的设置](assets/4-1Z4011504341C.gif)

设置 ContextMenuStrip1 菜单中的选项，如下图所示。

![ContextMenuStrip1菜单中的选项](assets/4-1Z40115053T50.gif)

在每个菜单项的单击事件中加入相关的操作代码，即可实现右键菜单的功能，具体代码如下。

```c#
public partial class ContextMenuStrip : Form
{
    public ContextMenuStrip()
    {
        InitializeComponent();
    }
    //打开新窗体的菜单项单击事件
    private void 打开窗体ToolStripMenuItem_Click(object sender, EventArgs e)
    {
        ContextMenuStrip menu1 = new ContextMenuStrip();
        menu1.Show();
    }
    //关闭窗体菜单项的单击事件
    private void 关闭窗体ToolStripMenuItem_Click(object sender, EventArgs e)
    {
        this.Close();
    }
}public partial class ContextMenuStrip : Form
{
    public ContextMenuStrip()
    {
        InitializeComponent();
    }
    //打开新窗体的菜单项单击事件
    private void 打开窗体ToolStripMenuItem_Click(object sender, EventArgs e)
    {
        ContextMenuStrip menu1 = new ContextMenuStrip();
        menu1.Show();
    }
    //关闭窗体菜单项的单击事件
    private void 关闭窗体ToolStripMenuItem_Click(object sender, EventArgs e)
    {
        this.Close();
    }
}
```

运行该窗体并右击，展开的右键菜单如下图所示。

![右键菜单的效果](assets/4-1Z401150A3I9.gif)

从运行效果可以看出，右击窗体后会出现如上图所示的右键菜单。选择相应的菜单项即可执行相应的打开窗体和关闭窗体的功能。

## 20.MenuStrip：菜单栏控件

在窗体上添加菜单栏控件 MenuStrip，直接按住 MenuStrip 不放，将其拖到右边的 Windows 窗体中即可，如下图所示。

![添加Menustrip控件](assets/4-1Z40115242XT.gif)

完成 MenuStrip 控件的添加后，在 Windows 窗体设计界面中就能看到“请在此处键入” 选项，直接单击它，然后输入菜单的名称，例如，“文件”“编辑”“视图”等。

此外，添加一级菜单后还能添加二级菜单，例如，为“文件”菜单添加“新建”“打开”“关闭”等二级菜单，如下图所示，模拟一个文件菜单（包括二级菜单）和编辑菜单。

![添加菜单栏](assets/4-1Z401152632217.gif)

## 21.StatusStrip：状态栏菜单控件

在 Windows 窗体应用程序中，状态栏菜单（StatusStrip）用于在界面中给用户一些提示，例如登录到一个系统后，在状态栏上显示登录人的用户名、系统时间等信息。

在 Office 的 Word 软件中，状态中显示的是当前的页数、当前页的字数统计、页面分辨率等信息，如下图所示。

![Word软件中的状态栏](assets/4-1Z401154TL43.gif)

在添加状态栏菜单时，按住 StatusStrip 选项不放，将其拖到右边的 Windows 窗体中即可，如下图所示。

![添加状态栏](assets/4-1Z4011549311Q.gif)

在状态栏上不能直接编辑文字，需要添加其他的控件来辅助。

单击上图所示界面中新添加的状态栏控件，则会显示如下图所示的下拉菜单，其中包括标签控件（StatusLabel）、进度条（ProgressBar）、下拉列表按钮（DropDownButton）、分割按钮（SplitButton）。

![状态栏中允许添加的控件](assets/4-1Z40115500N63.gif)

## 22.ToolStrip：工具栏控件

在 

C#

 WinForm开发中添加工具栏（ToolStrip）和添加菜单栏类似，在工具箱中将 ToolStrip 控件直接拖到 Windows 窗体中即可。

为了美观和界面的统一，应将其拖到菜单栏的下方，如下图所示。

![添加ToolStrip控件](assets/4-1Z401160F61R.gif)

在添加了 ToolStrip 控件之后，它只是一个工具条，上面并没有控件，所以它不能响应 一些事件，从而没有功能。

我们可以把它理解成一个占位符，就像是占着一个区域的位置，然后在其上面再添加按钮。

添加按钮也很简单，如下图所示。

![为工具栏添加控件](assets/4-1Z40116095B27.gif)

## 23.MDI窗体

在 Windows 窗体应用程序中，经常会在一个窗体中打开另一个窗体， 通过窗体上的不同菜单选择不同的操作，这种在一个窗体中打开另一个窗体的方式可以通过设置 MDI 窗体的方式实现。

MDI (Multiple Document Interface) 窗体被称为多文档窗体，它是很多 Windows 应用程序中常用的界面设计。

MDI 窗体的设置并不复杂，只需要将窗体的属性 IsMdiContainer 设置为 True 即可。

该属性既可以在 Windows 窗体的属性窗口中设置，也可以通过代码设置，这里在窗体加载事件 Load 中设置窗体为 MDI 窗体，代码如下。
```c#
this.IsMdiContainer = True;
```
此外，还可以在窗体类的构造方法中加入上面的代码。

在设置 MDI 窗体以后，窗体的运行效果如下图所示。

![MDI窗体](assets/4-1Z401164JL20.gif)

在 MDI 窗体中，弹出窗体的代码与直接弹出窗体有些不同，在使用 Show 方法显示窗体前需要使用窗体的 MdiParent 设置显示当前窗体的父窗体，实现的代码如下。

```
Test t = new Test();t.MdiParent = this;t.Show();
```

这里，`this`代表的是当前窗体。

下面通过实例来演示 MDI 窗体的使用。

【实例】创建 MDI 窗体，并在该窗体上设置菜单，包括打开文件、保存文件两个菜单项。

根据题目要求创建名为 MDIForm 的窗体，并设置该界面为 MDI 窗体，然后为该界面添加一个菜单和两个菜单项，界面设计如下图所示。

![MDIForm窗体](assets/4-1Z401164Z3T5.gif)

创建打开文件窗体和保存文件窗体，并分别通过菜单项的单击事件在 MDI 窗体中打开相应的窗体，代码如下。

```c#
public partial class MDIForm : Form
{
    public MDIForm()
    {
        InitializeComponent();
        this.IsMdiContainer = true;
    }
    //打开文件菜单项的单击事件
    private void 打开文件ToolStripMenuItem_Click(object sender, EventArgs e)
    {
        OpenFile f = new OpenFile();
        f.MdiParent = this;
        f.Show();
    }
    //保存文件菜单项单击事件
    private void 保存文件ToolStripMenuItem_Click(object sender, EventArgs e)
    {
        SaveFile f = new SaveFile();
        f.MdiParent = this;
        f.Show();
    }
}
```

运行该窗体并单击“打开文件”菜单项，界面如下图所示。

![在MDI窗体中打开新窗体效果](assets/4-1Z401164955622.gif)

从运行效果可以看出，打开文件窗体（OpenFile）在 MDI 窗体（MDIForm）中打开。

## 24.ColorDialog：颜色对话框控件

在 C# WinForm开发中颜色对话框控件（ColorDialog）用于对界面中的文字设置颜色，例如在 Word 中为文字设置颜色。

颜色对话框的运行效果如下图所示。

![颜色对话框](assets/4-1Z4011J04aD.gif)

在使用颜色对话框时不会在窗体中直接显示该控件，需要通过事件调用该控件的 ShowDialog 方法显示对话框。

下面通过实例学习颜色对话框的应用。

【实例】使用颜色对话框完成文本框中字体颜色的设置。

根据题目要求，界面设计如下图所示。

![颜色对话框使用界面的设计](assets/4-1Z4011J1235S.gif)

在激活“更改文本颜色”按钮的单击事件时弹出颜色对话框，并为界面中文本框的文本设置文本颜色，实现的代码如下。

```c#
public partial class ColorDialogForm : Form
{
    public ColorDialogForm()
    {
        InitializeComponent();
    }
    //“更改文本颜色”按钮的单击事件
    private void button1_Click(object sender, EventArgs e)
    {
        //显示颜色对话框
        DialogResult dr = colorDialog1.ShowDialog();
        //如果选中颜色，单击“确定”按钮则改变文本框的文本颜色
        if (dr == DialogResult.OK)
        {
            textBox1.ForeColor = colorDialog1.Color;
        }
    }
}
```

运行该窗体，并将文本框中的文本设置为蓝色，效果如下图所示。

![更改文本颜色前后的效果](assets/4-1Z4011J224118.gif)

通过运行前后的效果可以看出，文本框中的文字已经通过颜色对话框更改为蓝色。

## 25.FontDialog：字体对话框控件

在 

C#

 WinForm开发中字体对话框 (FontDialog) 用于设置在界面上显示的字体，与 Word 中设置字体的效果类似，能够设置字体的大小以及显示的字体样式等。

字体对话框的运行效果如下图所示。

![字体对话框](assets/4-1Z402091539430.gif)

下面通过实例演示字体对话框的应用。

【实例】使用字体对话框改变文本框中的字体。

根据题目要求，界面设计如下图所示。

![更改字体的设计界面](assets/4-1Z402091G3146.gif)

实现的代码如下。

```c#
public partial class FontDialogForm : Form
{
    public FontDialogForm()
    {
        InitializeComponent();
    }
    //“改变文本字体”按钮的单击事件
    private void button1_Click(object sender, EventArgs e)
    {
        //显示字体对话框
        DialogResult dr = fontDialog1.ShowDialog();
        //如果在对话框中单击“确认”按钮，则更改文本框中的字体
        if (dr == DialogResult.OK)
        {
            textBox1.Font = fontDialog1.Font;
        }
    }
}
```

运行该窗体，并更改字体的大小，效果如下图所示。

![更改文本字体前后效果对比](assets/4-1Z402091KG02.gif)

从运行效果的前后对比可以看出，文本框中的字体已经通过字体对话框改变了。

## 26.OpenFileDialog和SaveFileDialog：打开文件与保存文件

在 

C#

 WinForm 开发中文件对话框（FileDialog）主要包括文件浏览对话框，以及用于查找、打开、保存文件的功能，与 Windows 中的文件对话框类似，下面通过实例来演示文件对话框的使用。

【实例】打开一个记事本文件，并更改记事本中的内容，保存到文件中。

根据题目要求，界面设计如下图所示。

![文件对话框应用界面设计](assets/4-1Z402101K1946.gif)

在该界面中文本框使用的仍然是 TextBox，并将其设置为允许显示多行文本。

在该界面中“打开文件”和“保存文件”按钮的单击事件分别使用文件读入流和文件写入流来完成对文本信息的读写操作，实现的代码如下。

```c#
public partial class FileDialogForm : Form
{
    public FileDialogForm()
    {
        InitializeComponent();
    }
    //打开文件
    private void button1_Click(object sender, EventArgs e)
    {
        DialogResult dr = openFileDialog1.ShowDialog();
        //获取所打开文件的文件名
        string filename = openFileDialog1.FileName;
        if(dr==System.Windows.Forms.DialogResult.OK && !string.IsNullOrEmpty(filename))
        {
            StreamReader sr = new StreamReader(filename);
            textBox1.Text = sr.ReadToEnd();
            sr.Close();
        }
    }
    //保存文件
    private void button2_Click(object sender, EventArgs e)
    {
        DialogResult dr = saveFileDialog1.ShowDialog();
        string filename = saveFileDialog1.FileName;
        if(dr==System.Windows.Forms.DialogResult.OK && !string.IsNullOrEmpty(filename))
        {
            StreamWriter sw = new StreamWriter(filename, true, Encoding.UTF8);
            sw.Write(textBox1.Text);
            sw.Close();
        }
    }
}
```

运行该窗体，即可完成读取记事本文件的操作。

## 27.RichTextBox：富文本框控件

在上一节《C# OpenFileDialog和SaveFileDialog》中我们介绍了文件的打开和保存，但是实际开发中可能需要在读取文本信息时需要保留原有的文本格式，这时候就不能使用普通的文本控件 (TextBox) 了，而需要使用富文本框控件 (RichTextBox) 来完成。

RichTextBox 控件在使用时与 TextBox 控件是非常类似的，但其对于读取多行文本更有优势，它可以处理特殊格式的文本。

此外，在 RichTextBox 控件中还提供了文件加载和保存的方法，不需要使用文件流即可完成对文件的读写操作。

下面通过实例来演示 RichTextBox 控件的使用。

【实例】使用 RichTextBox 控件完成文件的打开与保存操作。

根据题目要求，将上一实例中的 TextBox 控件换成 RichTextBox 控件，并使用 RichTextBox 控件中提供的文件加载和保存方法来操作文件，实现的代码如下。

```c#
blic partial class FileDialogForm : Form
{
    public FileDialogForm()
    {
        InitializeComponent();
    }
    //打开文件
    private void button1_Click(object sender, EventArgs e)
    {
        DialogResult dr = openFileDialog1.ShowDialog();
        //获取打开文件的文件名
        string filename = openFileDialog1.FileName;
        if(dr==System.Windows.Forms.DialogResult.OK && !string.IsNullOrEmpty(filename))
        {
            richTextBox1.LoadFile(filename, RichTextBoxStreamType.PlainText);
        }
    }
    //保存文件
    private void button2_Click(object sender, EventArgs e)
    {
        DialogResult dr = saveFileDialog1.ShowDialog();
        //获取所保存文件的文件名
        string filename = saveFileDialog1.FileName;
        if(dr==System.Windows.Forms.DialogResult.OK && !string.IsNullOrEmpty(filename))
        {
            richTextBox1.SaveFile(filename, RichTextBoxStreamType.PlainText);
        }
    }
}
```

运行上面的窗体会发现使用 RichTextBox 控件的使用非常方式与简单, 并且能更好地控制文件的格式。

