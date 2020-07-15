<template>
	<view>
		<view class="con">
			<u-top-tips ref="uTips"></u-top-tips>
			<u-search placeholder='大家都在搜"风入松猫咖"' v-model="value" @search="search" :shape="round" :clearabled="true" :show-action="false"
			 :input-align="left" style="padding-left: 5%;padding-right: 5%;padding-top: 8px;">
			</u-search>
		</view>
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
						<view class="title">
							{{item.title}}
						</view>
						<view style="padding-top: 5px;">
							<u-row>
								<u-col span="3.4">
									<view class="star">
										{{item.star}}分
									</view>
								</u-col>
								<u-col span="8">
									<view class="star-comment">{{item.comment}}</view>
								</u-col>
							</u-row>
						</view>
						<view class="more-details">
							人均 ${{item.avg}}｜月售 {{item.month}}
						</view>
						<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
						<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
					</view>
				</template>
				<template v-slot:right="{rightList}">
					<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="details(item)">
						<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
						<!-- #ifndef MP-WEIXIN -->
						<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
						<!-- #endif -->
						<!-- #ifdef MP-WEIXIN -->
						<view class="demo-img-wrap">
							<image class="demo-image" :src="item.image" mode="widthFix"></image>
						</view>
						<!-- #endif -->
						<view class="title">
							{{item.title}}
						</view>
						<view style="padding-top: 5px;">
							<u-row>
								<u-col span="3.4">
									<view class="star">
										{{item.star}}分
									</view>
								</u-col>
								<u-col span="8">
									<view class="star-comment">{{item.comment}}</view>
								</u-col>
							</u-row>
						</view>
						<view class="more-details">
							人均 ${{item.avg}}｜月售 {{item.month}}
						</view>
						<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
						<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
					</view>
				</template>
			</u-waterfall>

		</scroll-view>

	</view>


</template>

<script>
	export default {
		data() {
			return {
				value: '',
				//瀑布流
				loadStatus: 'loadmore',
				flowList: [],
				list: [{
						title: '猫咖1',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖2',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖3',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖444444',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/04.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖5',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖666',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖7',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/07.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},
					{
						title: '猫咖888888',
						star: '4.7',
						image: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						comment: '挺不错的挺不错的',
						avg: '13',
						month: '183'
					},

				]

			}
		},
		onLoad() {
			this.addRandomData();
		},
		methods: {
			//搜索
			search(value) {
				this.$refs.uTips.show({
					title: '搜索内容为：' + value,
					type: 'success',
					duration: '1000'
				})
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
					url: "/pages/tabbar/tabbar-2/detalis2?text=" + item.title
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.con {
		text-align: center;
	}

	// 瀑布流
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

	.title {
		font-size: 16px;
		margin-top: 5px;
		font-weight: bolder;
		color: #1b1c1c;
	}

	.star {
		color: #ef7133;
		font-size: 15px;
		font-weight: bold;
	}

	.star-comment {
		font-size: 12px;

	}

	.more-details {
		padding-left: 2px;
		padding-top: 5px;
	}
</style>
