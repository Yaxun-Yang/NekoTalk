<template>
	<view>
		id = {{id}}
		<br>
		opposite-username = {{opposite}}
		<br>
		me<u-avatar :src="avatar" style="width:40px;height: 40px;"></u-avatar>
		opposite<u-avatar :src="oavatar" style="width:40px;height: 40px;"></u-avatar>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				opposite:'name1',
				oavatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
				avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
				con:[
					{
					me:"hihi",
					time: "05:02"
				},
				]
			
			}
		},
		onLoad(option) {
			console.log(option.id); //打印出上个页面传递的参数。
			this.id = option.id;	
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
	
	
		// onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
	
		//     // console.log(option.name); //打印出上个页面传递的参数。
		// },
	
	};
</script>

<style>
</style>
