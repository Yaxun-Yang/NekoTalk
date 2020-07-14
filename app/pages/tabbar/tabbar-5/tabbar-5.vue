<template>
	<view class="me">
		<u-row justify="around">
			<u-col span="2">
				<view class="">
					<u-avatar :src="user.avatar" style="width:65px;height: 65px;"></u-avatar>
				</view>
			</u-col>
			<u-col span="8">
				<view class="pad-left">
					<span class="star-head">{{user.username}}</span>
					<br>
					<span class="star-next">id: &nbsp;{{user.id}}</span>
				</view>
				<view span="11" class="pad-right">
					{{user.sex}}
				</view>
			</u-col>
			<u-col span="1">
				<view class="">
					<u-icon name="setting" color="#ffffff" size="38"></u-icon>
				</view>
			</u-col>
		</u-row>
		<!-- 个人简介 -->
		<view class="pad-leftt">
			<view class="pad">
				{{user.text}}
			</view>
			<!-- 三个 -->
			<view class="padd">
				<u-row justify="around" style="width: 160px;padding-left: 7px;">
					<u-col span="4">
						{{user.allFollowings}}
					</u-col>
					<u-col span="4">
						{{user.allFans}}
					</u-col>
					<u-col span="4">
						{{user.allStars}}
					</u-col>
				</u-row>
				<u-row justify="around" style="width: 160px;">
					<u-col span="4">
						关注
					</u-col>
					<u-col span="4">
						粉丝
					</u-col>
					<u-col span="4">
						获赞
					</u-col>
				</u-row>
			</view>
		</view>
		<!-- 点赞过的 -->
		<view class="">

	<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper activeColor="#f29100" ref="tabs" :list="headList" :current="current" @change="change" :is-scroll="false"
				 swiperWidth="750"></u-tabs-swiper>
			</view>
			<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;" @scrolltolower="reachBottom">
						<u-waterfall v-model="flowList" ref="uWaterfall">
							<template v-slot:left="{leftList}">
								<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
									<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
									<!-- #ifndef MP-WEIXIN -->
									<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
									<!-- #endif -->
									<!-- #ifdef MP-WEIXIN -->
									<view class="demo-img-wrap">
										<image class="demo-image" :src="item.image" mode="widthFix"></image>
									</view>
									<!-- #endif -->
									<view class="demo-title">
										{{item.title}}
									</view>
									<view class="demo-tag">
										<view style="background-color: white;">&nbsp;</view>
										<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
										<!-- 	<view class="demo-tag-text" v-if="item.follow=false">关注</view> -->

									</view>
									<view class="demo-shop">
										<u-row>
											<u-col span="2">
												<view>
													<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
												</view>
											</u-col>
											<u-col span="9">
												<view class="demo-flex">{{item.shop}}</view>
											</u-col>
											<u-col span="1">
												<view class="">
													<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30" ></u-rate>
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
									<!-- #ifndef MP-WEIXIN -->
									<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
									<!-- #endif -->
									<!-- #ifdef MP-WEIXIN -->
									<view class="demo-img-wrap">
										<image class="demo-image" :src="item.image" mode="widthFix"></image>
									</view>
									<!-- #endif -->

									<view class="demo-title">
										{{item.title}}
									</view>
									<view class="demo-tag">
										<view class="demo-tag-owner">
											转发
										</view>
									</view>
									<view class="demo-shop">
										{{item.shop}}
									</view>
									<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
									<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
								</view>
							</template>
						</u-waterfall>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;" @scrolltolower="reachBottom">
						<u-waterfall v-model="flowList" ref="uWaterfall">
							<template v-slot:left="{leftList}">
								<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
									<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
									<!-- #ifndef MP-WEIXIN -->
									<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
									<!-- #endif -->
									<!-- #ifdef MP-WEIXIN -->
									<view class="demo-img-wrap">
										<image class="demo-image" :src="item.image" mode="widthFix"></image>
									</view>
									<!-- #endif -->
									<view class="demo-title">
										{{item.title}}
									</view>
									<view class="demo-tag">
										<view style="background-color: white;">&nbsp;</view>
										<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
										<!-- 	<view class="demo-tag-text" v-if="item.follow=false">关注</view> -->

									</view>
									<view class="demo-shop">
										<u-row>
											<u-col span="2">
												<view>
													<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
												</view>
											</u-col>
											<u-col span="9">
												<view class="demo-flex">{{item.shop}}</view>
											</u-col>
											
											<u-col span="1">
												<view class="">
													<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30" ></u-rate>
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
									<!-- #ifndef MP-WEIXIN -->
									<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
									<!-- #endif -->
									<!-- #ifdef MP-WEIXIN -->
									<view class="demo-img-wrap">
										<image class="demo-image" :src="item.image" mode="widthFix"></image>
									</view>
									<!-- #endif -->

									<view class="demo-title">
										{{item.title}}
									</view>
									<view class="demo-tag">
										<view class="demo-tag-owner">
											转发
										</view>
									</view>
									<view class="demo-shop">
										{{item.shop}}
									</view>
									<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
									<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
								</view>
							</template>
						</u-waterfall>
					</scroll-view>
				</swiper-item>
				<swiper-item class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;" @scrolltolower="reachBottom">
						<u-waterfall v-model="flowList" ref="uWaterfall">
							<template v-slot:left="{leftList}">
								<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
									<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
									<!-- #ifndef MP-WEIXIN -->
									<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
									<!-- #endif -->
									<!-- #ifdef MP-WEIXIN -->
									<view class="demo-img-wrap">
										<image class="demo-image" :src="item.image" mode="widthFix"></image>
									</view>
									<!-- #endif -->
									<view class="demo-title">
										{{item.title}}
									</view>
									<view class="demo-tag">
										<view style="background-color: white;">&nbsp;</view>
										<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
										<!-- 	<view class="demo-tag-text" v-if="item.follow=false">关注</view> -->
				
									</view>
									<view class="demo-shop">
										<u-row>
											<u-col span="2">
												<view>
													<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
												</view>
											</u-col>
											<u-col span="9">
												<view class="demo-flex">{{item.shop}}</view>
											</u-col>
											<u-col span="1">
												<view class="">
													<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30" ></u-rate>
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
									<!-- #ifndef MP-WEIXIN -->
									<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
									<!-- #endif -->
									<!-- #ifdef MP-WEIXIN -->
									<view class="demo-img-wrap">
										<image class="demo-image" :src="item.image" mode="widthFix"></image>
									</view>
									<!-- #endif -->
				
									<view class="demo-title">
										{{item.title}}
									</view>
									<view class="demo-tag">
										<view class="demo-tag-owner">
											转发
										</view>
									</view>
									<view class="demo-shop">
										{{item.shop}}
									</view>
									<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
									<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
								</view>
							</template>
						</u-waterfall>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
	
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				user: {
					id: '13093645456',
					avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
					sex: "女",
					username: '小饼干',
					text: "这是个人简介",
					allStars: 12,
					allFollowings: 11,
					allFans: 15,
					},
				// 哈哈哈哈
				value: '',
				headList: [{
						name: '浏览过'
					},
					{
						name: '评论过'
					},
					{
						name: '赞过'
					}
				],
				current: 0,
				swiperCurrent: 0,
				tabsHeight: 0,
				dx: 0,
				//瀑布流
				loadStatus: 'loadmore',
				flowList: [],
				list: [{
						title: '北国风光，千里冰封，万里雪飘',
						shop: 'guanzhu4',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						follow: 'Y',
					},
					{
						title: '望长城内外，惟余莽莽',
						shop: 'guanzhu3',
						image: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/09.JPG',
						follow: 'Y',
					},
					{
						title: '大河上下，顿失滔滔',
						shop: 'guanzhu2',
						image: 'http://qdeujgrtl.bkt.clouddn.com/07.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						follow: 'Y',
					},
					{
						title: '欲与天公试比高',
						shop: 'guanzhu',
						image: 'http://qdeujgrtl.bkt.clouddn.com/11.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/11.JPG',
						follow: 'Y',
					},
					{
						title: '北国风光，千里冰封，万里雪飘',
						shop: 'nooooo',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						follow: 'N',
					},
					{
						title: '江山如此多娇，引无数英雄竞折腰',
						shop: 'no2',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						follow: 'N',
					},
					{
						shop: 'nono22222222',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						title: '惜秦皇汉武，略输文采',
						follow: 'N',
					},
					{
						title: '唐宗宋祖，稍逊风骚',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						shop: 'nono4333333',
						follow: 'N',
					},
					{
						title: '一代天骄，成吉思汗',
						shop: 'nono555555',
						image: 'http://qdeujgrtl.bkt.clouddn.com/07.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						follow: 'N',
					},
					{
						title: '只识弯弓射大雕',
						shop: 'nono566666',

						image: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/09.JPG',
						follow: 'N',
					},
					{
						image: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						title: '俱往矣，数风流人物，还看今朝',
						shop: 'nono77777',
						follow: 'N',
					},
				]
			};
		},
		onLoad() {
			this.addRandomData();
		},
		computed: {

		},
		onReachBottom() {
			this.loadStatus = 'loading';
			// 模拟数据加载
			setTimeout(() => {
				this.addRandomData();
				this.loadStatus = 'loadmore';
			}, 1000)
		},
		methods: {
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
			//瀑布流
			addRandomData() {
				for (let i = 0; i < 10; i++) {
					let index = this.$u.random(0, this.list.length - 1);
					// 先转成字符串再转成对象，避免数组对象引用导致数据混乱
					let item = JSON.parse(JSON.stringify(this.list[index]))
					item.id = this.$u.guid();
					this.flowList.push(item);
				}
			},
			//跳转
			details(item) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/detalis?text=" + item.title + "&image=" + item.image + "&shop=" + item.shop +
						"&avatar=" + item.avatar + "&follow=" + item.follow
				})
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
	.me {
		background-color: #969a9e;
		color: #FFFFFF;
	}
	
	.pad-left {
		padding-left: 5px;
	}
	
	.pad-leftt {
		padding-left: 15px;
	}
	
	.star-head {
		font-size: 17px;
		font-weight: bolder;
	}
	
	.star-next {
		padding-right: 5px;
		font-size: 13px;
		font-weight: 500;
	}
	
	.pad {
		padding-top: 5px;
	}
	
	.padd {
		padding-top: 10px;
		font-weight: bold;
	}
	
	.pad-right {
		padding-right: 1px;
	
	}
	//i11111
	.con {
		text-align: center;
	}

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top));
		width: 100%;
	}

	.swiper-box {
		flex: 1;
	}

	.swiper-item {
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
		padding: 4rpx 14rpx;
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
