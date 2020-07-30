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
		<!-- 动态 --> 
		<view style="background-color: #FFFFFF;">

			<view class="" v-for="(momentList, index1) in momentList" :key="index1">
				<u-swiper :list="momentList.pictureList" name="url" :autoplay="false" img-mode="aspectFit" height="390" @click="details(momentList.momentId)"></u-swiper>
				<view style="padding-left: 20px;">
					<text style="font-size: 15px;color: #000000;">
						{{momentList.text}} （id = {{momentList.momentId}}）
					</text>
					<text style="font-size: 15px;color: #808080;">
						<br>
						{{momentList.momentTimeStamp}}
					</text>
					<br>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//
				allStars: 6,
				allFollowings: 4,
				allFans: 2,
				user: {},
				momentList: [],
			}
		},
		onLoad(option) {
			this.userphone = option.phoneNumber;
			this.getUser();
			this.getMom();
		},
		computed: {

		},
		methods: {
			//user
			getUser() {
				uni.request({
					url: this.apiServer + '/users/user?phoneNumber=' + this.userphone,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.user = res.data.data.user;
					}
				});
			},

			getMom() {
				console.log( this.userphone)

				uni.request({
					url: this.apiServer + '/moment/userMomentList?phoneNumber=' + this.userphone,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						console.log(res)
						this.momentList = res.data.data.momentList;
					}
				});
			},
			//跳转
			details(id) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/detalis?momentId=" + id
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
