# camunda_demo
camunda+springboot<br/>
**q1:Key/Id?**<br/>
_http://camunda-cn.shaochenfeng.com/user-guide/process-engine/process-versioning/_
<br/>
RuntimeService().startProcessInstanceByKey<br/>
RuntimeService().startProcessInstanceById 两个方法都启动一个流程实例，区别是需要传入的参数不一样<br/>
key = 流程图的id </br>
id = 数据库的id，可以通过RepositoryService()输入流程图的id+版本号查询<br/>
普通Exception会引起camunda的事务，BpmnError不会触发事务<br/><br/>
**q2:job?** <br/>
_http://camunda-cn.shaochenfeng.com/user-guide/process-engine/the-job-executor/_ <br/>
①事务回滚到Asynchronous Continuations Before位置 <br/>
②定时器<br/>
③Asynchronous handling of BPMN events<br/>

job的retries变成0时 将会生成事件Incidents <br/>
失败事件重试：<br/>
Administrative action is necessary to reset the retries<br/>
camunda前端dashboard/processes页面找到失败的事件，点进去，点击刷新按钮(右侧或者底部Action)<br/>
![img.png](img.png)