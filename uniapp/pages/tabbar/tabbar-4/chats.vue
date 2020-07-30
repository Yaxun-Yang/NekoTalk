<template>
	<view>

		<view class="info-1">
			<!-- 布局 -->
			<view style="margin-bottom:70px;">
				<!-- 注释 -->
				<!-- <view class="zs">点击对方头像可进入私聊模式</view> -->
				<!-- 自身回答模式 -->
				<!-- 					<view v-for="(item, key) in list" :key="key">
						<view v-if="item.ss == getname" class="right">
							<view>
								<view style="font-size:12px;color:#999;text-align:right;">{{ item.ss }}</view>
								<view class="langcon">{{ item.con }}</view>
							</view>
							<view style="margin-left:10px;">
								<image src="../../../static/youkeer.png" style="width:40px;height:40px;border-radius:50%;border:1px solid #aaa;" />
							</view>
						</view>
						<view v-else class="left">
							<view>
								<image src="../../../static/youkeer.png" style="width:40px;height:40px;border-radius:50%;border:1px solid #aaa;" />
							</view>
							<view style="margin-left:10px;">
								<view style="font-size:12px;color:#999;">{{ item.ss }}</view>
								<view class="langcon">{{ item.con }}</view>
							</view>
						</view>
					</view> -->
				<view class="yes">

					<u-row class="nome">
						<u-col span="1.5">
							<u-avatar :src="oavatar" style="width:40px;height: 40px;"></u-avatar>
						</u-col>
						<u-col span="9.5">
							<view class="nome-text">你好</view>
						</u-col>
					</u-row>
					<u-row class="me" v-if="rs==='Y'">
						<u-col span="6">

						</u-col>
						<u-col span="1.5">
							<view class="nome-text">你好</view>
						</u-col>
						<u-col span="1.5">
							<u-avatar :src="avatar" style="width:40px;height: 40px;" @click=""></u-avatar>
						</u-col>
						<u-col span="1">

						</u-col>
					</u-row>
				</view>
			</view>


		</view>
		<!-- 输入框 -->
		<view class="bottom">
			<view class="bottom-p">
				<view><input type="text" placeholder="请输入你要发送的内容" cursor-spacing="50" v-model="value" /></view>
				<view @tap="clickRequest('Y')">发送</view>
			</view>
			<!-- 底部分类 -->
			<view class="classification">
				<view>
					<image src="../../../static/icon/yuyin.png"></image>
				</view>
				<view>
					<image src="../../../static/icon/tupian.png"></image>
				</view>
				<view @click="camera">
					<image src="../../../static/icon/xiangji.png" style="width:25px;height:25px;"></image>
				</view>
				<view>
					<image src="../../../static/icon/hongbao.png"></image>
				</view>
				<view>
					<image src="../../../static/icon/biaoqing.png" style="width:27px;height:27px;"></image>
				</view>
				<view>
					<image src="../../../static/icon/gengduo.png" style="width:27px;height:27px;"></image>
				</view>
			</view>
		</view>
	</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				opposite: 'name1',
				oavatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
				avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
				con: [{
					me: "hihihi",
					time: "05:02"
				}, ],
				value: '',
				tableData: [],
				list: [],
				start: true,
				rs: '',

			}
		},
		onLoad(option) {
			console.log(option.rs); //打印出上个页面传递的参数。
			this.rs = option.rs;
		},

		computed: {
			phoneNumber() {
				return 15243666630
			}
		},
		methods: {
			clickRequest(e) {
				console.log('ok')
				uni.redirectTo({
					url: "/pages/tabbar/tabbar-4/chats?rs=" + e
				})
			},
			// getList(){
			// 	this.id = option.id;
			// }
		}



	};
</script>

<style>
	.nome {
		padding-left: 20px;
		padding-top: 20px;
	}

	.nome-text {
		width: 70px;
		height: 35px;
		background-color: #0c6ce3;
		border-radius: 30px;
		color: #FFFFFF;
		font-size: 17px;
		padding-left: 13px;
		padding-top: 5px;
	}

	.bottom {
		position: fixed;
		bottom: 0;
		width: 100%;
		background: #f1f1f1;
		padding-top: 10px;
	}

	.bottom-p {
		height: 37px;
		display: flex;
		justify-content: space-between;
		flex-wrap: nowrap;
	}

	.bottom-p view:nth-child(1) {
		width: 77%;
		margin-left: 10px;
		background-color: #ffffff;
		border-radius: 40px;
		padding: 7px 0 0 10px;
	}

	.bottom-p view:nth-child(2) {
		width: 20%;
		margin-left: 5px;
		font-size: 14px;
		text-align: center;
		height: 37px;
		margin-right: 10px;
		border-radius: 40px;
		color: #ffffff;
		background: #25b0f3;
		line-height: 37px;
	}

	.center {
		text-align: center;
		font-size: 12px;
		color: #aaa;
		margin-top: 10px;
		letter-spacing: 1px;
	}

	.left {
		display: flex;
		flex-wrap: nowrap;
		justify-content: flex-start;
		margin-left: 10px;
		margin-top: 10px;
	}

	.right {
		display: flex;
		flex-wrap: nowrap;
		justify-content: flex-end;
		margin-right: 10px;
		margin-top: 10px;
	}

	.langcon {
		border: 1px solid #333333;
		font-size: 14px;
		color: #414141;
		height: 30px;
		line-height: 30px;
		border-radius: 7px;
		margin-top: 5px;
		text-align: right;
		padding: 3px 10px 3px 10px;
		background: #f8f8f8;
	}

	/* 	.zs {
		font-size: 12px;
		letter-spacing: 1px;
		color: #AAAAAA;
		margin-top: 5px;
		text-align: center;
	} */

	/* 分类 */
	.classification {
		display: flex;
		flex-wrap: nowrap;
		justify-content: space-between;
		width: 90%;
		margin: 0 auto;
		padding-top: 10px;
	}

	.classification image {
		width: 21px;
		height: 21px;
	}

	.yes {
		width: 100%;
	}

	.nome {

		width: 100%;
	}

	.me {

		padding-left: 100px;

	}
</style>
