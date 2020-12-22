function $(id){
	return typeof id==='string'?document.getElementById(id):id;
}

window.onload=function(){
  // 标签的索引
  

  var lis=$('notice-tit').getElementsByTagName('li'),
      divs=$('notice-con').getElementsByTagName('div');
//alert(lis.length);
  if(lis.length!=divs.length) return;

  // 遍历所有的页签
  for(var i=0;i<lis.length;i++){
    lis[i].id=i;
    lis[i].onmouseover=function(){
      // 用that这个变量来引用当前滑过的li
      //alert(this.id);
		
        for(var j=0;j<lis.length;j++){
          lis[j].className='';
          divs[j].style.display='none';
        }
		//设置当前为高亮显示
        this.className='select';
        divs[this.id].style.display='block';
      }
    }
  }
