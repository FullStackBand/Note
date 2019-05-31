# 使用：`sys:gridselect `标签
1. **url**：数据源
2. **id** : id
3. **name** : *实体类中关联的属性名称*（例：`name="projectId"`）
4. **value** : *用EL表达式取出的id*（例：`value="${costreimburAct.projectId}"` ）
5. **labelName** : *展示的属性名*（例：`labelName=".name"`；注意：必须带点`.`）
6. **lableValue** : *用EL表达式取出的展示的内容*（例：`labelValue="${costreimburAct.projectName}`" ）
7. **title** : *Grid选择框的标题*
8. **cssClass** : *css样式*
9. **filldLables** : *Grid选择框显示的属性标签*--**可以写多个**（例如：`名称|地址|区域`）
10. **fieldKeys** : *Grid选择框显示的属性名*--**可以写多个**（例：`name|address|area`）
11. **searchLabels** : *Grid选择框的搜索的属性标签*--**可以写多个**（例：`名称|地址`）
12. **searchKeys** : *Grid选择框的搜索的属性名*--**可以写多个**（例：`name|address`）

#代码示例
```html
<sys:gridselect 
url="${ctx}/busi/project/project/data"
id="project" 
name="projectId"
value="${costreimburAct.projectId}" 
labelName=".name"
labelValue="${costreimburAct.projectName}" 
title="选择项目"
cssClass="form-control required" 
fieldLabels="项目名称"
fieldKeys="name" 
searchLabels="" 
searchKeys="">
</sys:gridselect>
```
