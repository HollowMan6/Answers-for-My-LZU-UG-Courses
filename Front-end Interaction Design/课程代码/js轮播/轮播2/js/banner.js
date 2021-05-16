$(function(){
	
	var i,left,right,box,boxli,boxleng,width,dot,first,last,IsAuto;
	
	i = 0; 
    
	left = $(".Homeleft"); 
	
	right = $(".Homeright");
	
	box = $(".Homebanner ul");
	
	boxli = $(".Homebanner li");
	
	dotbox = $(".Homedot");
	
	dot = $(".Homedot").find("a");
	
	width = boxli.width();
	
	boxleng = boxli.length;
	
	box.css({width:width*(boxleng)})
	
	boxli.css({width:width});
	
	dot.eq(0).addClass("cur");
	
	boxli.eq(0).addClass("cur");
	
	boxli.each(function(index) {
       zindex = boxleng-(index+1);
	   $(this).css({"z-index":zindex})
    });
	
	IsAuto = true;
	
	left.click(function(){
		if(box.is(":animated")){return}
		i--;
		if(i<0){i=boxleng-1};
		boxanimate();
		
	})
	
	right.click(function(){
		if(box.is(":animated")){return}
		i++;
		boxanimate();
	})
	
	function boxanimate(){
		if(i>boxleng-1){i=0}		
		boxli.addClass("cur1");
		setTimeout(function(){
			boxli.removeClass("cur1");
			boxli.removeClass("cur").eq(i).addClass("cur");
			boxli.stop().animate({opacity:0,"z-index":"1"},500).eq(i).stop().animate({opacity:1,"z-index":boxleng},500);
			bannerdot(i)
		},400)	
	}
	
	function bannerdot(i){
		if(i>boxleng-1){i=0}
		dot.removeClass("cur").eq(i).addClass("cur");
	}

	dot.click(function(){
		i = $(this).index();
		bannerdot(i);
		boxanimate(i);
	})
	
	setInterval(function(){
		if(IsAuto){
			i++;
			boxanimate();
			bannerdot(i)
		}
	},6000)
	
	box.hover(function(){
		IsAuto = false;
	},function(){
		IsAuto = true;
	})
	
	dotbox.hover(function(){
		IsAuto = false;
	},function(){
		IsAuto = true;
	})
	
	left.hover(function(){
		IsAuto = false;
	},function(){
		IsAuto = true;
	})
	
	right.hover(function(){
		IsAuto = false;
	},function(){
		IsAuto = true;
	})
	
	
	
})