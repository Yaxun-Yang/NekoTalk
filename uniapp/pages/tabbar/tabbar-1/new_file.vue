<template>
	<view class="view">
		<view class="notice">本Demo演示下拉刷新和上拉加载</view>
	<view class="cu-bar search bg-white">
		<view class="search-form round">
			<text class="cuIcon-search"></text>
			<input :adjust-position="false" v-model="search.text" type="text" placeholder="搜索图片、文章、视频" confirm-type="search"></input>
		</view>
		<view class="action">
			<button class="cu-btn bg-green shadow-blur round"  @tap="searchSubmit">搜索</button>
		</view>
	</view>
		<view class="news-li" v-for="news in list" :key="news.id">
			<view>{{ news.title }}</view>
			<view class="new-content">
			<text>{{ news.content }}</text>
			</view>
			<view class="new-content">
			<text>{{ news.search }}</text>
			</view>
		</view>
		<m-loading :loading="loading" :finished="finished"></m-loading>
	</view>
</template>

<script>
/**
 * 本页面提供演示
 */
import mLoading from '../../../components/maybe-list-mixin_1.0.2/components/m-loading.vue';
import listMixin from '../../../components/maybe-list-mixin_1.0.2/common/mixins/list-mixin.js';
export default {
	mixins: [listMixin],
	components: { mLoading },
	data() {
		return {
			list: [], //列表必须为key list的数组
			search:{  //搜索对象必须为key search的对象
				text:'',
				arr:[1,2,3]
			}
		};
	},
	methods: {
		// 搜索
		 searchSubmit(){
		// 调用混合搜索
		 this._searchData();
		},
		// 模拟后端分页
		getData(requestParams) {
			let self = this;
			return new Promise((res, rej) => {
				let params = requestParams;
				if (!params) {
					params = {
						page: 1,
						size: 10,
						search:{
						}
					};
				}
				console.log(params)
				setTimeout(() => {
					let arr = [];
					for (let i = (params.page - 1) * params.size; i < params.page * params.size; i++) {
						arr.push({
							id: i,
							title: '【新增' + i + '】 标题',
							content: '搜索对象为'+JSON.stringify(params.search),
						});
					}
					res({
						code: 200,
						data: {
							page: params.page,
							size: params.size,
							list: arr,
							total: 20 // 设置20就加载完成
						}
					});
				}, 500);
			});
		},
		// 数据请求(没错就是这么少的代码)
		async _getList() {
			// 根据实际情况修改自己修改key 
			let result = await this.getData({
				page:this.page, // 传入页码
				size:this.size, // 传入每页条数
				search:this.search  // 传入搜索的对象
			});
			let self = this;
			this.total = result.data.total;
			result.data.list.map(item => {
				self.list.push(item);
			});
		}
	}
};
</script>

<style>
/*说明*/
.notice {
	font-size: 30upx;
	padding: 40upx 0;
	border-bottom: 1upx solid #eee;
	text-align: center;
}
/*展示上拉加载的数据列表*/
.news-li {
	font-size: 32upx;
	padding: 32upx;
	border-bottom: 1upx solid #eee;
	color: $uni;
}
.news-li .new-content {
	font-size: 28upx;
	margin-top: 10upx;
	margin-left: 20upx;
	color: #666;
}
</style>
