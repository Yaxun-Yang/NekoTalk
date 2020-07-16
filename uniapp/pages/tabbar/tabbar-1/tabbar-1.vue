<template>
	<view>
		<!-- 绝对定位的导航栏-->
		<view class="status_bar-fixed">

		</view>
		<!-- 占位 -->
		<view class="status_bar">

		</view>
		<!-- 自定义导航栏 -->
		<view class="header-dh flex-row-between" id="ssav">
			<view class=" padding-left" style="font-size: 38rpx;">
			</view>
			<text>NekoTalk</text>
			<view class=" padding-right" style="font-size: 35rpx;">

			</view>
		</view>

		<view class="flex-column-center" @touchstart="test.touchstart" @touchmove="test.touchmove" :change:prop="test.end"
		 :prop="propValue" @touchend="test.touchend" :data-top="scrollTop" id="refresh-container">

			<!-- 起始会隐藏在导航栏里 -->
			<view class="flex-row-center" style="height: 70rpx;width: 750rpx">
				<view class="cuIcon-loading1" id="lot" style="font-size: 35rpx;color: #00CDCD;">
				</view>
				<text class="my-neirong-sm padding-left" style="color: #9c9c9c;">{{refreshText}}</text>
			</view>

			<!-- 内容开始 -->


			<view class="wrap">
				<view class="con">
					<u-top-tips ref="uTips"></u-top-tips>
					<u-search placeholder='大家都在搜"可爱猫咪"' v-model="value" @search="search" shape="round" :clearabled="true" :show-action="false"
					 input-align="left" style="padding-left: 5%;padding-right: 5%;padding-top: 8px;background-color: #FFFFFF;">
					</u-search>
				</view>
				<view class="u-tabs-box">
					<u-tabs-swiper activeColor="#f29100" ref="tabs" :list="headList" :current="current" @change="change" :is-scroll="false"
					 swiperWidth="750"></u-tabs-swiper>
				</view>
				<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">

					<swiper-item class="swiper-item">
						<!-- @scrolltolower="reachBottom" -->
						<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;">
							<u-waterfall v-model="flowList" ref="uWaterfall">
								<template v-slot:left="{leftList}">
									<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.url" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.url" mode="widthFix"></image>
										</view>
										<!-- #endif -->
										<view class="demo-title">
											{{item.text}}
											<!-- ( id = {{item.momentId}} ) -->
											<!-- 	{{ getUser(item.phoneNumber).username }} -->
										</view>
										<view class="demo-tag">
											<view class="demo-tag-text" v-if="item.phoneNumber==='222'">已关注</view>
											<view style="background-color: white;width: 5px;"> </view>
											<view class="demo-tag-owner" v-if="item.originality==='N'">转发</view>
										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2" v-if="item.phoneNumber==='222'">
													<view>
														<!-- 		getUser(item.phoneNumber).avatar -->
														<u-avatar :src="user1.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="2" v-if="item.phoneNumber==='15243666630'">
													<view>
														<!-- 		getUser(item.phoneNumber).avatar -->
														<u-avatar :src="user2.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="8" v-if="item.phoneNumber==='222'">
													<!-- {{getUser(item.phoneNumber).username}} -->
													<view class="demo-flex">{{name1}}</view>
												</u-col>
												<u-col span="8" v-if="item.phoneNumber==='15243666630'">
													<!-- {{getUser(item.phoneNumber).username}} -->
													<view class="demo-flex">{{name2}}</view>
												</u-col>
												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30" v-model='userlike'></u-rate>
														<view style="padding-left: 5px;">

														</view>
													</view>
												</u-col>

											</u-row>
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
								<template v-slot:right="{rightList}">
									<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="details(item)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.url" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.url" mode="widthFix"></image>
										</view>
										<!-- #endif -->
										<view class="demo-title">
											{{item.text}} {{value1}}
											<!-- ( id = {{item.momentId}} )  -->
											<!-- 		{{getUser(item.phoneNumber).username}} -->
										</view>
										<view class="demo-tag">
											<view class="demo-tag-text" v-if="item.phoneNumber==='222'">已关注</view>
											<view style="background-color: white;width: 5px;"> </view>
											<view class="demo-tag-owner" v-if="item.originality==='N'">转发</view>
										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2" v-if="item.phoneNumber==='222'">
													<view>
														<!-- 		getUser(item.phoneNumber).avatar -->
														<u-avatar :src="user1.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="2" v-if="item.phoneNumber==='15243666630'">
													<view>
														<!-- 		getUser(item.phoneNumber).avatar -->
														<u-avatar :src="user2.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="9" v-if="item.phoneNumber==='222'">
													<!-- {{getUser(item.phoneNumber).username}} -->
													<view class="demo-flex">{{name1}}</view>
												</u-col>
												<u-col span="9" v-if="item.phoneNumber==='15243666630'">
													<!-- {{getUser(item.phoneNumber).username}} -->
													<view class="demo-flex">{{name2}}</view>
												</u-col>
												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
													</view>
												</u-col>
											</u-row>
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
							</u-waterfall>
							<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="addRandomData"></u-loadmore>
						</scroll-view>
					</swiper-item>
					<swiper-item class="swiper-item2">

						<view>
							<u-row>
								<u-col span="2">
									<u-avatar :src="user1.avatar" style="width:40px;height: 40px;"></u-avatar>
								</u-col>
								<u-col span="2">
									<u-avatar :src="user2.avatar" style="width:40px;height: 40px;"></u-avatar>
								</u-col>
							</u-row>
						</view>
						<u-divider half-width="200px"></u-divider>
						<view>
							<u-swiper :list="list0" :autoplay="false" img-mode="aspectFit" height="300"  :effect3d="true"></u-swiper>
						
							<view class="demo-shop">
								<u-row>
									<u-col span="1.5" >
										<view>
											<!-- 		getUser(item.phoneNumber).avatar -->
											<u-avatar :src="user2.avatar" style="width:35px;height: 35px;" @click="details2(idid)"></u-avatar>
										</view>
									</u-col>
									
									<u-col span="9" >
										<!-- {{getUser(item.phoneNumber).username}} -->
										<view class="demo-flex">{{name2}}</view>
									</u-col>
									
									<u-col span="1">
										<view class="">
											<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
										</view>
									</u-col>
								</u-row>
							</view>
						</view>
						<view>
							<u-swiper :list="list" :autoplay="false" img-mode="aspectFit" height="300"  :effect3d="true"></u-swiper>
						
							<view class="demo-shop">
								<u-row>
									<u-col span="1.5" >
										<view>
											<!-- 		getUser(item.phoneNumber).avatar -->
											<u-avatar :src="user1.avatar" style="width:35px;height: 35px;"></u-avatar>
										</view>
									</u-col>
									
									<u-col span="9" >
										<!-- {{getUser(item.phoneNumber).username}} -->
										<view class="demo-flex">{{name1}}</view>
									</u-col>
									
									<u-col span="1">
										<view class="">
											<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
										</view>
									</u-col>
								</u-row>
							</view>
						</view>
						<view>
							<u-swiper :list="list2" :autoplay="false" img-mode="aspectFit" height="300"  :effect3d="true"></u-swiper>
						
							<view class="demo-shop">
								<u-row>
									<u-col span="1.5" >
										<view>
											<!-- 		getUser(item.phoneNumber).avatar -->
											<u-avatar :src="user2.avatar" style="width:35px;height: 35px;"></u-avatar>
										</view>
									</u-col>
									
									<u-col span="9" >
										<!-- {{getUser(item.phoneNumber).username}} -->
										<view class="demo-flex">{{name2}}</view>
									</u-col>
									
									<u-col span="1">
										<view class="">
											<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
										</view>
									</u-col>
								</u-row>
							</view>
						</view>
						<view>
							<u-swiper :list="list3" :autoplay="false" img-mode="aspectFit" height="300"  :effect3d="true"></u-swiper>
						
							<view class="demo-shop">
								<u-row>
									<u-col span="1.5" >
										<view>
											<!-- 		getUser(item.phoneNumber).avatar -->
											<u-avatar :src="user2.avatar" style="width:35px;height: 35px;"></u-avatar>
										</view>
									</u-col>
									
									<u-col span="9" >
										<!-- {{getUser(item.phoneNumber).username}} -->
										<view class="demo-flex">{{name2}}</view>
									</u-col>
									
									<u-col span="1">
										<view class="">
											<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
										</view>
									</u-col>
								</u-row>
							</view>
						</view>
						
					</swiper-item>
				</swiper>
			</view>

			<!-- 内容结束 -->
		</view>
	</view>

</template>

<script module="test" lang="wxs">
	// 起始y的坐标
	var startY = 0
	// y轴移动距离
	var top = 0
	// 防止重复下拉刷新
	var code = 0
	// 防止重复向逻辑层交互
	var mit = 0

	function touchstart(event, ins) {
		if (code == 0) {
			mit = 0
			ins.callMethod('refresh', 2);
			var touch = event.touches[0] || event.changedTouches[0]
			startY = touch.pageY
		}
	}

	function end(newValue, oldValue, ownerInstance, instance) {
		// 收回
		ownerInstance.selectComponent('#refresh-container').setStyle({
			'transform': 'translateY(0)',
			'transition': 'ease 0.3s'
		})
		// 停止转圈
		ownerInstance.selectComponent('#lot').removeClass('turn-load')
		// 复原
		code = 0
	}

	function touchend(event, ins) {
		if (code == 0) {
			// 这里根据自己业务处理,小于40则不作操作
			if (top < 40) {
				ins.selectComponent('#refresh-container').setStyle({
					'transform': 'translateY(0)',
					'transition': 'ease 0.3s'
				})
			} else {
				// 改变提示文字
				ins.callMethod('refresh', 1);
				// 防止重复下拉
				code = 1
				ins.selectComponent('#refresh-container').setStyle({
					// 成功刷新回弹的距离
					'transform': 'translateY(60px)',
					'transition': 'ease 0.3s'
				})
				// 可以替换旋转速率
				// ins.selectComponent('#lot').setStyle({
				// 	'transform': 'rotate(0)',
				// 	'transition': 'ease 3s'
				// })
				ins.selectComponent('#lot').addClass('turn-load')
			}
		}
	}

	function touchmove(event, ins) {
		if (code == 0) {
			var touch = event.touches[0] || event.changedTouches[0]
			var pageY = touch.pageY
			var vew = ins.selectComponent('#refresh-container')
			var dataset = vew.getDataset();
			top = pageY - startY
			// 页面是否触底
			if (dataset.top == 0) {
				// 改变提示文字,且只会触发一次
				if (top > 60) {
					if (mit == 0) {
						ins.callMethod('refresh', 0);
					}
					mit = 1
				}
				vew.setStyle({
					'transform': 'translateY(' + (top) + 'px)'
				})
				ins.selectComponent('#lot').setStyle({
					'transform': 'rotate(' + (top * 6) + 'deg)'
				})
			} else {
				// 从长列表下拉上来 ,这里要实时更新起始位置
				startY = pageY
			}
		}
	}
	module.exports = {
		end: end,
		touchend: touchend,
		touchstart: touchstart,
		touchmove: touchmove
	}
</script>

<script>
	export default {

		data() {
			return {
				//点击
				userlike: 0,
				refreshText: "下拉刷新",
				scrollTop: 0,
				propValue: true,
				// // // // // // // //
				user: {},
				name1: 'chen',
				user1: {
					avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
				},
				name2: 'han',
				user2: {
					avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
				},
				follow: '',
				checkphone: '',
				value: '',
				headList: [{
						name: '发现'
					},
					{
						name: '关注'
					}
				],
				current: 0,
				swiperCurrent: 0,
				tabsHeight: 0,
				dx: 0,
				//瀑布流
				loadStatus: 'loadmore',
				flowList: [],
				value1: '',
				idid: 44,
				list0:[{
					image: 'http://qdeujgrtl.bkt.clouddn.com/100.JPG',
				}],
				list: [{
						image: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',

					},
					{
						image: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',

					},
					{
						image: 'http://qdeujgrtl.bkt.clouddn.com/04.JPG',

					},
				],
	list2: [{
						image: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',

					},
					{
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',

					},
					{
						image: 'http://qdeujgrtl.bkt.clouddn.com/07.JPG',

					},
				],
				list3: [{
									image: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
				
								},
								{
									image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
				
								},
								{
									image: 'http://qdeujgrtl.bkt.clouddn.com/07.JPG',
				
								},
							],
				url1: 'http://qdeujgrtl.bkt.clouddn.com/09.JPG',

			}
		},
		onPageScroll(e) {
			if (e.scrollTop < 1) {
				this.scrollTop = 0
			} else {
				// 提高性能
				if (this.scrollTop != 1) {
					this.scrollTop = 1
				}
			}
		},
		onLoad() {
			this.addRandomData();
		},
		computed: {
			userPhone() {
				// return sessionStorage.getItem('phoneNumber');
				return 15243666630;
			},
		},
		//触底加载
		onReachBottom() {
			this.loadStatus = 'loading';
			// 模拟数据加载
			setTimeout(() => {
				this.addRandomData();
				this.loadStatus = 'loadmore';
			}, 1000)
		},
		methods: {
			refresh(i) {
				if (i == 0) {
					this.refreshText = "松开刷新"
				} else if (i == 1) {
					// 这里写刷新业务
					// uni.redirectTo({
					// 	url: "/pages/tabbar/tabbar-1/new_file",
					// });
					this.addRandomData();
					this.refreshText = "正在刷新"
					setTimeout(() => {
						// 收回下拉刷新
						this.propValue = !this.propValue
					}, 1000)
				} else if (i == 2) {
					this.refreshText = "下拉刷新"
				}
			},
			//搜索
			search(value) {
				this.$refs.uTips.show({
					title: '搜索内容为：' + value,
					type: 'success',
					duration: '1000'
				})
			},
			// tab栏切换
			change(index) {
				this.swiperCurrent = index;
			},
			transition({
				detail: {
					dx
				}
			}) {
				this.$refs.tabs.setDx(dx);
			},
			animationfinish({
				detail: {
					current
				}
			}) {
				this.$refs.tabs.setFinishCurrent(current);
				this.swiperCurrent = current;
				this.current = current;
			},
			//跳转
			details(item) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/detalis?momentId=" + item.momentId
				})
			},
			details2(idid) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/fresh?momentId=" + idid
				})
			},
			//瀑布流
			addRandomData() {
				//详见官网：https://uniapp.dcloud.io/api/request/request
				uni.request({
					url: this.apiServer + '/moment/momentList',
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.flowList = res.data.data.momentList;
					}
				});
			},
			//喜欢
			change(id) {
				console.log('ccc')

				if (this.flag === true) {
					uni.request({
						url: this.apiServer + '/moment/favour',
						method: 'POST',
						data: {
							'phoneNumber': this.userPhone,
							'momentId': id,
						},
						header: {
							'content-type': 'application/json'
						},
						success: res => {

							console.log(res)
						}
					});
				}

			},
		}
	}
</script>


<style>
	/* #ifndef H5 */
	page {
		height: 100%;
		background-color: #f2f2f2;
	}

	/* #endif */
</style>

<style lang="scss" scoped>
	.con {
		text-align: center;
		background-color: #FFFFFF;
	}

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top));
		width: 100%;
		background-color: #FFFFFF;
		// background-color: #f2f2f2;
	}

	.swiper-box {
		flex: 1;
	}

	.swiper-item {
		height: 100%;
	}
	
	.swiper-item2 {
		height: 100%;

	}
	

	//瀑布流
	.demo-warter {
		border-radius: 8px;
		margin: 5px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}

	.u-close {
		position: absolute;
		top: 32rpx;
		right: 32rpx;
	}

	.demo-image {
		width: 100%;
		border-radius: 4px;
	}

	.demo-title {
		font-size: 30rpx;
		margin-top: 5px;
		color: $u-main-color;
	}

	.demo-tag {
		display: flex;
		margin-top: 5px;
	}

	.demo-tag-owner {
		background-color: $u-type-error;
		color: #FFFFFF;
		display: flex;
		align-items: center;
		padding: 4rpx 14rpx;
		border-radius: 50rpx;
		font-size: 20rpx;
		line-height: 1;

	}

	.demo-tag-text {
		border: 1px solid $u-type-primary;
		color: $u-type-primary;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 13rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 20rpx;
	}

	.demo-price {
		font-size: 30rpx;
		color: $u-type-error;
		margin-top: 5px;
	}

	.demo-shop {
		font-size: 15px;
		color: $u-tips-color;
		margin-top: 5px;

	}

	.demo-flex {
		display: flex;
		align-items: left;
		justify-content: center;
		flex-direction: column;
		padding-bottom: 2px;
		padding-left: 2px;

	}

	.order {
		width: 710rpx;
		background-color: #ffffff;
		margin: 20rpx auto;
		border-radius: 20rpx;
		box-sizing: border-box;
		padding: 20rpx;
		font-size: 28rpx;

		.top {
			display: flex;
			justify-content: space-between;

			.left {
				display: flex;
				align-items: center;

				.store {
					margin: 0 10rpx;
					font-size: 32rpx;
					font-weight: bold;
				}
			}

			.right {
				color: $u-type-warning-dark;
			}
		}

		.item {
			display: flex;
			margin: 20rpx 0 0;

			.left {
				margin-right: 20rpx;

				image {
					width: 200rpx;
					height: 200rpx;
					border-radius: 10rpx;
				}
			}

			.content {
				.title {
					font-size: 28rpx;
					line-height: 50rpx;
				}

				.type {
					margin: 10rpx 0;
					font-size: 24rpx;
					color: $u-tips-color;
				}

				.delivery-time {
					color: #e5d001;
					font-size: 24rpx;
				}
			}

			.right {
				margin-left: 10rpx;
				padding-top: 20rpx;
				text-align: right;

				.decimal {
					font-size: 24rpx;
					margin-top: 4rpx;
				}

				.number {
					color: $u-tips-color;
					font-size: 24rpx;
				}
			}
		}

		.total {
			margin-top: 20rpx;
			text-align: right;
			font-size: 24rpx;

			.total-price {
				font-size: 32rpx;
			}
		}

		.bottom {
			display: flex;
			margin-top: 40rpx;
			padding: 0 10rpx;
			justify-content: space-between;
			align-items: center;

			.btn {
				line-height: 52rpx;
				width: 160rpx;
				border-radius: 26rpx;
				border: 2rpx solid $u-border-color;
				font-size: 26rpx;
				text-align: center;
				color: $u-type-info-dark;
			}

			.evaluate {
				color: $u-type-warning-dark;
				border-color: $u-type-warning-dark;
			}
		}
	}

	.centre {
		text-align: center;
		margin: 200rpx auto;
		font-size: 32rpx;

		image {
			width: 164rpx;
			height: 164rpx;
			border-radius: 50%;
			margin-bottom: 20rpx;
		}

		.tips {
			font-size: 24rpx;
			color: #999999;
			margin-top: 20rpx;
		}

		.btn {
			margin: 80rpx auto;
			width: 200rpx;
			border-radius: 32rpx;
			line-height: 64rpx;
			color: #ffffff;
			font-size: 26rpx;
			background: linear-gradient(270deg, rgba(249, 116, 90, 1) 0%, rgba(255, 158, 1, 1) 100%);
		}
	}
</style>
