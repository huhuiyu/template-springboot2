<#assign rootPath=springMacroRequestContext.contextPath />
<!DOCTYPE html>
<html lang="zh-cmn-Hans">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <title>程序猿学习-胡辉煜</title>
  <meta name="author" content="胡辉煜">
  <meta name="keywords" content="胡辉煜">
  <meta name="description" content="胡辉煜-程序猿学习">
  <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
  <link rel="shortcut icon" href="https://media.huhuiyu.top/huhuiyu.top/hu-logo.jpg" type="image/jpeg">
  <link rel="stylesheet" href="${rootPath}/freemarker.css">
</head>

<body>
  <h1>freemarker页面</h1>
  <div>${dept}</div>
  <hr/>
  <div>
    <#list list as d>${d}<hr></#list>
  </div>
</body>

</html>