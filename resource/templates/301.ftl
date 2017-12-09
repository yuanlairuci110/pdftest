<!DOCTYPE HTML>
<html>
<head>

</head>
<body style="font-family: SimSun;line-height:1">
<div style="width:100%;">

    <p align="center">
        <span style="font-size:25px;font-weight:bolder;"><b>供应商认证报告</b></span><br/>
    </p>

    <p style="line-height:32px;text-indent:2em">
    <div>基本情况：</div>
    </p>
    <p style="text-indent:2em">

    <div style="padding-left:30px">
        <table border="0.5">
            <tr><td>认证编码</td><td>${code}</td><td>项目编号</td><td>${bidProjectCode}</td></tr>
            <tr><td>企业机构名称</td><td>${supplierName}</td><td>统一信用代码</td><td>${businessLicenseNo}</td></tr>
            <tr><td>供应类别</td><td>${supplierName}</td><td>经营类型</td><td>${jyms}</td></tr>
            <tr><td>认证方式</td><td>${zzsh}</td><td>${xcrz}</td><td>${cpsy}</td></tr>
            <tr><td>认证结论</td><td>${zzsh}</td><td>${xcrzresult}</td><td>${cpsyresult}</td></tr>
            <tr><td>认证结果</td><td>${result}</td><td>报告时间</td><td>${bgsj}</td></tr>
            <tr><td>认证单位</td><td>${certificateOrgName}</td><td>认证发起人</td><td>${creatorName}</td></tr>
        </table>
    </div>
    </p>

    <p style="line-height:32px;text-indent:2em">
    <div>资质审核：</div>
    </p>
    <p style="text-indent:2em">
    <div style="padding-left:30px">
        <table border="0.5">
            <tr><td>资质项目</td><td>分级标准</td><td>资质情况</td><td>审核结果</td><td>备注</td></tr>
        <#list qualifyChecklist as item>
            <tr><td>${item.zzxm}</td><td>${item.fjbz}</td><td>${item.zzqk}</td><td>${item.shjg}</td><td>${item.bz}</td></tr>
        </#list>
        </table>
    </div>
    </p>

    <p style="line-height:32px;text-indent:2em">
    <div>现场认证：</div>
    </p>

    <p style="text-indent:2em">
    <div style="padding-left:30px">
        <table border="0.5">
            <tr><td>行号</td><td>所属分类</td><td>指标名称</td><td>指标描述</td><td>评分依据</td><td>指标权重</td><td>认证</td></tr>
        <#list applyFieldList as item>
            <#if item_index%2==0 >
                <tr><td>${item_index}</td><td>基数 ：111</td><td>${item.rbmc}</td><td>${item.zbms}</td><td>${item.pfyj}</td><td>${item.zbqz}</td><td>${item.rzh}</td></tr>
            <#else>
                <tr><td>${item_index}</td><td>偶数 ：${item.ssfl}</td><td>${item.rbmc}</td><td>${item.zbms}</td><td>${item.pfyj}</td><td>${item.zbqz}</td><td>${item.rzh}</td></tr>
            </#if>

        </#list>
        </table>
    </div>
    </p>

</div>
</body>
</html>



