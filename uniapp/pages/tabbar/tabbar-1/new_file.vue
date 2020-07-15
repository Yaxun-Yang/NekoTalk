<template>
	<view>
		<!-- 
	    "phoneNumber": "{{$randomPhoneNumber}}",
	    "username": "zhenzhen",
	    "password": "111",
	    "avatarPicture": "{{pricture}}",
	    "avatarPictureName":"test.jpg",
	    "sex": " female",
	    "sign": "sign ",
	    "lastAddress": "湖南省湘潭市" 
	 
	 -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				item: '',

			};
		},
		computed: {},
		onLoad() {
			// 模拟数据加载
			setTimeout(() => {
				uni.switchTab({
					url: "/pages/tabbar/tabbar-1/tabbar-1",
			
				})

			}, 100)

		},
		methods: {

		}
	}
</script>

<style>
</style>
