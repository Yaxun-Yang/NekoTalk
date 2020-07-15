<!-- <template>
	<view>
		<u-button @click="submit"></u-button>

	</view>

</template>

<script>
	import {
		pathToBase64,
		base64ToPath
	} from '../../../js_sdk/image-tools/index.js'


	export default {
		data() {
			// 演示地址，请勿直接使用
			action: 'http://www.example.com/upload',
			fileList: [{
				url: 'http://pics.sc.chinaz.com/files/pic/pic9/201912/hpic1886.jpg',
			}]
		}
		methods: {

			submit() {
				uni.chooseImage({
					sourceType: ["camera", "album"],
					sizeType: "compressed",
					count: 1,
					success: (res) => {
						pathToBase64(res.tempFilePaths[0])
							.then(base64 => {
								console.log(base64);
							})
							.catch(error => {
								console.error(error);
							});
					}
				});

			}
		},

	}
</script>



<style lang="scss" scoped>

</style>
 -->