/*
 2015 ReadSpeaker International B.v.
*/
window.ReadSpeakerDefer={deferred:null,clickhandler:function(b){b=b||window.event;var c=b.target||b.srcElement;3===c.nodeType&&(c=c.parentNode);if(c!==document&&window.ReadSpeakerDefer.isRSLink(c))return window.ReadSpeakerDefer.deferred=c,(c=window.ReadSpeakerDefer.findRSParent(c))&&c.className&&!/rsdeferred/i.test(c.className)&&(c.className+=" rsdeferred"),window.ReadSpeakerJIT&&window.rspkr.loadCore(),b.cancelBubble=!0,b.preventDefault&&b.preventDefault(),b.stopPropagation&&b.stopPropagation(),
!1},init:function(){this.RSDeferClick(document)},isRSLink:function(b){return this.isRSParent(b.parentNode)||b.href&&-1<b.href.indexOf("readspeaker.com/cgi-bin/rsent")},isRSParent:function(b){return b?b.className&&-1<b.className.indexOf("rsbtn")||b.id&&"string"===typeof b.id&&-1<b.id.indexOf("readspeaker_button"):!1},findRSParent:function(b){for(;b.parentNode&&b.parentNode!==document&&(b=b.parentNode,"a"!=b.tagName.toLowerCase()||!this.isRSLink(b)););return b==document?void 0:b.parentNode},RSDeferClick:function(b){b.addEventListener?
b.addEventListener("click",this.clickhandler,!1):b.attachEvent?b.attachEvent("onclick",this.clickhandler):b.onclick=this.clickhandler}};window.ReadSpeakerDefer.init();
(function(b){var c=function(){var c={major:"2",minor:"5",update:"9",revision:"3943",prod:"embhl"},h=[],E=0,r=0,F=!1,t=[],G=0,k=[],H=!1,u=!1,P=0,x=!1,l=null,f="default",I=!1,v=[],J=!1,y="",z={},A=!1,B="",w=null,n=!1,C=function(a){if("string"==typeof a){a="ReadSpeaker."+a.replace("_",".");a=a.split(".");for(var e=b,d=0,c=a.length;d<c;d++)if(e)if(e[a[d]]){if(d==c-1)return e[a[d]];e=e[a[d]]}else break;else break;return!1}},L=function(a,e){t.push(a);G++;e=e||[a];for(var d=0,c=e.length;d<c;d++)try{var p=
C(e[d]);"function"==typeof p.init&&p.init.apply(p,[])}catch(f){s("[rspkr] Could not load: "+e[d]+" | "+f,3)}G===r&&!0===F&&(s("[rspkr] All prod mods loaded. _domready = "+x,4),n&&b.ReadSpeaker.init(),d=function(){rspkr.Common.createShortcuts();rspkr.devt("onModsLoaded",b);rspkr.devt("onAfterModsLoaded",b);z.onAdapterReady?rspkr.devt("onReady",b):rspkr.evt("onAdapterReady",function(){rspkr.devt("onReady",b)});b.ReadSpeaker.ui.viewport={width:$rs.width(b),height:$rs.height(b)}},b.ReadSpeaker.Common.addEvent("onReady",
function(){K.executeCode();K.flush();if(w)a:{rspkr.log("[rspkr.startAutoplay] Id: "+w);var a=$rs.get(w);if($rs.isArray(a)&&0<a.length)a=a[0];else if($rs.isArray(a)&&0==a.length)break a;a=$rs.findIn(a,"a");if($rs.isArray(a)&&0<a.length)a=a[0];else if($rs.isArray(a)&&0==a.length)break a;b.readpage(a)}}),x?d():b.ReadSpeaker.Common.addEvent("onDOMReady",d))},R=function(){var a={id:"",type:"text/javascript",src:"",cb:function(){var a=(b.event&&b.event.srcElement&&b.event.srcElement.id?b.event.srcElement.id:
this.id).replace("rsmod_","");t.push(a);a=C(a);"function"==typeof a.init&&a.init.apply(a,[]);Q()}},e=b.ReadSpeaker.lib.Facade.currentLib().toLowerCase();"rslib"==e?(a.id="rsmod_lib.RSLib",a.src="ReadSpeaker.lib.RSLib.js"):(a.id="rsmod_lib.Facade.adapter."+e,a.src="ReadSpeaker.lib.Facade.adapter."+e+".js");m.load(a)},Q=function(){if(J&&b.ReadSpeaker.modmap&&!H){for(var a=b.ReadSpeaker.modmap,e=[],d=0,c="|",p=[],f=0,h=v.length;f<h;f++)if(e=a.products&&"function"==typeof a.products[v[f]]?a.products[v[f]]():
null){for(var g=d=0,k=e.length;g<k;g++)-1===c.indexOf("|"+e[g][0]+e[g][1]+"|")&&(e[g][0].length&&(p[e[g][0]]=e[g][2],/text\/javascript/.test(e[g][1])&&(cb=function(){var a=(b.event&&b.event.srcElement&&b.event.srcElement.id?b.event.srcElement.id:this.id).replace("rsmod_","");L(a,p[a])}),m.load({id:"rsmod_"+e[g][0],type:e[g][1],src:"ReadSpeaker."+e[g][0]+("text/css"===e[g][1]?".css":".js"),cb:cb,async:!0})),"undefined"!==typeof e[g][1]&&/text\/javascript/.test(e[g][1])&&d++,c+=e[g][0]+e[g][1]+"|");
r+=d}F=!0}},M=function(){if(!u){n&&!A&&(document.getElementById("rsmod_Styles").href=B+"ReadSpeaker.Styles.css");for(var a in h)h.hasOwnProperty(a)&&(mod=a,m.load({id:"req_"+mod,type:"text/javascript",src:"ReadSpeaker."+mod+".js",cb:function(){var a=(b.event&&b.event.srcElement&&b.event.srcElement.id?b.event.srcElement.id:this.id).replace("req_",""),a=a.replace("_","."),a=!1===h[a]?[a]:h[a],d;E++;for(var c=0,f=a.length;c<f;c++)t.push(a[c]),d=C(a[c]),"function"==typeof d.init&&d.init.apply(d,[]);E===
Object.size(h)&&(b.ReadSpeaker.Common.addEvent("onModsLoaded",b.ReadSpeaker.pub.Config.setup),J=!0,R())},async:!0}))}},N=function(){rspkr.log("[rspkr.updateBaseClass] Attempting to update..");for(var a=document.getElementsByTagName("div"),e=/\brsbtn\b/,d=0,c=a.length;d<c;d++)e.test(a[d].className)&&(a[d].className=a[d].className.replace(e,b.rsConf.ui.rsbtnClass));rspkr.log("[rspkr.updateBaseClass] Update successful!");L("skinfile")},S={extract:function(a){if("string"==typeof a){var b={};a=a.split(/[;&]/);
for(var d,c=0;c<a.length;c++)(d=a[c].split("="))&&2==d.length&&(b[unescape(d[0])]=unescape(d[1]).replace(/\+/g," "));return b}return{}}},K={isok:!0,executeCode:function(){this.isok=!0;if(!k.length)return!0;for(idx in k)if(k.hasOwnProperty(idx)&&"function"==typeof k[idx])try{k[idx].apply(b,[])}catch(a){this.isok=!1,rspkr.log("[rspkr.q] "+a,3)}},flush:function(){k=[]}},m={load:function(a){if("text/javascript"==a.type||"text/css"==a.type){a.src=l.path+a.src;var e=document.getElementsByTagName("head")[0],
d=document.createElement("text/javascript"==a.type?"script":"link"),f=[c.major,c.minor,c.update,c.revision].join(".");"function"==typeof a.cb&&(void 0!==d.onreadystatechange?d.onreadystatechange=function(){"complete"!=this.readyState&&"loaded"!=this.readyState||a.cb.apply(b)}:d.onload=a.cb);d.id=a.id.replace(".","_");d.type=a.type;"text/javascript"==a.type?(d.src=a.src+"?v="+f,a.async&&(d.async=!0)):(d.rel="stylesheet",d.href=a.src+"?v="+f);e.appendChild(d)}}},T=0,D={1:[],2:[],3:[],4:[],5:[],6:[]},
q={1:{lbl:"Info",method:"log"},2:{lbl:"Warn",method:"warn"},3:{lbl:"Err",method:"error"},4:{lbl:"AS",method:"log"},5:{lbl:"SW",method:"log"}},s=function(a,b){var d=y;b=b||1;D[b].push(a);if(d&&"string"===typeof d&&-1<d.indexOf(","+b+",")){d=q[b].lbl;try{console[q[b].method]&&console[q[b].method](T++ +". "+d+": "+a)}catch(c){}}},U=function(){var a;if(/rsdebug=rsdebug/i.test(document.location.href))try{a=","+document.location.href.split("?").pop().match(/rsdebug=rsdebug([^$|&]*)/i).pop()+","}catch(b){a=
",3,"}else a="";return a},V=function(a){y=/^,[0-9,]*,$/.test(a)?a:","+a+","};(function(){Object.size=function(a){var b=0,d;for(d in a)a.hasOwnProperty(d)&&b++;return b}})();(function(){if(b.rsConf&&b.rsConf.params&&"string"===typeof b.rsConf.params&&b.rsConf.params)var a=b.rsConf.params,e=a.split("?");else e=document.getElementsByTagName("script"),a=e[e.length-1].getAttribute("src"),e=a.split("?");/\?/i.test(a)&&1<e.length&&e[1].length?(B=a.replace(/[^\/]*$/,""),y=U(),l=a=S.extract(e[1]),f=a.skin||
"default",v=a.pids.split(","),l.path=e[0].replace("ReadSpeaker.js",""),h.Core=["Common","lib.Facade","modmap"],h["pub.Config"]=!1,w=a.autoplay,n=b.ReadSpeakerJIT="1"===a.jit,l.forceBasicMode&&"1"===l.forceBasicMode||document.attachEvent&&/MSIE/i.test(navigator.userAgent)&&(document.compatMode&&"backcompat"===document.compatMode.toLowerCase()||/MSIE 6\./i.test(navigator.userAgent))?(A=!0,m.load({id:"rsmod_Styles",type:"text/css",src:(l.skinPathBasic||"ReadSpeaker.Styles-Basic")+".css",cb:null})):(m.load({id:"rsmod_Styles",
type:"text/css",src:"ReadSpeaker.Styles"+(n?"-Button":"")+".css",cb:null}),"default"===f||n||(r++,m.load({id:"rsskin_"+f+"_style",type:"text/css",src:"skins/"+f+"/"+f+".css",cb:null}),m.load({id:"rsskin_"+f+"_js",type:"text/javascript",src:"skins/"+f+"/"+f+".js",cb:function(){"default"!==f&&u?N():I=!0},async:!0}))),n||M()):H=!0})();return new function(){this.meta={obj:c,version:[c.major,c.minor,c.update].join(".")+"_rev"+c.revision+"-"+c.prod};this.q=function(a){"function"==typeof a&&(z.onReady?a.apply(b,
[]):k.push(a))};this.init=function(){u||(u=!0,document.addEventListener&&document.removeEventListener("DOMContentLoaded",b.ReadSpeaker.init,!1),x=!0,b.ReadSpeaker.Common&&b.ReadSpeaker.Common.dispatchEvent("onDOMReady"),s("[rspkr] DOM Ready!"),I&&(s("[rspkr] Updating base class.",1),N()))};this.getLoadedMods=function(){return t};this.rsidCount=1E3;this.logcount=0;this.log=function(a,b){s(a,b||1)};this.showLog=function(a){a=a||"1";rspkr.log("[rspkr.printErrorLog]",1);a=(a||"3").split(",");for(var b=
0;b<a.length;b++)if(D.hasOwnProperty(a[b])){var d=D[a[b]],c=a[b],c=parseInt(c)||3,f=q[c].lbl;console.groupCollapsed&&console.groupCollapsed(f);for(f=0;f<d.length;f++)try{console[q[c].method]&&console[q[c].method](d[f])}catch(h){}console.groupCollapsed&&console.groupEnd()}};this.getID=function(){return"readspeaker"+P++};this.getVersion=function(){return this.meta.version};this.skin=f;this.displog=z;this.basicMode=A;this.params=l;this.setDebugLevel=V;this.baseUrl=B;this.loadCore=M;this.audio=null}}();
b.ReadSpeaker=b.rs=b.rspkr=c})(window);ReadSpeaker.enums={mime:{tjs:"text/javascript",tcss:"text/css",thtml:"text/html"}};(function(b){if(!window.ReadSpeakerJIT){var c=navigator.userAgent,O=/*@cc_on!@*/false,h=setTimeout;/mozilla/i.test(c)&&!/(compati)/.test(c)||/opera/i.test(c)||/webkit/i.test(c)?document.addEventListener("DOMContentLoaded",b,!1):O?function(){var c=document.createElement("doc:rdy");try{c.doScroll("left"),b()}catch(r){h(arguments.callee,0)}}():window.onload=b}})(ReadSpeaker.init);