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
					success: function(e) {
						var page = getCurrentPages().pop();
						if (page == undefined || page == null) return;
						page.onLoad();
					}
				})

			}, 170)

		},
		methods: {

		}
	}
</script>

<style>
</style>
