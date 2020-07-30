<template>
	<view class="info-1">
		<!-- 布局 -->
		<view style="margin-bottom:70px;">
			<!-- 注释 -->
			<!-- <view class="zs">点击对方头像可进入私聊模式</view> -->
			<!-- 自身回答模式 -->
			<view v-for="(item, key) in list" :key="key">
				<view v-if="item.ss == getname" class="right">
					<view>
						<view style="font-size:12px;color:#999;text-align:right;">{{ item.ss }}</view>
						<view class="langcon">{{ item.con }}</view>
					</view>
					<view style="margin-left:10px;">
						<image src="../../../static/youkeer.png" style="width:40px;height:40px;border-radius:50%;border:1px solid #aaa;" />
					</view>  
				</view>
				<view v-else class="left">
					<view>
						<image src="../../../static/youkeer.png" style="width:40px;height:40px;border-radius:50%;border:1px solid #aaa;" />
					</view>
					<view style="margin-left:10px;">
						<view style="font-size:12px;color:#999;">{{ item.ss }}</view>
						<view class="langcon">{{ item.con }}</view>
					</view>
				</view>
			</view>
		</view>
		<!-- 输入框 -->
		<view class="bottom">
			<view class="bottom-p">
				<view><input type="text" placeholder="请输入你要发送的内容" cursor-spacing="50" v-model="value" /></view>
				<view @tap="clickRequest">发送</view>
			</view>
			<!-- 底部分类 -->
			<view class="classification">
				<view>
					<image src="../../../static/icon/yuyin.png"></image>
				</view>
				<view>
					<image src="../../../static/icon/tupian.png"></image>
				</view>
				<view @click="camera">
					<image src="../../../static/icon/xiangji.png" style="width:25px;height:25px;"></image>
				</view>
				<view>
					<image src="../../../static/icon/hongbao.png"></image>
				</view>
				<view>
					<image src="../../../static/icon/biaoqing.png" style="width:27px;height:27px;"></image>
				</view>
				<view>
					<image src="../../../static/icon/gengduo.png" style="width:27px;height:27px;"></image>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				value: '',
				tableData: [],
				list: [
					{
						ss:'111',
						con:'222'
					}
				],
				start: true
			};
		},
		methods: {
			connectSocketInit() {
				var that = this;
				uni.connectSocket({
					url: '', //请输入链接websocke.io地址
					success() {
						uni.onSocketOpen(function(res) {
							uni.showToast({
								icon: 'none', 
								position: 'bottom',
								title: 'Socket连接已打开！'
							});
							uni.sendSocketMessage({
								data: message, //自身定义一个发送消息对象
								success: function() {
									uni.onSocketMessage(function(res) {
										var s = JSON.parse(res.data);
										that.list.push({
											con: s.msg,
											ss: s.name
										});
									});
								},
								fail: function() {
									uni.showToast({
										icon: 'none',
										position: 'bottom',
										title: '数据发送失败'
									});
								}
							});
						});
					}
				});
			},
			clickRequest() {
				if (this.value == '') {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '不能发送空白消息'
					});
				} else {
					var _self = this;
					var ok = JSON.stringify({
						type: 'say',
						fromid: 'null',
						toid: 'null',
						name: uni.getStorageSync('name'),
						msg: this.value
					});
					uni.sendSocketMessage({
						data: ok,
						success: function() {
							uni.onSocketMessage(function(res) {
								var ret = JSON.parse(res.data);
								_self.list.push({
									con: ret.msg,
									ss: ret.name
								});
								for (var s = 0; s < _self.list.length; s++) {
									if (_self.list[s].ss == uni.getStorageSync('name')) {
										_self.start = true;
										_self.value = '';
									} else {
										_self.start = false;
									}
								}
								// 初始化
								_self.value = '';
							});
						},
						fail: function() {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: '数据发送失败'
							});
						}
					});
				}
			},
			// 关闭socket
			closeSocket() {
				uni.closeSocket(function(res) {
					console.log('WebSocket 已关闭！');
				});
			},
			camera() {
				uni.chooseImage({
					count: 6, //默认9
					sizeType: ['original', 'compressed'], //可以指定是原图还是压缩图，默认二者都有
					sourceType: ['camera'], //从相册选择
					success: function(res) {
						console.log(JSON.stringify(res.tempFilePaths));
					}
				});
			}
		},
		onLoad() {
			this.connectSocketInit();
		},
		computed: {
			getname() {
				return uni.getStorageSync('name');
			}
		},
		// 关闭socket
		beforeDestroy() {
			this.closeSocket();
		}
	};
</script>

<style>
	.bottom {
		position: fixed;
		bottom: 0;
		width: 100%;
		background: #f1f1f1;
		padding-top: 10px;
	}

	.bottom-p {
		height: 37px;
		display: flex;
		justify-content: space-between;
		flex-wrap: nowrap;
	}

	.bottom-p view:nth-child(1) {
		width: 77%;
		margin-left: 10px;
		background-color: #ffffff;
		border-radius: 40px;
		padding: 7px 0 0 10px;
	}

	.bottom-p view:nth-child(2) {
		width: 20%;
		margin-left: 5px;
		font-size: 14px;
		text-align: center;
		height: 37px;
		margin-right: 10px;
		border-radius: 40px;
		color: #ffffff;
		background: #25b0f3;
		line-height: 37px;
	}

	.center {
		text-align: center;
		font-size: 12px;
		color: #aaa;
		margin-top: 10px;
		letter-spacing: 1px;
	}

	.left {
		display: flex;
		flex-wrap: nowrap;
		justify-content: flex-start;
		margin-left: 10px;
		margin-top: 10px;
	}

	.right {
		display: flex;
		flex-wrap: nowrap;
		justify-content: flex-end;
		margin-right: 10px;
		margin-top: 10px;
	}

	.langcon {
		border: 1px solid #333333;
		font-size: 14px;
		color: #414141;
		height: 30px;
		line-height: 30px;
		border-radius: 7px;
		margin-top: 5px;
		text-align: right;
		padding: 3px 10px 3px 10px;
		background: #f8f8f8;
	}

	/* 	.zs {
		font-size: 12px;
		letter-spacing: 1px;
		color: #AAAAAA;
		margin-top: 5px;
		text-align: center;
	} */

	/* 分类 */
	.classification {
		display: flex;
		flex-wrap: nowrap;
		justify-content: space-between;
		width: 90%;
		margin: 0 auto;
		padding-top: 10px;
	}

	.classification image {
		width: 21px;
		height: 21px;
	}
</style>
