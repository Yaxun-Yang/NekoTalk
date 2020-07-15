<template>
	<view class="wrap">
  <u-button @click="submit()"></u-button>
  {{model}}
  {{num}}
  {{cou}}
  <!-- 提示 -->
  <u-top-tips ref="uTips"></u-top-tips>
	</view>
</template>

<script>
	export default {
		data() {
	
			return {
			num: '22',
			cou: '11',
				model: {
					phoneNumber: '222',
					text: 'yes',
					powerType: 1,
					address: '湖南省长沙市',
				},

			};
		},
		onLoad() {

		},
		computed: {
		
		},
		onReady() {
	
		},
		methods: {
			submit() {
	
			uni.request({
				url: this.apiServer + '/moment/originalityMoment' ,
				method: 'POST',
				data: this.model,
				header: {
					'content-type': 'application/json'
				},
				success: res => {
					console.log(res);
					// this.$refs.uTips.show({
					// 	title: '发送成功',
					// 	type: 'success',
					// 	duration: '1300'
					// })
					uni.request({
						
					})
				}
			});

},
		}
	};
</script>

<style scoped lang="scss">
	.wrap {
		padding: 30rpx;
		background-color: #FFFFFF;
	}


</style>
