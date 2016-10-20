<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>车讯伙伴</title>
<link type="text/css" href="/statics/css/style.css" rel="stylesheet" />
<script type="text/javascript" src="/statics/js/jq.js"></script>
<script type="text/javascript" src="/statics/js/public.js"></script>
<!--#var(${packageName?replace('/','.')}.model.${mypackageName}.${voClassName} query)-->
<!--#set(PageEntity page)-->
<script>
$(function(){
	$('.i-2').click(function(){
		$('.pop2').show()
		$('#bg').show()
	})
	$('.off').click(function(){
		$('.pop2').hide()
		$('#bg').hide()
	})
})
	
$(function(){
	$('.i-8').click(function(){
		openAddDialog();
	})
	$('.off').click(function(){
		$('.pop15').hide()
		$('#bg').hide()
	})
})

$(function(){
	$('.i-10').click(function(){
		openEditDialog();
	})
	$('.off').click(function(){
		$('.pop15').hide()
		$('#bg').hide()
	})
})

function getSelectObjId(){
	return $('.cur').attr('oid');
}

//搜索
function search(){
	$("#pageCurrentPage").val(1);
	$("#searchForm").submit();
}

//新增
function openAddDialog(){
	$('#editDialogTitle').html('信息管理-添加');
	$('#id').val(null);
	$('#dictName').val(null);
	$('#description').val(null);
	//$('#dictType').val(null);
	$('#status').val('0');
	$('.pop15').show();
	$('#bg').show();
}

//编辑
function openEditDialog(){
	var oid = getSelectObjId();
	if(oid == null || oid == ''){
		alert("请选择要修改的记录");
		return false;
	}
	$('#editDialogTitle').html('信息管理-编辑');
	var params = {
		id:oid
	};
	$.get("/manage/${mypackageName}/${voClassName?lower_case}/edit",params,function(data){
		if(data != null){
			$('#id').val(data.id);
			$('#dictName').val(data.dictName);
			$('#description').val(data.description);
			$('#dictType').val(data.dictType);
			$('#status').val(data.status + "");
			$('.pop15').show();
			$('#bg').show();
		}
	});
}

//新增、修改保存
function saveSubmit(){
	var params = $('#editForm').serialize();
	var url;
	var vid = $('#id').val();
	if(vid != null && vid != ''){
		url = "/manage/${mypackageName}/${voClassName?lower_case}/edit";
	}else{
		url = "/manage/${mypackageName}/${voClassName?lower_case}/add";
	}
	$.post(url,params,function(data){
		if(data != null && data.ret == 1){
			$('.pop15').hide();
			$('#bg').hide();
			$("#searchForm").submit();
		}else {
			alert('保存失败');
		}
	});
}
</script>
</head>
<body>
    <div id="bg"></div>
    <div class="right">
        <div class="special">
            <div class="special-hd">
                <h3>XX维护管理</h3>
            </div>
        </div>
        <div class="right-box" id="tab">
            <ul class="icon clearfix">
            	<li class="i-2"><span></span>查询</li>
                <li class="i-8"><span></span>新增</li>
                <li class="i-10"><span></span>编辑</li>
            </ul>
            <!--END 右边icon-->
            
            <form action="/manage/${mypackageName}/${voClassName?lower_case}/list" method="post" id="searchForm">
            <input type="hidden" id="pageCurrentPage" name="page.currentPage" value="$${"{"}page.currentPage${"}"}"/>
            <div class="icon-pop pop2" style="width:280px;">
                <div class="icon-pop-hd clearfix">
                    <h3>查询</h3>
                    <a href="javascript:;" class="off"><img src="/statics/images/off.jpg" /></a></div>
                <div class="icon-pop-bd">
                    <p>
                        <span class="fl" style="width:30px;">名称</span>
                        <input name="name" value="" type="text" style="margin:0 0 0 5px; width:180px"/>
                    </p>
                    <p>
                        <span class="fl" style="width:30px;">类型</span>
	                        <select name="dictType" id="ad_position" style="margin:0 0 0 5px; width:150px">
	    						<option value=""> --全部--</option>
	    				</select>
                    </p>
                    <p class="clearfix tc" style="padding:10px 5px;"><a href="javascript:search();">查询</a></p>
                </div>
            </div>
            </form>
            <!--END 查询弹出层-->
            
            <form id="editForm"> 
            <input name="id" id="id" type="hidden" value=""/>
            <div class="icon-pop pop15">
                <div class="icon-pop-hd clearfix">
                    <h3 id="editDialogTitle">信息-添加</h3>
                    <a href="javascript:;" class="off"><img src="/statics/images/off.jpg" /></a></div>
                <div class="icon-pop-bd">
                    <#list columnList as column>
		    		<p><span class="fl">${column.propertyName}</span>
                        <input name="${column.propertyName}" id="${column.propertyName}" type="text"/>
                    </p>
		    		</#list>
                        
                    <p class="clearfix" style="padding:10px 80px;"><a href="javascript:void(0);" onclick="javascript:saveSubmit();">确 定</a></p>
                </div>
            </div>
            </form>
            <!--END 添加、修改弹出层-->
           	
            <div class="con clearfix">
                <div class="con-bd clearfix">
                    <div id="tabs-1">
                        <table  width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <#list columnList as column>
				        		<th>${column.propertyName}</th>
				        		</#list>
				        		<th>状态</th>
                            </tr>
                            <!--#for(${packageName?replace('/','.')}.model.${mypackageName}.${voClassName} ${voClassName?uncap_first} : ${voClassName?uncap_first}List)-->
                            <tr oid="$${"{"}${voClassName?uncap_first}.id ${"}"}">
                            	<#list columnList as column>
			        			<td>$${"{"}${voClassName?uncap_first}.${column.propertyName} ${"}"}</td>
			        			</#list>
				        		<td id="statusName_$${"{"}${voClassName?uncap_first}.id ${"}"}">
				        			<!--#if(${voClassName?uncap_first}.status==0)-->启用<!--#else--><span style="color:red;">已禁用</span><!--#end-->
				        		</td>
                            </tr>
                            <!--#end-->
                        </table>
                        $${"{include(\"/common/page.httl\")}"}
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

