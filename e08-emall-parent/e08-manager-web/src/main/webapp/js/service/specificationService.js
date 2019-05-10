app.service("specifyService",function($http){
		this.findPage=function(page,size,searchEntity)
	{
			return $http.post('../specify/findByPager?pageNo=' + page + '&pageSize=' + size,searchEntity)
	}
    this.save=function(entity){
    	
    	return $http.post('../specify/add',entity);
    }
    this.update=function(entity){
    	return $http.post('../specify/update',entity)
    }
    
	this.findById=function(id)
	{
		return $http.get('../specify/findById?id=' + id)
	}
    this.dele=function(selectIds)
    {
    	return  $http.get('../specify/delete?ids=' +selectIds)
    }
	});