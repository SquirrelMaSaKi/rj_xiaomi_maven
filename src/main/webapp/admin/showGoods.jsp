<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css" />
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/DatePicker.js"></script>
<title>商品列表</title>

</head>
<body>
<div class="row" style="width:98%;margin-left: 1%;">
	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				会员列表
			</div>
			<div class="panel-body">
				<div class="row">
                    <form action="goodsservlet?method=getGoodsList" method="post">
                        <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                            <div class="form-group form-inline">
                                <span>商品名称</span>
                                <input type="text" name="name" class="form-control" value="${name}">
                            </div>
                        </div>
                        <div class="col-xs-5 col-sm-5 col-md-5 col-lg-5">
                            <div class="form-group form-inline">
                                <span>上架时间</span>
                                <input type="text" readonly="readonly"  name="pubdate" class="form-control" onclick="setday(this)" value="${pubdate}">
                            </div>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <!--搜索-->
                            <button type="submit" class="btn btn-primary" id="search"><span class="glyphicon glyphicon-search"></span></button>
                        </div>
                    </form>
				</div>
				<div style="height: 330px;overflow: scroll;">
					<table id="tb_list" class="table table-striped table-hover table-bordered">
						<tr>
							<td>序号</td><td>商品名称</td><td>价格</td><td>上架时间</td><td>类型</td><td>操作</td>
						</tr>
						<c:forEach items="${pageBean.data}" var="goods" varStatus="i">
							<tr>
								<td>${i.count}</td>
								<td>${goods.name}</td>
								<td>${goods.price}</td>
								<td>${goods.pubdate}</td>
								<td>${goods.goodsType.name}</td>
								<td><a href="goodsservlet?method=deleteGoods&id=${goods.id}" onclick="return confirm('确认删除吗？')">删除</a> &nbsp;<a href="goodsservlet?method=takeGoods&id=${goods.id}">修改</a> &nbsp;
									<a tabindex="0" id="example${goods.id}" class="btn btn-primary btn-xs"
									role="button" data-toggle="popover"
									data-trigger="focus"
									data-placement="left"
									data-content="${goods.intro}">描述</a>
									<script type="text/javascript">
										$(function(){
											$("#example${goods.id}").popover();
										})
									</script>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<nav aria-label="..." class="text-center">
					<ul id="mypage" class="pagination">

					</ul>
				</nav>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function () {
        //清空
        $("#mypage").empty();

        //添加上一页
        $("#mypage").append("<li><a href='goodsservlet?method=getGoodsList&pageNum=${pageBean.pageNum-1}&pageSize=${pageBean.pageSize}&name=${name}&pubdate=${pubdate}'>«</a></li>");


        //显示导航
        for (var i=${pageBean.startPage};i<=${pageBean.endPage};i++) {
            if (${pageBean.pageNum} == i) {
                $("#mypage").append("<li class='active'><a href='goodsservlet?method=getGoodsList&pageNum="+i+"&pageSize=${pageBean.pageSize}&name=${name}&pubdate=${pubdate}'>" + i + "</a></li>");

            } else {
                $("#mypage").append("<li><a href='goodsservlet?method=getGoodsList&pageNum="+i+"&pageSize=${pageBean.pageSize}&name=${name}&pubdate=${pubdate}'>" + i + "</a></li>");
            }
        }

        //添加下一页
        $("#mypage").append("<li><a href='goodsservlet?method=getGoodsList&pageNum=${pageBean.pageNum+1}&pageSize=${pageBean.pageSize}&name=${name}&pubdate=${pubdate}'>»</a></li>");
    });
</script>
</body>
</html>