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
									<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item.momentId)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.url" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->  
										<view class=""> 
											<image class="demo-image" :src="item.url" mode="widthFix"></image>
							 			</view>
										<!-- #endif --> 
										<view class="demo-title">
											{{item.text}}
										
										</view>
										<view class="demo-tag">
											<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
											<view style="background-color: white;width: 5px;"> </view>
											<view class="demo-tag-owner" v-if="item.originality==='N'">转发</view>
										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2">
													<view>
														<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="8">
													<view class="demo-flex">{{item.username}}</view>
												</u-col>
												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
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
									<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="details(item.momentId)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.url" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="">
											<image class="demo-image" :src="item.url" mode="widthFix"></image>
										</view>
										<!-- #endif -->
										<view class="demo-title">
											{{item.text}}
											
										</view>
										<view class="demo-tag">
											<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
											<view style="background-color: white;width: 5px;"> </view>
											<view class="demo-tag-owner" v-if="item.originality==='N'">转发</view>
										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2">
													<view>
														<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="8">
													<view class="demo-flex">{{item.username}}</view>
												</u-col>
												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
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
							</u-waterfall>
							<u-loadmore bg-color="rgb(240, 240, 240)" :status="loadStatus" @loadmore="addRandomData"></u-loadmore>
						</scroll-view>
					</swiper-item>
					<swiper-item class="swiper-item2">

						<view>
							<view class="wrap">

								<view class="u-menu-wrap">
									<scroll-view scroll-y scroll-with-animation class="u-tab-view menu-scroll-view" :scroll-top="scrollTop"
									 :scroll-into-view="itemId">
										<view v-for="(item,index) in tabbar" :key="index" class="u-tab-item" :class="[ccurrent == index ? 'u-tab-item-active' : '']"
										 @tap.stop="swichMenu(index)">
											<u-avatar :src="item.user.avatar"></u-avatar>
										</view>
									</scroll-view>
									<scroll-view :scroll-top="scrollRightTop" scroll-y scroll-with-animation class="right-box" @scroll="rightScroll">
										<view class="page-view">
											<view class="class-item" :id="'item' + index" v-for="(item , index) in tabbar" :key="index">
												<view class="item-title">
													<text>{{item.user.username}}</text>
												</view>
												<view class="item-container">
													<view class="" v-for="(momentList, index1) in item.momentList" :key="index1">
														<u-swiper :list="momentList.pictureList" name="url" :autoplay="false" img-mode="aspectFit" height="390"
														 @click="details(momentList.momentId)"></u-swiper>
														<view class="center">
															<text style="font-size: 15px;color: #000000;">
																{{momentList.text}} （id = {{momentList.momentId}}）
															</text>
															<text style="font-size: 15px;color: #808080;">

																{{momentList.momentTimeStamp}}
															</text>
															<br>
														</view>
													</view>
												</view>
											</view>
										</view>
									</scroll-view>
								</view>
						
							</view>

						</view>


					</swiper-item>
				</swiper>
			</view>

			<!-- 内容结束 -->
		</view>
	</view>

</template>

<script>
	export default {

		data() {
			return {
				testlist: [{
						url: 'https://cdn.uviewui.com/uview/swiper/1.jpg',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东'
					},
					{
						url: 'https://cdn.uviewui.com/uview/swiper/2.jpg',
						title: '身无彩凤双飞翼，心有灵犀一点通'
					},
					{
						url: 'https://cdn.uviewui.com/uview/swiper/3.jpg',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
					}
				],
				//大标签
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
				//刷新
				refreshText: "下拉刷新",
				scrollTop: 0,
				propValue: true,
				//搜索
				value: '',
				//瀑布流
				loadStatus: 'loadmore',
				flowList: [],
				//关注
				scrollTop: 0, //tab标题的滚动条位置
				oldScrollTop: 0,
				ccurrent: 0, // 预设当前项的值
				menuHeight: 0, // 左边菜单的高度
				menuItemHeight: 0, // 左边菜单item的高度
				itemId: '', // 栏目右边scroll-view用于滚动的id
				tabbar: [], //关注列表
				menuItemPos: [],
				arr: [],
				scrollRightTop: 0, // 右边栏目scroll-view的滚动条高度
				timer: null, // 定时器
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
			//关注
			this.getFollow();
			this.getMenuItemTop()
		},
		computed: {
			userPhone() {
				// return sessionStorage.getItem('phoneNumber');
				return 15243666630;
			},
		},
		methods: {
			//下拉刷新
			refresh(i) {
				if (i == 0) {
					this.refreshText = "松开刷新"
				} else if (i == 1) {
					// 这里写刷新业务
					// uni.redirectTo({
					// 	url: "/pages/tabbar/tabbar-1/new_file",
					// });
					// this.addRandomData();
					this.flowList = []
					this.refreshText = "正在刷新"
					uni.request({
						url: this.apiServer + '/moment/momentList',
						method: 'GET',
						header: {
							'content-type': 'application/x-www-form-urlencoded'
						},
						success: res => {
							this.flowList = res.data.data.momentList;
							// 收回下拉刷新
							this.propValue = !this.propValue
						}
					});
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
			//关注的小tab
			changee(index) {
				this.currentt = index;
			},
			// 大tab栏切换
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
			details(id) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/detalis?momentId=" + id
				})
			},

			//喜欢
			// change(id) {

			// 	if (this.flag === true) {
			// 		uni.request({
			// 			url: this.apiServer + '/moment/favour',
			// 			method: 'POST',
			// 			data: {
			// 				'phoneNumber': this.userPhone,
			// 				'momentId': id,
			// 			},
			// 			header: {
			// 				'content-type': 'application/json'
			// 			},
			// 			success: res => {

			// 				console.log(res)
			// 			}
			// 		});
			// 	}

			// },
			//瀑布流
			addRandomData() {
				//详见官网：https://uniapp.dcloud.io/api/request/request
				uni.request({
					url: this.apiServer + '/moment/momentList?phoneNumber=15243666630' ,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.flowList = res.data.data.momentList;

					}
				});
			},
			//得到关注的列表
			getFollow() {
				uni.request({
					url: this.apiServer + '/users/followingList?phoneNumber=15243666630' ,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.tabbar = res.data.data.followingList;

					}
				});
			},
			// 点击左边的栏目切换
			async swichMenu(index) {
				if (this.arr.length == 0) {
					await this.getMenuItemTop();
				}
				if (index == this.ccurrent) return;
				this.scrollRightTop = this.oldScrollTop;
				this.$nextTick(function() {
					this.scrollRightTop = this.arr[index];
					this.ccurrent = index;
					this.leftMenuStatus(index);
				})
			},
			// 获取一个目标元素的高度
			getElRect(elClass, dataVal) {
				new Promise((resolve, reject) => {
					const query = uni.createSelectorQuery().in(this);
					query.select('.' + elClass).fields({
						size: true
					}, res => {
						// 如果节点尚未生成，res值为null，循环调用执行
						if (!res) {
							setTimeout(() => {
								this.getElRect(elClass);
							}, 10);
							return;
						}
						this[dataVal] = res.height;
						resolve();
					}).exec();
				})
			},
			// 观测元素相交状态
			async observer() {
				this.tabbar.map((val, index) => {
					let observer = uni.createIntersectionObserver(this);
					// 检测右边scroll-view的id为itemxx的元素与right-box的相交状态
					// 如果跟.right-box底部相交，就动态设置左边栏目的活动状态
					observer.relativeTo('.right-box', {
						top: 0
					}).observe('#item' + index, res => {
						if (res.intersectionRatio > 0) {
							let id = res.id.substring(4);
							this.leftMenuStatus(id);
						}
					})
				})
			},  
			// 设置左边菜单的滚动状态
			async leftMenuStatus(index) {
				this.ccurrent = index;
				// 如果为0，意味着尚未初始化
				if (this.menuHeight == 0 || this.menuItemHeight == 0) {
					await this.getElRect('menu-scroll-view', 'menuHeight');
					await this.getElRect('u-tab-item', 'menuItemHeight');
				}
				// 将菜单活动item垂直居中
				this.scrollTop = index * this.menuItemHeight + this.menuItemHeight / 2 - this.menuHeight / 2;
			},
			// 获取右边菜单每个item到顶部的距离
			getMenuItemTop() {
				new Promise(resolve => {
					let selectorQuery = uni.createSelectorQuery();
					selectorQuery.selectAll('.class-item').boundingClientRect((rects) => {
						// 如果节点尚未生成，rects值为[](因为用selectAll，所以返回的是数组)，循环调用执行
						if (!rects.length) {
							setTimeout(() => {
								this.getMenuItemTop();
							}, 10);
							return;
						}
						rects.forEach((rect) => {
							// 这里减去rects[0].top，是因为第一项顶部可能不是贴到导航栏(比如有个搜索框的情况)
							this.arr.push(rect.top - rects[0].top);
							resolve();
						})
					}).exec()
				})
			},
			// 右边菜单滚动
			async rightScroll(e) {
				this.oldScrollTop = e.detail.scrollTop;
				if (this.arr.length == 0) {
					await this.getMenuItemTop();
				}
				if (this.timer) return;
				if (!this.menuHeight) {
					await this.getElRect('menu-scroll-view', 'menuHeight');
				}
				setTimeout(() => { // 节流
					this.timer = null;
					// scrollHeight为右边菜单垂直中点位置
					let scrollHeight = e.detail.scrollTop + this.menuHeight / 2;
					for (let i = 0; i < this.arr.length; i++) {
						let height1 = this.arr[i];
						let height2 = this.arr[i + 1];
						// 如果不存在height2，意味着数据循环已经到了最后一个，设置左边菜单为最后一项即可
						if (!height2 || scrollHeight >= height1 && scrollHeight < height2) {
							this.leftMenuStatus(i);
							return;
						}
					}
				}, 10)
			}
		}
	}
</script>


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

	// .u-wrap {
	// 	height: calc(100vh);
	// 	/* #ifdef H5 */
	// 	height: calc(100vh - var(--window-top));
	// 	/* #endif */
	// 	display: flex;
	// 	flex-direction: column;
	// }

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

	///
	// .u-wrap {
	// 	height: calc(100vh);
	// 	/* #ifdef H5 */
	// 	height: calc(100vh - var(--window-top));
	// 	/* #endif */
	// 	display: flex;
	// 	flex-direction: column;
	// }

	.u-search-box {
		padding: 18rpx 30rpx;
	}

	.u-menu-wrap {
		flex: 1;
		display: flex;
		overflow: hidden;
	}

	.u-search-inner {
		background-color: rgb(234, 234, 234);
		border-radius: 100rpx;
		display: flex;
		align-items: center;
		padding: 10rpx 16rpx;
	}

	.u-search-text {
		font-size: 26rpx;
		color: $u-tips-color;
		margin-left: 10rpx;
	}

	.u-tab-view {
		width: 200rpx;
		height: 100%;
	}

	.u-tab-item {
		height: 110rpx;
		background: #f6f6f6;
		box-sizing: border-box;
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 26rpx;
		color: #444;
		font-weight: 400;
		line-height: 1;
	}

	.u-tab-item-active {
		position: relative;
		color: #000;
		font-size: 30rpx;
		font-weight: 600;
		background: #fff;
	}

	.u-tab-item-active::before {
		content: "";
		position: absolute;
		border-left: 4px solid $u-type-primary;
		height: 32rpx;
		left: 0;
		top: 39rpx;
	}

	.u-tab-view {
		height: 100%;
	}

	.right-box {
		background-color: rgb(250, 250, 250);
	}

	.page-view {
		padding: 16rpx;
	}

	.class-item {
		margin-bottom: 30rpx;
		background-color: #fff;
		padding: 16rpx;
		border-radius: 8rpx;
	}

	.class-item:last-child {
		min-height: 100vh;
	}

	.item-title {
		font-size: 26rpx;
		color: $u-main-color;
		font-weight: bold;
	}

	.item-menu-name {
		font-weight: normal;
		font-size: 24rpx;
		color: $u-main-color;
	}

	.item-container {
		display: flex;
		flex-wrap: wrap;

	}

	.center {
		display: flex;

		flex-direction: column;

	}

	// .thumb-box {
	// 	width: 100%;
	// 	display: flex;
	// 	align-items: center;
	// 	justify-content: center;
	// 	flex-direction: column;
	// 	margin-top: 20rpx;
	// }

	.item-menu-image {
		width: 120rpx;
		height: 120rpx;
	}
</style>
