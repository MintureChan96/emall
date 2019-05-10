//添加方法
app.controller('specifyController',function($scope,$controller,specifyService){
	$controller('BaseController',{$scope:$scope})
	
	$scope.findPage = function(page, size) { //post请求发送到控制器传入参数.如果serchEntity为空则为无条件分页查询
		specifyService.findPage(page,size,$scope.searchEntity).success(function(response) {
			//成功后从新给scope的list赋值值为Tbspecify对象数组
			$scope.list = response.data.rows;
			//设置总记录数
			$scope.paginationConf.totalItems = response.data.total;
		});
	}
	
	$scope.save = function() {
		var obj =null;
		//判断 scope中tbspecify的id是否为空如果为空则添加 否则该方法将是修改，因为修改和添加共用一个页面
		if ($scope.entity.specification.id != null) {
			obj =specifyService.update($scope.entity);
		}else
			{
			obj = specifyService.save($scope.entity);
			}
		
		obj.success(
				function(response) {
					//成功刷新页面,失败弹出对话框。
					if (response.status==200) {
						$scope.reloadList();
					} else {
						alert(response.msg);
					}
				});
	}
	//根据id查询tbspecify
	$scope.findById = function(id) {
		specifyService.findById(id).success(
		//成功返回对象tbspecify
		function(response) {
			$scope.entity = response;
		}
		)};
	//创建空的品牌id集合
	$scope.selectIds = [];
	$scope.updateSelection = function($event, id) {
		/* 传入事件与参数 
		每次checkebox选中都会触发该函数
		 */
		if ($event.target.checked) {
			//向空集合中加入选中的id
			$scope.selectIds.push(id);
		} else {
			//设置选中的集合中的索引
			var index = $scope.selectedIds.indexof(id);
			//删除没有选中的id
			$scope.selectIds.splice(index, 1);
		}
	}

	//删除事件
	$scope.dele = function() {
		if (confirm('确定要删除吗？')) {
			specifyService.dele($scope.selectIds).success(
					function(response) {
						if (response.status == 200) {
							$scope.reloadList();
						} else {
							alert(response.msg);
						}
					});
		}
	}
	
/*	$scope.entity={specificationOption:[]};*/
	
	$scope.addTableRow=function()
	{
		$scope.entity.specificationOption.push({});
	}
	$scope.deleTableRow=function(index)
	{
		$scope.entity.specificationOption.splice(index,1);
	}
});

	