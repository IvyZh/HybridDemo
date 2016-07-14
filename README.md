# HybridDemo
Android和Html5混合式开发

Html代码：（在html文件夹中）

<html> 
<head> 
请输入你的姓名！
	
	</br>

<input type="text" id="123">
<input type="button" value="Click me!" onclick="alerting();"/>
<Script Language="JavaScript"> 
	function alerting(){ 
	var content = "";
	content=window.document.getElementById("123").value;
		if(content.length<1){
			alert("no content!"); 
		}else{
			//alert(content); 
			window.person.setName(content);
		}
	}

</Script> 
</head> 
</html> 


结果页面Html：


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function show(jsondata){ 
		var jsonobjs = eval(jsondata);
		var table = document.getElementById("personTable");
		for(var y=0; y<jsonobjs.length; y++){
			var tr = table.insertRow(table.rows.length); 
			var td1 = tr.insertCell(0);
			var td2 = tr.insertCell(1);
			td2.align = "center";
			var td3 = tr.insertCell(2);
			td3.align = "center";
			td1.innerHTML = jsonobjs[y].name; 
			td2.innerHTML = jsonobjs[y].age;
			td3.innerHTML = jsonobjs[y].phone; 			
			
		}
	}
</script>
</head>
<body onload="javascript:person.getResult()">
<table border="0" width="100%" id="personTable" cellspacing="0">
<tr>
	<td width="30%">姓名</td>
	<td width="30%" align="center">年龄</td>
	<td align="center">电话</td>
</tr>
</table>
</body>
</html>


