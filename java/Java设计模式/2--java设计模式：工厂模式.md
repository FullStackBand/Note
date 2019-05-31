#一、介绍
工厂模式（Factory Pattern）是 Java 中最常用的设计模式之一。这种类型的设计模式属于创建型模式，它提供了一种创建对象的最佳方式。

在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且是通过使用一个共同的接口来指向新创建的对象。
#二、创建步骤 
1. 创建Person接口
```
package factory;

public interface Person {
    public void println();
}

```
2. 创建实现类
```
package factory;

import java.util.Objects;

//运动员
public class Athlete implements Person {
    private String name;
    private Sex sex;
    private Integer age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Athlete)) return false;
        Athlete athlete = (Athlete) o;
        return Objects.equals(getName(), athlete.getName()) &&
                getSex() == athlete.getSex() &&
                Objects.equals(getAge(), athlete.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSex(), getAge());
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

    public Athlete(String name, Sex sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Athlete() {
    }

    @Override
    public void println() {
        System.out.println("我叫" + name + ",性别" + sex + "+，今年" + age + "岁。是一名运动员！");
    }
}


```
```
package factory;

import java.util.Objects;

//厨师
public class Chef implements Person {
    private String name;
    private Sex sex;
    private Integer age;


    public Chef(String name, Sex sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Chef() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Chef{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Chef)) return false;
        Chef chef = (Chef) o;
        return Objects.equals(getName(), chef.getName()) &&
                getSex() == chef.getSex() &&
                Objects.equals(getAge(), chef.getAge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSex(), getAge());
    }

    @Override
    public void println() {
        System.out.println("我叫" + name + ",性别" + sex + "+，今年" + age + "岁。是一名厨师！");
    }
}

```
```
package factory;

//警察
public class Cop implements Person {
    private String name;
    private Sex sex;
    private Integer age;


    public Cop(String name, Sex sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public Cop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public void println() {
        System.out.println("我叫" + name + ",性别" + sex + "+，今年" + age + "岁。是一名警察！");
    }
}

```

```
package factory;

public enum Sex {
    男,女
}

```
3. 创建一个工厂
```
package factory;

public class PersonFactory {
    public Person getPerson(String profession) {
        if (profession == null) {
            return null;
        } else if ("athlete".equalsIgnoreCase(profession)) {
            return new Athlete("刘翔",Sex.男,28);
        } else if ("chef".equalsIgnoreCase(profession)) {
            return new Chef("王大厨",Sex.男,35);
        } else if ("cop".equalsIgnoreCase(profession)) {
            return new Cop("小倩",Sex.女,22);
        } else {
            return null;
        }
    }

}
```
4. 通过工厂获取对象
```
package factory;

public class Test {
    public static void main(String[] args) {
        PersonFactory factory = new PersonFactory();
        Person athlete = factory.getPerson("athlete");
        Person chef = factory.getPerson("chef");
        Person cop = factory.getPerson("cop");
        athlete.println();
        chef.println();
        cop.println();

    }
}

```
5. 结果
```
我叫刘翔,性别男+，今年28岁。是一名运动员！
我叫王大厨,性别男+，今年35岁。是一名厨师！
我叫小倩,性别女+，今年22岁。是一名厨师！
```

