
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/tabbar/tabbar-1/login","pages/tabbar/tabbar-1/code","pages/tabbar/tabbar-1/fresh","pages/tabbar/tabbar-1/new_file","pages/tabbar/tabbar-1/tabbar-1","pages/tabbar/tabbar-1/detalis","pages/tabbar/tabbar-1/user","pages/tabbar/tabbar-2/tabbar-2","pages/tabbar/tabbar-2/detalis2","pages/tabbar/tabbar-2/address","pages/tabbar/tabbar-3/tabbar-3","pages/tabbar/tabbar-4/tabbar-4","pages/tabbar/tabbar-4/chats","pages/tabbar/tabbar-4/chat","pages/tabbar/tabbar-4/stars","pages/tabbar/tabbar-4/fans","pages/tabbar/tabbar-4/comments","pages/tabbar/tabbar-5/tabbar-5","pages/tabbar/tabbar-5/allfans","pages/tabbar/tabbar-5/allfollows","pages/tabbar/tabbar-5/allstars","pages/tabbar/tabbar-5/new_file","pages/tabbar-3-detial/tabbar-3-release/tabbar-3-release","pages/tabbar-3-detial/tabbar-3-video/tabbar-3-video","pages/tabbar-3-detial/tabbar-3-qa/tabbar-3-qa"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"NekoTalk","navigationBarBackgroundColor":"#F8F8F8","backgroundColor":"#F8F8F8"},"tabBar":{"borderStyle":"black","backgroundColor":"#333","color":"#8F8F94","selectedColor":"#f33e54","list":[{"pagePath":"pages/tabbar/tabbar-1/tabbar-1","iconPath":"static/img/tabbar/home.png","selectedIconPath":"static/img/tabbar/homeactive.png","text":"首页"},{"pagePath":"pages/tabbar/tabbar-2/tabbar-2","iconPath":"static/img/tabbar/guanzhu.png","selectedIconPath":"static/img/tabbar/guanzhuactive.png","text":"附近"},{"pagePath":"pages/tabbar/tabbar-3/tabbar-3","iconPath":"static/img/tabbar/add.png","selectedIconPath":"static/img/tabbar/addactive.png"},{"pagePath":"pages/tabbar/tabbar-4/tabbar-4","iconPath":"static/img/tabbar/news.png","selectedIconPath":"static/img/tabbar/newsactive.png","text":"消息"},{"pagePath":"pages/tabbar/tabbar-5/tabbar-5","iconPath":"static/img/tabbar/me.png","selectedIconPath":"static/img/tabbar/meactive.png","text":"我"}]},"nvueCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"brandnew","compilerVersion":"2.7.14","entryPagePath":"pages/tabbar/tabbar-1/login","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/tabbar/tabbar-1/login","meta":{"isQuit":true},"window":{"navigationBarTitleText":"登录"}},{"path":"/pages/tabbar/tabbar-1/code","meta":{},"window":{"navigationBarTitleText":"获取验证码"}},{"path":"/pages/tabbar/tabbar-1/fresh","meta":{},"window":{"navigationStyle":"custom","navigationBarTextStyle":"white"}},{"path":"/pages/tabbar/tabbar-1/new_file","meta":{},"window":{"navigationStyle":"custom","navigationBarTextStyle":"white"}},{"path":"/pages/tabbar/tabbar-1/tabbar-1","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationStyle":"custom","navigationBarTextStyle":"white"}},{"path":"/pages/tabbar/tabbar-1/detalis","meta":{},"window":{"navigationStyle":"custom","navigationBarTextStyle":"white"}},{"path":"/pages/tabbar/tabbar-1/user","meta":{},"window":{"navigationBarTitleText":"个人主页"}},{"path":"/pages/tabbar/tabbar-2/tabbar-2","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"附近猫咖"}},{"path":"/pages/tabbar/tabbar-2/detalis2","meta":{},"window":{"navigationBarTitleText":"查看猫咖"}},{"path":"/pages/tabbar/tabbar-2/address","meta":{},"window":{"navigationBarTitleText":"猫咖地址"}},{"path":"/pages/tabbar/tabbar-3/tabbar-3","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"添加"}},{"path":"/pages/tabbar/tabbar-4/tabbar-4","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我的消息"}},{"path":"/pages/tabbar/tabbar-4/chats","meta":{},"window":{"navigationBarTitleText":"user"}},{"path":"/pages/tabbar/tabbar-4/chat","meta":{},"window":{"navigationBarTitleText":"chat"}},{"path":"/pages/tabbar/tabbar-4/stars","meta":{},"window":{"navigationBarTitleText":"收到的点赞"}},{"path":"/pages/tabbar/tabbar-4/fans","meta":{},"window":{"navigationBarTitleText":"新增粉丝"}},{"path":"/pages/tabbar/tabbar-4/comments","meta":{},"window":{"navigationBarTitleText":"收到的评论"}},{"path":"/pages/tabbar/tabbar-5/tabbar-5","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"我"}},{"path":"/pages/tabbar/tabbar-5/allfans","meta":{},"window":{"navigationBarTitleText":"我的粉丝"}},{"path":"/pages/tabbar/tabbar-5/allfollows","meta":{},"window":{"navigationBarTitleText":"我的关注"}},{"path":"/pages/tabbar/tabbar-5/allstars","meta":{},"window":{"navigationBarTitleText":"我的获赞"}},{"path":"/pages/tabbar/tabbar-5/new_file","meta":{},"window":{"navigationBarTitleText":"我"}},{"path":"/pages/tabbar-3-detial/tabbar-3-release/tabbar-3-release","meta":{},"window":{"navigationBarTitleText":"发布动态"}},{"path":"/pages/tabbar-3-detial/tabbar-3-video/tabbar-3-video","meta":{},"window":{}},{"path":"/pages/tabbar-3-detial/tabbar-3-qa/tabbar-3-qa","meta":{},"window":{}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
