<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加</title>
<link href="/statics/css/style.css" rel="stylesheet" type="text/css" />
<link href="/statics/css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/statics/js/jquery.js"></script>
<script type="text/javascript" src="/statics/js/select-ui.min.js"></script>
<script>
$(document).ready(function()${"{"}
	$("#cancel").click(function()${"{"}
  		window.location.href="/manage/system/${voClassName?lower_case}/list";
  	${"}"});
${"}"});
</script>
</head>
<body>
	<!--#var(${packageName?replace('/','.')}.model.${mypackageName}.${voClassName} ${voClassName?uncap_first})-->
	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    <div class="formbody">
    	<div class="formtitle"><span>基本信息</span></div>
    	<form method="post" action="/manage/system/${voClassName?lower_case}/edit">
    	<ul class="forminfo">
    		<#list columnList as column>
    		<li><label>${column.propertyName}</label><input name="${column.propertyName}" type="text" class="dfinput" value="$${"{"}${voClassName?uncap_first}.${column.propertyName} ${"}"}"/><i>${column.propertyName}不能超过30个字符</i></li>
    		</#list>
    		<li><label>&nbsp;</label><input name="" type="submit" class="btn" id="submit" value="保存"/>&nbsp;<input name="" id="cancel" type="button" class="btn" value="返回"/></li>
    	</ul>
    	</form>
    </div>
</body>
</html>
