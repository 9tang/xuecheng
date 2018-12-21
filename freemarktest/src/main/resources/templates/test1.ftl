<!DOCTYPE html>
<html>
<head>
    <meta charset="utf‐8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name!'name'}!
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
姓名：${stuMap.stu1.name}<br/>
年龄：${stuMap['stu1'].age}<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
        <td>时间</td>
    </tr>
    ${stus?size}
    <#list stus as stu>
    <tr <#if stu.name == '小明'>style="background: red" </#if>>
        <td >${stu_index}</td>
        <td>${stu.name}</td>
        <td>${stu.age}</td>
        <td>${stu.money}</td>
        <td>${(stu.birthday?date)!''}</td>
        </tr>
    </#list>
</table>
${point?c!'99'}
</body>
</html>