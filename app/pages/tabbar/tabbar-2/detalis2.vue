<template>
	<view>
		<u-top-tips ref="uTips"></u-top-tips>
		<!-- 轮播 -->
		<u-swiper :list="shop.imageList" :autoplay="false" img-mode="aspectFit" height="530"></u-swiper>
		<view style="padding-left: 20px;">
			<!-- 猫咖名字 -->
			<view class="shop">
				{{shop.name}}
			</view>
			<view class="rate">
				<u-rate count="count" v-model="shop.value" active-color="#FA3534" inactive-color="#b2b2b2" gutter="10" :disabled="true"></u-rate>
				<span style="padding-left: 5px;color: #ef7133;">{{shop.star}}分</span>
			</view>
			<!-- 	<u-line color="#555656" margin="30rpx" length="90%"/> -->
			<view class="time">
				{{shop.open}}&nbsp;{{shop.time}}
			</view>
			<view class="phone">
				{{shop.phone}}
				<view class="add">
					{{shop.address}}
				</view>
			</view>
			<view class="circle" >
			</view>
			<view class="call" @click="call(shop)">
				<u-icon name="map-fill"></u-icon>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				count: 4,
				shop: {
					name: '猫咖111',
					value: 4,
					star: '4.2',
					open: '营业中',
					time: '周一至周日10:00-21:00',
					phone: '15243766666',
					address: '江苏省南通市崇川区启秀路19号正西方向83米',
					imageList: [{
							image: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG'
						},
						{
							image: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG'
						},
						{
							image: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG'
						},
						{
							image: 'http://qdeujgrtl.bkt.clouddn.com/04.JPG'
						}
					],
				},
			}
		},
		onLoad(option) {
			console.log(option.title); //打印出上个页面传递的参数。
			// if (option.follow === 'Y') {
			// 	this.follow = true;
			// } else {
			// 	this.follow = false;
			// }


		},
		computed: {
			// this.getList();
		},
		computed: {

		},
		methods: {
			//打电话
			call(shop) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-2/address?name=" + shop.name 
				})
			}
			// getList(){
			// 	this.id = option.id;
			// }
		}


		// onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数

		//     // console.log(option.name); //打印出上个页面传递的参数。
		// },

	};
</script>


<style lang="scss" scoped>
	.shop {
		padding-top: 20px;
		font-size: 20px;
		font-weight: bold;
	}

	.rate {
		padding-top: 6px;
		font-size: 15px;
		font-weight: bold;
	}

	.time {
		padding-top: 20px;
		font-size: 15px;
		font-weight: bold;
	}

	.phone {
		padding-top: 20px;
		font-size: 15px;
		font-weight: bold;
	}

	.add {
		padding-top: 10px;
		color: #808386;
	}

	.circle {
		width: 50px;
		height: 50px;
		background: #f07020;
		border-radius: 50px;
		position: relative;
		top: -60px;
		left: 320px;

	}

	.call {
		font-size: 30px;
		position: relative;
		top: -106px;
		left: 330px;
		color: #FFFFFF;
	}
</style>
