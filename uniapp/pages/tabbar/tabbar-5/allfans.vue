<template>
	<view>
		<view class="fans" v-for="(fans, index) in list" :key="index">
			<u-row justify="around">
				<u-col span="1">
					<view class="">
						<u-avatar :src="fans.avatar" style="width:40px;height: 40px;"></u-avatar>
					</view>
				</u-col>
				<u-col span="8">
					<view class="pad-left">
						<span class="star-head">{{fans.name}}</span>
						<br>
						<!-- <span class="star-next">开始关注你了&nbsp;{{fans.time}}</span> -->
					</view>
				</u-col>
				<u-col span="2">
					<view class="">
						<view v-if="fans.follow">
							<view class="followTag">已关注</view>
						</view>
						<view class="nofollowTag" v-else>
							<view>回关</view>
						</view>
					</view>
				</u-col>
			</u-row>
			<u-gap height="2" bg-color="#dadada" margin-top="15"></u-gap>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list: [{
						name: 'useranme020020',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						time: '09-05',
						follow: false,
					},
					{
						name: 'useranme11111111111111',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						time: '09-05',
						follow: true,
					},
				]

			}
		},
		onLoad() {

		},
		methods: {},
	}
</script>

<style lang="scss" scoped>
	.fans {
		padding-top: 10px;
	}

	.pad-left {
		padding-left: 5px;
	}

	.star-head {
		font-size: 17px;
		font-weight: bold;
	}

	.star-next {
		padding-right: 5px;
		font-size: 11px;
		font-weight: 300;
		color: #6a6c6c;
	}

	.pad {
		padding-top: 5px;
		color: #6a6c6c;
	}

	.pad-right {
		padding-right: 1px;
	}

	// fo
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
		width: 62px;
		height: 24px;
		color: $u-type-error;
		border: 1px solid $u-type-error;
		border-radius: 50rpx;
		line-height: 1;
		padding: 4rpx 30rpx;
		display: flex;
		align-items: center;
		border-radius: 50rpx;
		font-size: 14px;
	}
</style>
