###创建项目
> 关于创建项目,起始从Gradle建项目,再自己更改为Plugin插件项目
[参考资料](http://planet.jboss.org/post/managing_dependencies_for_intellij_idea_plug_in_development)
<br>

*以下gradle task配置为*
```groovy
task setup {
    dependsOn ideaModule, ideaProject
    doLast {
        copy {
            from '.'
            into '.idea/'
            include '*.ipr'
            rename { "modules.xml" }
        }
        project.delete "${project.name}.ipr"
    }
}
//这里将所有module type:从JAVA_MODULE直接改为PLUGIN_MODULE,因为使用gradle创建项目,而不是默认的plugin项目结构
idea.module.iml {
    withXml {
        println it.node
        it.node.@type = "PLUGIN_MODULE"
    }
}

```
其他步骤:
* 手动打开Module Setting,更改project sdk 更改Dependencies module sdk,必须更改为java sdk+ plugin插件sdk的共同依赖库
* 打开Edit Configurations 找到plugin 项目模块,手动添加运行实例
<br>

>
以上为gradle项目更改plugin项目的做法,为的是引包,开发的便利,其实并不便利.但相比起plugin本身的项目模式,还是要便利很多的.
目前没有找到一键转换,以及自动添加运行实例的办法,非常抱歉


### 打包问题
> out 目录为plugin module默认输出目录,可以通过其他方式修改,
但build/prepare plugin module 默认会取此目录作为打包插件目录,所以直接将编译输出目录改为此目录

```gradle
sourceSets {
    main {
        java.srcDirs = ['src/main/java']
        resources.srcDirs = ['src/main/resources']
        output.classesDir   = 'out/production/AndroidLayout2Anko'
        output.resourcesDir = 'out/production/AndroidLayout2Anko'
    }
}
```
<br>

#### 2017/12/15更新
* 以上gradle构建暂停,因为无法与plugin项目完全贴近,导致启动时.无法调试.所以暂时以plugin项目直接开发
* 检索sdk26内所有java widget源码,获得所有控件attr引用属性
* parser目录下为,正则分析所有attr并生成类代码
* 在iml文件中添加 <sourceFolder url="file://$MODULE_DIR$/src/com/cz/layout2anko/test" isTestSource="true" /> 使之成为测试目录

#### 2017/12/20
* 代码生成了所有映射关系类
* 完成了View类的所有属性映射


#### 包结构说明
* action 为事件源
* delegate 为工具定义,如弹出消息,刷新项目结构
* exception 为自定义异常
* inflateAttributes 为xml解析以及生成anko的控件映射类
* model涉及部分对象体
* prefs为配置项
* util为一个扩展工具类

####测试类介绍
* test/LayoutParser 测试xml解析
* test/PlatformWidgetParserTest 为解析平台Source目录内widget源码,然后生成anko的映射解析类代码,构建类似LayoutInflater的解析机制代码