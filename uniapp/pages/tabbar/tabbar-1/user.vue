<template>

		<view>
		{{userPhone}}
	
	</view>
</template>

<script>

	export default {
		data() {
			return {
				userPhone: '',
	
			};
		},
		onLoad(option) {
			console.log(option.phoneNumber)
			this.userPhone = option.phoneNumber;
		
		},
		computed: {
		
		},
	
		methods: {

		

		}
	}
</script>





<style lang="scss" scoped>

</style>
