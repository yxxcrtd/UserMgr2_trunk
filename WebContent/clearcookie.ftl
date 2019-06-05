<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="javaScript" src="./js/jquery.js"></script>
	</head>
	<body>
	   <#if projects??>
		<#list projects as p>
			<iframe id="f${p_index}" name="f${p_index}" style="display:none" src="${p!}"></iframe>
		</#list>
       </#if> 
		<div style="text-align:center;padding:100px 0;top:200px">
			正在注销登录信息......<br/><br/>
			<img src="images/loading2.gif" />
		</div>

		<script type="text/javascript">
		window.onload = function(){ 
		setTimeout(function() {
				location.href="${redUrl}";
		}, 4000);
		}; 
		</script>
	</body>
</html>	

  
