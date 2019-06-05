<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script language="javaScript" src="./js/jquery.js"></script>
	</head>
	
	<body>
	   <#if projects??>
		<#list projects as p>
			<iframe id="f${p_index}" name="f${p_index}" style="display:none" src="${p!}?t=${userTicket}"></iframe>
		</#list>
       </#if> 
		<div style="text-align:center;padding:100px 0;top:200px">
			正在登录......<br/><br/>
			<img src="images/loading2.gif" />
		</div>

		<script type="text/javascript">
		window.onload = function(){ 
		setTimeout(function() {
				var p="${p!}";
				var p2="${p2!}";
				var redUrl="${redUrl}";
									if(p!=""){
										location.href="${p!}/cookie2.aspx?t=${userTicket}";
									}
									else if(p2!=""){
										location.href="${p2}?t=${userTicket}";
									}else{
										location.href="${redUrl}";
									}
		}, 4000);
		}; 
		</script>
	</body>
</html>	

  
