app.controller('BaseController', function($scope) {
		//paninationConf用来画页面的分页栏
		$scope.paginationConf = {
			currentPage : 1, //设置当前页号
			totalItems : 5, //设置每页的总记录数
			itemsPerPage : 10, //设置页面行数
			perPageOptions : [ 5, 10, 15, 20 ], //设置页面总记录数可选项
			onChange : function() {
				$scope.reloadList(); //当分页栏发生改变时调用reloadList()方法重新加载页面
			}

		};
		$scope.reloadList = function() {
			$scope.findPage($scope.paginationConf.currentPage,
					$scope.paginationConf.itemsPerPage); //传入页面大小和当前页号 传给findPage函数
		}
		$scope.searchEntity = {}; //设置查询条件为空

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
});