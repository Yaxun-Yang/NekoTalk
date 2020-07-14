<template>
	<view class="add">

<u-image width="100%" height="600px" :src="url"></u-image>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				name: '这里应该有一个地图',
				url: 'http://qdeujgrtl.bkt.clouddn.com/add.jpg'
			}
		},
		onLoad(option) {
			console.log(option.name); //打印出上个页面传递的参数。
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

			// getList(){
			// 	this.id = option.id;
			// }
		}


	};
</script>


<style lang="scss" scoped>

.add{
width: 100%;
height: 100%;
}
</style>
