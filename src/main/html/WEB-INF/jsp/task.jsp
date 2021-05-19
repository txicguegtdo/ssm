<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.zpwq.bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User user = (User)request.getSession().getAttribute("user");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>任务列表</title>
	<script src="<%=path%>/js/jquery-1.11.1.js"></script>
	<script src="<%=path%>/js/layer.js"></script>
	<style type="text/css">
		body,.login-submit,.login-submit:before,.login-submit:after {
			background: url("<%=path%>/img/bg.png") 0 0 repeat;
			color: #00CD00;
		}
		
		input {
			width: 100px;
			height: 20px;
		}
		
		.show {
			display: none;
		}
	</style>
</head>
<body>
	<div style="text-align:right">
		<label>用户:</label><label><%=user.getName() %></label>
	</div>
	<fieldset>
		<legend>任务列表</legend>
		<table id="task">
			<tr>
				<td>序号</td>
				<td>任务名</td>
				<td>邮箱</td>
				<td>服务器</td>
				<td>密码</td>
				<td>发送</td>
				<td>抄送</td>
				<td>密送</td>
				<td>发送类型</td>
				<td>发送时间</td>
				<td>操作</td>
			</tr>
			<c:forEach var="task" items="${taskList}" varStatus="s">
				<tr id="task_${s.index+1}">
					<td>${s.index+1}
						<input style="display: none" name='id' value="${task.id}" />
					</td>
					<td><input disabled name='code' value="${task.code}" />
					</td>
					<td><input disabled name='email' value='${task.email }' />
					</td>
					<td><input disabled name='host' value='${task.host }' />
					</td>
					<td><input disabled name='pwd' value='${task.pwd }'
						type='password' />
					</td>
					<td><input disabled name='toSend' value='${task.toSend }' />
					</td>
					<td><input disabled name='toCC' value='${task.toCC }' />
					</td>
					<td><input disabled name='toBCC' value='${task.toBCC }' />
					</td>
					<td><select disabled name='jobType'>
							<c:choose>
								<c:when test="${task.jobType==0 }">
									<option value='0' selected>立即发送</option>
								</c:when>
								<c:otherwise>
									<option value='0'>立即发送</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${task.jobType==1 }">
									<option value='1' selected>指定时间</option>
								</c:when>
								<c:otherwise>
									<option value='1'>指定时间</option>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${task.jobType==2 }">
									<option value='2' selected>指定周期</option>
								</c:when>
								<c:otherwise>
									<option value='2'>指定周期</option>
								</c:otherwise>
							</c:choose>
					</select></td>
					<td><input disabled name='jobTime' value='${task.jobTime }' />
					</td>

					<td><button onclick="saveTask('${s.index+1}')">保存</button>
						<button name='modfiy' onclick="modfiy('${s.index+1}')">编辑</button>
						<button name='emailValidate' onclick="emailValidate('${s.index+1}')">验证</button>
						<button name='del' onclick="del('${task.id}')">删除</button>
						<button name='send' onclick="send('${task.id}')">立即发送</button>
						<c:choose>
								<c:when test="${task.extend1=='1' }">
									<button name='work' onclick="work('${task.id}',this)" style="background:#00CD00" data-flag="0" >任务中..</button>
								</c:when>
								<c:otherwise>
									<button name='work' onclick="work('${task.id}',this)" data-flag="1">开启任务</button>
								</c:otherwise>
							</c:choose>
					</td>
				</tr>
				<tr>
					<td></td>
					<td colspan="9">
						<div id="body_${s.index+1}" class='show'>
							<fieldset>
								<legend>邮件正文</legend>
								<p style="display:none;" name="mainBody" >
									<input name='mainBodyPath' style="width:300px;" />
									主题: <input name='theme' style="width:200px;" />
								</p>
								<button name='mainBody' onclick="upload('mainBody',this)">上传正文</button>
							</fieldset>
							<fieldset>
								<legend>邮件附件</legend>
								<p style="display:inline;" name="attachment">
								</p>
								<button onclick="upload('attachment')">上传附件</button>
							</fieldset>
							<fieldset>
								<legend>规则</legend>
								<p style="display:inline;" name="rule">
								</p>
								<button onclick="addRule()">添加规则</button>
							</fieldset>
						</div></td>
				</tr>

			</c:forEach>
		</table>
		<button onclick="newTask()">新建</button>
	</fieldset>
	<div style="display: none" id="upload">
		<form id="fileLoad" enctype="multipart/form-data" >
			<input type="text" name="id" style="display: none" value='0'/>
			<input type="text" name="taskId" style="display: none" />
			<input type="text" name="bodyType" style="display: none" />
			<input type="text" name="path" style="display: none" />
			<input type="file" name="file" style="width:300px;" />
			<p>
				<label></label><input type="text" name="name" style="width:200px;" />
			</p>
		</form>
	</div>
	<div style="display: none" id="rule">
		<form id="fileLoad" enctype="multipart/form-data" >
			<input type="text" name="id" style="display: none" value='0'/>
			<input type="text" name="taskId" style="display: none" value='0'/>
			<input type="text" name="bodyId" style="display: none" value='0'/>
			<input type="text" name="path" style="display: none" />
			<input type="file" name="file" style="width:300px;" />
			<p>
				<label></label><input type="text" name="name" style="width:200px;" />
			</p>
		</form>
	</div>
</body>
<script type="text/javascript">
  		var rowId = "";
  		
  		function toUpLoad(){
  			var form = new FormData(document.getElementById("fileLoad"));
  			$.ajax({
                url:"<%=basePath%>/body/upload",
                type:"post",
                data:form,
                processData:false,
                contentType:false,
                dataType:"json",
                success:function(data){
                	alert(data.msg);
                	if(data.flag==1){
                		var body = $("#body_"+rowId);
                		if(data.body.bodyType=='0'){
                			body.find("input[name='mainBodyPath']").val(data.body.path);
                			body.find("input[name='theme']").val(data.body.name);
                			body.find("p[name='mainBody']").css("display","inline");
                			body.find("button[name='mainBody']").data("body",data.body);
                			body.find("button[name='mainBody']").text("修改");
                		}else if(data.body.bodyType=='1'){
                			var path = $("<input style='width:300px;' value='"+data.body.path+"' />");
							var name = $("<label> 别名: </label><input style='width:200px;' value='"+data.body.name+"' />");
							var btn =  $("<button onclick=\"upload('attachment',this)\" >修改</button>"
										+ "<br/>");
							btn.data("body",data.body);
							body.find("p[name='attachment']").append(path).append(name).append(" ").append(btn);
                		}
                	}
                },
                error:function(e){
                    alert("上传发生错误！！");
                }
            });
  		}
  		
  		function upload(bodyType,obj){
			$("#fileLoad").find("input[name='bodyType']").val(bodyType=="mainBody"?"0":"1");
			var fileLoad = $("#fileLoad");
			fileLoad.find("input[name='path']").val("");
			fileLoad.find("input[name='id']").val("0");
			fileLoad.find("input[name='name']").val("");
			
			if(obj && $(obj).data("body")){
				var body = $(obj).data("body");
				var fileLoad = $("#fileLoad");
				fileLoad.find("input[name='path']").val(body.path);
				fileLoad.find("input[name='id']").val(body.id);
				fileLoad.find("input[name='name']").val(body.name);
			}
			$("#fileLoad").find("label").text(bodyType=="mainBody"?"主题:":"别名:");
  			layer.open({
  				type: 1,
  			  	title: bodyType=="mainBody"?"上传正文":"上传附件", //不显示标题
  			  	content: $('#upload'), //捕获的元素
  			  	btn:['提交'],
	  			yes: function(index, layero){
	  			    layer.close(index); //如果设定了yes回调，需进行手工关闭
	  			  	toUpLoad(); 
	  			},
  			});	
  		}
  		function uploadRule(obj){
  			var btn = $(obj);
  			var taskId = $("#task_"+rowId).find("input[name='id']").val();
  			var id = 0;
  			if(obj && btn.data("rule")){
  				id = btn.data("rule").id;
  			}
  			var ruleCode = btn.parent().find("input[name='ruleCode']").val();
  			var ruleType = btn.parent().find("input[name='ruleType']").val();
  			$.post("<%=basePath%>/body/uploadRule",
  					{'id':id,'taskId':taskId,'ruleCode':ruleCode,'ruleType':ruleType},
  					function(data){
  						alert(data.msg);
  						if(data.flag==1){
  							btn.text("修改");
  						}
  					},
  					"json"
  			);
  		}
  		function addRule(){
  			var rule = $("#body_"+rowId).find("p[name='rule']");
  			var code = $("<label>原内容: </label><input style='width:100px;' name='ruleCode' />");
			var type = $("<label> 替换格式: </label><input style='width:100px;' name='ruleType' />");
			var btn = $("<button onclick=\"uploadRule(this)\" >保存</button>");
			var p = $("<p></p>");
			p.append(code).append(type).append(" ").append(btn);
			rule.append(p);
  		}
  		function modfiy(id){
  			if(rowId==id){
  				$("#task_"+id).find("input").attr("disabled",true);
  	  			$("#task_"+id).find("select").attr("disabled",true);
  	  			$("#body_"+id).css("display","none");
  	  			rowId = "";
  	  			return;
  			}
  			rowId = id;
  			
  			$("#task").find("input").attr("disabled",true);
  			$("#task").find("select").attr("disabled",true);
  			
  			$("#task_"+id).find("input").removeAttr("disabled");
  			$("#task_"+id).find("select").removeAttr("disabled");
  			$("#body_"+id).find("input").removeAttr("disabled");
			$(".show").css("display","none");
  			$("#body_"+id).css("display","block");
  			showBody(id);
  		}
  		function showBody(id){
  			var taskId = $("#task_"+id).find("input[name='id']").val();
  			$("#upload").find("input[name='taskId']").val(taskId);
  			$.post("<%=basePath%>/body/getByTaskId",
  					{'taskId':taskId},
  					function(data){
  						var body = $("#body_"+id);
  						if(data.rule){
  							body.find("p[name='rule']").text("");
  							for(var i=0;i<data.rule.length;i++){
  								var code = $("<label>原内容: </label><input style='width:100px;' name='ruleCode' value='"+data.rule[i].ruleCode+"' />");
  								var type = $("<label> 替换格式: </label><input style='width:100px;' name='ruleType' value='"+data.rule[i].ruleType+"' />");
  								var btn = $("<button onclick=\"uploadRule(this)\" >修改</button>");
  								btn.data("rule",data.rule[i]);
  								var p = $("<p></p>");
  								p.append(code).append(type).append(" ").append(btn);
								body.find("p[name='rule']").append(p);
  							}
  							
  						}
  						if(data.list){
  							body.find("p[name='attachment']").text("");
  							for(var i=0;i<data.list.length;i++){
  								if(data.list[i].bodyType=='0'){//正文
  									body.find("input[name='mainBodyPath']").val(data.list[i].path);
  									body.find("input[name='theme']").val(data.list[i].name);
  									body.find("p[name='mainBody']").css("display","inline");
  									body.find("button[name='mainBody']").data("body",data.list[i]);
  									body.find("button[name='mainBody']").text("修改");
  								}else if(data.list[i].bodyType=='1'){//附件
  									var path = $("<input style='width:300px;' value='"+data.list[i].path+"' />");
  									var name = $("<label> 别名: </label><input style='width:200px;' value='"+data.list[i].name+"' />");
  									var btn =  $("<button onclick=\"upload('attachment',this)\" >修改</button>"
  												+ "<br/>");
  									btn.data("body",data.list[i]);
  									body.find("p[name='attachment']").append(path).append(name).append(" ").append(btn);
  								}
  							}
  						}
  					},
  					"json"
  			);
  			
  		}
  		function newTask(){
  			var row = $("#task tr").length;
  			var table = $("#task");
  			var tr = "<tr id='task_"+row+"'>"+
  						"<td>"+row+
  							"<input name='id' style='display: none' value='0' />"+
  						"</td>"+
  						"<td><input name='code'/></td>"+
  						"<td><input name='email'/></td>"+
  						"<td><input name='host' /></td>"+
  						"<td><input name='pwd' type='password'/></td>"+
  						"<td><input name='toSend' /></td>"+
  						"<td><input name='toCC' /></td>"+
  						"<td><input name='toBCC' /></td>"+
  						"<td>"+
  							"<select name='jobType' >"+  
							  "<option value ='0' selected >立即发送</option>"+  
							  "<option value ='1'>指定时间</option>"+  
							  "<option value='2'>指定周期</option>"+  
							"</select>"+
						"</td>"+
  						"<td><input disabled name='jobTime' /></td>"+
  						"<td><button onclick=\"saveTask('"+row+"')\">保存</button> "+
  						    "<button name='del' onclick='reload()' >删除</button>"+
  						"</td>"+
  					"</tr>";
  			table.append(tr);
  		}
  						    
  						   
  		function saveTask(id){
  			var task = $("#task_"+id);
  			var id = task.find("input[name='id']").val();
  			var code = task.find("input[name='code']").val();
  			var email = task.find("input[name='email']").val();
  			var host = task.find("input[name='host']").val();
  			var pwd = task.find("input[name='pwd']").val();
  			var toSend = task.find("input[name='toSend']").val();
  			var toCC = task.find("input[name='toCC']").val();
  			var toBCC = task.find("input[name='toBCC']").val();
  			var jobType = task.find("select[name='jobType']").val();
  			var jobTime = task.find("input[name='jobTime']").val();
  			var emailBodyId = task.find("input[name='emailBodyId']").val();
  			
  			$.post("<%=basePath%>/task/save",
  					{'id':id,'code':code,'email':email,'host':host,'pwd':pwd,
  					 'toSend':toSend,'toCC':toCC,'toBCC':toBCC,'emailBodyId':emailBodyId,
  					 'jobType':jobType,'jobTime':jobTime},
  					function(data){
  						alert(data.msg);
  						reload();
  					},
  					"json"
  			);
  		}
  		function del(id){
  			var taskId = id;
  			if(taskId>0){
  				$.post("<%=basePath%>/task/del",
  	  					{'taskId':taskId},
  	  					function(data){
  	  						alert(data.msg);
  	  						reload();
  	  					},
  	  					"json"
  	  			);
  			}else{
  				window.location.href="<%=basePath%>task/list";
  			}	
  		}
  		function emailValidate(id){
  			var task = $("#task_"+id);
  			var email = task.find("input[name='email']").val();
  			var host = task.find("input[name='host']").val();
  			var pwd = task.find("input[name='pwd']").val();
  			if(email && host && pwd){
 				$.post("<%=basePath%>/task/emailValidate",
 	  					{'email':email,'host':host,'pwd':pwd},
 	  					function(data){
 	  						alert(data.msg);
 	  					},
 	  					"json"
 	  			);
  			}
  			
  		}
  		function send(id){
  			var taskId = id;
  			if(taskId>0){
  				$.post("<%=basePath%>/task/send",
  	  					{'taskId':taskId},
  	  					function(data){
  	  						alert(data.msg);
  	  					},
  	  					"json"
  	  			);
  			}
  		}
  		function reload(){
  			window.location.href="<%=basePath%>task/list";
  		}
  		
  		function work(taskId,obj){
  			var btn = $(obj);
  			var index = btn.data("flag");
  			if(taskId>0){
  				$.post("<%=basePath%>/task/work",
  	  					{'taskId':taskId,'index':index},
  	  					function(data){
  	  						if(data.flag=='1'){
	  	  						if(data.index=="1"){
		  	  		  				btn.text("任务中..");
		  	  		  				btn.css("background","#00CD00");
		  	  		  				btn.data("flag","0");
		  	  		  			}else{
		  	  		  				btn.text("开启任务");
		  	  		  				btn.data("flag","1");
		  	  		  				btn.css("background","");
		  	  		  			}
  	  						}
  	  					},
  	  					"json"
  	  			);
  			}
  		}

  </script>
</html>
