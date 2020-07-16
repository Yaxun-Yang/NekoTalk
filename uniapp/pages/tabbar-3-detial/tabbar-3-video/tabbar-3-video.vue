<template>
	<view class="wrap">
		<!-- 提示 -->
		<u-top-tips ref="uTips"></u-top-tips>
		<!-- 表 -->
		<u-form :model="model" :rules="rules" ref="uForm" :errorType="['message']">
			<u-form-item label-position="left" prop="pictureName" label-width="0">
				<u-upload width="160" ref="uUpload" @on-success="onSuccess" :action="action" max-count="9" ></u-upload>
			
			</u-form-item>
			<u-form-item label-position="left" prop="text" label-width="0">
				<u-input type="textarea" :border="false" placeholder="在这里输入想说的话" v-model="model.text" />
			</u-form-item>
			<u-form-item label-position="left" label="参与话题" prop="description" label-width="150">
				<u-input :border="false" placeholder="参与话题让更多人看到" v-model="model.description" type="text"></u-input>
			</u-form-item>
			<u-form-item label-position="left" label="选择权限" prop="powerType" label-width="150">
				<u-input :border="false" type="select" :select-open="actionSheetShow" v-model="model.powerType" placeholder="选择动态权限"
				 @click="actionSheetShow = true"></u-input>
			</u-form-item>
			<u-form-item label-position="left" label="添加地点" prop="address" label-width="150">
				<u-input :border="false" type="select" :select-open="pickerShow" v-model="model.address" placeholder="选择地点" @click="pickerShow = true"></u-input>
			</u-form-item>
			{{this.model.address}}
		</u-form>
		<br><br><br><br><br><br><br>
		<u-button @click="submit" type="error">发送</u-button>
		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
		<!-- aa -->
		<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
		<u-picker mode="address" v-model="pickerShow" @confirm="addressConfirm"></u-picker>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				actionSheetShow: false,
				pickerShow: false,
				//
				action: this.apiServer + '/moment/test',
				//表
				model: {
					description: '',
					text: '',
					phoneNumber: '',
					powerType: 1,
					address: '',
					pictureList:[],
					// base 64
					//picture: '',
					//路径
					//pictureName: '',

				},
				rules: {
					text: [{
							required: true,
							message: '请填写简介'
						},
						{
							min: 2,
							message: '简介不能少于2个字',
							trigger: 'change',
						}
					],
					powerType: [{
						required: true,
						message: '请选择权限'
					}],
				},
				actionSheetList: [{
						text: '1：所有人可见'
					},
					{
						text: '2：关注可见'
					},
					{
						text: '3：指定可见'
					},
					{
						text: '4：指定不可见'
					},
					{
						text: '5：仅本人可见'
					}
				],

			};
		},
		onLoad(option) {
			console.log(option.phoneNumber)
			this.model.phoneNumber = option.phoneNumber;
		},
		computed: {
			// phoneNumber() {
			// 	// return sessionStorage.getItem('phoneNumber');
			// 	return 15273299601;
			// },
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		methods: {
			 onSuccess(data, index, list) {
			                this.model.pictureList.push(data);
			            },


			submit() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						uni.request({
							url: this.apiServer + '/moment/originalityMoment',
							method: 'POST',
							data: this.model,
							header: {
								'content-type': 'application/json'
							},
							success: res => {
								var id = res.data.data.momentId
								console.log('id' + id)
								uni.request({
									url: this.apiServer + '/moment/label',
									method: 'POST',
									data: this.model,
									header: {
										'content-type': 'application/json'
									},
									success: res => {

										console.log(res)
										console.log('iiiid ' + id)
										uni.request({
											url: this.apiServer + '/moment/labelInclude',
											method: 'POST',
											data: {
												momentId: id,
												description: this.model.description,
											},
											header: {
												'content-type': 'application/json'
											},
											success: res => {
												console.log('pic')
												console.log(res)
												uni.request({
													url: this.apiServer + '/moment/momentPicture?momentId=' + id,
													method: 'POST',
													data: {
														pictureList: this.model.pictureList,
													},
													header: {
														'content-type': 'application/json'
													},
													success: res => {
														
														
														
														
														this.$refs.uTips.show({
															title: '发送成功',
															type: 'success',
															duration: '1300'
														})
														uni.switchTab({
																		url: "/pages/tabbar/tabbar-1/tabbar-1",
																		})
													}

												})
											}
										})

									}
								});
								console.log('验证通过');
							},
						})
					} else {
						console.log('验证失败');
					}
				});
			},
			// 点击actionSheet回调 权限
			actionSheetCallback(index) {
				uni.hideKeyboard();
				this.model.powerType = index + 1;
			},
			// 选择地区回调
			addressConfirm(e) {
				this.model.address = e.province.label + '-' + e.city.label + '-' + e.area.label;
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
