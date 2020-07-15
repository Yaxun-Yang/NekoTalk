<template>
	<view>
		<!-- 上面仨 -->
		<view class="three">
			<u-row justify="around">
				<u-col span="4">
					<view class="demo-layout bg-purple">
						<u-badge type="error" count="4" :offset="[-10,550]"></u-badge>
						<u-icon name="heart-fill" color="#ffffff" size="50" style="padding-left: 8px;padding-top: 7px;" @click="starTo"></u-icon>
						<br><br>新点赞
					</view>
				</u-col>
				<u-col span="4">
					<view class="demo-layout bg-purple-light">
						<u-badge type="error" count="2" :offset="[-10,330]"></u-badge>
						<u-icon name="account-fill" color="#ffffff" size="50" style="padding-left: 8px;padding-top: 7px;" @click="fansTo"></u-icon>
						<br><br>新粉丝
					</view>
				</u-col>
				<u-col span="4">
					<view class="demo-layout bg-purple-dark">
						<u-badge type="error" count="2" :offset="[-10,120]"></u-badge>
						<u-icon name="chat" color="#ffffff" size="50" style="padding-left: 8px;padding-top: 7px;" @click="comTo"></u-icon>
						<br><br>新评论
					</view>
				</u-col>
			</u-row>
		</view>
		<br>
		<!-- 聊天 -->
		<view class="back">
			<!-- 消息list -->
			<view style="padding-top: 1px;">
				<view class="meg" v-for="(chat, index) in chat" :key="index">
					<u-row class="one" @click="toChat(chat)">
						<u-col span="1.5">
							<u-avatar :src="chat.avatar" style="width:40px;height: 40px;"></u-avatar>
						</u-col>
						<u-col span="8.5">
							<view class="chat-name">
								{{chat.name}}
							</view>
							<view class="chat-con">
								{{chat.content}}
							</view>
						</u-col>
						<u-col span="1" class="chat-con">
							{{chat.time}}
						</u-col>
					</u-row>
					<u-line color="#d9e5ef" margin="-40rpx 1rpx" :hair-line="false" />
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				chat: [{
						id: 'chat1',
						name: 'name1',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						content: '你好',
						time: '05:54',
					},
					{
						id: 'chat2',
						name: 'name2',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',
						content: '在吗？',
						time: '04:54',
					},
					{
						id: 'chat3',
						name: 'name3',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/04.JPG',
						content: '你家猫很可爱',
						time: '01:54',
					},
					{
						id: 'chat4',
						name: 'name4',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						content: '早上好',
						time: '02:54',
					}
				]
			}
		},
		onLoad() {

		},
		methods: {
			toChat(chat) {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-4/chats?id=" + chat.id
				})
			},
			starTo() {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-4/stars"
				})
			},
			fansTo() {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-4/fans"
				})
			},
			comTo() {
				uni.navigateTo({
					url: "/pages/tabbar/tabbar-4/comments"
				})
			},

		}
	}
</script>

<style lang="scss" scoped>
	.three {
		padding-left: 50px;

	}

	.u-row {
		margin: 40rpx 0;
	}

	.demo-layout {
		height: 80rpx;
		width: 80rpx;
		border-radius: 8rpx;
		font-size: 13px;
		font-weight: bold;
	}

	.bg-purple {
		background: #fd5c79;
	}

	.bg-purple-light {
		background: #4b7bff;
	}

	.bg-purple-dark {
		background: #2dcc33;
	}

	.back {
		background-color: #F8F8F8;
		height: 470px;
	}

	.meg {
		background-color: #FFFFFF;
	}

	.one {
		padding-left: 20px;
		background-color: #FFFFFF;
		padding-top: 12px;
		padding-bottom: 8px;
	}

	.chat-name {
		font-size: 15px;
		font-weight: 900;
	}

	.chat-con {
		padding-top: 3px;
		font-size: 13px;
		font-weight: 500;
		color: #808080;
	}

	.chat-time {
		font-size: 14px;
		font-weight: 500;
		color: #808080;
	}
</style>
