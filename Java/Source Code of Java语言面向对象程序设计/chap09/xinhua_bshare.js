// JavaScript Document

/*bshare js*/
var ishare='<A class=ishare target="_blank" style="float:left; margin:5px 4px 0 0;"><img src="http://www.xinhuanet.com/static/img/icon_small.gif"></A>';
var bshare_s='<DIV class=bshare-custom sizcache08521370026118069="0" sizset="0" style="float:left;padding-right:10px; padding-top:5px;">';
var xinhua='<A class=bshare-xinhuamb title=\u5206\u4eab\u5230\u65b0\u534e\u5fae\u535a href="javascript:void(0);"></A>';
var sina='<A class=bshare-sinaminiblog title=\u5206\u4eab\u5230\u65b0\u6d6a\u5fae\u535a href="javascript:void(0);"></A>';
var qqkj='<A class=bshare-qzone title=\u5206\u4eab\u5230QQ\u7a7a\u95f4 href="javascript:void(0);"></A>';var qq='<A class=bshare-qqmb title=\u5206\u4eab\u5230\u817e\u8baf\u5fae\u535a href="javascript:void(0);"></A>';var renren='<A class=bshare-renren title=\u5206\u4eab\u5230\u4eba\u4eba\u7f51 href="javascript:void(0);"></A>';var bsharesync='<A class=bshare-bsharesync title=\u5206\u4eab\u5230\u4e00\u952e\u901a href="javascript:void(0);"></A>';var more='<A class="bshare-more bshare-more-icon more-style-addthis" title=\u66f4\u591a\u5e73\u53f0></A>';var bshare_e='<SPAN class="BSHARE_COUNT bshare-share-count">0</SPAN></DIV>';var bshare=ishare+bshare_s+xinhua+sina+qqkj+qq+renren+bsharesync+more+bshare_e;$("#bshare01").html(bshare);$("#bshare02").html(bshare);
var linkurl;
var SharePageUrl = location.href;
var ShareTitle = document.title;
linkurl = "http://go.10086.cn/ishare.do?m=wt&u="+encodeURI(SharePageUrl)+"&t="+encodeURI(ShareTitle)+"&sid=052412243205";
$(".ishare").attr("href",linkurl);

document.writeln("<script type='text/javascript' charset='utf-8'> var bShareOpt = {style: -1, pop: 1, uuid: 'fae4ac1c-0568-4b2b-9749-7ca9475eb315',pophcol: 2};<\/script>");
document.writeln("<script type='text/javascript' src='http://static.bshare.cn/b/buttonLite.js' charset='utf-8'><\/script>");
document.writeln("<script type='text/javascript' src='http://static.bshare.cn/b/bshareC0.js' charset='utf-8'><\/script>");
/*bshare js end*/
