<template>
	<view class="wrap">
		<u-top-tips ref="uTips"></u-top-tips>
		<view class="key-input">
			<view class="title" @click="openinfo(item)">输入验证码</view>
			<view class="tips">验证码已发送至 +152****6630</view>
			<u-message-input :focus="true" :value="value" @change="change" @finish="finish" mode="bottomLine" :maxlength="maxlength"
			 style="width: 330px;"></u-message-input>
			<text :class="{ error: error }">验证码错误，请重新输入</text>
			<view class="captcha">
				<text :class="{ noCaptcha: show }" @tap="noCaptcha">收不到验证码点这里</text>
				<text :class="{ regain: !show }">{{ second }}秒后重新获取验证码</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				maxlength: 6,
				value: '',
				second: 60,
				show: false,
				error: false,
				item:'',
				tel: '',
			};
		},
		computed: {},
		onLoad(option ) {
			// this.getCaptcha()
			this.tel = option.tel
			let interval = setInterval(() => {
				this.second--;
				if (this.second <= 0) {
					this.show = true;
					if (this.value.lenth != 4) {
						this.error = true;
					}
					clearInterval(interval);
				}
			}, 1000);
		},
		methods: {
			// 收不到验证码选择时的选择
			noCaptcha() {
				uni.showActionSheet({
					itemList: ['重新获取验证码', '接听语音验证码'],
					success: function(res) {

					},
					fail: function(res) {

					}
				});
			},
			// change事件侦听
			change(value) {
				// console.log('change', value);
			},
			//输入完验证码最后一位执行
			finish(value) {
				uni.request({
				
					url: this.apiServer + '/users/token',
					method: 'POST',
					data: {
					phoneNumber: this.tel,
					verifyCode: value
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						// this.$refs.uTips.show({
						// 	title: '发送成功',
						// 	type: 'success',
						// 	duration: '1300'
						// })
						console.log('hi');
						uni.switchTab({
							url: "/pages/tabbar/tabbar-1/tabbar-1?item=" + value,
									
						});
					}
				});
				  
				
			},
			openinfo(item) {
				console.log('hi');
				uni.switchTab({
					url: "/pages/tabbar/tabbar-1/tabbar-1?item=" + item,
			
				});
			},
		}
	};
</script>

<style lang="scss" scoped>
	.wrap {
		padding: 80rpx;
	}

	.box {
		margin: 30rpx 0;
		font-size: 30rpx;
		color: 555;
	}

	.key-input {
		padding: 10rpx 0;

		text {
			display: none;
		}

		.error {
			display: block;
			color: red;
			font-size: 30rpx;
			margin: 20rpx 0;
		}
	}

	.title {
		font-size: 50rpx;
		color: #333;
	}

	.key-input .tips {
		font-size: 30rpx;
		color: #333;
		margin-top: 20rpx;
		margin-bottom: 60rpx;
	}

	.captcha {
		color: $u-type-warning;
		font-size: 30rpx;
		margin-top: 40rpx;

		.noCaptcha {
			display: block;
		}

		.regain {
			display: block;
		}
	}
</style>
