<template>
	<view class="me">
		<u-row justify="around">
			<u-col span="2">
				<view class="">
					<u-avatar :src="user.avatar" style="width:65px;height: 65px;"></u-avatar>
				</view>
			</u-col>
			<u-col span="8">
				<view class="pad-left">
					<span class="star-head">{{user.username}}</span>
					<br>
					<span class="star-next">id: &nbsp;{{user.phoneNumber}}</span>
				</view>
				<view class="pad-right">
					{{user.sex}}
				</view>
			</u-col>
			<u-col span="1">
				<view class="set">
					<u-popup v-model="show">
						<view class="userForm">
							<u-form :model="model" :rules="rules" ref="uForm" :errorType="['message']">
								<u-form-item :leftIconStyle="{color: '#888', fontSize: '32rpx'}" label-width="120" :label-position="labelPosition"
								 label="昵称" prop="name">
									<u-input :border="border" placeholder="请输入昵称" v-model="model.name" type="text"></u-input>
								</u-form-item>
								<u-form-item :label-position="labelPosition" label="性别" prop="sex">
									<u-input :border="border" type="select" :select-open="actionSheetShow" v-model="model.sex" placeholder="请选择性别"
									 @click="actionSheetShow = true"></u-input>
								</u-form-item>
								<u-form-item :label-position="labelPosition" label="简介" prop="intro">
									<u-input type="textarea" :border="border" placeholder="请填写简介" v-model="model.intro" />
								</u-form-item>
								<u-form-item :label-position="labelPosition" label="密码" prop="password">
									<u-input :password-icon="true" :border="border" type="password" v-model="model.password" placeholder="请输入密码"></u-input>
								</u-form-item>
								<u-form-item :label-position="labelPosition" label="确认密码" label-width="150" prop="rePassword">
									<u-input :border="border" type="password" v-model="model.rePassword" placeholder="请确认密码"></u-input>
								</u-form-item>

								<u-form-item :label-position="labelPosition" label="所在地区" prop="region" label-width="150">
									<u-input :border="border" type="select" :select-open="pickerShow" v-model="model.region" placeholder="请选择地区"
									 @click="pickerShow = true"></u-input>
								</u-form-item>

								<u-form-item :rightIconStyle="{color: '#888', fontSize: '32rpx'}" right-icon="kefu-ermai" :label-position="labelPosition"
								 label="手机号码" prop="phone" label-width="150">
									<u-input :border="border" placeholder="请输入手机号" v-model="model.phone" type="number"></u-input>
								</u-form-item>
								<u-form-item :label-position="labelPosition" label="验证码" prop="code" label-width="150">
									<u-input :border="border" placeholder="请输入验证码" v-model="model.code" type="text"></u-input>
									<u-button slot="right" type="success" size="mini" @click="getCode">{{codeTips}}</u-button>
								</u-form-item>

								<u-form-item :label-position="labelPosition" label="上传图片" prop="photo" label-width="150">
									<u-button @click="getPhoto()"></u-button>
								</u-form-item>
							</u-form>

							<u-button @click="submit">提交</u-button>
							<u-action-sheet :list="actionSheetList" v-model="actionSheetShow" @click="actionSheetCallback"></u-action-sheet>
							<u-select mode="single-column" :list="selectList" v-model="selectShow" @confirm="selectConfirm"></u-select>
							<u-picker mode="region" v-model="pickerShow" @confirm="regionConfirm"></u-picker>
							<u-verification-code seconds="60" ref="uCode" @change="codeChange"></u-verification-code>
							<br><br><br><br><br><br>

							<view class="u-config-wrap">
								<view class="u-config-title u-border-bottom">
									参数配置
								</view>
								<view class="u-config-item">
									<view class="u-item-title">label对齐方式</view>
									<u-subsection vibrateShort :list="['左边', '上方']" @change="labelPositionChange"></u-subsection>
								</view>
								<view class="u-config-item">
									<view class="u-item-title">边框</view>
									<u-subsection vibrateShort :current="borderCurrent" :list="['显示', '隐藏']" @change="borderChange"></u-subsection>
								</view>
								<view class="u-config-item">
									<view class="u-item-title">radio、checkbox样式</view>
									<u-subsection vibrateShort :list="['自适应', '换行', '50%宽度']" @change="radioCheckboxChange"></u-subsection>
								</view>
								<view class="u-config-item">
									<view class="u-item-title">错误提示方式</view>
									<u-subsection vibrateShort :list="['message', 'toast', '下划线', '输入框']" @change="errorChange"></u-subsection>
								</view>
							</view>
						</view>
					</u-popup>
					<u-button @click="show = true">
						<u-icon name="setting" color="#3c3d3d" size="38"></u-icon>
					</u-button>
				</view>
			</u-col>
		</u-row>
		<!-- 个人简介 -->
		<view class="pad-leftt">
			<view class="pad">
				{{user.sign}}
				<br>我住在{{user.lastAddress}}
			</view>
			<!-- 三个 -->
			<view class="padd">
				<u-row justify="around" style="width: 160px;padding-left: 7px;">
					<u-col span="4">
						<view @click="allFollowing()">
							{{allFollowings}}
						</view>
					</u-col>
					<u-col span="4">
						<view @click="allFan()">
							{{allFans}}
						</view>
					</u-col>
					<u-col span="4">
						<view @click="allStar()">
							{{allStars}}
						</view>

					</u-col>
				</u-row>
				<u-row justify="around" style="width: 160px;">
					<u-col span="4">
						关注
					</u-col>
					<u-col span="4">
						粉丝
					</u-col>
					<u-col span="4">
						获赞
					</u-col>
				</u-row>
			</view>
		</view>
		<!-- 点赞过的 -->
		<view class="">
			<br>
			<view class="wrap">
				<view class="u-tabs-box">
					<u-tabs-swiper activeColor="#f29100" ref="tabs" :list="headList" :current="current" @change="change" :is-scroll="false"
					 swiperWidth="750"></u-tabs-swiper>
				</view>
				<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
					<swiper-item class="swiper-item">
						<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;" @scrolltolower="reachBottom">
							<u-waterfall v-model="flowList" ref="uWaterfall">
								<template v-slot:left="{leftList}">
									<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.image" mode="widthFix"></image>
										</view>
										<!-- #endif -->
										<view class="demo-title">
											{{item.title}}
										</view>
										<view class="demo-tag">
											<view style="background-color: white;">&nbsp;</view>
											<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
											<!-- 	<view class="demo-tag-text" v-if="item.follow=false">关注</view> -->

										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2">
													<view>
														<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="9">
													<view class="demo-flex">{{item.shop}}</view>
												</u-col>
												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
													</view>
												</u-col>

											</u-row>
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
								<template v-slot:right="{rightList}">
									<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="details(item)">
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.image" mode="widthFix"></image>
										</view>
										<!-- #endif -->

										<view class="demo-title">
											{{item.title}}
										</view>
										<view class="demo-tag">
											<view class="demo-tag-owner">
												转发
											</view>
										</view>
										<view class="demo-shop">
											{{item.shop}}
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
							</u-waterfall>
						</scroll-view>
					</swiper-item>
					<swiper-item class="swiper-item">
						<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;" @scrolltolower="reachBottom">
							<u-waterfall v-model="flowList" ref="uWaterfall">
								<template v-slot:left="{leftList}">
									<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.image" mode="widthFix"></image>
										</view>
										<!-- #endif -->
										<view class="demo-title">
											{{item.title}}
										</view>
										<view class="demo-tag">
											<view style="background-color: white;">&nbsp;</view>
											<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
											<!-- 	<view class="demo-tag-text" v-if="item.follow=false">关注</view> -->

										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2">
													<view>
														<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="9">
													<view class="demo-flex">{{item.shop}}</view>
												</u-col>

												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
													</view>
												</u-col>
											</u-row>

										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
								<template v-slot:right="{rightList}">
									<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="details(item)">
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.image" mode="widthFix"></image>
										</view>
										<!-- #endif -->

										<view class="demo-title">
											{{item.title}}
										</view>
										<view class="demo-tag">
											<view class="demo-tag-owner">
												转发
											</view>
										</view>
										<view class="demo-shop">
											{{item.shop}}
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
							</u-waterfall>
						</scroll-view>
					</swiper-item>
					<swiper-item class="swiper-item">
						<scroll-view scroll-y style="height: 100%;width: 100%;background-color: #FFFFFF;" @scrolltolower="reachBottom">
							<u-waterfall v-model="flowList" ref="uWaterfall">
								<template v-slot:left="{leftList}">
									<view class="demo-warter" v-for="(item, index) in leftList" :key="index" @click="details(item)">
										<!-- 警告：微信小程序不支持嵌入lazyload组件，请自行如下使用image标签 -->
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.image" mode="widthFix"></image>
										</view>
										<!-- #endif -->
										<view class="demo-title">
											{{item.title}}
										</view>
										<view class="demo-tag">
											<view style="background-color: white;">&nbsp;</view>
											<view class="demo-tag-text" v-if="item.follow==='Y'">已关注</view>
											<!-- 	<view class="demo-tag-text" v-if="item.follow=false">关注</view> -->

										</view>
										<view class="demo-shop">
											<u-row>
												<u-col span="2">
													<view>
														<u-avatar :src="item.avatar" style="width:25px;height: 25px;"></u-avatar>
													</view>
												</u-col>
												<u-col span="9">
													<view class="demo-flex">{{item.shop}}</view>
												</u-col>
												<u-col span="1">
													<view class="">
														<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="30"></u-rate>
													</view>
												</u-col>

											</u-row>
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
								<template v-slot:right="{rightList}">
									<view class="demo-warter" v-for="(item, index) in rightList" :key="index" @click="details(item)">
										<!-- #ifndef MP-WEIXIN -->
										<u-lazy-load threshold="-450" border-radius="10" :image="item.image" :index="index"></u-lazy-load>
										<!-- #endif -->
										<!-- #ifdef MP-WEIXIN -->
										<view class="demo-img-wrap">
											<image class="demo-image" :src="item.image" mode="widthFix"></image>
										</view>
										<!-- #endif -->

										<view class="demo-title">
											{{item.title}}
										</view>
										<view class="demo-tag">
											<view class="demo-tag-owner">
												转发
											</view>
										</view>
										<view class="demo-shop">
											{{item.shop}}
										</view>
										<!-- 微信小程序无效，因为它不支持在template中引入组件 -->
										<!-- <u-icon name="close-circle-fill" color="#fa3534" size="34" class="u-close" @click="remove(item.id)"></u-icon> -->
									</view>
								</template>
							</u-waterfall>
						</scroll-view>
					</swiper-item>
				</swiper>
			</view>

		</view>
	</view>
</template>

<script>
import { pathToBase64, base64ToPath } from '../../../js_sdk/image-tools/index.js'



	export default {
		data() {
			return {
				//form
				model: {
					phoneNumber: '',
					username: '',
					password: '',
					avatarPicture: '',
					avatarPictureName: '',
					sex: 'female',
					sign: 'sign',
					lastAddress: '湖南省湘潭市',


					name: '',
					sex: '',
					likeFruit: '',
					intro: '',
					payType: '支付宝',
					agreement: false,
					region: '',
					goodsType: '',
					phone: '',
					code: '',
					password: '',
					rePassword: '',
					remember: false,
					photo: ''
				},
				selectList: [{
						value: '电子产品',
						label: '电子产品'
					},
					{
						value: '服装',
						label: '服装'
					},
					{
						value: '工艺品',
						label: '工艺品'
					}
				],
				rules: {
					name: [{
							required: true,
							message: '请输入姓名',
							trigger: 'blur',
						},
						{
							min: 3,
							max: 5,
							message: '姓名长度在3到5个字符',
							trigger: ['change', 'blur'],
						},
						{
							// 此为同步验证，可以直接返回true或者false，如果是异步验证，稍微不同，见下方说明
							validator: (rule, value, callback) => {
								// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
								return this.$u.test.chinese(value);
							},
							message: '姓名必须为中文',
							// 触发器可以同时用blur和change，二者之间用英文逗号隔开
							trigger: ['change', 'blur'],
						},
						// 异步验证，用途：比如用户注册时输入完账号，后端检查账号是否已存在
						// {
						// 	trigger: ['blur'],
						// 	// 异步验证需要通过调用callback()，并且在里面抛出new Error()
						// 	// 抛出的内容为需要提示的信息，和其他方式的message属性的提示一样
						// 	asyncValidator: (rule, value, callback) => {
						// 		this.$u.post('/ebapi/public_api/index').then(res => {
						// 			// 如果验证出错，需要在callback()抛出new Error('错误提示信息')
						// 			if(res.error) {
						// 				callback(new Error('姓名重复'));
						// 			} else {
						// 				// 如果没有错误，也要执行callback()回调
						// 				callback();
						// 			}
						// 		})
						// 	},
						// }
					],
					sex: [{
						required: true,
						message: '请选择性别',
						trigger: 'change'
					}, ],
					intro: [{
							required: true,
							message: '请填写简介'
						},
						{
							min: 5,
							message: '简介不能少于5个字',
							trigger: 'change',
						},
						// 正则校验示例，此处用正则校验是否中文，此处仅为示例，因为uView有this.$u.test.chinese可以判断是否中文
						{
							pattern: /^[\u4e00-\u9fa5]+$/gi,
							message: '简介只能为中文',
							trigger: 'change',
						},
					],
					likeFruit: [{
						required: true,
						message: '请选择您喜欢的水果',
						trigger: 'change',
						type: 'array',
					}],
					payType: [{
						required: true,
						message: '请选择任意一种支付方式',
						trigger: 'change',
					}],
					region: [{
						required: true,
						message: '请选择地区',
						trigger: 'change',
					}],
					goodsType: [{
						required: true,
						message: '请选择商品类型',
						trigger: 'change',
					}],
					phone: [{
							required: true,
							message: '请输入手机号',
							trigger: ['change', 'blur'],
						},
						{
							validator: (rule, value, callback) => {
								// 调用uView自带的js验证规则，详见：https://www.uviewui.com/js/test.html
								return this.$u.test.mobile(value);
							},
							message: '手机号码不正确',
							// 触发器可以同时用blur和change，二者之间用英文逗号隔开
							trigger: ['change', 'blur'],
						}
					],
					code: [{
							required: true,
							message: '请输入验证码',
							trigger: ['change', 'blur'],
						},
						{
							type: 'number',
							message: '验证码只能为数字',
							trigger: ['change', 'blur'],
						}
					],
					password: [{
							required: true,
							message: '请输入密码',
							trigger: ['change', 'blur'],
						},
						{
							// 正则不能含有两边的引号
							pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]+\S{5,12}$/,
							message: '需同时含有字母和数字，长度在6-12之间',
							trigger: ['change', 'blur'],
						}
					],
					rePassword: [{
							required: true,
							message: '请重新输入密码',
							trigger: ['change', 'blur'],
						},
						{
							validator: (rule, value, callback) => {
								return value === this.model.password;
							},
							message: '两次输入的密码不相等',
							trigger: ['change', 'blur'],
						}
					],
				},
				border: false,
				check: false,
				selectStatus: 'close',
				checkboxList: [{
						name: '荔枝',
						checked: false,
						disabled: false
					},
					{
						name: '香蕉',
						checked: false,
						disabled: false
					},
					{
						name: '橙子',
						checked: false,
						disabled: false
					},
					{
						name: '草莓',
						checked: false,
						disabled: false
					}
				],
				radioList: [{
						name: '支付宝',
						checked: true,
						disabled: false
					},
					{
						name: '微信',
						checked: false,
						disabled: false
					},
					{
						name: '银联',
						checked: false,
						disabled: false
					},
					{
						name: '现金',
						checked: false,
						disabled: false
					}
				],
				radio: '支付宝',
				actionSheetList: [{
						text: '男'
					},
					{
						text: '女'
					},
					{
						text: '保密'
					}
				],
				actionSheetShow: false,
				pickerShow: false,
				selectShow: false,
				radioCheckWidth: 'auto',
				radioCheckWrap: false,
				labelPosition: 'left',
				codeTips: '',
				errorType: ['message'],

				//
				allStars: 12,
				allFollowings: 11,
				allFans: 15,
				user: {},
				show: true,
				// 搜索
				value: '',
				headList: [{
						name: '浏览过'
					},
					{
						name: '评论过'
					},
					{
						name: '赞过'
					}
				],
				current: 0,
				swiperCurrent: 0,
				tabsHeight: 0,
				dx: 0,
				//瀑布流
				loadStatus: 'loadmore',
				flowList: [],
				list: [{
						title: '好可爱的猫咪',
						shop: 'guanzhu4',
						image: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/04.JPG',
						follow: 'Y',
					},
					{
						title: '小小',
						shop: 'guanzhu3',
						image: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/09.JPG',
						follow: 'Y',
					},
					{
						title: '可可爱爱',
						shop: 'guanzhu2',
						image: 'http://qdeujgrtl.bkt.clouddn.com/07.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						follow: 'Y',
					},
					{
						title: '是长毛',
						shop: 'guanzhu',
						image: 'http://qdeujgrtl.bkt.clouddn.com/11.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/11.JPG',
						follow: 'Y',
					},
					{
						title: '什么时候长大',
						shop: 'nooooo',
						image: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						follow: 'N',
					},
					{
						title: '好漂亮',
						shop: 'no2',
						image: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						follow: 'N',
					},
					{
						shop: 'nono22222222',
						image: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						title: '看起来傻傻的',
						follow: 'N',
					},
					{
						title: '哈哈哈哈哈',
						image: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',
						shop: 'nono4333333',
						follow: 'N',
					},
					{
						title: '看起来傻傻的，',
						shop: 'nono555555',
						image: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						follow: 'N',
					},
					{
						title: '帽子可爱',
						shop: 'nono566666',

						image: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/09.JPG',
						follow: 'N',
					},
					{
						image: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						title: '写真还不错',
						shop: 'nono77777',
						follow: 'N',
					},
				]
			};
		},
		onLoad() {
			this.getUser();
			this.addRandomData();
		},
		computed: {
			borderCurrent() {
				return this.border ? 0 : 1;
			},
			userPhone() {
				// return sessionStorage.getItem('phoneNumber');
				return 15273299601;
			},
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		onReachBottom() {
			this.loadStatus = 'loading';
			// 模拟数据加载
			setTimeout(() => {
				this.addRandomData();
				this.loadStatus = 'loadmore';
			}, 1000)
		},
		methods: {
		

			submit() {
				this.$refs.uForm.validate(valid => {
					if (valid) {
						if (!this.model.agreement) return this.$u.toast('请勾选协议');
						console.log('验证通过');
					} else {
						console.log('验证失败');
					}
				});
			},
			// 点击actionSheet回调
			actionSheetCallback(index) {
				uni.hideKeyboard();
				this.model.sex = this.actionSheetList[index].text;
			},
			// checkbox选择发生变化
			checkboxGroupChange(e) {
				this.model.likeFruit = e;
			},
			// radio选择发生变化
			radioGroupChange(e) {
				this.model.payType = e;
			},
			// 勾选版权协议
			checkboxChange(e) {
				this.model.agreement = e.value;
			},
			// 选择地区回调
			regionConfirm(e) {
				this.model.region = e.province.label + '-' + e.city.label + '-' + e.area.label;
			},
			// 选择商品类型回调
			selectConfirm(e) {
				this.model.goodsType = '';
				e.map((val, index) => {
					this.model.goodsType += this.model.goodsType == '' ? val.label : '-' + val.label;
				})
			},
			borderChange(index) {
				this.border = !index;
			},
			radioCheckboxChange(index) {
				if (index == 0) {
					this.radioCheckWrap = false;
					this.radioCheckWidth = 'auto';
				} else if (index == 1) {
					this.radioCheckWrap = true;
					this.radioCheckWidth = 'auto';
				} else if (index == 2) {
					this.radioCheckWrap = false;
					this.radioCheckWidth = '50%';
				}
			},
			labelPositionChange(index) {
				this.labelPosition = index == 0 ? 'left' : 'right';
			},
			codeChange(text) {
				this.codeTips = text;
			},
			// 获取验证码
			getCode() {
				if (this.$refs.uCode.canGetCode) {
					// 模拟向后端请求验证码
					uni.showLoading({
						title: '正在获取验证码',
						mask: true
					})
					setTimeout(() => {
						uni.hideLoading();
						// 这里此提示会被this.start()方法中的提示覆盖
						this.$u.toast('验证码已发送');
						// 通知验证码组件内部开始倒计时
						this.$refs.uCode.start();
					}, 2000);
				} else {
					this.$u.toast('倒计时结束后再发送');
				}
			},
			errorChange(index) {
				if (index == 0) this.errorType = ['message'];
				if (index == 1) this.errorType = ['toast'];
				if (index == 2) this.errorType = ['border-bottom'];
				if (index == 3) this.errorType = ['border'];
			},





			//user
			getUser() {
				uni.request({
					url: this.apiServer + '/users/user?phoneNumber=' + this.userPhone,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.user = res.data.data.user;
					}
				});
			},
			// tab栏切换
			change(index) {
				this.swiperCurrent = index;
			},
			transition({
				detail: {
					dx
				}
			}) {
				this.$refs.tabs.setDx(dx);
			},
			animationfinish({
				detail: {
					current
				}
			}) {
				this.$refs.tabs.setFinishCurrent(current);
				this.swiperCurrent = current;
				this.current = current;
			},
			//瀑布流
			addRandomData() {
				for (let i = 0; i < 10; i++) {
					let index = this.$u.random(0, this.list.length - 1);
					// 先转成字符串再转成对象，避免数组对象引用导致数据混乱
					let item = JSON.parse(JSON.stringify(this.list[index]))
					item.id = this.$u.guid();
					this.flowList.push(item);
				}
			},
			//跳转
			details(item) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/detalis?text=" + item.title + "&image=" + item.image + "&shop=" + item.shop +
						"&avatar=" + item.avatar + "&follow=" + item.follow
				})
			},
			allFollowing() {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-5/allfollows"
				})
			},
			allFan() {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-5/allfans"
				})
			},
			allStar() {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-5/allstars"
				})
			},

		}
	}
</script>



<style>
	/* #ifndef H5 */
	page {
		height: 100%;
		background-color: #f2f2f2;
	}

	/* #endif */
</style>

<style lang="scss" scoped>
	.me {
		background-color: #969a9e;
		color: #FFFFFF;
	}

	.pad-left {
		padding-left: 5px;
	}

	.pad-leftt {
		padding-left: 15px;
	}

	.star-head {
		font-size: 17px;
		font-weight: bolder;
	}

	.star-next {
		padding-right: 5px;
		font-size: 13px;
		font-weight: 500;
	}

	.pad {
		padding-top: 5px;
	}

	.padd {
		padding-top: 10px;
		font-weight: bold;
	}

	.pad-right {
		padding-left: 5px;

	}

	.set {
		color: #303133;
	}

	.userForm {
		width: 250px;
		padding-left: 10px;
		padding-right: 10px;
	}

	//i11111
	.con {
		text-align: center;
	}

	.wrap {
		display: flex;
		flex-direction: column;
		height: calc(100vh - var(--window-top));
		width: 100%;
	}

	.swiper-box {
		flex: 1;
	}

	.swiper-item {
		height: 100%;
	}

	//瀑布流
	.demo-warter {
		border-radius: 8px;
		margin: 5px;
		background-color: #ffffff;
		padding: 8px;
		position: relative;
	}

	.u-close {
		position: absolute;
		top: 32rpx;
		right: 32rpx;
	}

	.demo-image {
		width: 100%;
		border-radius: 4px;
	}

	.demo-title {
		font-size: 30rpx;
		margin-top: 5px;
		color: $u-main-color;
	}

	.demo-tag {
		display: flex;
		margin-top: 5px;
	}

	.demo-tag-owner {
		background-color: $u-type-error;
		color: #FFFFFF;
		display: flex;
		align-items: center;
		padding: 4rpx 14rpx;
		border-radius: 50rpx;
		font-size: 20rpx;
		line-height: 1;

	}

	.demo-tag-text {
		border: 1px solid $u-type-primary;
		color: $u-type-primary;

		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 14rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 20rpx;
	}

	.demo-price {
		font-size: 30rpx;
		color: $u-type-error;
		margin-top: 5px;
	}

	.demo-shop {
		font-size: 15px;
		color: $u-tips-color;
		margin-top: 5px;

	}

	.demo-flex {
		display: flex;
		align-items: left;
		justify-content: center;
		flex-direction: column;
		padding-bottom: 2px;
		padding-left: 2px;

	}

	.order {
		width: 710rpx;
		background-color: #ffffff;
		margin: 20rpx auto;
		border-radius: 20rpx;
		box-sizing: border-box;
		padding: 20rpx;
		font-size: 28rpx;

		.top {
			display: flex;
			justify-content: space-between;

			.left {
				display: flex;
				align-items: center;

				.store {
					margin: 0 10rpx;
					font-size: 32rpx;
					font-weight: bold;
				}
			}

			.right {
				color: $u-type-warning-dark;
			}
		}

		.item {
			display: flex;
			margin: 20rpx 0 0;

			.left {
				margin-right: 20rpx;

				image {
					width: 200rpx;
					height: 200rpx;
					border-radius: 10rpx;
				}
			}

			.content {
				.title {
					font-size: 28rpx;
					line-height: 50rpx;
				}

				.type {
					margin: 10rpx 0;
					font-size: 24rpx;
					color: $u-tips-color;
				}

				.delivery-time {
					color: #e5d001;
					font-size: 24rpx;
				}
			}

			.right {
				margin-left: 10rpx;
				padding-top: 20rpx;
				text-align: right;

				.decimal {
					font-size: 24rpx;
					margin-top: 4rpx;
				}

				.number {
					color: $u-tips-color;
					font-size: 24rpx;
				}
			}
		}

		.total {
			margin-top: 20rpx;
			text-align: right;
			font-size: 24rpx;

			.total-price {
				font-size: 32rpx;
			}
		}

		.bottom {
			display: flex;
			margin-top: 40rpx;
			padding: 0 10rpx;
			justify-content: space-between;
			align-items: center;

			.btn {
				line-height: 52rpx;
				width: 160rpx;
				border-radius: 26rpx;
				border: 2rpx solid $u-border-color;
				font-size: 26rpx;
				text-align: center;
				color: $u-type-info-dark;
			}

			.evaluate {
				color: $u-type-warning-dark;
				border-color: $u-type-warning-dark;
			}
		}
	}

	.centre {
		text-align: center;
		margin: 200rpx auto;
		font-size: 32rpx;

		image {
			width: 164rpx;
			height: 164rpx;
			border-radius: 50%;
			margin-bottom: 20rpx;
		}

		.tips {
			font-size: 24rpx;
			color: #999999;
			margin-top: 20rpx;
		}

		.btn {
			margin: 80rpx auto;
			width: 200rpx;
			border-radius: 32rpx;
			line-height: 64rpx;
			color: #ffffff;
			font-size: 26rpx;
			background: linear-gradient(270deg, rgba(249, 116, 90, 1) 0%, rgba(255, 158, 1, 1) 100%);
		}
	}
</style>
