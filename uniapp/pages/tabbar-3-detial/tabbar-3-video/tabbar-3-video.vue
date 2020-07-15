<template>
	<view>
			<u-upload    :action="action" ></u-upload>

	</view>

</template>

<script>
	export default {
		data() {
		return{
			action: 'jdbc:mysql://47.106.91.85:3306/neko?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF8'
		}

		},
		methods:{
	
		}
	}
</script>