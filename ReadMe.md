# NekoTalk
铲屎官的社交平台
前端 uni-app 后端spring-boot +阿里云ESC实例

## 联合开发者请注意
请自行建立以自己名字首字母缩写命名的分支， 例如我的分支叫“yyx”
每一次都将自己的改动push到自己的分支上，merge的工作将由仓库持有者来完成
如果你还没有将你的本地仓库和此仓库连接起来，请根据以下教程来进行操作：

1.  clone远程代码到本地
```
git clone git@github.com:Yaxun-Yang/NekoTalk.git
```
2. 将本地仓库与远程仓库关联()
```
cd NekoTalk
git remote add origin git@github.com:Yaxun-Yang/MultiScanner.git
```
ps: 如果出现`fatal : romote origin already exists.`的提示，说明在上一步clone的时候已经自动添加了远程仓库，可直接进入下一步

3. 从已有的master的基础建立本地的分支（以自己名字缩写命名）
```
 git pull
 git checkout develop
 git checkout -b yyx develop #创建远程分支并进入
```
4. 接下来，你就可以在自己的分支里编写代码了

5. 当你已经写完了代码并完成了add和commit之后，你可以选择自己将自己的分支merge到develop分支上（步骤6）或者直接提交自己的分支，并告知仓库所有者，由仓库所有者来进行merge(步骤7)再或者通过pull request的方式来提交代码(请自行学习方法)
6. 你选择自己将修改merge到develop分支上并提交
```
git checkout develop
git pull origin develop #检擦本地的develop是否为最新版并更新
git checkout yyx 


```


