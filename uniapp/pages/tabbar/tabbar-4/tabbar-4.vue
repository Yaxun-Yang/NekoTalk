<template>
	<view style="background-color: #FFFFFF;">
		<!-- 上面仨 -->
		<view class="three">
			<u-row justify="around">
				<u-col span="4" style="background-color: #FFFFFF;">
					<view class="demo-layout bg-purple">
						<u-badge type="error" count="4" :offset="[24,550]"></u-badge>
						<u-icon name="heart-fill" color="#ffffff" size="50" style="padding-left: 8px;padding-top: 7px;" @click="starTo"></u-icon>
						<br><br>新点赞
					</view>
				</u-col>
				<u-col span="4">
					<view class="demo-layout bg-purple-light">
						<u-badge type="error" count="2" :offset="[24,330]"></u-badge>
						<u-icon name="account-fill" color="#ffffff" size="50" style="padding-left: 8px;padding-top: 7px;" @click="fansTo"></u-icon>
						<br><br>新粉丝
					</view>
				</u-col>
				<u-col span="4">
					<view class="demo-layout bg-purple-dark">
						<u-badge type="error" count="2" :offset="[24,120]"></u-badge>
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
		

			<!-- qq -->
			<u-swipe-action :show="item.show" :index="index" v-for="(item, index) in list" :key="item.id" @click="click" @open="open"
			 :options="options">
				<view class="item u-border-bottom">
					<image mode="aspectFill" :src="item.images" />
					<!-- 此层wrap在此为必写的，否则可能会出现标题定位错误 -->
					<view class="title-wrap">
						<u-row style="width: 350px;" @click="toChat(chat)">
							<u-col span="9">
								<view class="chat-name">
									{{item.name}}
								</view>
								<view class="chat-con">
									{{item.content}}
								</view>
							</u-col>
							<u-col span="1" class="chat-con">
								{{item.time}}
							</u-col>
						</u-row>
					
						
					</view>
				</view>
			</u-swipe-action>
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
				],
				///
				list: [{
						id: 1,
							name: 'name1',
								content: '你好',
						images: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						show: false,
								time: '10:54',
					},
					{
						id: 2,
							name: 'name2',
									content: '在吗？',
						title: '新丰绿树起黄埃，数骑渔阳探使回，霓裳一曲千峰上，舞破中原始下来',
						images: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',
						show: false,
							time: '01:54',
					},
					{
						id: 3,
								name: 'name3',
								content: '你家猫很可爱',
						title: '登临送目，正故国晚秋，天气初肃。千里澄江似练，翠峰如簇',
						images: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						show: false,
							time: '04:54',
					}
				],
				disabled: false,
				btnWidth: 180,
				show: false,
				options: [{
						text: '收藏',
						style: {
							backgroundColor: '#007aff'
						}
					},
					{
						text: '删除',
						style: {
							backgroundColor: '#dd524d'
						}
					}
				]
			}
		},
		onLoad() {

		},
		methods: {
			click(index, index1) {
				if (index1 == 1) {
					this.list.splice(index, 1);
					this.$u.toast(`删除了第${index}个cell`);
				} else {
					this.list[index].show = false;
					this.$u.toast(`收藏成功`);
				}
			},
			// 如果打开一个的时候，不需要关闭其他，则无需实现本方法
			open(index) {
				// 先将正在被操作的swipeAction标记为打开状态，否则由于props的特性限制，
				// 原本为'false'，再次设置为'false'会无效
				this.list[index].show = true;
				this.list.map((val, idx) => {
					if (index != idx) this.list[idx].show = false;
				})
			},
			////
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
		.item {
			display: flex;
			padding: 20rpx;
		}
		
		image {
			width: 120rpx;
			flex: 0 0 120rpx;
			height: 120rpx;
			margin-right: 20rpx;
			border-radius: 12rpx;
		}
		
		.title {
			text-align: left;
			font-size: 28rpx;
			color: $u-content-color;
			margin-top: 20rpx;
		}
	.three {
		padding-left: 50px;
		background-color: #FFFFFF;

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
		color: #000000;
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
