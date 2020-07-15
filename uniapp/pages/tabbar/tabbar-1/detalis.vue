<template>
	<view>
		<!-- 头 -->
		<u-navbar back-text="">
			<u-row style="background-color: #FFFFFF;width: 100%;">
				<u-col span="1.5" v-if="details.phoneNumber==='222'">
					<view>
						<u-avatar :src="avatar1"  style="width:35px;height: 35px;"></u-avatar>
					</view>
				</u-col>
				<u-col span="1.5" v-if="details.phoneNumber==='15243666630'">
					<view>
						<u-avatar :src="avatar2" style="width:35px;height: 35px;" @click="toUser(avatar2)"></u-avatar>
					</view>
				</u-col>
				<u-col span="5" v-if="details.phoneNumber==='222'">
					<view class="username">
						{{name1}}
					</view>
					<view style="font-size: 10px;">{{details.address}}</view>
				</u-col>
				<u-col span="6" v-if="details.phoneNumber==='15243666630'">
					<view class="username" v-if="details.phoneNumber==='15243666630'">
						{{name2}}
					</view>
					<view style="font-size: 10px;">{{details.address}}</view>
				</u-col>

				<u-col span="1.5">
					<!-- v-if="details.originality==='N'" -->
					<view class="zhuan">转发</view>
				</u-col>
				<u-col span="1.5">
					<view v-if="details.phoneNumber==='222'">
						<view class="followTag">已关注</view>
					</view>
					<!-- 	<view class="nofollowTag" >
						<view>关注</view>
					</view> -->
				</u-col>
			</u-row>
		</u-navbar>
		<!-- 提示 -->
		<u-top-tips ref="uTips"></u-top-tips>
		<!-- 轮播 -->
		<u-swiper :list="details.pictureList" :autoplay="false" img-mode="aspectFit" height="530" name="url"></u-swiper>
		<view style="padding-left: 10px;">
			<!-- 文字内容 -->
			<view class="text">
				{{details.text}}
			</view>
			<!-- tag -->
			<view style="padding-top: 9px;">
				<u-tag v-text="details.labelDescription" mode="light" shape="circle" />
			</view>
			<!-- date -->
			<view class="date">
				{{details.momentTimeStamp}}
				<!-- 点赞 -->
				<span style="padding-left: 270px;padding-bottom: 30px;">
					<u-rate count="1" value="0" active-icon="heart-fill" inactive-icon="heart" size="60"></u-rate>
				</span>
			</view>
		</view>
		<br>
		<!-- 评论 -->
		<u-divider half-width="100%">共222条评论</u-divider>
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
						<view class="comment-useranme">
							<u-input v-model="value" :type="text" :border="true" />
						</view>
					</view>
				</u-col>
				<u-col span="1.5">
					<u-button type="success" size="mini" @click="postCom(id)">发送</u-button>
				</u-col>

			</u-row>
		</view>
		<br>
		<view class="comment" v-for="(comment, index) in comments" :key="index">
			<u-row justify="start">
				<u-col span="1.2">
					<view style="height: 30px;">
						<u-avatar :src="comment.avatar" style="width:30px;height: 30px;"></u-avatar>
					</view>
				</u-col>
				<u-col span="9.5">
					<view style="height: 30px;">
						<view class="comment-useranme">{{comment.useranme}}</view>
					</view>
				</u-col>
			</u-row>
			<view style="width:350px;position:  relative;top:-10px;left:44px">{{comment.text}}
				<span class="date">
					{{comment.momentTimeStamp}}</span>
			</view>

			<!-- 评论的回复 -->
			<view class="reply" v-for="(reply, index2) in comment.reply" :key="index2">
				<u-row justify="start">
					<u-col span="1.5">
						<view style="height: 20px;">
							<u-avatar :src="reply.avatar" style="width:30px;height: 30px;"></u-avatar>
						</view>
					</u-col>
					<u-col span="9.5">
						<view style="height: 20px;">
							<view class="comment-useranme">{{reply.useranme}}</view>

						</view>
					</u-col>
				</u-row>
				<view style="position: relative;top:-5px;left:46px">{{reply.text}}
					<span class="date">
						{{reply.date}}</span>
				</view>
			</view>

		</view>

	</view>

</template>

<script>

	export default {
		data() {
			return {
				name1: 'chen',
				avatar1: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
				name2: 'han',
				avatar2: 'http://qdeujgrtl.bkt.clouddn.com/05.JPG',

				// momentId: '',
				avatar: 'http://qdeujgrtl.bkt.clouddn.com/11.JPG',
				details: {},
				// "commentList": [
				//     {
				//         "replyCommentList": [],
				//         "comment": {
				//             "commentId": "2",
				//             "phoneNumber": "15243666630",
				//             "momentId": "5",
				//             "replyCommentId": "0",
				//             "text": "this is a comment2",
				//             "commentTimeStamp": "2020-07-14T19:30:19.692+00:00"
				//         }
				//     }
				// ]
				comments: [{
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						useranme: 'hhhh',
						date: '09-05',
						text: 'CSS设置DIV的定位绝对定位和相对定位,设置DIV或者其他元素在浏览器中显示的位置是HTML布局中重要的一个内用,我们通过合理的布局使得我们的网站美观',
						reply: [{
								avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
								useranme: 'hhhh',
								date: '09-05',
								text: 'rep1',
							},
							{
								avatar: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',
								useranme: 'hhhh',
								date: '09-05',
								text: 'rep2',
							},
						]
					},
					{
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/09.JPG',
						useranme: 'hhhh',
						date: '09-05',
						text: 'CSS设置DIV的定位绝对定位和相对定位,设置DIV或者其他元素在浏览器中显示的位置是HTML布局中重要的一个内用,我们通过合理的布局使得我们的网站美观',
						reply: [{
								avatar: 'http://qdeujgrtl.bkt.clouddn.com/11.JPG',
								useranme: 'hhhh',
								date: '09-05',
								text: 'rep1',
							},
							{
								avatar: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
								useranme: 'hhhh',
								date: '09-05',
								text: 'rep2',
							},
						]
					},
				]
			}
		},
		onLoad(option) {
			this.getDetais(option.momentId);
			this.details.text = option.text;
			this.details.username = option.username;
			this.details.avatar = option.avatar;
			this.details.follow = option.follow;
			this.details.originality = option.originality;
			this.details.date = option.momentTimeStamp;
			// this.details.tag: '巨可爱的小猫咪';
			// this.details.imageList: [{
		},
		computed: {
			userPhone() {
				// return sessionStorage.getItem('phoneNumber');
				return 15243666630;
			},
		},
		methods: {
			toUser(e){
				console.log('tp')
				
				uni.navigateTo({
					
					url: "/pages/tabbar/tabbar-1/user?phoneNumber=" + this.userPhone
				})
			},
			getDetais(id) {
				uni.request({
					url: this.apiServer + '/moment/moment?momentId=' + id,
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.details = res.data.data.moment;
					}
				});
			},
			getCom() {

			},
			postCom(id) {
				uni.request({

					url: this.apiServer + '/moment/comment?phoneNumber=' + this.userPhone + '&momentId=' + id,
					method: 'POST',
					data: {
						"replyId": 0,
						"text": "this is a comment3"
					},
					header: {
						'content-type': 'application/x-www-form-urlencoded'
					},
					success: res => {
						this.$refs.uTips.show({
							title: '发送成功',
							type: 'success',
							duration: '1300'
						})
					}
				});

			}

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

	.comment-useranme {
		font-size: 14px;
		color: #7a7c81;
	}

	.reply {
		padding-left: 30px;

	}
</style>
