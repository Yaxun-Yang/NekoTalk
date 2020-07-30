<template>
	<view>
		<!-- 头 -->
		<u-navbar back-text="">
			<u-row style="background-color: #FFFFFF;width: 100%;">
				<u-col span="1.5">
					<view>
						<u-avatar :src="moment.avatar" style="width:35px;height: 35px;" @click="toUser(moment.phoneNumber)"></u-avatar>
					</view>
				</u-col>

				<u-col span="6">
					<view class="username">
						{{moment.username}}
					</view>
					<view style="font-size: 10px;">{{moment.address}}</view>
				</u-col>
				<u-col span="1.5">
					<view class="demo-tag-ownerr" v-if="moment.originality==='N'">转发</view>
				</u-col>
				<u-col span="2">
					<view class="demo-tag-textt" v-if="moment.follow==='Y'" @click="unfo(moment.phoneNumber)">已关注</view>
					<view class="demo-tag-ownerr" v-else @click="fo(moment.phoneNumber)">关注</view>
				</u-col>
			</u-row>
		</u-navbar>

		<!-- 轮播 -->
		<u-swiper :list="moment.pictureList" :autoplay="false" img-mode="aspectFit" height="530" name="url"></u-swiper>
		<view style="padding-left: 10px;">
			<!-- 文字内容 -->
			<view class="text">
				{{moment.text}}
			</view>
			<!-- tag -->
			<view style="padding-top: 9px;">
				<u-tag v-text="moment.labelDescription" mode="light" shape="circle" />
			</view>
			<!-- date -->
			<view class="date">
				{{moment.momentTimeStamp}}
				<!-- 点赞 -->
				<span style="padding-left: 270px;">
					<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="60"></u-rate>
				</span>
			</view>
		</view>
		<br>


		<!-- 评论 -->
		<u-divider half-width="100%">共{{number}}条评论</u-divider>
		<!-- 你的评论 -->
		<view style="width: 100%;padding-top: 10px;">
			<u-row justify="start">
				<u-col span="1.2">
					<view style="height: 30px;">
						<u-avatar :src="avatar" style="width:30px;height: 30px;"></u-avatar>
					</view>
				</u-col>
				<u-col span="9">
					<view style="height: 30px;">
						<view class="comment-username">
							<u-input v-model="value" :type="text" :border="true" />
						</view>
					</view>
				</u-col>
				<u-col span="1.5">
					<u-button type="success" size="mini" @click="postCom(id)">发送</u-button>
				</u-col>
				<!-- 提示 -->
				<u-top-tips ref="uTips"></u-top-tips>
			</u-row>
		</view>
		<br>

		<view class="comment" v-for="(comment, index) in comments" :key="index">
			<u-row justify="start">
				<u-col span="1.2">
					<view style="height: 30px;">
						<u-avatar :src="comment.commentShow.user.avatar" style="width:30px;height: 30px;"></u-avatar>
					</view>
				</u-col>
				<u-col span="9.5">
					<view style="height: 30px;">
						<view class="comment-username">{{comment.commentShow.user.username}}</view>
					</view>
				</u-col>
			</u-row>
			<!-- @click="reply(comment.commentShow.comment.momentId)" -->
			<view style="width:350px;position:  relative;top:-10px;left:44px">{{comment.commentShow.comment.text}}
				<view class="date">
					{{comment.commentShow.comment.commentTimeStamp}}</view>
			</view>

			<!-- 评论的回复 -->
			<view class="reply" v-for="(reply, index2) in comment.replyCommentList" :key="index2">

				<u-row justify="start">
					<u-col span="1.5">
						<view style="height: 20px;">
							<u-avatar :src="reply.user.avatar" style="width:30px;height: 30px;"></u-avatar>
						</view>
					</u-col>
					<u-col span="9.5">
						<view style="height: 20px;">
							<view class="comment-username">{{reply.user.username}}</view>

						</view>
					</u-col>
				</u-row>
				<view style="position: relative;top:-5px;left:46px">{{reply.comment.text}}
					<view class="date">
						{{reply.comment.commentTimeStamp}}</view>
				</view>
			</view>

		</view>

	</view>

</template>

<script>
	export default {
		data() {
			return {
				moment: {},
				momentId: '',
				number: 0,
				avatar: '', //你的头像
				value: '', //你回复的内容
				comments: [],
				show: false,
			}
		},
		onLoad(option) {
			this.momentId = option.momentId;
			this.getDetais(option.momentId);
			this.getCom(option.momentId);
			this.getComNum(option.momentId);
			this.getavatar();
		},
		computed: {
			userPhone() {
				// return sessionStorage.getItem('phoneNumber');
				return 15243666630;
			},

		},
		methods: {
			toUser(e) {
				console.log('toUser')
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-1/user?phoneNumber=" + e,
				})
			},
			getDetais(id) {
				uni.request({
					url: this.apiServer + '/moment/moment?momentId=' + id + '&phoneNumber=' + this.userPhone,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.moment = res.data.data.moment;
					}
				});
			},
			getCom(id) {
				uni.request({
					url: this.apiServer + '/moment/commentList?momentId=' + id,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.comments = res.data.data.commentList;
						console.log(this.comments)
					}
				});
			},
			getComNum(id) {
				uni.request({
					url: this.apiServer + '/moment/commentNumber?momentId=' + id,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.number = res.data.data.commentNumber;
					}
				});
			},
			reply(id) {
				this.show = true;

			},
			postCom(id) {
				uni.request({
					url: this.apiServer + '/moment/comment?phoneNumber=' + this.userPhone + '&momentId=' + this.momentId,
					method: 'POST',
					data: {
						replyId: 0,
						text: this.value
					},
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						console.log(res);

						uni.redirectTo({
							url: '/pages/tabbar/tabbar-1/detalis?momentId=' + this.momentId
						});
						this.$refs.uTips.show({
							title: '发送成功',
							type: 'success',
							duration: '1300'
						})
					}
				});
			},
			getavatar() {
				uni.request({
					url: this.apiServer + '/users/user?phoneNumber=' + this.userPhone,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.avatar = res.data.data.user.avatar;
					}
				});
			},
			fo(phoneNumber){
				uni.request({
					url: this.apiServer + '/users/following',
					method: 'POST',
					data: {
						userPhoneNumber: 15243666630,
						followingPhoneNumber: phoneNumber
					},
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						console.log(res);
						uni.redirectTo({
							url: '/pages/tabbar/tabbar-1/detalis?momentId=' + this.momentId
						});
 
						this.$refs.uTips.show({
							title: '发送成功',
							type: 'success',
							duration: '1300'
						})
					}
				});
			},
			unfo(phoneNumber){
				
				uni.request({
					url: this.apiServer + '/users/following',
					method: 'DELETE',
					data: {
						userPhoneNumber: 15243666630,
						followingPhoneNumber: phoneNumber
					},
					header: {
						'content-type': 'application/json'
					},
					success: res => {
						console.log(res);
						uni.redirectTo({
							url: '/pages/tabbar/tabbar-1/detalis?momentId=' + this.momentId
						});
				 
						this.$refs.uTips.show({
							title: '发送成功',
							type: 'success',
							duration: '1300'
						})
					}
				});
			},
			

		}

	};
</script>


<style lang="scss" scoped>
	.moment-head {}

	.followTag {
		width: 62px;
		height: 24px;
		border: 1px solid $u-type-info;
		color: $u-type-info;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 18rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 14px;
	}

	.nofollowTag {
		width: 50px;
		height: 24px;
		color: $u-type-error;
		border: 1px solid $u-type-error;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 19rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 14px;
	}

	.zhuan {
		width: 50px;
		height: 24px;
		background-color: $u-type-error;
		color: #FFFFFF;
		border: 1px solid $u-type-error;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 19rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 14px;

	}

	.username {
		font-size: 16px;
	}

	.text {
		padding-top: 9px;
		font-size: 18px;
		font-weight: 500;
	}
 
	.date {
		padding-top: 10px;
		padding-left: 5px;
		font-size: 13px;
		font-weight: 200;
		color: #888b8b;
	}

	// 评论
	.comment {}

	.comment-username {
		font-size: 14px;
	}

	.reply {
		padding-left: 30px;

	}
	.demo-tag-ownerr {
		width: 40px;
		height: 20px;
		background-color: $u-type-error;
		color: #FFFFFF;
		display: flex;
		align-items: center;
		padding: 4rpx 14rpx;
		border-radius: 50rpx;
		font-size: 20rpx;
		line-height: 1;
	}
	
	.demo-tag-textt {
		width: 50px;
		height: 20px;
		border: 1px solid $u-type-primary;
		color: $u-type-primary;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 13rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 20rpx;
	}
	
</style>
