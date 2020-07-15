<template>
	<view>
		<view class="stars" v-for="(stars, index) in list" :key="index">
			<u-row justify="around">
				<u-col span="1.1">
					<view class="">
						<u-avatar :src="stars.avatar" style="width:40px;height: 40px;"></u-avatar>
					</view>
				</u-col>
				<u-col span="8.4">
					<view class="pad-left">
						<span class="star-head">{{stars.name}}</span>
						<br>
						<span class="star-next">赞了你的评论&nbsp;{{stars.time}}</span>
							</view>
						<u-row class="pad">
							<u-col span="0.1">
								<u-gap height="40" bg-color="#c7cbcb" style="width: 3px;"></u-gap>
							</u-col>
							<u-col span="11" class="pad-right">
									{{stars.content}}
							</u-col>
						</u-row>
				
				</u-col>
				<u-col span="2">
					<view class="">
						<u-avatar :src="stars.img" mode="square" style="width:45px;height: 45px;"></u-avatar>
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
				list:[
					{
						name: 'user1',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/08.JPG',
						img: 'http://qdeujgrtl.bkt.clouddn.com/06.JPG',
						content: '这只猫真好看',
						time: '10-05',
					},
					{
						name: 'user2',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/03.JPG',
						img: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						content: '哇哦，太好看了吧，怎么才能...',
						time: '08-05',
					},
					
					{
						name: 'user3',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/01.JPG',
						img: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						content: '绝了啊',
						time: '08-05',
					},
					
					{
						name: 'user4',
						avatar: 'http://qdeujgrtl.bkt.clouddn.com/10.JPG',
						img: 'http://qdeujgrtl.bkt.clouddn.com/02.JPG',
						content: '真的好',
						time: '08-05',
					},
				],
				
				
			}
		},
		onLoad() {

		},
		methods: {},
	}
</script>

<style lang="scss" scoped>
	.stars {
		padding-top: 10px;
	}
	.pad-left{
			padding-left: 5px;
	}
.star-head{
	font-size: 17px;
	font-weight: bold;
}
.star-next{
		padding-right: 5px;
	font-size: 11px;
	font-weight: 300;
		color: #6a6c6c;
}
.pad{
	padding-top: 5px;
	color: #6a6c6c;
}
	
.pad-right{
		padding-right: 1px;
}
</style>
