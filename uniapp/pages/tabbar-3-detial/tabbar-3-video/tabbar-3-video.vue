<!-- 图标按钮 -->
        <view class="flex align-center" >
            <view class="flex align-center justify-center flex-1 animated" 
            hover-class="jello text-main"  
            @click="doSupport('support')" 
            :class="item.support.type === 'support' ? 'support_color' : '' "
            >
                <text class="iconfont icon-dianzan1 mr-2"></text>
                <text>{{item.support.support_count > 0 ? item.support.support_count : '顶'}}</text>
            </view>
            <view class="flex align-center justify-center flex-1 animated" 
            hover-class="jello text-main" 
            @click="doSupport('unsupport')" 
            :class="item.support.type === 'unsupport' ? 'support_color' : '' "
            >
                <text class="iconfont icon-cai mr-2"></text>
                <text>{{item.support.unsupport_count > 0 ? item.support.unsupport_count : '踩'}}</text>
            </view>
            <view class="flex align-center justify-center flex-1 animated" hover-class="jello text-main">
                <text class="iconfont icon-pinglun2 mr-2"></text>
                <text>{{item.support.comment_count}}</text>
            </view>
            <view class="flex align-center justify-center flex-1 animated" hover-class="jello text-main">
                <text class="iconfont icon-fenxiang mr-2"></text>
                <text>{{item.support.share_num}}</text>
            </view>

//js
<template>
    <view class="content">
        <block v-for="(item,index) in list" :key="index">
            <common-list :item="item" :index="index" @follow="follow" @doSupport="doSupport"></common-list>
            <divider></divider>
        </block>
    </view>
</template>

<script>
    import commonList from '@/components/common/common-list.vue'
    export default {
        components:{
            commonList
        },
        data() {
            return {
                list:[
                    {
                        username:'张三',
                        userpic:'../../static/default.jpg',
                        newstime:'2019-07-10 下午 04：10',
                        isFollow:false,
                        title:'张三',
                        titlepic:'../../static/demo/datapic/11.jpg',
                        support:{
                            type:"",
                            support_count:0,
                            unsupport_count:0
                        },
                        comment_count:2,
                        share_num:2
                    },
                    {
                        username:'张三',
                        userpic:'../../static/default.jpg',
                        newstime:'2019-07-10 下午 04：10',
                        isFollow:false,
                        title:'张三',
                        titlepic:'../../static/demo/datapic/11.jpg',
                        support:{
                            type:"",
                            support_count:1,
                            unsupport_count:2
                        },
                        comment_count:2,
                        share_num:3
                    },
                    {
                        username:'张三',
                        userpic:'../../static/default.jpg',
                        newstime:'2019-07-10 下午 04：10',
                        isFollow:false,
                        title:'张三',
                        titlepic:'../../static/demo/datapic/11.jpg',
                        support:{
                            type:"",
                            support_count:1,
                            unsupport_count:2
                        },
                        comment_count:2,
                        share_num:3
                    }
                ]
            }
        },
        onLoad() {

        },
        methods: {
            // 关注
            follow(e){
                this.list[e].isFollow = true
                uni.showToast({
                    title:"关注成功" 
                })
            },
            // 顶，踩操作
            doSupport(e){
                // 拿到当前对象
                let item = this.list[e.index]
                let msg = e.type === "support" ? "顶" : "踩"
                // 之前没有操作过
                if(item.support.type === ""){
                    item.support[e.type+'_count']++
                } else if(item.support.type === "support" && e.type === "unsupport" ){
                    // 顶 -1
                    item.support.support_count--
                    // 顶 +1
                    item.support.unsupport_count++
                }
                // 之前踩过 , 现在要顶
                else if(item.support.type === "unsupport" && e.type === "support" ){
                    // 顶 +1
                    item.support.support_count++
                    // 顶 -1
                    item.support.unsupport_count--
                }
                item.support.type = e.type
                uni.showToast({
                    title: msg + "成功"
                })
            }
        }
    }
</script>

<style>
    
</style>
